package excecoes;

public class EmailInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmailInvalidoException() {
		super("E-mail inválido!");
	}
	
}
