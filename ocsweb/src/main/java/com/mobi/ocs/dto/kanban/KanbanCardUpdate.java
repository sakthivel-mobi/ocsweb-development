package com.mobi.ocs.dto.kanban;

import java.util.List;

public class KanbanCardUpdate {
	
	private String board;
	private List<KanbanCustomFields> customFields;
	
	
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public List<KanbanCustomFields> getCustomFields() {
		return customFields;
	}
	public void setCustomFields(List<KanbanCustomFields> customFields) {
		this.customFields = customFields;
	}
	@Override
	public String toString() {
		return "KanbanCardUpdate [board=" + board + ", customFields=" + customFields + "]";
	}
	
	
	

}
