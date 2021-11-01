package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimbDao {

	boolean createReimbursement(Reimbursement reimb);
	
	Reimbursement getReimbursementById(int reimbId);
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getPendingReimbursementsBy(User user);
	List<Reimbursement> getResolvedReimbursementsBy(User user);
	List<Reimbursement> getPendingReimbursements();
	List<Reimbursement> getResolvedReimbursements();
	List<Reimbursement> getAllReimbursementsBy(User user);
	

	boolean updateReimbursement(Reimbursement reimb);
	
	boolean deleteReimbursement(Reimbursement reimb);
	
}
