package edu.asu.safemoney.dao.impln;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.asu.safemoney.dao.AdminUserDAO;
import edu.asu.safemoney.dto.TransactionDTO;

@Repository
public class AdminUserDAOImpl implements AdminUserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<TransactionDTO> getTransactionRequest() {
		// TODO Auto-generated method stub
		List<TransactionDTO> transactionRequest = new ArrayList<TransactionDTO>();

		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("TransactionDTO.findByStatus")
				.setString("status", "PENDING_BANK");
		List requests = query.list();
		if (requests != null) {
			for (Object request : requests) {
				TransactionDTO transactionRequestDTO = (TransactionDTO) request;
				if(transactionRequestDTO.getIsCritical())
				{
					transactionRequest.add(transactionRequestDTO);
				}
			}
			System.out.println("Found some transactions");
		}

		return transactionRequest;
	}

}
