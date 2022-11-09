package com.mobi.ocs.dto.kanban;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class KanbanCard {
	
	private String _id;
	private KanbanCardDetail CardItem;
	
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}

	
	
	public KanbanCardDetail getCardItem() {
		return CardItem;
	}
	@JsonSetter("CardItem")
	public void setCardItem(KanbanCardDetail CardItem) {
		this.CardItem = CardItem;
	}
	@Override
	public String toString() {
		return "KanbanCard [_id=" + _id + ", CardItem=" + CardItem + "]";
	}
	

}
