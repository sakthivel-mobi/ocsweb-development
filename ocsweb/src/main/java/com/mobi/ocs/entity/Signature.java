package com.mobi.ocs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity()
@Table(name = "signature")
public class Signature {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String username;
	String imageUrl;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	Date updatedAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;

	public Signature(String username, String imageUrl) {
		this.username = username;
		this.imageUrl = imageUrl;
	}

	public Signature() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Signature [id=" + id + ", username=" + username + ", imageUrl=" + imageUrl + ", updatedAt=" + updatedAt
				+ ", createdAt=" + createdAt + " ]";
	}

}
