package com.mobi.ocs.modal;

public class RollbackRequestData {
	
	private String rollbackStage;
	private String rollbackReason;
	
	
	public RollbackRequestData() {

	}


	public String getRollbackStage() {
		return rollbackStage;
	}


	public void setRollbackStage(String rollbackStage) {
		this.rollbackStage = rollbackStage;
	}


	public String getRollbackReason() {
		return rollbackReason;
	}


	public void setRollbackReason(String rollbackReason) {
		this.rollbackReason = rollbackReason;
	}


	@Override
	public String toString() {
		return "RollbackRequestData [rollbackStage=" + rollbackStage + ", rollbackReason=" + rollbackReason + "]";
	}

	
	
}
