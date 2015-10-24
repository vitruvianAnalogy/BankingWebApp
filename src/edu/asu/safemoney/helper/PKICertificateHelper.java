
package edu.asu.safemoney.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v1CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v1CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import edu.asu.safemoney.service.impln.LoginServiceImpl;
public class PKICertificateHelper {
	

	public static final Logger logger = Logger.getLogger(PKICertificateHelper.class);

	public static java.security.cert.X509Certificate generateCert(KeyStore.PrivateKeyEntry priKey, KeyPair kP, String userName) {

		java.security.cert.X509Certificate certificate = null;
		try {
			System.out.println("before construct");		
			Date certDate = new Date(System.currentTimeMillis()-24*60*60*1000); 
			Date certEndDate = new Date(System.currentTimeMillis()+365*24*60*60*1000);
			BigInteger certSerialNum = BigInteger.ONE;		
			/*X500Name issuer = new X500Name("CN=Safe Money Corporation");
			X500Name subject = new X500Name(userName);*/
			
			X500Principal issuer = new X500Principal("CN=Safe Money Corporation");
			X509v1CertificateBuilder certGen = 
					new JcaX509v1CertificateBuilder(issuer, certSerialNum, certDate, certEndDate, issuer, kP.getPublic());

			
			Security.addProvider(new BouncyCastleProvider());
			JcaContentSignerBuilder contentSignerBuilder = new JcaContentSignerBuilder("SHA256withRSA");
			ContentSigner contentSigner = contentSignerBuilder.build(kP.getPrivate());

			X509CertificateHolder certHolder = certGen.build(contentSigner);

			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certificate = certConverter.getCertificate(certHolder);

		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return certificate;
	}
	
	public void getCertificateForUser(String userName)
	{
		KeyStore ks = null;
		try {

			ks = loadKeyStore();
			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("changeit".toCharArray());

			boolean isInKeyStore = isUserCertificateInKeyStore(userName);

			X509Certificate userCertificate = null;
			if(isInKeyStore){
				System.out.println("Certificate Retrieved from Keystore");
				userCertificate = (X509Certificate)ks.getCertificate(userName+"Certificate");
			}
			else{
				System.out.println("Created New Certificate");

				SecureRandom random = null;
				random = SecureRandom.getInstance("SHA1PRNG", "SUN");
				KeyPairGenerator keyGen = null;
				keyGen = KeyPairGenerator.getInstance("RSA");
				keyGen.initialize(1024, random);
				KeyPair keyPairUser = keyGen.generateKeyPair();

				KeyStore.PrivateKeyEntry userPrivateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("selfsigned", passwordProtection);

				userCertificate = generateCert(userPrivateKeyEntry, keyPairUser, userName);

				ks.setCertificateEntry(userName + "Certificate" ,userCertificate); 

				setValuesInKeystore(ks);
			}

			System.out.println("Certificate: " + userCertificate);
			Certificate certificateToWrite = ks.getCertificate(userName + "Certificate");
			convertToCertificateFile(userName, certificateToWrite);

			//certificateToWrite.verify(ks.getCertificate("custCertificate").getPublicKey());
			

		} catch (Exception e) {
			System.out.println("In else exception : "+e );

		}

	}
	
	public void convertToCertificateFile(String userName, Certificate certificate) {
		try {
			// CHANGE THE PATH FOR STORING THE .CER FILES 
			String catalinaPath = System.getProperty("catalina.base");
			File dir = new File(catalinaPath + File.separator + "UserCertificates");
            if (!dir.exists())
                dir.mkdirs();
            logger.debug("PKI file" + dir.getAbsolutePath());
			FileOutputStream cos = new FileOutputStream(dir.getAbsolutePath() + File.separator + userName + ".cer");
			cos.write(certificate.getEncoded());
			cos.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isUserCertificateInKeyStore(String userName){
		try{
			KeyStore ks = loadKeyStore();
			if(ks.getCertificate(userName+"Certificate")!=null){
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("In isUserCertificateInKeyStore exception");// TODO: handle exception
		}
		return false;
	}


	public KeyStore loadKeyStore(){
		KeyStore ks = null;
		try{
			ks = KeyStore.getInstance("JKS");


			java.io.FileInputStream fis = null; 
			try {
				// cHANGE THE PATH AS PER YOUR KEYSTORE LOCATION
				fis = new
					java.io.FileInputStream("c:/Tomcat/Keystore/pkikeystore.jks"); 
			ks.load(fis, "changeit".toCharArray()); }  

			finally {

				if (fis != null) {
				}
				fis.close(); }	
		}catch(Exception e){
			System.out.println("In loadKeyStore exception : "+e);
		}
		return ks;
	}

	public void setValuesInKeystore(KeyStore ks){
		java.io.FileOutputStream fos = null; 
		try { 
			// CHANGE PATH
			fos = new java.io.FileOutputStream("C:/Tomcat/Keystore/pkikeystore.jks"); 
			ks.store(fos,"changeit".toCharArray());
		} 

		catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public X509Certificate getCertificateFromFile(String fileName)
	{
		InputStream in;
		try {
			System.out.println("fileName" + fileName);
			in = new FileInputStream(fileName);
			CertificateFactory factory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) factory.generateCertificate(in);
			return cert;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean verifyCertificate(String fileName, String userName) {
		KeyStore ks = loadKeyStore();
		try {
			if(ks != null)
			{
				Certificate bankCertificate = ks.getCertificate(userName+"Certificate");
				Certificate userCertificate = getCertificateFromFile(fileName);
				userCertificate.verify(bankCertificate.getPublicKey());
				return true;
			}
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}