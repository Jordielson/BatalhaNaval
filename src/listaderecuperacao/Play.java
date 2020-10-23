package listaderecuperacao;

public class Play {
	public static void main(String[] args) {
		new Play().start();
	}
	
	public void start() {
		Persistencia p = new Persistencia();
		CentralDeInformacoes central = p.recuperarCentral("central.xml");
		Jogador jogador = new Login().login(central);
		Jogo jogo = new Jogo(jogador, central);
		jogo.play();
	}
}
