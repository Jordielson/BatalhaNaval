package modelo;

import java.time.LocalDateTime;

public class Pontuacao {
	private LocalDateTime data;
	private int pontuacao;
	
	public Pontuacao(LocalDateTime data, int pontuacao) {
		super();
		this.data = data;
		this.pontuacao = pontuacao;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
}
