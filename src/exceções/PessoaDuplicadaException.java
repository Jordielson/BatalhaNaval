package exceções;

public class PessoaDuplicadaException extends Exception {
	private static final long serialVersionUID = 1L;
	public PessoaDuplicadaException(String s) {
		super(s + " já existe no sistema");
	}
}
