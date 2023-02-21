/**
 * 
 */
package com.walmart.densify.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * @author m0n00qb
 *
 * 
 *         CREATE TABLE `team` ( `id` int NOT NULL AUTO_INCREMENT, `name`
 *         varchar(30) DEFAULT NULL, `onboarding-page-URL` varchar(100) NOT
 *         NULL, `availability-URL` varchar(100) NOT NULL, `golden-signal-URL`
 *         varchar(100) NOT NULL, `availability` tinyint not null,
 *         `golden-signal` tinyint not null, `ut` int not NULL, `it` int not
 *         NULL, `more` varchar(100) NOT NULL, PRIMARY KEY (`id`) )
 *         ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
 * 
 * 
 */
@Entity
@Table(name = "team")
@Setter
@Getter
public class Team {

	public Team() {
		name = "";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String onboardingPageUrl;
	private boolean availability;
	private boolean goldenSignal;
	//
	private String availabilityUrl;
	private String goldenSignalUrl;
	private int ut;
	private int it;
	private String more;
	
	@Transient
	private double cost;

}
