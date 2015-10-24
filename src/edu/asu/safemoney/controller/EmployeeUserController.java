/*
 * Author		:	Nishant Rawat
 * StudentID	:	1206343628
 * Course		:	CSE 545 Software Security
 */
package edu.asu.safemoney.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.safemoney.dto.AccountDTO;
import edu.asu.safemoney.dto.PaymentRequestDTO;
import edu.asu.safemoney.dao.EmployeeUserDAO;

	

import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.TransactionReviewDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.*;
import edu.asu.safemoney.service.EmployeeUserService;
import edu.asu.safemoney.service.ManageExternalUserAccountService;
import edu.asu.safemoney.service.impln.ManageExternalUserAccountServiceImpl;

@Controller
@SessionAttributes
public class EmployeeUserController {
	
	@Autowired
	private EmployeeUserService employeeUserService;
	
	@Autowired
	private EmployeeUserDAO employeeUserDAO;
	
	@Autowired
	private ManageExternalUserAccountService manageExternalUserAccountService;
	
	
	boolean isRequestSent = false;
	
	public static final Logger logger = Logger.getLogger(LoginController.class);

	
	
	/*
	 * Generates a page that Sends "VIEW ACCOUNT" request from the employee to
	 * the customer or merchant. Upon creating it displays the list of customer
	 * to whom the request has been sent 
	 */
	@RequestMapping(value="/internal/sendViewRequests")
	public ModelAndView getInternalUserAccountRequests(HttpSession session)
	{	
		
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);
		List<RequestDTO> requestList= employeeUserService.getRequestList(currentEmployeeId);
		if(requestList!=null)
		{
			if(!requestList.isEmpty())
			{
				return new ModelAndView("/internal/EmpRequestCustView").addObject("requestList",requestList);
			}
			else
			{
				return new ModelAndView("/internal/EmpRequestCustView").addObject("requestList",requestList);
			}
				
		}
		else
		{
			return new ModelAndView("/internal/EmpRequestCustView");
		}
	}
	
	
	//Added by Fei
	@RequestMapping(value="/internal/manageTransactionRequest", method=RequestMethod.GET)
	public ModelAndView getTransactionRequest(HttpSession session) {

		List<PaymentRequestDTO> paymentList = employeeUserService
				.getPaymentRequest();
		List<TransactionDTO> transactionList = employeeUserService
				.getTransactionRequest();
		
		return new ModelAndView("/internal/EmpManageExtTransactions")
				.addObject("paymentRequestList", paymentList).addObject(
						"transactionRequestList", transactionList);
	}
	
	
	@RequestMapping(value="/internal/processPaymentRequest", method=RequestMethod.POST)
	public ModelAndView processPayment(
			@ModelAttribute("processPaymentRequest") @Valid EmpProcessPaymentModel empProcessPaymentModel,
			BindingResult validateResult,
			HttpSession session) {
		// ////////////////////////modified by fei
		String processResult = null;

		long paymentRequestId = empProcessPaymentModel.getPaymentRequestId2();
		String manageAction = empProcessPaymentModel.getManagePaymentAction();
			if(validateResult.hasErrors())
			{
				List<PaymentRequestDTO> paymentList = employeeUserService
						.getPaymentRequest();
				List<TransactionDTO> transactionList = employeeUserService
						.getTransactionRequest();

				return new ModelAndView("/internal/EmpManageExtTransactions")
						.addObject("paymentRequestList", paymentList)
						.addObject("transactionRequestList", transactionList)
						.addObject("error", "Invalid input(s) detected in the process payment section");
			}
		if (manageAction.equals("approved")) {
			String myresult = employeeUserService.updatePaymentRequest(
					paymentRequestId, "APPROVED_BANK");
			//
			if(myresult.equals("NOTFOUND"))
			{
				List<PaymentRequestDTO> paymentList = employeeUserService
						.getPaymentRequest();
				List<TransactionDTO> transactionList = employeeUserService
						.getTransactionRequest();

				return new ModelAndView("/internal/EmpManageExtTransactions")
						.addObject("paymentRequestList", paymentList)
						.addObject("transactionRequestList", transactionList)
						.addObject("error", "Invalid request Id Input");
			}
			PaymentRequestDTO paymentDTO = employeeUserService
					.getPaymentDTOById(paymentRequestId);
			//
			UserDTO metchantDTO = paymentDTO.getMerchantMemberId();

			boolean myresult2 = employeeUserService.makeCredit(
					metchantDTO.getMemberId(), paymentDTO.getAmount());

			if ((myresult.equals("success")) && myresult2) {
				processResult = "You have successfully approved one payment request";
			} else {
				processResult = "Approve payment request failed";
			}
		} else if (manageAction.equals("declined")) {
			String myresult = employeeUserService.updatePaymentRequest(
					paymentRequestId, "DECLINED_BANK");
			PaymentRequestDTO paymentDTO = employeeUserService
					.getPaymentDTOById(paymentRequestId);

			boolean myresult2 = employeeUserService.makeCredit(
					paymentDTO.getAuthorizerMemberId(), paymentDTO.getAmount());

			if ((myresult.equals("success")) && myresult2) {
				processResult = "You have successfully declined one payment request";
			} else {
				processResult = "declined payment request failed";
			}
		}
		else
		{
			processResult = "Invalid manage action input";
		}

		List<PaymentRequestDTO> paymentList = employeeUserService
				.getPaymentRequest();
		List<TransactionDTO> transactionList = employeeUserService
				.getTransactionRequest();

		return new ModelAndView("/internal/EmpManageExtTransactions")
				.addObject("paymentRequestList", paymentList)
				.addObject("transactionRequestList", transactionList)
				.addObject("message", processResult);
	}
	
	
	//EmpAuthorizePaymentRequestModel
	@RequestMapping(value="/internal/authorizePaymentRequest", method=RequestMethod.POST)
	public ModelAndView processTransaction(
			@ModelAttribute("authorizePaymentRequest") @Valid EmpAuthorizePaymentRequestModel empAuthorizePaymentRequestModel,
			BindingResult validateResult,
			
			HttpSession session) 
	{
		////////////////modified by fei
		String processResult = null;
		long transactionRequestId = empAuthorizePaymentRequestModel.getTransactionRequestId();
		String manageAction = empAuthorizePaymentRequestModel.getManageTransactionAction();
		
		if(validateResult.hasErrors())
		{
			List<PaymentRequestDTO> paymentList = employeeUserService
					.getPaymentRequest();
			List<TransactionDTO> transactionList = employeeUserService
					.getTransactionRequest();
			System.out.println(processResult);
			return new ModelAndView("/internal/EmpManageExtTransactions")
			.addObject("paymentRequestList", paymentList).addObject(
					"transactionRequestList", transactionList).addObject("error","Invalid Input(s) detected in the process transaction section");
		}
		if(manageAction.equals("approved"))
		{
			String myresult =employeeUserService.updateTransactionRequest( transactionRequestId, "APPROVED_BANK");
			
			if(myresult.equals("NOTFOUND"))
			{
				List<PaymentRequestDTO> paymentList = employeeUserService
						.getPaymentRequest();
				List<TransactionDTO> transactionList = employeeUserService
						.getTransactionRequest();
				return new ModelAndView("/internal/EmpManageExtTransactions")
				.addObject("paymentRequestList", paymentList).addObject(
						"transactionRequestList", transactionList).addObject("error","Invalid Transaction ID input");
			}
			TransactionDTO transactiontDTO = employeeUserService.getTransactionDTOById(transactionRequestId);
			
			int toMemberId = employeeUserService.getMemberIdByAccount(transactiontDTO.getToAccount());
			int fromMemberId = employeeUserService.getMemberIdByAccount(transactiontDTO.getFromAccount());
			
			//transactiontDTO.getf
			boolean myresult2 = false;
			String type = transactiontDTO.getTransactionType();
			if(type.equals("Debit"))
			{
				
				myresult2=true;
			}
			else
			{	
				myresult2=employeeUserService.makeCredit(toMemberId,transactiontDTO.getAmount());
			}
			
			if((myresult.equals("success"))&&myresult2)
				processResult="You have successfully approved one transaction";
			else
				processResult="Failed";
			
			
			
		}
		else if(manageAction.equals("declined"))
		{
			
			String myresult =employeeUserService.updateTransactionRequest( transactionRequestId, "DECLINED_BANK");
			if(myresult.equals("NOTFOUND"))
			{
				List<PaymentRequestDTO> paymentList = employeeUserService
						.getPaymentRequest();
				List<TransactionDTO> transactionList = employeeUserService
						.getTransactionRequest();
				return new ModelAndView("/internal/EmpManageExtTransactions")
				.addObject("paymentRequestList", paymentList).addObject(
						"transactionRequestList", transactionList).addObject("error","Invalid Transaction ID input");
			}
			TransactionDTO transactiontDTO = employeeUserService.getTransactionDTOById(transactionRequestId);
			
			int fromMemberId = employeeUserService.getMemberIdByAccount(transactiontDTO.getFromAccount());
			boolean myresult2=false;
			
			//transactiontDTO.getf
			String type = transactiontDTO.getTransactionType();
			if(type.equals("Debit"))
			{
				myresult2 = employeeUserService.makeCredit(fromMemberId,transactiontDTO.getAmount());
			}
			else
			{	
				myresult2 = employeeUserService.makeCredit(fromMemberId,transactiontDTO.getAmount());
			}
			
			if((myresult.equals("success"))&&myresult2)
				processResult="You have successfully declined one transaction";
			else
				processResult="Failed";
			
		}
		else
		{
		List<PaymentRequestDTO> paymentList = employeeUserService
				.getPaymentRequest();
		List<TransactionDTO> transactionList = employeeUserService
				.getTransactionRequest();
		System.out.println(processResult);
		return new ModelAndView("/internal/EmpManageExtTransactions")
		.addObject("paymentRequestList", paymentList).addObject(
				"transactionRequestList", transactionList).addObject("error","Invalid Process Action value");
		}
		return null;
	}
	
	/*
	 * Actually sends the View Account request from the employee
	 * to customer
	 */
	@RequestMapping(value="/internal/requestTransactionAccess", method=RequestMethod.POST)
	public ModelAndView sendViewRequests(@ModelAttribute("sendRequestForm") @Valid RequestModel requestModel, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Invalid Request");
		}
		else
		{
			boolean isUserNameAvailable = false;
			int currentEmployeeId = (Integer)session.getAttribute("memberId");
			int internalUserId = requestModel.getMemberId();
	
			//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);	
			List<RequestDTO> requestList = null; 
			if (requestModel.getMemberId()<0)
			{
				logger.error("The member Id cannot be negative");
				isUserNameAvailable=false;
			}
			else
			{
				isUserNameAvailable=true;
			}
			
			if(isUserNameAvailable)
			{		
				
				isRequestSent = employeeUserService.sendExtUserViewRequests(internalUserId, currentEmployeeId);
				requestList = employeeUserService.getRequestList(currentEmployeeId);
			}
			else
			{
				
				return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Invalid Member Id").addObject("requestList",requestList);
			}
			
			if(isRequestSent)
			{
				return new ModelAndView("/internal/EmpRequestCustView").addObject("message","Request Sent to User").addObject("requestList",requestList);
			}
			else
			{
				return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Request Failed").addObject("requestList",requestList);
			}
		}
	}
		
