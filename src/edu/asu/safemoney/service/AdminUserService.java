package edu.asu.safemoney.service;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.model.ModifyUserModel;
import edu.asu.safemoney.model.UserModel;

public interface AdminUserService {
	
	public List<RequestDTO> getExterUserAccountRequests();
	
	public boolean generateBankAccount(int memberId);
	
	public boolean approveExtUserRequest(long requestId);
	
	public boolean declineExtUserRequest(long requestId);
	
	public boolean deleteExtUserAccount(int memberId);
	
	public List<TransactionDTO> getTransactionRequest();

	public boolean createEmployee(UserModel userModel);
	
	public String sendWithAttachment(String userName, String path);
	
	public ModifyUserModel getEmployee(int memberId);
	


}