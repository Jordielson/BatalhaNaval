package exceções;

public class SenhaInvalidaException extends Exception {
	private static final long serialVersionUID = 1L;
	public SenhaInvalidaException() {
		super("Senha inválida! A senha deve ter pelo menos 6 letras.");
	}
}
