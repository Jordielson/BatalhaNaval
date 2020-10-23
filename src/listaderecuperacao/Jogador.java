package listaderecuperacao;

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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
	   this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
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
