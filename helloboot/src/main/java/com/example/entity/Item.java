package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * ToDoひとつのアイテムを表すエンティティ
 */
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String content;

}
