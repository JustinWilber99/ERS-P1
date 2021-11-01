package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbService {

	private ReimbDao rDao;
	
	public ReimbService(ReimbDao dao) {
		this.rDao = dao;
	}
	
	public boolean submitReimbursement(Reimbursement reimb) {
		boolean created = false;
		created = rDao.createReimbursement(reimb);
		return created;
	}
	
	public List<Reimbursement> viewAllResolvedReimbursements() {
		List<Reimbursement> reimbursements = rDao.getResolvedReimbursements();
		return reimbursements;
	}
	
	public List<Reimbursement> viewAllReimbursementsBy(User u) {
		List<Reimbursement> reimbursements = rDao.getAllReimbursementsBy(u);
		return reimbursements;
	}
	
	public List<Reimbursement> viewAllPendingReimbursements() {
		List<Reimbursement> reimbursements = rDao.getPendingReimbursements();
		return reimbursements;
	}
	
	public List<Reimbursement> viewPendingReimbursementsByUser(User user) {
		List<Reimbursement> reimbursements = rDao.getPendingReimbursementsBy(user);
		return reimbursements;
	}
	
	public List<Reimbursement> viewResolvedReimbursementsByUser(User user) {
		List<Reimbursement> reimbursements = rDao.getResolvedReimbursementsBy(user);
		return reimbursements;
	}
	
	public boolean resolveReimbursement(Reimbursement reimb) {
		return rDao.updateReimbursement(reimb);
	}
	
	
	
}
