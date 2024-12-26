package br.com.icc.medapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NegocioException extends ResponseStatusException {
	
	private static final long serialVersionUID = 1L;

	public NegocioException(String mensagem) {
		this(HttpStatus.NOT_FOUND , mensagem);
	}

	public NegocioException(HttpStatusCode status, String reason) {
		super(status, reason);
		
	}

}
