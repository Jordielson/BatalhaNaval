package exceções;

public class ApelidoInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ApelidoInvalidoException() {
		super("Apelido inválido! O apelido deve ter pelo menos 4 letras.");	
	}
}
