/**
 * 
 */
package com.walmart.densify.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * @author m0n00qb
 *
 * 
 *         CREATE TABLE `cost` ( `id` int NOT NULL AUTO_INCREMENT, `team_id` int
 *         DEFAULT NULL, `mls` float DEFAULT NULL , `cassandra` float DEFAULT
 *         NULL , `wcnp` float DEFAULT NULL , `ingestion` float DEFAULT NULL ,
 *         `sfnsf` float DEFAULT NULL , `year` int not null, `month` varchar(10)
 *         not null, PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT
 *         CHARSET=utf8mb3;
 * 
 * 
 */
@Entity
@Table(name = "cost")
@Setter
@Getter
public class Cost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Team team;
	private double mls;
	private double cassandra;
	private double wcnp;
	private double ingestion;
	private double sfnsf;
	private int year;
	private String month;
	private int ingestionRate;
	
	@Transient
	private double cost;

}
