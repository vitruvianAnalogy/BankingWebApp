

package edu.asu.safemoney.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.helper.PKICertificateHelper;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.AuthorizePaymentRequestModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.SubmitAuthorizedPaymentModel;
import edu.asu.safemoney.model.TransactionModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.model.InitiatePaymentModel;
import edu.asu.safemoney.model.DoTransferModel;
import edu.asu.safemoney.model.DoCreditOrDebitModel;
import edu.asu.safemoney.service.EmployeeUserService;
import edu.asu.safemoney.service.ManageExternalUserAccountService;



@Controller
@SessionAttributes
public class ManageExternalUserController {

	@Autowired
	ManageExternalUserAccountService manageExternalUserAccountService;
	

@Autowired
private EmployeeUserService employeeUserService;

	// Takes place in the URL for ManageAccount in side menu
	// Populate External user details in the form.
	// Action for the ManageAccount URL in side menu should be
	// "displayExternalUserDetails"
	// Form name in ManageExternalUsers should be "ExternalUserAccountForm"
	// How to populate values ***

	@RequestMapping(value = "/external/displayExternalUserDetails", method = RequestMethod.GET)
	// get userName from session and use @RequestParam
	public ModelAndView populateExternalUserAccount(HttpSession session) {
		int memberId = (Integer) session.getAttribute("memberId");
		UserDTO userDTO = manageExternalUserAccountService
				.displayUserAccount(memberId);
		// Should redirect to "ManageExternalUserAccount.jsp" page
		return new ModelAndView("external/ManageExternalUser").addObject(
				"userDTO", userDTO);
	}

	// Redirect to Update Page
	@RequestMapping(value = "updateButton", method = RequestMethod.GET)
	public String redirectToUpdateExternalUserAccount(ModelMap model) {
		return "shared/UpdateExternalUserAccount";
	}

	// Redirect to Delete Page
	@RequestMapping(value = "deleteButton", method = RequestMethod.GET)
	public String redirectToDeleteExternalUserAccount(ModelMap model) {
		return "shared/DeleteExternalUserAccount";
	}

