/**
 * 
 */
package com.walmart.densify.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author m0n00qb
 *
 */
@Setter
@Getter
public class RestResponse {

	private String message;
	private ResponseType responseType;
	private long id;

	public enum ResponseType {
		SUCCESS,FAILURE,INFO;
	}
}
