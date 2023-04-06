package com.project.customexpection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object getResourceField() {
		return resourceField;
	}

	public void setResourceField(Object resourceField) {
		this.resourceField = resourceField;
	}

	private Object resourceField;

	public ResourceNotFoundException(Object resourceField) {
		super();
		this.resourceField = resourceField;
	}

}
