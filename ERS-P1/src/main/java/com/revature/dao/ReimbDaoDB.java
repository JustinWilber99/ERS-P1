package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbDaoDB implements ReimbDao {

	private ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public boolean createReimbursement(Reimbursement reimb) {
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "insert into ers_reimbursement (reimb_amount, " 
					   + "reimb_author, reimb_type_id, reimb_description) " 
				       + "values (?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, reimb.getAmount());
			ps.setInt(2, reimb.getAuthorId());
			ps.setInt(3, reimb.getTypeId());
			ps.setString(4, reimb.getDescription());
			
			ps.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Reimbursement getReimbursementById(int reimbId) {
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_reimbursement where reimb_id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, reimbId);
			
			ResultSet rs = ps.executeQuery();
			
			Reimbursement reimb = new Reimbursement();
			while(rs.next()) {
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getDouble(2));
				reimb.setTimeSubmitted(rs.getTimestamp(3));
				reimb.setTimeResolved(rs.getTimestamp(4));
				reimb.setDescription(rs.getString(5));
				reimb.setReceipt(rs.getBytes(6));
				reimb.setAuthorId(rs.getInt(7));
				reimb.setResolverId(rs.getInt(8));
				reimb.setStatusId(rs.getInt(9));
				reimb.setTypeId(rs.getInt(10));
			}
			return reimb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_reimbursement";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp timeSubmitted = rs.getTimestamp(3);
				Timestamp timeResolved = rs.getTimestamp(4);
				String description = rs.getString(5);
				byte[] receipt = rs.getBytes(6);
				int authorId = rs.getInt(7);
				int resolverId = rs.getInt(8);
				int statusId = rs.getInt(9);
				int typeId = rs.getInt(10);
				
				reimbursements.add(new Reimbursement(reimbId, amount, timeSubmitted, timeResolved, description, receipt, authorId, resolverId, statusId, typeId));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursementsBy(User user) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_reimbursement where reimb_author=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp timeSubmitted = rs.getTimestamp(3);
				Timestamp timeResolved = rs.getTimestamp(4);
				String description = rs.getString(5);
				byte[] receipt = rs.getBytes(6);
				int authorId = rs.getInt(7);
				int resolverId = rs.getInt(8);
				int statusId = rs.getInt(9);
				int typeId = rs.getInt(10);
				
				reimbursements.add(new Reimbursement(reimbId, amount, timeSubmitted, timeResolved, description, receipt, authorId, resolverId, statusId, typeId));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getPendingReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_reimbursement where reimb_status_id = 1";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp timeSubmitted = rs.getTimestamp(3);
				Timestamp timeResolved = rs.getTimestamp(4);
				String description = rs.getString(5);
				byte[] receipt = rs.getBytes(6);
				int authorId = rs.getInt(7);
				int resolverId = rs.getInt(8);
				int statusId = rs.getInt(9);
				int typeId = rs.getInt(10);
				
				reimbursements.add(new Reimbursement(reimbId, amount, timeSubmitted, timeResolved, description, receipt, authorId, resolverId, statusId, typeId));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getResolvedReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_reimbursement where reimb_status_id != 1";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp timeSubmitted = rs.getTimestamp(3);
				Timestamp timeResolved = rs.getTimestamp(4);
				String description = rs.getString(5);
				byte[] receipt = rs.getBytes(6);
				int authorId = rs.getInt(7);
				int resolverId = rs.getInt(8);
				int statusId = rs.getInt(9);
				int typeId = rs.getInt(10);
				
				reimbursements.add(new Reimbursement(reimbId, amount, timeSubmitted, timeResolved, description, receipt, authorId, resolverId, statusId, typeId));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getPendingReimbursementsBy(User user) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_reimbursement where (reimb_author=?) AND (reimb_status_id = 1)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp timeSubmitted = rs.getTimestamp(3);
				Timestamp timeResolved = rs.getTimestamp(4);
				String description = rs.getString(5);
				byte[] receipt = rs.getBytes(6);
				int authorId = rs.getInt(7);
				int resolverId = rs.getInt(8);
				int statusId = rs.getInt(9);
				int typeId = rs.getInt(10);
				
				reimbursements.add(new Reimbursement(reimbId, amount, timeSubmitted, timeResolved, description, receipt, authorId, resolverId, statusId, typeId));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getResolvedReimbursementsBy(User user) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_reimbursement where (reimb_author=?) AND (reimb_status_id != 1)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int reimbId = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp timeSubmitted = rs.getTimestamp(3);
				Timestamp timeResolved = rs.getTimestamp(4);
				String description = rs.getString(5);
				byte[] receipt = rs.getBytes(6);
				int authorId = rs.getInt(7);
				int resolverId = rs.getInt(8);
				int statusId = rs.getInt(9);
				int typeId = rs.getInt(10);
				
				reimbursements.add(new Reimbursement(reimbId, amount, timeSubmitted, timeResolved, description, receipt, authorId, resolverId, statusId, typeId));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public boolean updateReimbursement(Reimbursement reimb) {
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "update ers_reimbursement set reimb_amount=?, " 
					+ "reimb_submitted=?, reimb_resolved=?, " 
					+ "reimb_description=?, reimb_receipt=?, reimb_author=?, " 
					+ "reimb_resolver=?, reimb_status_id=?, reimb_type_id=? " 
					+ "where reimb_id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, reimb.getAmount());
			ps.setTimestamp(2, reimb.getTimeSubmitted());
			ps.setTimestamp(3, reimb.getTimeResolved());
			ps.setString(4, reimb.getDescription());
			ps.setBytes(5, reimb.getReceipt());
			ps.setInt(6, reimb.getAuthorId());
			ps.setInt(7, reimb.getResolverId());
			ps.setInt(8, reimb.getStatusId());
			ps.setInt(9, reimb.getTypeId());
			ps.setInt(10, reimb.getId());
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteReimbursement(Reimbursement reimb) {
		try {
			
			Connection con = conUtil.getConnection();
			
			String sql = "delete from ers_reimbursement where reimb_id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, reimb.getId());
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
