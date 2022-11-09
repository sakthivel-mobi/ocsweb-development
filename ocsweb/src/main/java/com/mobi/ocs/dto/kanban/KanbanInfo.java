package com.mobi.ocs.dto.kanban;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobi.ocs.dto.Property;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KanbanInfo {
	
	private List<KanbanCard> cards;
	private String test;

	public List<KanbanCard> getCards() {
		return cards;
	}

	public void setCards(List<KanbanCard> cards) {
		this.cards = cards;
	}
	

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "KanbanInfo [cards=" + cards + "]";
	}
	
	
	
	
	
	

}