	// This takes place in "UpdateExternalUserAccount.jsp" page
	// Update User Account. Action for the update button should be
	// "updateExternalUserDetails".
	// Form name should be "ExternalUserUpdateForm".
	@RequestMapping(value = "/external/updateExternalUserDetails", method = RequestMethod.POST)
	public ModelAndView doUpdateAccount(
			@ModelAttribute("updateUser") ModifyUserModel modifyUserModel, ModelMap model, @RequestParam("certFile") MultipartFile file, HttpSession session) {
		// manageExternalUserAccountService.updateUser(userModel);
		// Should redirect to "updateSuccess.jsp"
		String filePath=System.getProperty("catalina.home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null)
		{
			int memberId = (Integer) session.getAttribute("memberId");
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			File userCertFile = manageExternalUserAccountService.writeCertFile(file, filePath, auth.getName());
			if(userCertFile != null)
			{
				PKICertificateHelper pkiHelper = new PKICertificateHelper();
				boolean state= pkiHelper.verifyCertificate(userCertFile.getAbsolutePath(), auth.getName());
				if(state)
				{
					return new ModelAndView("external/ManageExternalUser").addObject("message", "Certificate Verification Successful");
				}
				else
				{
					return new ModelAndView("external/ManageExternalUser").addObject("error", "Certificate Verification Failed");
				}
			}
			else
			{
				return new ModelAndView("external/ManageExternalUser").addObject("error", "File Upload Failed");
			}
		}
		
		boolean isUpdated = manageExternalUserAccountService.updateUser(modifyUserModel);
		UserDTO userDTO = manageExternalUserAccountService.displayUserAccount(modifyUserModel.getMemberId());
		ModelAndView mv = new ModelAndView("external/ManageExternalUser").addObject("userDTO", userDTO);
		
		if(isUpdated)
		{
			return mv.addObject("message", "Profile Updated Successfully");
		}
		else
		{
			
			return mv.addObject("error", "Update Failed!");
		}
		
	}

	@RequestMapping(value = "/external/deleteExternalUserDetials", method = RequestMethod.POST)
	public ModelAndView doDeleteAccount(HttpSession session) {
		int memberID= (Integer)session.getAttribute("memberId");
		boolean delete= manageExternalUserAccountService.deleteUser(memberID);
		UserDTO userDTO= manageExternalUserAccountService.displayUserAccount(memberID);
		ModelAndView mv= new ModelAndView("external/ManageExternalUser").addObject("userDTO", userDTO);
		// Should redirect to "updateExternalUserAccount"
		if(delete){
			return mv.addObject("message", "Delete Account request sent");
			
		}
		else{
			return mv.addObject("error", "Delete Request not sent");
		}
	}
	
	@RequestMapping(value = "/external/transactions", method = RequestMethod.GET)
	public ModelAndView doTransaction(HttpSession session) {
		int memberId = (Integer) session.getAttribute("memberId");
		AccountModel accountModel = manageExternalUserAccountService
				.getAccountDetails(memberId);
		ModelAndView aaa = new ModelAndView("external/transactions");
		aaa.addObject("account",accountModel);
		
		List<PaymentRequestDTO> requestList = manageExternalUserAccountService.getPaymentRequest(memberId);
		aaa.addObject("requestList", requestList);
		//return new ModelAndView("external/transactions").addObject("account",accountModel);
		return aaa;
	}

	
	
	@RequestMapping(value = "/external/creditDebit", method = RequestMethod.POST)
	public ModelAndView doCreditOrDebit(
			@ModelAttribute("creditDebit") @Valid DoCreditOrDebitModel doCreditOrDebitModel,
			BindingResult validateResult,
			HttpSession session)
	// @ModelAttribute("doCreditOrDebit") TransactionModel transaction
	{
		/////////////////////modified by fei
		int memberId = (Integer) session.getAttribute("memberId");
		List<PaymentRequestDTO> requestList = manageExternalUserAccountService.getPaymentRequest(memberId);
		
		Integer type=doCreditOrDebitModel.getOptionsRadiosInline();
		double amount = doCreditOrDebitModel.getCreditDebitAmount();
		
		if(validateResult.hasErrors())
		{
			validateResult.rejectValue("creditDebitAmount", "error.credit", "Invalid Amount");
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			return new ModelAndView("external/transactions")
			.addObject("account",accountModel).addObject("requestList", requestList)
			.addObject("error", "Transaction Failed. Invalid input(s) detected in credit/debit section");
		}
		if (type == 1) {
			String result = manageExternalUserAccountService
					.makeCreditTransaction(memberId, amount,memberId,"Credit");
			
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			if (result.equals("Pending Approve by Bank")) {

				return new ModelAndView("external/transactions").addObject(
						"message", "The credit transaction is pending approved by bank, if approved, the money will be credited to your account").addObject("account",
								accountModel).addObject("requestList", requestList);
			} else if (result.equals("failure")) {
				return new ModelAndView("external/transactions").addObject(
						"error", "Credit Transaction failed.").addObject("account",
								accountModel).addObject("requestList", requestList);
			} else if (result.equals("NOFUND")) {
				return new ModelAndView("external/transactions")
						.addObject("error",
								"There is no sufficient fund in your account.").addObject("account",
										accountModel).addObject("requestList", requestList);

			} else if (result.equals("CriticalDebit")){
				return new ModelAndView("external/transactions")
				.addObject("message",
						"Greater than 2000 is critical debit, please wait for authorization from Admin").addObject("account",
								accountModel).addObject("requestList", requestList);
			} else if (result.equals("CriticalCredit")){
				return new ModelAndView("external/transactions")
				.addObject("message",
						"This is a critical Credit, please wait for authorization, if approved, the money will be credited to your account").addObject("account",
								accountModel).addObject("requestList", requestList);
			}
		} else if (type == 2) {
			String result = manageExternalUserAccountService
					.makeDebitTransaction(memberId, amount,memberId,"Debit");
			System.out.println(type);
			System.out.println(result);
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			if (result.equals("success")) {

				return new ModelAndView("external/transactions").addObject(
						"message", "Debit Transaction Successfull.").addObject("account",
								accountModel).addObject("requestList", requestList);
			} else if (result.equals("failure")) {
				return new ModelAndView("external/transactions").addObject(
						"error", "Debit Transaction failed.").addObject("account",
								accountModel).addObject("requestList", requestList);
			} else if (result.equals("NOFUND")) {
				return new ModelAndView("external/transactions")
						.addObject("error",
								"There is no sufficient fund in your account.").addObject("account",
										accountModel).addObject("requestList", requestList);

			} else if (result.equals("CriticalDebit")){
				return new ModelAndView("external/transactions")
				.addObject("message",
						"This is a critical debit, please wait for authorization, if not approved, the money will be refund to your account").addObject("account",
								accountModel).addObject("requestList", requestList);

			} 
		}
		 return null;
		

	}

	
	
	@RequestMapping(value = "/external/transfer", method = RequestMethod.POST)
	public ModelAndView doTransform(
			@ModelAttribute("Transform") @Valid DoTransferModel doTransferModel,
			BindingResult validateResult, HttpSession session)

	{
		
//////////////////////////////////modified by fei
		int memberId = (Integer) session.getAttribute("memberId");
		long toAccount = doTransferModel.getToAccountNumber();
		double amount = doTransferModel.getTransformAmount();

		if (validateResult.hasErrors()) {
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);

			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions")
					.addObject("account", accountModel)
					.addObject("requestList", requestList)
					.addObject("error",
							"You have entered some invalid data in transfer page");
		}
		if (manageExternalUserAccountService.findAccount(toAccount)) {

			if (!manageExternalUserAccountService.findIsEnabled(toAccount)) {
				AccountModel accountModel = manageExternalUserAccountService
						.getAccountDetails(memberId);

				List<PaymentRequestDTO> requestList = manageExternalUserAccountService
						.getPaymentRequest(memberId);
				return new ModelAndView("external/transactions")
						.addObject("account", accountModel)
						.addObject("requestList", requestList)
						.addObject("error", "Invalid Account");
			}
			String result = manageExternalUserAccountService.makeTransform(
					memberId, amount, toAccount);
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);

			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);

