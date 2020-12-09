package modelo;

import java.util.ArrayList;

public class Frota {
	private ArrayList<Embarcacao> frota;
	
	public Frota() {
		frota = new ArrayList<Embarcacao>();
		Embarcacao e = new EmbarcacaoA();
		e.configurarEmbarcacao(0);
		frota.add(e);
		e = new EmbarcacaoA();
		e.configurarEmbarcacao(5);
		frota.add(e);
		e = new EmbarcacaoB();
		e.configurarEmbarcacao(10);
		frota.add(e);
		e = new EmbarcacaoB();
		e.configurarEmbarcacao(15);
		frota.add(e);
	}
	
	private Frota(ArrayList<Embarcacao> frota) {
		this.frota = frota;
	}
	
	public Embarcacao getEmbarcacao(int index) {
		return frota.get(index);
	}
	
	public boolean isValido() {
		ArrayList<Integer> posicoesOcupadas = new ArrayList<Integer>();
		for (Embarcacao e : frota) {
			for (int valor : e.getEmbarcacao()) {
				if(posicoesOcupadas.indexOf(valor) != -1) {
					return false;
				}
				posicoesOcupadas.add(valor);
			}
		}
		return true;
	}
	
	public void sortearPosicionamentoDaFrota() {
		ArrayList<Integer> posicoesOcupadas = new ArrayList<Integer>();
		for (int i = 0; i < frota.size(); i++) {
			Embarcacao e = frota.get(i);
			boolean condicao = true;
			while(condicao) {
				condicao = false;
				if(((int) (Math.random()*2)) == 0)
					e.setHorizontal(true);
				else
					e.setHorizontal(false);
				
				if (e.configurarEmbarcacao((int) (Math.random()*Mapa.tamanho)) == true) {
					for (int j : e.getEmbarcacao()) {
						if(posicoesOcupadas.indexOf(j) != -1) {
							condicao = true;
							break;
						}
					}
				} else {
					condicao = true;
				}
				
				if(!condicao) {
					for (int j : e.getEmbarcacao()) {
						posicoesOcupadas.add(j);
					}
				}
			}
		}
	}

	public Embarcacao embarcacaoNaPosicao(int posicao) {
		for (Embarcacao embarcacao : frota) {
			for (int p : embarcacao.getEmbarcacao()) {
				if(p == posicao)
					return embarcacao;
			}
		}
		return null;
	}
	
	public Embarcacao atacar(int valor) {
		for (Embarcacao embarcacao : frota) {
			if (embarcacao.atacar(valor)) {
				if (embarcacao.getBarcos() == 0) {
					return embarcacao;
				}
			}
		}
		return null;
	}
	
	public Frota clone() {
		ArrayList<Embarcacao> f = new ArrayList<Embarcacao>();
		for (Embarcacao embarcacao : frota) {
			f.add(embarcacao.clonar());			
		}
		
		return new Frota(f);
	}
	
	public int length() {
		return frota.size();
	}
}
