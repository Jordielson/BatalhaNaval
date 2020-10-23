package listaderecuperacao;

public class Jogo {
	private Jogador jogador;
	private CentralDeInformacoes central;
	
	public Jogo(Jogador jogador, CentralDeInformacoes central) {
		this.jogador = jogador;
		this.central = central;
	}
	
	public void play() {
		if(this.validarListaDeJogadores() == true) {
			Jogador adversario = this.sortearAdversario();
			Partida partida = new Partida(jogador, adversario);
			if(partida.validarPosicionamentoDosBarcos() == true) {
				partida.start();
				partida.finalizarPartida();
			}
		}
	}
	
	public boolean validarListaDeJogadores() {
		if(central.getTodosOsJogadores().size() < 2) {
			System.out.println("Não há jogadores cadastrados");
			return false;
		}
		return true;
	}
	
	public Jogador sortearAdversario() {
		Jogador adversario = null;
		while(adversario == null) {
			int i = (int) (Math.random()*central.getTodosOsJogadores().size());
			Jogador j = central.getTodosOsJogadores().get(i);
			if(jogador.equals(j) == false) {
				adversario = j;
			}
		}
		return adversario;
	}
}
