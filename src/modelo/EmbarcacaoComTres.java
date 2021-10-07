package modelo;

import java.awt.Point;

public class EmbarcacaoComTres implements Embarcacao {
	private int[] embarcacao =  new int[3];
	private boolean isHorizontal = true;
	private int barcos = 3;
	
	private EmbarcacaoComTres(int[] e) {
		embarcacao = e;
	}
	
	public EmbarcacaoComTres() {
	}
	
	public boolean configurarEmbarcacao(int valor) {
		int valor2 = valor;
		int valor3 = valor;
		boolean b = false;
		if(isHorizontal) {
			valor2 += 1;
			valor3 += 2;
			int z = valor3%5; 
			b = z != 0 && z != 1;
		} else {
			valor2 += 5;
			valor3 += 10;
			b = valor3 < 25;
		}
		if(b) {
			embarcacao[0] = valor;
			embarcacao[1] = valor2;
			embarcacao[2] = valor3;
			barcos = 3;
			return true;
		}
		return false;
	}
	
	public boolean configurarEmbarcacao(int x, int y) {
		return configurarEmbarcacao(Mapa.converter(x, y));
	}
	
	public void girar() {
		int k = 1;
		if(isHorizontal) {
			k = 5;
			isHorizontal = false;
		} else {
			isHorizontal = true;
		}
		
		while(configurarEmbarcacao(embarcacao[0]) == false) {
			embarcacao[0] -= k;
		}
	}
	
	
	public boolean atacar(int valor) {
		for (int i = 0; i < embarcacao.length; i++) {
			if(valor == embarcacao[i]) {
				barcos--;
				return true;
			}
		}
		return false;
	}
	
	public int getBarcos() {
		return barcos;
	}

	public int[] getEmbarcacao() {
		return embarcacao;
	}

	public boolean isHorizontal() {
		return isHorizontal;
	}
	
	public void setHorizontal(boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
	}

	@Override
	public Point getPoint() {
		int x = embarcacao[0]%5;
		int y = (int) embarcacao[0]/5;
		Point point = new Point(x, y);
		return point;
	}
	
	@Override
	public Embarcacao clonar() {
		EmbarcacaoComTres e = new EmbarcacaoComTres(embarcacao.clone());
		e.isHorizontal = isHorizontal;
		return e;
	}
}