//	/*
//	 * Actually sends the View Account request from the employee
//	 * to customer
//	 */
//	@RequestMapping(value="/internal/requestTransactionAccess", method=RequestMethod.POST)
//	public ModelAndView sendViewRequests(@RequestParam("memberId") int memberId, HttpServletRequest request, HttpSession session)
//	{
//		boolean isUserNameAvailable = false;
//		int currentEmployeeId = (Integer)session.getAttribute("memberId");
//		int internalUserId = memberId;
//
//		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);	
//		List<RequestDTO> requestList = null; 
//		if (memberId<0)
//		{
//			logger.error("The member Id cannot be negative");
//			isUserNameAvailable=false;
//		}
//		else
//		{
//			isUserNameAvailable=true;
//		}
//		
//		if(isUserNameAvailable)
//		{		
//			
//			isRequestSent = employeeUserService.sendExtUserViewRequests(internalUserId, currentEmployeeId);
//			requestList = employeeUserService.getRequestList(currentEmployeeId);
//		}
//		else
//		{
//			
//			return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Invalid Member Id").addObject("requestList",requestList);
//		}
//		
//		if(isRequestSent)
//		{
//			return new ModelAndView("/internal/EmpRequestCustView").addObject("message","Request Sent to User").addObject("requestList",requestList);
//		}
//		else
//		{
//			return new ModelAndView("/internal/EmpRequestCustView").addObject("error","Request Failed").addObject("requestList",requestList);
//		}
//	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Maps the page that has the list of VIEW ACCOUNT requests which were approved by the 
	 * customers for 
	 */
	@RequestMapping(value="/internal/viewAccounts")
	public ModelAndView viewAccounts(HttpSession session)
	{	
		
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);
		List<RequestDTO> requestList= employeeUserService.getRequestList(currentEmployeeId);
		if(requestList!=null)
		{
			if(!requestList.isEmpty())
			{
				return new ModelAndView("/internal/intAccountManagement").addObject("requestList",requestList);
			}
			else
			{
				return new ModelAndView("/internal/intAccountManagement").addObject("requestList",requestList);
			}
				
		}
		else
		{
			return new ModelAndView("/internal/intAccountManagement");
		}
		
	}
	
	@RequestMapping(value = "/internal/viewUserAccounts", method = RequestMethod.POST)
	public ModelAndView sendRequestId(@ModelAttribute("viewsUserAccounts") @Valid RequestModel requestModel, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("/internal/customerAccount").addObject("error","Invalid Request");
		}
		else
		{
		int customerId = employeeUserService.getCustomerId(requestModel.getRequestId());
		UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(customerId);
//		long accountNo = customerDTO.getAccountDTOList().get(0).getAccountNo();
		long accountNo = employeeUserService.getAccountNo(customerId);
		return new ModelAndView("/internal/customerAccount").addObject("customerInfo",customerDTO).addObject("accountNo",accountNo);
		}
	}
	
