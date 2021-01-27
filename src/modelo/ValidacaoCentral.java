package modelo;

import excecoes.*;

public class ValidacaoCentral {
	public void validarEmail(String email) throws EmailInvalidoException {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(email.matches(regex) == false) {
			EmailInvalidoException eie = new EmailInvalidoException();
			throw eie;
		}
	}

	public void validarSenha(String senha) throws SenhaInvalidaException {
		if(senha == null || senha.length() < 6)
			throw new SenhaInvalidaException();
		
	}
	public void validarApelido(String apelido) throws ApelidoInvalidoException {
		if(apelido == null || apelido.length() < 4)
			throw new ApelidoInvalidoException();
	}
	
	public void validarUsuario(CentralDeInformacoes central, Jogador jogador) throws PessoaDuplicadaException {
		for (Jogador j: central.getTodosOsJogadores()) {
			if(jogador.getEmail().equals(j.getEmail())) {
				throw new PessoaDuplicadaException("E-mail");
			}
			if(jogador.getApelido().equals(j.getApelido()))
				throw new PessoaDuplicadaException("Apelido");
		}
	}
}
