package modelo;

import java.util.ArrayList;

public class BotMedio implements Bot{
	private ArrayList<Integer> ataquesRestantes = new ArrayList<Integer>();
	private ArrayList<Integer> acertos = new ArrayList<Integer>();
	
	public BotMedio() {
		for (int i = 0; i < 25; i++) {
			ataquesRestantes.add(i);
		}
	}
	
	public int atacar() {
		Integer x = -1;
		if(acertos.size() > 0) {
			x = ataqueInteligente();
			ataquesRestantes.remove(x);
		} else {
			int k = (int) (Math.random()*ataquesRestantes.size());
			x = ataquesRestantes.remove(k);
		}
		
		return x;
	}
	
	private Integer ataqueInteligente() {
		ArrayList<Integer> possiveis = new ArrayList<Integer>();
		for (int i = 0; i < acertos.size(); i++) {
			Integer[] x = new Integer[4];
			int p = acertos.get(i);
			if(p%5 != 0) {
				x[0] = p-1;
			} if(p%5 != 4) {
				x[1] = p+1;
			}
			if(p > 4) {
				x[2] = p-5;
			} if(p < 20) {
				x[3] = p+5;
			}
			for (Integer j : x) {
				if(j != null && ataquesRestantes.indexOf(j) != -1) {
					possiveis.add(j);
				}
			}
		}

		return possiveis.get((int) (Math.random()*possiveis.size()));
	}
	
	public void acertou(int posicao) {
		acertos.add(posicao);
	}
	
	public void destriuEmbarcação(int[] x) {
		for (Integer i : x) {
			acertos.remove(i);
		}
	}
	
}
