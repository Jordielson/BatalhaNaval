package modelo;

import java.util.Comparator;

public class ComparacaoPontuacao implements Comparator<Jogador> {
	@Override
	public int compare(Jogador arg0, Jogador arg1) {
		return arg1.getPontuacao() - arg0.getPontuacao();
	}
}
