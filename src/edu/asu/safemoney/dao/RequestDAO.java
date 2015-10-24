package edu.asu.safemoney.dao;

import java.util.List;

import edu.asu.safemoney.dto.RequestDTO;
import edu.asu.safemoney.dto.UserDTO;

public interface RequestDAO {
	
	public boolean generateRequest(RequestDTO requestDTO);
	
	public boolean createRequest(UserDTO userDTO);
	
	public List<RequestDTO> getRequestsForAdminUser();
	
	public RequestDTO getRequestByRequestId(long requestId);
	
	public boolean updateRequest(RequestDTO requestDTO);

}
