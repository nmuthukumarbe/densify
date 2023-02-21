/**
 * 
 */
package com.walmart.densify.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author m0n00qb
 *
 *         CREATE TABLE app_user ( id int NOT NULL AUTO_INCREMENT, name
 *         varchar(30) DEFAULT NULL, mobile varchar(20) NOT NULL, password
 *         varchar(100) NOT NULL, cmp_id int NOT NULL, updated_at timestamp NULL
 *         DEFAULT NULL, created_at timestamp NULL DEFAULT NULL, PRIMARY KEY
 *         (id), UNIQUE(mobile,cmp_id), role int NOT NULL );
 * 
 */
@Entity
@Table(name = "app_user")
@Setter
@Getter
public class AppUser {

	public AppUser() {
		name = "";
		mobile = "";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String mobile;
	private String password;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date updatedAt;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date createdAt;

}
