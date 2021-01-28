package modelo;

import java.util.Comparator;

public class ComparacaoNome implements Comparator<Jogador>{
	@Override
	public int compare(Jogador arg0, Jogador arg1) {
		return arg0.compareTo(arg1);
	}

}
