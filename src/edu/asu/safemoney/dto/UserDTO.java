/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.safemoney.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ObuliKarthikeyan
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDTO.findAll", query = "SELECT u FROM UserDTO u"),
    @NamedQuery(name = "UserDTO.findByMemberId", query = "SELECT u FROM UserDTO u WHERE u.memberId = :memberId"),
    @NamedQuery(name = "UserDTO.findByFirstName", query = "SELECT u FROM UserDTO u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UserDTO.findByLastName", query = "SELECT u FROM UserDTO u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "UserDTO.findByEmailId", query = "SELECT u FROM UserDTO u WHERE u.emailId = :emailId"),
    @NamedQuery(name = "UserDTO.findByContactNo", query = "SELECT u FROM UserDTO u WHERE u.contactNo = :contactNo"),
    @NamedQuery(name = "UserDTO.findByAddress1", query = "SELECT u FROM UserDTO u WHERE u.address1 = :address1"),
    @NamedQuery(name = "UserDTO.findByAddress2", query = "SELECT u FROM UserDTO u WHERE u.address2 = :address2"),
    @NamedQuery(name = "UserDTO.findByCity", query = "SELECT u FROM UserDTO u WHERE u.city = :city"),
    @NamedQuery(name = "UserDTO.findByState", query = "SELECT u FROM UserDTO u WHERE u.state = :state"),
    @NamedQuery(name = "UserDTO.findByZip", query = "SELECT u FROM UserDTO u WHERE u.zip = :zip"),
    @NamedQuery(name = "UserDTO.findBySsn", query = "SELECT u FROM UserDTO u WHERE u.ssn = :ssn"),
    @NamedQuery(name = "UserDTO.findBySecQuestion1", query = "SELECT u FROM UserDTO u WHERE u.secQuestion1 = :secQuestion1"),
    @NamedQuery(name = "UserDTO.findBySecQuestion2", query = "SELECT u FROM UserDTO u WHERE u.secQuestion2 = :secQuestion2"),
    @NamedQuery(name = "UserDTO.findBySecQuestion3", query = "SELECT u FROM UserDTO u WHERE u.secQuestion3 = :secQuestion3"),
    @NamedQuery(name = "UserDTO.findBySecAnswer1", query = "SELECT u FROM UserDTO u WHERE u.secAnswer1 = :secAnswer1"),
    @NamedQuery(name = "UserDTO.findBySecAnswer2", query = "SELECT u FROM UserDTO u WHERE u.secAnswer2 = :secAnswer2"),
    @NamedQuery(name = "UserDTO.findBySecAnswer3", query = "SELECT u FROM UserDTO u WHERE u.secAnswer3 = :secAnswer3"),
    @NamedQuery(name = "UserDTO.findByDateOfBirth", query = "SELECT u FROM UserDTO u WHERE u.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "UserDTO.findByAge", query = "SELECT u FROM UserDTO u WHERE u.age = :age"),
    @NamedQuery(name = "UserDTO.findByIsCustomer", query = "SELECT u FROM UserDTO u WHERE u.isCustomer = :isCustomer"),
    @NamedQuery(name = "UserDTO.findByCreatedBy", query = "SELECT u FROM UserDTO u WHERE u.createdBy = :createdBy"),
    @NamedQuery(name = "UserDTO.findByCreatedDate", query = "SELECT u FROM UserDTO u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "UserDTO.findByExpiryDate", query = "SELECT u FROM UserDTO u WHERE u.expiryDate = :expiryDate"),
    @NamedQuery(name = "UserDTO.findByIsActive", query = "SELECT u FROM UserDTO u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "UserDTO.findByIsEmployee", query = "SELECT u FROM UserDTO u WHERE u.isEmployee = :isEmployee"),
    @NamedQuery(name = "UserDTO.findByDesignation", query = "SELECT u FROM UserDTO u WHERE u.designation = :designation"),
    @NamedQuery(name = "UserDTO.findByIsPIIAuthorized", query = "SELECT u FROM UserDTO u WHERE u.isPIIAuthorized = :isPIIAuthorized")})
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "member_id")
    private Integer memberId;
    @Size(max = 25)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 25)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "email_id")
    private String emailId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contact_no")
    private long contactNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "address_1")
    private String address1;
    @Size(max = 50)
    @Column(name = "address_2")
    private String address2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zip")
    private long zip;
    @Column(name = "ssn")
    private long ssn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "sec_question_1")
    private String secQuestion1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "sec_question_2")
    private String secQuestion2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "sec_question_3")
    private String secQuestion3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "sec_answer_1")
    private String secAnswer1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "sec_answer_2")
    private String secAnswer2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "sec_answer_3")
    private String secAnswer3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "age")
    private Integer age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "isCustomer")
    private String isCustomer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "created_by")
    private String createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "is_active")
    private String isActive;
    @Column(name = "isEmployee")
    private Boolean isEmployee;
    @Size(max = 25)
    @Column(name = "designation")
    private String designation;
    @Column(name = "isPIIAuthorized")
    private Boolean isPIIAuthorized;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private List<TransactionDTO> transactionDTOList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custMemberId")
    private List<TransactionReviewDTO> transactionReviewDTOList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private List<RequestDTO> requestDTOList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private List<AccountDTO> accountDTOList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchantMemberId")
    private List<PaymentRequestDTO> paymentRequestDTOList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userDTO")
    private LoginDTO loginDTO;
    @JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
    @ManyToOne(optional = false)
    private UserTypeDTO userTypeId;

    public UserDTO() {
    }

    public UserDTO(Integer memberId) {
        this.memberId = memberId;
    }

    public UserDTO(Integer memberId, String emailId, long contactNo, String address1, String city, String state, long zip, String secQuestion1, String secQuestion2, String secQuestion3, String secAnswer1, String secAnswer2, String secAnswer3, Date dateOfBirth, String isCustomer, String createdBy, Date createdDate, Date expiryDate, String isActive) {
        this.memberId = memberId;
        this.emailId = emailId;
        this.contactNo = contactNo;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.secQuestion1 = secQuestion1;
        this.secQuestion2 = secQuestion2;
        this.secQuestion3 = secQuestion3;
        this.secAnswer1 = secAnswer1;
        this.secAnswer2 = secAnswer2;
        this.secAnswer3 = secAnswer3;
        this.dateOfBirth = dateOfBirth;
        this.isCustomer = isCustomer;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public String getSecQuestion1() {
        return secQuestion1;
    }

    public void setSecQuestion1(String secQuestion1) {
        this.secQuestion1 = secQuestion1;
    }

    public String getSecQuestion2() {
        return secQuestion2;
    }

    public void setSecQuestion2(String secQuestion2) {
        this.secQuestion2 = secQuestion2;
    }

    public String getSecQuestion3() {
        return secQuestion3;
    }

    public void setSecQuestion3(String secQuestion3) {
        this.secQuestion3 = secQuestion3;
    }

    public String getSecAnswer1() {
        return secAnswer1;
    }

    public void setSecAnswer1(String secAnswer1) {
        this.secAnswer1 = secAnswer1;
    }

    public String getSecAnswer2() {
        return secAnswer2;
    }

    public void setSecAnswer2(String secAnswer2) {
        this.secAnswer2 = secAnswer2;
    }

    public String getSecAnswer3() {
        return secAnswer3;
    }

    public void setSecAnswer3(String secAnswer3) {
        this.secAnswer3 = secAnswer3;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(String isCustomer) {
        this.isCustomer = isCustomer;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(Boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getIsPIIAuthorized() {
        return isPIIAuthorized;
    }

    public void setIsPIIAuthorized(Boolean isPIIAuthorized) {
        this.isPIIAuthorized = isPIIAuthorized;
    }

    @XmlTransient
    public List<TransactionDTO> getTransactionDTOList() {
        return transactionDTOList;
    }

    public void setTransactionDTOList(List<TransactionDTO> transactionDTOList) {
        this.transactionDTOList = transactionDTOList;
    }

    @XmlTransient
    public List<TransactionReviewDTO> getTransactionReviewDTOList() {
        return transactionReviewDTOList;
    }

    public void setTransactionReviewDTOList(List<TransactionReviewDTO> transactionReviewDTOList) {
        this.transactionReviewDTOList = transactionReviewDTOList;
    }

    @XmlTransient
    public List<RequestDTO> getRequestDTOList() {
        return requestDTOList;
    }

    public void setRequestDTOList(List<RequestDTO> requestDTOList) {
        this.requestDTOList = requestDTOList;
    }

    @XmlTransient
    public List<AccountDTO> getAccountDTOList() {
        return accountDTOList;
    }

    public void setAccountDTOList(List<AccountDTO> accountDTOList) {
        this.accountDTOList = accountDTOList;
    }

    @XmlTransient
    public List<PaymentRequestDTO> getPaymentRequestDTOList() {
        return paymentRequestDTOList;
    }

    public void setPaymentRequestDTOList(List<PaymentRequestDTO> paymentRequestDTOList) {
        this.paymentRequestDTOList = paymentRequestDTOList;
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public UserTypeDTO getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserTypeDTO userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDTO)) {
            return false;
        }
        UserDTO other = (UserDTO) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.asu.safemoney.dto.UserDTO[ memberId=" + memberId + " ]";
    }
    
}
