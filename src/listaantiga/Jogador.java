package listaantiga;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Jogador {
	private String apelido;
	private String email;
	private String senha;
	private int quantidadeDeVitorias;
	private Mapa mapa = new Mapa();

	public Jogador(String apelido, String email, String senha, int quantidadeDeVitorias) {
		this.setApelido(apelido);
		this.setEmail(email);
		this.setSenha(senha);
		this.setQuantidadeDeVitorias(quantidadeDeVitorias);
	}

	@Override
	public String toString() {
		return "Jogador [apelido=" + apelido + ", email=" + email + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		Jogador j = (Jogador) obj;
		return this.email.equals(j.email);
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		if (apelido.length() >= 4)
			this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
	    try {
	        InternetAddress emailAddr = new InternetAddress(email);
	        emailAddr.validate();
	        this.email = email;
	    } catch (AddressException ex) {
//	        ex.printStackTrace();
	    }
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if(senha.length() > 5)
			this.senha = senha;
	}

	public int getQuantidadeDeVitorias() {
		return quantidadeDeVitorias;
	}

	public void setQuantidadeDeVitorias(int quantidadeDeVitorias) {
		this.quantidadeDeVitorias = quantidadeDeVitorias;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
}
