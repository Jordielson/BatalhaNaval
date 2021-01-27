package modelo;

import java.time.LocalDate;

import excecoes.PosicaoInvalidaException;

public class Partida {
	private Jogador jogador;
	private Mapa mapaJogador;
	private Jogador adversario;
	private Mapa mapaAdversario;
	private Jogador vencedor = null;
	private StringBuilder relatorio = new StringBuilder();
	private LocalDate data;
	private Bot bot = new BotMedio();
	
	public Partida(Jogador jogador, Jogador adversario) {
		this.jogador = jogador;
		this.adversario = adversario;
	}
	
	public void detalharPartida() {
		mapaAdversario.setContar(0);
		mapaJogador.setContar(0);
	}
	
	public void salvarNoRelatorio(String s) {
		relatorio.append(s + "\n");
	}
	
	public void start() throws PosicaoInvalidaException {
		mapaJogador = new Mapa(jogador.getFrota());
		mapaAdversario = new Mapa(adversario.getFrota());
		this.salvarNoRelatorio(jogador.getApelido() + "  X  " + adversario.getApelido());
	}
	
	public int ataqueJogador(int posicao) {
		return this.ataque(jogador.getApelido(), mapaAdversario, posicao);
	}
	
	public void finalizarPartida() {
		this.salvarNoRelatorio("\n  Mapa de " + jogador.getApelido());
		this.salvarNoRelatorio(mapaJogador.toString());
		this.salvarNoRelatorio("  Mapa de " + adversario.getApelido());
		this.salvarNoRelatorio(mapaAdversario.toString());
		
		this.salvarNoRelatorio("Fim de Jogo!!!");
		this.salvarNoRelatorio("Vencedor: " + vencedor.getApelido());
		data = LocalDate.now();
		jogador.adicionarPartida(this);
		adversario.adicionarPartida(this);
		if(vencedor.equals(adversario)) {
			String t = adversario.getApelido() + ", seu mapa sobreviveu a uma partida";
			String m = "O computador utilizando seu mapa venceu um partida";
			Mensageiro.enviarEmailParaUmJogador(adversario.getEmail(), t, m);
		}
	}
	
	public void gerarRelatorio(String local) {
		GeradorDeRelatorios.gerarRelatorio(relatorio.toString(), local);
	}

	public int ataque(String apelido, Mapa mapa, int posicao) {
		int x = mapa.atacar(posicao);
		if(x == -2){
			return x;
		}
		this.salvarNoRelatorio("\n -----Vez de " + apelido + "----- ");
		
		int linha = posicao/5;
		int coluna = posicao%5;
		this.salvarNoRelatorio(apelido + " atacou a posicao: " + linha + ", " + coluna);
		if (x == -1) {
			this.salvarNoRelatorio(apelido + " errou o ataque");
		} else if(x == 2) {
			this.salvarNoRelatorio(apelido + " acertou uma embarcacao");
		} else {
			this.salvarNoRelatorio(apelido + " destruiu uma embarcacao");
			if(mapa.contarBarcosIntactos() == 0) {
				if(apelido.equals(jogador.getApelido())) {
					vencedor = jogador;
				} else {
					vencedor = adversario;
				}
				this.finalizarPartida();
			}
		}
		return x;
	}
	
	public int[] ataqueBot() {
		int[] x = {-2, 0};
		x[1] = bot.atacar();
		x[0] = ataque(adversario.getApelido(), mapaJogador, x[1]);
		if(x[0] == 3) {
			int[] y = jogador.getFrota().embarcacaoNaPosicao(x[1]).getEmbarcacao();
			bot.destriuEmbarcação(y);
		} else if(x[0] == 2) {
			bot.acertou(x[1]);
		}
		return x;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Jogador getAdversario() {
		return adversario;
	}

	public void setAdversario(Jogador adversario) {
		this.adversario = adversario;
	}

	public Jogador getVencedor() {
		return vencedor;
	}

	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}

	public StringBuilder getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(StringBuilder relatorio) {
		this.relatorio = relatorio;
	}

	public Mapa getMapaJogador() {
		return mapaJogador;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setMapaJogador(Mapa mapaJogador) {
		this.mapaJogador = mapaJogador;
	}

	public Mapa getMapaAdversario() {
		return mapaAdversario;
	}

	public void setMapaAdversario(Mapa mapaAdversario) {
		this.mapaAdversario = mapaAdversario;
	}
}
