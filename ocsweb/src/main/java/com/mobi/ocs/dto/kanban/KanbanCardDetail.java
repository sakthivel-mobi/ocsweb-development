package com.mobi.ocs.dto.kanban;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KanbanCardDetail {
	
	private String boardTitle;
	private String boardPublicId;
	private String columnId;
	private String columnTitle;
	private String columnState;
	private String owner;
	
	
	private String number;
	private String title;
	private String description;
	
	private List<KanbanCustomFields> customFields;

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardPublicId() {
		return boardPublicId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setBoardPublicId(String boardPublicId) {
		this.boardPublicId = boardPublicId;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}

	public String getColumnState() {
		return columnState;
	}

	public void setColumnState(String columnState) {
		this.columnState = columnState;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<KanbanCustomFields> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<KanbanCustomFields> customFields) {
		this.customFields = customFields;
	}

	@Override
	public String toString() {
		return "KanbanCardDetail [boardTitle=" + boardTitle + ", boardPublicId=" + boardPublicId + ", columnId="
				+ columnId + ", columnTitle=" + columnTitle + ", columnState=" + columnState + ", owner=" + owner
				+ ", number=" + number + ", title=" + title + ", description=" + description + ", customFields="
				+ customFields + "]";
	}
	
	
	

}

