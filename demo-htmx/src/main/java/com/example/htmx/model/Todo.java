package com.example.htmx.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "title é obrigatorio")
	@Length(max = 100, message = "title deve te no maximo 100 caracteres")
	private String title;

	@Length(max = 250, message = "description deve ter ate 250 caracteres")
	private String description;

	private Boolean completed;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private OffsetDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
