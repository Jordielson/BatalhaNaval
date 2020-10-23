package listaderecuperacao;

public class Partida {
	private Jogador jogador;
	private Jogador adversario;
	private Jogador vencedor = null;
	private StringBuilder relatorio = new StringBuilder();
	
	public Partida(Jogador jogador, Jogador adversario) {
		this.jogador = jogador;
		this.adversario = adversario;
	}
	
	public boolean validarPosicionamentoDosBarcos() {
		if(jogador == null || adversario == null)
			return false;
		if(jogador.getMapa().contarBarcosIntactos() != 10) {
			System.out.println("Posicione os barcos");
			return false;
		} else if(adversario.getMapa().contarBarcosIntactos() != 10) {
			System.out.println("Posicionamento dos barcos do adiversário inválidos");
			return false;
		}
		return true;
	}
	
	public void exibir(String s) {
		System.out.println(s);
		relatorio.append(s + "\n");
	}
	
	public void start() {
		Mapa cloneJogador = jogador.getMapa().clonar();
		Mapa cloneAdversario = adversario.getMapa().clonar();
		
		this.exibir(jogador.getApelido() + "  X  " + adversario.getApelido());
		Jogador vez = jogador;
		Jogador espera = adversario;
		this.exibir(" -----Vez de " + vez.getApelido() + "----- ");
		while(vencedor == null) {
			int linha, coluna;
			if(vez.equals(jogador)) {
				System.out.println("Digite as coordenadas");
				linha = Integer.parseInt(Util.input("Linha:"));
				coluna = Integer.parseInt(Util.input("Coluna:"));
			} else {
				linha = (int) (Math.random()*5);
				coluna = (int) (Math.random()*5);
			}
			if(this.ataque(vez.getApelido(), espera.getMapa(), linha, coluna) == true) {
				Jogador passe = vez;
				vez = espera;
				espera = passe;
				this.exibir("\n -----Vez de " + vez.getApelido() + "----- ");
			}
		}
		this.exibir("\n  Mapa de " + jogador.getApelido());
		this.exibir(jogador.getMapa().toString());
		this.exibir("  Mapa de " + adversario.getApelido());
		this.exibir(adversario.getMapa().toString());
		jogador.setMapa(cloneJogador);
		adversario.setMapa(cloneAdversario);
	}
	
	public void finalizarPartida() {
		this.exibir("Fim de Jogo!!!");
		this.exibir("Vencedor: " + vencedor.getApelido());
		if(vencedor.equals(adversario))
			Mensageiro.enviarEmailParaUmJogador(adversario);
		GeradorDeRelatorios.gerarRelatorio(relatorio.toString());
	}
	
	public boolean ataque(String apelido, Mapa mapa, int linha, int coluna) {
		int x = mapa.atacar(linha, coluna);
		if(x == -1){
			return false;
		}
		
		this.exibir(apelido + " atacou a posição: " + linha + ", " + coluna);
		if(x == 1) {
			this.exibir(apelido + " destruiu um barco");
			if(mapa.contarBarcosIntactos() == 0) {
				if(apelido.equals(jogador.getApelido())) {
					vencedor = jogador;
				} else {
					vencedor = adversario;
				}
			}
			return false;
		} else {
			this.exibir(apelido + " errou o ataque");
			return true;
		}
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
}