//	@RequestMapping(value = "/internal/viewUserAccounts", method = RequestMethod.POST)
//	public ModelAndView sendRequestId(@RequestParam("requestId") long requestId, HttpServletRequest request, HttpSession session)
//	{
//		int customerId = employeeUserService.getCustomerId(requestId);
//		UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(customerId);
////		long accountNo = customerDTO.getAccountDTOList().get(0).getAccountNo();
//		long accountNo = employeeUserService.getAccountNo(customerId);
//		return new ModelAndView("/internal/customerAccount").addObject("customerInfo",customerDTO).addObject("accountNo",accountNo);
//	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/*
	 * To load page that sends transaction view requests
	 * 
	 */
	@RequestMapping(value = "/internal/sendViewTransactionRequests")
	public ModelAndView sendViewTransactionRequests(HttpSession session)
	{
		
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);
		List<RequestDTO> transactionRequestList= employeeUserService.getTransactionRequestList(currentEmployeeId);
		if(transactionRequestList!=null)
		{
			if(!transactionRequestList.isEmpty())
			{
				return new ModelAndView("/internal/empCustTransactionRequest").addObject("transactionRequestList",transactionRequestList);
			}
			else
			{
				return new ModelAndView("/internal/empCustTransactionRequest").addObject("transactionRequestList",transactionRequestList);
			}
				
		}
		else
		{
			return new ModelAndView("/internal/empCustTransctionRequest");
		}
	}
		
	/*
	 * To reload page once transactions have been submitted to show the updated list of requests
	 */
	@RequestMapping(value="/internal/requestTransaction", method=RequestMethod.POST)
	public ModelAndView sendTransactionRequests(@ModelAttribute("sendRequestForm") @Valid RequestModel requestModel, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("/internal/empCustTransactionRequest").addObject("error","Invalid Request");
		}
		else
		{
			
			boolean isUserNameAvailable = false;
			int currentEmployeeId = (Integer)session.getAttribute("memberId");
			int internalUserId = requestModel.getMemberId();
	
			//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);	
			List<RequestDTO> transactionRequestList = null; 
			if (requestModel.getMemberId()<0)
			{
				logger.error("The member Id cannot be negative");
				isUserNameAvailable=false;
			}
			else
			{
				isUserNameAvailable=true;
			}
			
			if(isUserNameAvailable)
			{		
				
				isRequestSent = employeeUserService.sendExtUserTransactionViewRequests(internalUserId, currentEmployeeId);
				transactionRequestList = employeeUserService.getTransactionRequestList(currentEmployeeId);
			}
			else
			{
				
				return new ModelAndView("/internal/empCustTransactionRequest").addObject("error","Invalid Member Id").addObject("transactionRequestList",transactionRequestList);
			}
			
			if(isRequestSent)
			{
				return new ModelAndView("/internal/empCustTransactionRequest").addObject("message","Request Sent to User").addObject("transactionRequestList",transactionRequestList);
			}
			else
			{
				return new ModelAndView("/internal/empCustTransactionRequest").addObject("error","Request Failed").addObject("transactionRequestList",transactionRequestList);
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(value = "/internal/viewTransactionRequests")
	public ModelAndView viewTransactionRequests(HttpSession session)
	{	
		
		int currentEmployeeId = (Integer)session.getAttribute("memberId");
		//UserDTO currentEmployeeDTO = manageExternalUserAccountService.displayUserAccount(currentEmployeeId);
		List<RequestDTO> requestList= employeeUserService.getTransactionRequestList(currentEmployeeId);
		if(requestList!=null)
		{
			if(!requestList.isEmpty())
			{
				return new ModelAndView("/internal/internalAccountTransaction").addObject("requestList",requestList);
			}
			else
			{
				return new ModelAndView("/internal/internalAccountTransaction").addObject("requestList",requestList);
			}
				
		}
		else
		{
			return new ModelAndView("/internal/internalAccountTransaction");
		}
		
	}
	
	@RequestMapping(value = "/internal/viewUserTransactions", method = RequestMethod.POST)
	public ModelAndView showTransactionsPage(@ModelAttribute("viewsUserTransactions") @Valid RequestModel requestModel, BindingResult result, HttpServletRequest request, HttpSession session)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("/internal/customerTransactionAccount").addObject("error", "Invalid Request");
		}
		else
		{
			int customerId = employeeUserService.getCustomerId(requestModel.getRequestId());
			UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(customerId);
			
			List<TransactionDTO> transactionInfo = employeeUserService.getAllTransactions(customerId);

			return new ModelAndView("/internal/customerTransactionAccount").addObject("transactionInfo",transactionInfo);
		}

	}
	
