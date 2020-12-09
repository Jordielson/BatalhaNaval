package modelo;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

import imagens.Users;

public class Jogador {
	private String apelido;
	private String email;
	private String senha;
	private int quantidadeDeVitorias;
	private int quantidadeDeDerrotas;
	private int pontuacao;
	private URL icon;
	private ArrayList<Jogador> jogadoresProibidos = new ArrayList<Jogador>();
	private ArrayList<Partida> historico = new ArrayList<Partida>();
	private ArrayList<Pontuacao> historicoPontuacao = new ArrayList<Pontuacao>();
	private Frota frota = new Frota();

	public Jogador(String apelido, String email, String senha, int quantidadeDeVitorias) {
		this.setApelido(apelido);
		this.setEmail(email);
		this.setSenha(senha);
		this.setQuantidadeDeVitorias(quantidadeDeVitorias);
		jogadoresProibidos.add(this);
		icon = Users.obterIcon();
	}
	
	public void adicionarPartida(Partida partida) {
		historico.add(0, partida);
		if(partida.getJogador().equals(this)) {
			jogadoresProibidos.add(partida.getAdversario());
			if(partida.getVencedor().equals(this)) {
				quantidadeDeVitorias++;
				pontuacao += 10;
			} else {
				quantidadeDeDerrotas++;
				pontuacao -= 5;
			}			
			historicoPontuacao.add(0, new Pontuacao(LocalDateTime.now(), pontuacao));
		} else {
			if(partida.getVencedor().equals(this)) {
				pontuacao += 2;
				historicoPontuacao.add(0, new Pontuacao(LocalDateTime.now(), pontuacao));
			}
		}
	}
	
	@Override
	public String toString() {
		return apelido;
	}
	
	public boolean verificarJogador(Jogador j) {
		int i = jogadoresProibidos.indexOf(j);
		if(i >= 0)
			return false;
		return true;			
	}
	
	public void removarJogador(Jogador j) {
		jogadoresProibidos.remove(j);
	}

	public String getApelido() {
		return apelido;
	}
	
	public ArrayList<Pontuacao> getHistoricoPontuacao() {
		return historicoPontuacao;
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

	public Frota getFrota() {
		return frota;
	}

	public void setMapa(Frota frota) {
		this.frota = frota;
	}

	public int getQuantidadeDeDerrotas() {
		return quantidadeDeDerrotas;
	}

	public int getPontuacao() {
		return pontuacao;
	}
	
	public ArrayList<Partida> getHistorico() {
		return historico;
	}
	
	public URL getIcon() {
		return icon;
	}
}
