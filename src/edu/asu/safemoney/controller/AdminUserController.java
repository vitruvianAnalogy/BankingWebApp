package edu.asu.safemoney.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer.AmbiguousBindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.AdminAuthorizePaymentRequest;
import edu.asu.safemoney.model.EmpProcessPaymentModel;
import edu.asu.safemoney.model.RequestModel;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.AdminUserService;
import edu.asu.safemoney.service.EmployeeUserService;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

@Controller
@SessionAttributes
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private EmployeeUserService employeeUserService;
	
	@Autowired
	private ManageExternalUserAccountService manageExternalUserAccountService;
	
	@RequestMapping("/admin/extUserAccount")
	public ModelAndView getExternalUserAccountRequests()
	{	
		List<RequestDTO> requestList= adminUserService.getExterUserAccountRequests();
		return new ModelAndView("/admin/extAccountManagement").addObject("requestList", requestList);

	}
	
	@RequestMapping("/admin/UsersList")
	public ModelAndView getLegalUsersList(HttpSession session)
	{	
		List<UserDTO> externalUserList= manageExternalUserAccountService.getMemberList();
		List<UserDTO> internalUserList= employeeUserService.getInternalUserListForAdmin();
		return new ModelAndView("/admin/LegalUsers").addObject("externalUserList", externalUserList).addObject("internalUserList",internalUserList);

	}
	
	@RequestMapping(value="/admin/getEmp", method=RequestMethod.POST)
	public ModelAndView getEmployee(@RequestParam("memberId") int memberId){
		System.out.println("member Id: " + memberId);
		ModifyUserModel employeeDetails= adminUserService.getEmployee(memberId);
		System.out.println("Address 1 of the employee ********* " + employeeDetails.getAddress1());
		return new ModelAndView("admin/manageInternalUsers").addObject("employeeDetails", employeeDetails);
	}
	
	
	@RequestMapping("/admin/approveExtUserAccount")
	public ModelAndView approveExtUserAccountRequest(
			@RequestParam("requestId") long requestId,
			@RequestParam("requestType") String requestType, @RequestParam("adminAction") String adminAction) {
		ModelAndView mv = new ModelAndView("/admin/extAccountManagement");
		if(adminAction.equals("approve"))
		{
		boolean isApproved = adminUserService.approveExtUserRequest(requestId);
		List<RequestDTO> requestList = adminUserService
				.getExterUserAccountRequests();
		mv.addObject("requestList", requestList);
		if (requestType.equals("CREATE_ACCOUNT")) {
			if (isApproved) {
				mv.addObject("message",
						"Request has been Processed. Bank Account has been created for the user");
			} else {
				mv.addObject("error",
						"Sorry! The request could not be processed.");
			}
			return mv;
		}
		else if(requestType.equals("DELETE_ACCOUNT"))
		{
			if (isApproved) {
				mv.addObject("message",
						"Request has been Processed. The User Account has been deleted");
			} else {
				mv.addObject("error",
						"Sorry! The request could not be processed.");
			}
			return mv;
		}
		return mv;
		}
		else if(adminAction.equals("decline"))
		{
			boolean isDeclined = adminUserService.declineExtUserRequest(requestId);
			List<RequestDTO> requestList = adminUserService
					.getExterUserAccountRequests();
			mv.addObject("requestList", requestList);
			if (requestType.equals("CREATE_ACCOUNT")) {
				if (isDeclined) {
					mv.addObject("message",
							"Request has been Declined");
				} else {
					mv.addObject("error",
							"Sorry! The request could not be processed.");
				}
				return mv;
			}
			else if(requestType.equals("DELETE_ACCOUNT"))
			{
				if (isDeclined) {
					mv.addObject("message",
							"Request has been Declined");
				} else {
					mv.addObject("error",
							"Sorry! The request could not be processed.");
				}
				return mv;
			}
			return mv;
		}
		return mv;
	}
	
	@RequestMapping("/admin/homePage")
	public ModelAndView getAdminHome()
	{
		return new ModelAndView("/admin/home");
	}
	
	@RequestMapping("/admin/intUserAccount")
	public ModelAndView getInternalUserAccountRequests()
	{
		return new ModelAndView("/admin/intAccountManagement");
	}
	
	@RequestMapping("/admin/systemLogPage")
	public ModelAndView getSystemLogPage()
	{
		return new ModelAndView("/admin/viewSystemLog");
	}
	
	@RequestMapping("/admin/piiAuthorization")
	public ModelAndView getPiiAuthorizationPage()
	{
		List<UserDTO> userPII = manageExternalUserAccountService.getPIIAuthorizedUserAccounts();
		return new ModelAndView("/admin/viewPIIAuthorization").addObject("userPII", userPII);
	}
	
	@RequestMapping("/admin/transactionAuthorizationPage")
	public ModelAndView getTransactionAuthorizationPage()
	{
		return new ModelAndView("/admin/authorizeTransaction");
	}
	
	@RequestMapping("/admin/viewTransactionHistoryPage")
	public ModelAndView viewTransactionHistoryPage(HttpSession session)
	{
		return new ModelAndView("/admin/ExternalUserTransactions");
	}
	
	@RequestMapping(value="/admin/getTransactionHistoryForAdmin", method=RequestMethod.POST)
	public ModelAndView getTransactionHistoryForAdmin(@ModelAttribute("sendRequestForm") @Valid RequestModel requestModel, BindingResult result, HttpServletRequest request, HttpSession session)
	{		
		//UserDTO customerDTO = manageExternalUserAccountService.displayUserAccount(memberId);
		if(result.hasErrors())
		{
			return new ModelAndView("/admin/ExternalUserTransactions").addObject("error","Invalid Request");	
		}
		else
		{
			List<TransactionDTO> transactionInfo = employeeUserService.getAllTransactions(requestModel.getMemberId());
			return new ModelAndView("/admin/ExternalUserTransactions").addObject("transactionInfo",transactionInfo);				
		}
		

	}
	
	@RequestMapping("/admin/employeeRegistration")
	public ModelAndView employeeReigstration()
	{
		return new ModelAndView("/admin/manageInternalUsers");
	}
	
	
	@RequestMapping("admin/createEmployee")
	public ModelAndView createEmployee(@ModelAttribute("signUpForm") UserModel userModel){
		System.out.println("email" + userModel.getEmailId());
		boolean created= adminUserService.createEmployee(userModel);
		if(!created)
		{
			return new ModelAndView("admin/manageInternalUsers").addObject("signUpForm", userModel);
		}
		else
		{
			return new ModelAndView("shared/landing");
		}
			
	}
	//authorizeCriticalTransactions
	
	@RequestMapping("/admin/authorizeCriticalTransactions")
	public ModelAndView authorizeCriticalTransactions(HttpSession session)
	{

		List<TransactionDTO> transactionList = adminUserService
				.getTransactionRequest();
		
		return new ModelAndView("/admin/authorizeTransaction").addObject(
						"transactionRequestList", transactionList);
		
	}
	//AdminAuthorizePaymentRequest
	@RequestMapping(value = "/admin/authorizePaymentRequest", method = RequestMethod.POST)
	public ModelAndView processTransaction(
			@ModelAttribute("authorizePaymentRequest") @Valid AdminAuthorizePaymentRequest adminAuthorizePaymentRequest,
			BindingResult validateResult,
			
			HttpSession session) {
		
		// //////////////////////modified by fei
		String processResult = null;
		long transactionRequestId = adminAuthorizePaymentRequest.getTransactionRequestId();
		String manageAction = adminAuthorizePaymentRequest.getManageTransactionAction();
		
		if(validateResult.hasErrors())
		{
			List<TransactionDTO> transactionList = adminUserService
					.getTransactionRequest();
			System.out.println(processResult);
			return new ModelAndView("/admin/authorizeTransaction").addObject(
					"transactionRequestList", transactionList).addObject("error",
					"Invalid input(s) detected in the process transaction section");
		}
		
		if (manageAction.equals("approved")) {
			String myresult = employeeUserService.updateTransactionRequest(
					transactionRequestId, "APPROVED_BANK");
			
			if(myresult.equals("NOTFOUND"))
			{
				List<TransactionDTO> transactionList = adminUserService
						.getTransactionRequest();
				System.out.println(processResult);
				return new ModelAndView("/admin/authorizeTransaction").addObject(
						"transactionRequestList", transactionList).addObject("error",
						"Invalid transactionRequestId ");
			}

			TransactionDTO transactiontDTO = employeeUserService
					.getTransactionDTOById(transactionRequestId);

			int toMemberId = employeeUserService
					.getMemberIdByAccount(transactiontDTO.getToAccount());
			int fromMemberId = employeeUserService
					.getMemberIdByAccount(transactiontDTO.getFromAccount());

			// transactiontDTO.getf
			boolean myresult2 = false;
			String type = transactiontDTO.getTransactionType();
			if (type.equals("Debit")) {

				myresult2 = true;
			} else {
				myresult2 = employeeUserService.makeCredit(toMemberId,
						transactiontDTO.getAmount());
			}

			if ((myresult.equals("success")) && myresult2)
				processResult = "You have successfully approved one transaction";
			else
				processResult = "Failed";

		} else if (manageAction.equals("declined")) {
			String myresult = employeeUserService.updateTransactionRequest(
					transactionRequestId, "DECLINED_BANK");
			
			if(myresult.equals("NOTFOUND"))
			{
				List<TransactionDTO> transactionList = adminUserService
						.getTransactionRequest();
				System.out.println(processResult);
				return new ModelAndView("/admin/authorizeTransaction").addObject(
						"transactionRequestList", transactionList).addObject("error",
						"Invalid transactionRequestId ");
			}

			TransactionDTO transactiontDTO = employeeUserService
					.getTransactionDTOById(transactionRequestId);

			int fromMemberId = employeeUserService
					.getMemberIdByAccount(transactiontDTO.getFromAccount());
			boolean myresult2 = false;

			// transactiontDTO.getf
			String type = transactiontDTO.getTransactionType();
			if (type.equals("Debit")) {
				myresult2 = employeeUserService.makeCredit(fromMemberId,
						transactiontDTO.getAmount());
			} else {
				myresult2 = employeeUserService.makeCredit(fromMemberId,
						transactiontDTO.getAmount());
			}

			if ((myresult.equals("success")) && myresult2)
				processResult = "You have successfully declined one transaction";
			else
				processResult = "Failed";

		}
		
		 else{
			 List<TransactionDTO> transactionList = adminUserService
						.getTransactionRequest();
				System.out.println(processResult);
				return new ModelAndView("/admin/authorizeTransaction").addObject(
						"transactionRequestList", transactionList).addObject("error",
						"Invalid process action value");
			}

		List<TransactionDTO> transactionList = adminUserService
				.getTransactionRequest();
		System.out.println(processResult);

		return new ModelAndView("/admin/authorizeTransaction").addObject(
				"transactionRequestList", transactionList).addObject("message",
				processResult);

	}
	
	@RequestMapping("/admin/systemLog")
	public ModelAndView getSystemLog()
	{
		return new ModelAndView("/admin/viewSystemLog");
	}
	
}

