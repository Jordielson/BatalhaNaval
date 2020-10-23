package listaderecuperacao;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidarDadosDoJogador {
	private Jogador jogador;
	
	public ValidarDadosDoJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public boolean validarDados() {
		if(validarEmail() == false || validarApelido() == false || validarSenha() == false)
			return false;
		return true;
	}
	
	public boolean validarEmail() {
		 try {
		        InternetAddress emailAddr = new InternetAddress(jogador.getEmail());
		        emailAddr.validate();
		        return true;
		    } catch (AddressException ex) {
		        return false;
		    }
	}
	
	public boolean validarApelido() {
		return jogador.getApelido().length() >= 4;
	}
	
	public boolean validarSenha() {
		return jogador.getSenha().length() > 5;		
	}
}
