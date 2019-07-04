package br.com.codigolivre.cursoudemymc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	//repassa a mensagem para a super classe RunTimeException.
	public ObjectNotFoundException(String msg) {
		super(msg);

	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
