package com.revature.models;

import java.sql.Timestamp;
import java.util.Arrays;

/*
 * CREATE TABLE IF NOT EXISTS ers_reimbursement(
	reimb_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	reimb_amount NUMERIC NOT NULL,
	reimb_submitted timestamptz DEFAULT now(),
	reimb_resolved timestamptz,
	reimb_description varchar(250),
	reimb_receipt bytea,
	reimb_author int REFERENCES ers_users(ers_users_id) NOT NULL,
	reimb_resolver int REFERENCES ers_users(ers_users_id),
	reimb_status_id int REFERENCES ers_reimbursement_status(reimb_status_id) NOT NULL,
	reimb_type_id int REFERENCES ers_reimbursement_type(reimb_type_id) NOT NULL	
);
 */

public class Reimbursement {

	private int id;
	private double amount;
	private Timestamp timeSubmitted;
	private Timestamp timeResolved;
	private String description;
	private byte[] receipt;
	private int authorId;
	private int resolverId;
	private int statusId;
	private int typeId;
	
	public Reimbursement() {
		
	}

	public Reimbursement(double amount, int authorId, int typeId) {
		super();
		this.amount = amount;
		this.authorId = authorId;
		this.typeId = typeId;
	}
	
	public Reimbursement(int id, double amount, Timestamp timeSubmitted, 
			Timestamp timeResolved, String description, byte[] receipt, 
			int authorId, int resolverId, int statusId, int typeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public Timestamp getTimeResolved() {
		return timeResolved;
	}

	public void setTimeResolved(Timestamp timeResolved) {
		this.timeResolved = timeResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", timeSubmitted=" + timeSubmitted + ", timeResolved="
				+ timeResolved + ", description=" + description + ", receipt=" + Arrays.toString(receipt)
				+ ", authorId=" + authorId + ", resolverId=" + resolverId + ", statusId=" + statusId + ", typeId="
				+ typeId + "]";
	}
	
}
