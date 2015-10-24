package edu.asu.safemoney.dao;

import java.util.List;

import edu.asu.safemoney.dto.TransactionDTO;

public interface AdminUserDAO {
	
	public List<TransactionDTO> getTransactionRequest();

}