			if (result.equals("success")) {

				return new ModelAndView("external/transactions")
						.addObject(
								"message",
								"Transfer need to be approve by employee, if not approved, the money will be refunded")
						.addObject("account", accountModel)
						.addObject("requestList", requestList);
			} else if (result.startsWith("failure")) {
				return new ModelAndView("external/transactions")
						.addObject("error", "Transform Transaction failed.")
						.addObject("account", accountModel)
						.addObject("requestList", requestList);
			} else if (result.startsWith("NOFUND")) {
				return new ModelAndView("external/transactions")
						.addObject("error",
								"There is no sufficient fund in your account.")
						.addObject("account", accountModel)
						.addObject("requestList", requestList);
			} else if (result.startsWith("Critical")) {
				return new ModelAndView("external/transactions")
						.addObject(
								"message",
								"Greater than 2000 is Critical, Transfer need to be approve by admin, if not approved, the money will be refunded")
						.addObject("account", accountModel)
						.addObject("requestList", requestList);
			}
			return new ModelAndView("external/transactions");
		}

		else {
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);

			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions")
					.addObject("error", "Invalid Account")
					.addObject("account", accountModel)
					.addObject("requestList", requestList);

		}
	}
	
	@RequestMapping(value="/external/review", method = RequestMethod.GET)
	public ModelAndView reviewTransaction(HttpSession session)
	{
		int memberId = (Integer) session.getAttribute("memberId");
		List<TransactionDTO> approvedTransactionList = manageExternalUserAccountService.getApprovedTransactionListForUser(memberId);
		if(approvedTransactionList != null)
		{
			return new ModelAndView("external/transactionReview").addObject("transactionList", approvedTransactionList);
		}
		else
		{
			return new ModelAndView("external/transactionReview").addObject("error", "true");
		}
		
	}
	

	
	
	@RequestMapping(value = "/external/authorizePaymentRequest", method = RequestMethod.POST)
	public ModelAndView authorizePayment(
			@ModelAttribute("authorizePaymentRequest") @Valid AuthorizePaymentRequestModel authorizePaymentRequestModel,
			BindingResult validateResult,
			
			@RequestParam("certFile") MultipartFile file, 
			HttpSession session)
	{
		
/////////////////////////////////////////modified by fei
		String filePath = System.getProperty("catalina.home");
		
		long paymentRequestId = authorizePaymentRequestModel.getPaymentRequestId();
		String authorizeAction = authorizePaymentRequestModel.getAuthorizeAction();
		
		if(validateResult.hasErrors())
		{
			
			int memberId = (Integer) session.getAttribute("memberId");
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions")
			.addObject("account", accountModel)
			.addObject("requestList", requestList)
			.addObject("error",
					"Authorization failed. Invalid input(s) detected in authorization section");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null)
		{
			int memberId = (Integer) session.getAttribute("memberId");
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			File userCertFile = manageExternalUserAccountService.writeCertFile(file, filePath, auth.getName());
			if(userCertFile != null)
			{
				PKICertificateHelper pkiHelper = new PKICertificateHelper();
				boolean state = pkiHelper.verifyCertificate(userCertFile.getAbsolutePath(), auth.getName());
				if(state)
				{
				/*	return new ModelAndView("external/transactions").addObject("account", accountModel)
							.addObject("requestList", requestList).addObject("message", "Certificate Correct");*/
				}
				else
				{
				return new ModelAndView("external/transactions").addObject("account", accountModel)
						.addObject("requestList", requestList).addObject("error", "Certificate Incorrect");
				}
			}
			else
			{
				return new ModelAndView("external/transactions").addObject("account", accountModel)
						.addObject("requestList", requestList).addObject("error", "File Upload Failed");
			}
		}
		if (authorizeAction.equals("authorized")) {
			String result = manageExternalUserAccountService
					.authorizePayment(paymentRequestId);
			int memberId = (Integer) session.getAttribute("memberId");
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			if (result.equals("success")) {
				return new ModelAndView("external/transactions")
						.addObject("message",
								"Payment Authorized Successfully!")
						.addObject("account", accountModel)
						.addObject("requestList", requestList);
			} else if (result.equals("NOFUND")) {
				return new ModelAndView("external/transactions")
						.addObject("account", accountModel)
						.addObject("requestList", requestList)
						.addObject("error",
								"You don't have enough fund in your account");
			} else if(result.equals("NOTFOUND"))
			{
				return new ModelAndView("external/transactions")
				.addObject("account", accountModel)
				.addObject("requestList", requestList)
				.addObject("error",
						"Invalid payment request ID");
			}
			else {
				return new ModelAndView("external/transactions")
						.addObject("account", accountModel)
						.addObject("requestList", requestList)
						.addObject("error",
								"Authorize failed because of" + result);

			}
		}
		else if(authorizeAction.equals("declined"))
		{
			String result = manageExternalUserAccountService.declinePayment(paymentRequestId);
			
			int memberId = (Integer) session.getAttribute("memberId");
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			if(result.equals("NOTFOUND"))
			{
				return new ModelAndView("external/transactions")
				.addObject("error",
						"Invalid Payment Request ID")
				.addObject("account", accountModel)
				.addObject("requestList", requestList);
			}
			
			return new ModelAndView("external/transactions")
			.addObject("message",
					"You have declined this payment")
			.addObject("account", accountModel)
			.addObject("requestList", requestList);
		}
		else
		{
			
			int memberId = (Integer) session.getAttribute("memberId");
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions")
			.addObject("error",
					"invalid inpur data")
			.addObject("account", accountModel)
			.addObject("requestList", requestList);
		}
			
	}
	//requestingDate
	
	
	
	@RequestMapping(value = "/external/initiatePayment", method = RequestMethod.POST)
	public ModelAndView initiatePayment(@ModelAttribute("initiatePayment") @Valid InitiatePaymentModel initiatementPaymentModel,
			BindingResult validateResult,
			HttpSession session) {

/////////////////////////////////////////modified by fei
		int memberId = (Integer) session.getAttribute("memberId");


		long toMerchantAccount = initiatementPaymentModel.getToMerchantAccountNumber();
		double amount = initiatementPaymentModel.getAmount();
		String description = initiatementPaymentModel.getDescription();
		
		if(validateResult.hasErrors())
		{
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions")
					.addObject("account", accountModel)
					.addObject("requestList", requestList)
					.addObject("error", "Initiate Payment Failed. Invalid input(s) detected in initiate payment section");
		}
		

		if (manageExternalUserAccountService.findAccount(toMerchantAccount)) {
			
			
			if(!manageExternalUserAccountService.findIsEnabled(toMerchantAccount))
			{
				AccountModel accountModel = manageExternalUserAccountService
						.getAccountDetails(memberId);
				List<PaymentRequestDTO> requestList = manageExternalUserAccountService
						.getPaymentRequest(memberId);
				return new ModelAndView("external/transactions")
						.addObject("account", accountModel)
						.addObject("requestList", requestList)
						.addObject("error", "Invalid Account");
			}
			
			String result = manageExternalUserAccountService.initiatePayment(
					memberId, toMerchantAccount, amount, description);
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			if (result.equals("success")) {
				return new ModelAndView("external/transactions")
						.addObject("message",
								"Payment Authorized Successfully!")
						.addObject("account", accountModel)
						.addObject("requestList", requestList)
						.addObject("message",
								"You have initiated a payment successfully");
			} else if (result.equals("NOFUND")) {
				return new ModelAndView("external/transactions")
						.addObject("message",
								"Payment Authorized Successfully!")
						.addObject("account", accountModel)
						.addObject("requestList", requestList)
						.addObject("message",
								"You don't have enough fund in your account");
			} else {
				return new ModelAndView("external/transactions")
						.addObject("message",
								"Payment Authorized Successfully!")
						.addObject("account", accountModel)
						.addObject("requestList", requestList)
						.addObject("message",
								"Authorize failed because of" + result);

			}
		} else
		{
			AccountModel accountModel = manageExternalUserAccountService
					.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService
					.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions")
					.addObject("error", "Invalid Account")
					.addObject("account", accountModel)
					.addObject("requestList", requestList);
		}
	

	}
	
	
	
	
	@RequestMapping(value = "/external/submitAuthorizedPaymentRequest", method = RequestMethod.POST)
	public ModelAndView submitAuthorizedPayment(@ModelAttribute("submitAuthorizedPaymentRequest") @Valid SubmitAuthorizedPaymentModel submitAuthorizedPaymentModel,
			BindingResult validateResult,
			HttpSession session)
	{
		/////////////////////////////////////////modified by fei	
		int memberId = (Integer) session.getAttribute("memberId");
		
		long paymentRequestId = submitAuthorizedPaymentModel.getPaymentRequestId2();
		
		if(validateResult.hasErrors())
		{
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions")
			.addObject("account",accountModel).addObject("requestList", requestList)
			.addObject("error", "Authorization Failed. Invalid input(s) detected in Authorization section");
		}
		
		String result = manageExternalUserAccountService.submitPayment(paymentRequestId);
		if(result.equals("NOTFOUND"))
		{
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions")
			.addObject("account",accountModel).addObject("requestList", requestList)
			.addObject("error", "Authorization Failed. Invalid input request ID in Authorization section");
		}
			
		if(result.equals("success"))
		{
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions").addObject("message",
				"Payment Authorized Successfully!").addObject("account",
						accountModel).addObject("requestList", requestList).addObject("message", "You have sibmitted payment successfully");
		}
		else
		{
			AccountModel accountModel = manageExternalUserAccountService.getAccountDetails(memberId);
			List<PaymentRequestDTO> requestList = manageExternalUserAccountService.getPaymentRequest(memberId);
			return new ModelAndView("external/transactions").addObject("message",
					"Payment Authorized Successfully!").addObject("account",
							accountModel).addObject("requestList", requestList).addObject("message", "Authorize failed because");
		
		}
	}


	
	
	/*
	 * To load page from where External user can see and approve the 
	 * View Account requests from the emp
	 */
	@RequestMapping("/external/viewTransactionApproveRequests")
	public ModelAndView viewTransactionApproveRequests(HttpSession session)
	{
		int memberId= (int) session.getAttribute("memberId");
		List<RequestDTO> viewTransactionRequestList = manageExternalUserAccountService.getViewTransactionsRequests(memberId); 
		return new ModelAndView("/external/viewTransactionApproveRequests").addObject("viewTransactionRequestList",viewTransactionRequestList);
	}
	
	/*
	 * To approve the view account request by customer or merchant on button click
	 */
	@RequestMapping(value = "/external/approveViewUserAccounts", method=RequestMethod.POST)
	public ModelAndView approveViewAccountApproveRequests(@RequestParam("requestId") long requestId,HttpSession session)
	{
		int memberId= (int) session.getAttribute("memberId");
		boolean isAuthorized = manageExternalUserAccountService.authorizeViewAccountRequest(requestId);
		List<RequestDTO> viewAccountRequestList = manageExternalUserAccountService.getViewAccountRequests(memberId);		
		return new ModelAndView("/external/viewAccountApproveRequests").addObject("isAuthorized",isAuthorized).addObject("viewAccountRequestList", viewAccountRequestList);
	}
	
	
	/*
	 * To approve the view account request by customer or merchant on button click
	 */
	@RequestMapping(value = "/external/approveViewUserTransactions", method=RequestMethod.POST)
	public ModelAndView approveViewAccountApproveTransactions(@RequestParam("requestId") long requestId,HttpSession session)
	{
		int memberId= (int) session.getAttribute("memberId");
		boolean isAuthorized = manageExternalUserAccountService.authorizeViewTransactionsRequest(requestId);
		List<RequestDTO> viewTransactionRequestList = manageExternalUserAccountService.getViewTransactionsRequests(memberId);		
		return new ModelAndView("/external/viewTransactionApproveRequests").addObject("isAuthorized",isAuthorized).addObject("viewTransactionRequestList", viewTransactionRequestList);
	}
	
	
	


	/*
	 * To load page from where External user can see and approve the 
	 * View Account requests from the emp
	 */
	@RequestMapping("/external/viewAccountApproveRequests")
	public ModelAndView viewAccountApproveRequests(HttpSession session)
	{
		int memberId= (int) session.getAttribute("memberId");
		List<RequestDTO> viewAccountRequestList = manageExternalUserAccountService.getViewAccountRequests(memberId); 
		return new ModelAndView("/external/viewAccountApproveRequests").addObject("viewAccountRequestList",viewAccountRequestList);
	}
	
	
	/*
	 * To decline the view account request by customer or merchant on button click
	 */
	@RequestMapping(value = "/external/declineViewUserAccounts", method=RequestMethod.POST)
	public ModelAndView declineViewAccountApproveRequests(@RequestParam("requestId") long requestId,HttpSession session)
	{
		int memberId= (int) session.getAttribute("memberId");
		boolean isAuthorized = manageExternalUserAccountService.declineViewAccountRequest(requestId);
		List<RequestDTO> viewAccountRequestList = manageExternalUserAccountService.getViewAccountRequests(memberId);		
		return new ModelAndView("/external/viewAccountApproveRequests").addObject("isAuthorized",isAuthorized).addObject("viewAccountRequestList", viewAccountRequestList);
	}
	
	
	/*
	 * To decline the view account request by customer or merchant on button click
	 */
	@RequestMapping(value = "/external/declineViewUserTransactions", method=RequestMethod.POST)
	public ModelAndView declineViewTransactionsApproveRequests(@RequestParam("requestId") long requestId,HttpSession session)
	{
		int memberId= (int) session.getAttribute("memberId");
		boolean isAuthorized = manageExternalUserAccountService.declineViewTransactionsRequest(requestId);
		List<RequestDTO> viewTransactionRequestList = manageExternalUserAccountService.getViewTransactionsRequests(memberId);		
		return new ModelAndView("/external/viewTransactionApproveRequests").addObject("isAuthorized",isAuthorized).addObject("viewTransactionRequestList", viewTransactionRequestList);
	}
	
	@RequestMapping(value="/external/modifyTransaction", method=RequestMethod.POST)
	public ModelAndView modifyTransaction(@ModelAttribute("modifyTransactionForm") TransactionModel transactionModel, HttpSession session)
	{	
		
		int memberId = (Integer) session.getAttribute("memberId");
		boolean isReviewSubmitted = manageExternalUserAccountService.sendTransactionModificationRequest(transactionModel, memberId);
		List<TransactionDTO> approvedTransactionList = manageExternalUserAccountService.getApprovedTransactionListForUser(memberId);
		if(isReviewSubmitted)
		{
			return new ModelAndView("external/transactionReview").addObject("transactionList", approvedTransactionList).addObject("review","true");
		}
		else
		{
			return new ModelAndView("external/transactionReview").addObject("submitError", "Review could not be submitted");
		}
		
	}
	
	@RequestMapping(value="/external/deleteTransaction", method=RequestMethod.POST)
	public ModelAndView deleteTransaction(@RequestParam("transactionID") long transactionId, HttpSession session)
	{	
		int memberId = (Integer) session.getAttribute("memberId");
		boolean isDeleted = manageExternalUserAccountService.sendTransactionDeletionRequest(transactionId, memberId);
		List<TransactionDTO> approvedTransactionList = manageExternalUserAccountService.getApprovedTransactionListForUser(memberId);
		if(isDeleted)
		{
			return new ModelAndView("external/transactionReview").addObject("transactionList", approvedTransactionList).addObject("review","true");
		}
		else
		{
			return new ModelAndView("external/transactionReview").addObject("submitError", "Review could not be submitted");
		}
		
	}
	
	@RequestMapping(value = "/external/viewTransactionHistoryPage", method = RequestMethod.GET)
	public ModelAndView getTransactionHistoryPage(HttpSession session){
	int memberId = (Integer) session.getAttribute("memberId");
	UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(memberId);
	List<TransactionDTO> transactionInfo = employeeUserService.getAllTransactions(memberId);
	return new ModelAndView("/external/transactionHistory").addObject("transactionInfo",transactionInfo);
	}

	@RequestMapping(value="/external/createNewReq", method=RequestMethod.POST)
	public ModelAndView doNewTransaction(@ModelAttribute("createNewReq") TransactionModel transactionModel, HttpSession session)
	{
	int memberId= (int) session.getAttribute("memberId");
	System.out.println("memberId is : " +memberId);
	System.out.println("Transaction amount is:" + transactionModel.getTransactionAmount());
	System.out.println("Transaction type is:" + transactionModel.getTransactionType());
	System.out.println("Transaction date is:" + transactionModel.getTransactionDate());
	boolean isCreated= manageExternalUserAccountService.createRequest(transactionModel, memberId);
	UserDTO userDTO= manageExternalUserAccountService.displayUserAccount(memberId);
	ModelAndView mv = new ModelAndView("external/transactionReview").addObject("userDTO", userDTO);
	if(isCreated)
	{
	return mv.addObject("message", "Profile Updated Successfully");
	}
	else
	{
	return mv.addObject("error", "Update Failed!");
	}
	}

}

