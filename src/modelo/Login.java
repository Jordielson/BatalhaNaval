package modelo;

import excecoes.EmailInvalidoException;
import excecoes.SenhaInvalidaException;

public class Login {
	private CentralDeInformacoes central;
	private Jogador jogador;
	private Persistencia persistencia = new Persistencia();
	
	public Login() {
		central = persistencia.recuperarCentral("central.xml");
	}
	public Jogo login(String email, String senha) throws EmailInvalidoException, SenhaInvalidaException {
		this.validarEmail(email);
		this.validarSenha(senha);		
		
		return new Jogo(jogador, central);
	}
	
	public void validarEmail(String email) throws EmailInvalidoException {
		jogador = central.recuperarJogador(email);
		if(jogador == null) {
			throw new EmailInvalidoException();
		}
	}
	
	public void validarSenha(String senha) throws SenhaInvalidaException {
		if(! jogador.getSenha().equals(senha)) {
			throw new SenhaInvalidaException();
		}
	}
	
	public void salvar() {
		persistencia.salvarCentral(central, "central.xml");
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	
	public CentralDeInformacoes getCentral() {
		return central;
	}
}
