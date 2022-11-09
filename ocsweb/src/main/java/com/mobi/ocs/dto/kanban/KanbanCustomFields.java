package com.mobi.ocs.dto.kanban;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KanbanCustomFields {
	
	
	private String label;
	private String value;
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "KanbanCustomFields [label=" + label + ", value=" + value + "]";
	} 	
	
	
	    	
}
