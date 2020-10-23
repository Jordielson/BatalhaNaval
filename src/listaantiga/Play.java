package listaantiga;

public class Play {		
	public static void main(String[] args) {
		Play play = new Play();
		play.start();
	}
	
	void start() {
		Persistencia p = new Persistencia();
		Jogo jogo = new Jogo(p.recuperarCentral("central.xml"));
		jogo.play();
	}
}