//	@RequestMapping(value = "/internal/viewUserTransactions", method = RequestMethod.POST)
//	public ModelAndView showTransactionsPage(@RequestParam("requestId") int requestId, HttpServletRequest request, HttpSession session)
//	{
//		int customerId = employeeUserService.getCustomerId(requestId);
//		UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(customerId);
//		
//		List<TransactionDTO> transactionInfo = employeeUserService.getAllTransactions(customerId);
//
//		return new ModelAndView("/internal/customerTransactionAccount").addObject("transactionInfo",transactionInfo);
//	}
	
	@RequestMapping(value="/internal/getMemberIdLog")
	public ModelAndView getMemberIdList(HttpSession session)
	{
		List<UserDTO> memberInfo = manageExternalUserAccountService.getMemberList();
		return new ModelAndView("/internal/memberIdLog").addObject("memberInfo", memberInfo);
	}
	
	@RequestMapping(value="/internal/transactionReview", method= RequestMethod.GET)
	public ModelAndView getReviewList()
	{
		List<TransactionReviewDTO> reviewList = employeeUserService.getTransactionReviewList();
		if(reviewList != null)
		{
			return new ModelAndView("/internal/processTransactionReview").addObject("reviewList", reviewList);
		}else
		{
			return new ModelAndView("/internal/processTransactionReview").addObject("error", "No Requests found");
		}
	}
	
	@RequestMapping(value="/internal/processTransReview", method= RequestMethod.POST)
	public ModelAndView processTransactionReview(@ModelAttribute("processTransReview") @Valid TransactionReviewModel reviewModel, BindingResult result, HttpSession session)
	{
		List<TransactionReviewDTO> reviewList = employeeUserService.getTransactionReviewList();
		int memberId = (Integer) session.getAttribute("memberId");
		if(result.hasErrors())
		{
			return new ModelAndView("/internal/processTransactionReview").addObject("reviewList", reviewList).addObject("error", "Invalid Input");
		}
		else
		{
			if(reviewModel.getProcessTxnReview().equals("approved"))
			{
				boolean isSuccess = employeeUserService.approveTransactionReview(reviewModel.getTransactionReviewId(), memberId);
				reviewList = employeeUserService.getTransactionReviewList();
				if(isSuccess)
				{
					return new ModelAndView("/internal/processTransactionReview").addObject("reviewList", reviewList).addObject("message", "Processed Successfully");
				}
			}else if(reviewModel.getProcessTxnReview().equals("declined"))
			{
				boolean isDeclined = employeeUserService.declineTransactionReview(reviewModel.getTransactionReviewId(), memberId);
				reviewList = employeeUserService.getTransactionReviewList();
				if(isDeclined)
				{
					return new ModelAndView("/internal/processTransactionReview").addObject("reviewList", reviewList).addObject("message", "Processed Successfully");
				}
			}
		}
		return new ModelAndView("/internal/processTransactionReview").addObject("reviewList", reviewList).addObject("error", "Error: Process Failed");
	}
	

}
