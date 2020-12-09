package modelo;

import java.awt.Point;

public class EmbarcacaoA implements Embarcacao {
	private int[] embarcacao =  new int[2];
	private boolean isHorizontal = true;
	private int barcos = 2;
	
	private EmbarcacaoA(int[] e) {
		embarcacao = e;
	}
	
	public EmbarcacaoA() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean configurarEmbarcacao(int valor) {
		int valor2 = valor;
		boolean b = false;
		if(isHorizontal) {
			valor2 += 1;
			b = valor2%5 != 0;
		} else {
			valor2 += 5;
			b = valor2 < 25;
		}
		if(b) {
			embarcacao[0] = valor;
			embarcacao[1] = valor2;
			barcos = 2;
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
	
	public Point getPoint() {
		int x = embarcacao[0]%5;
		int y = (int) embarcacao[0]/5;
		Point point = new Point(x, y);
		return point;
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
	public Embarcacao clonar() {
		EmbarcacaoA e = new EmbarcacaoA(embarcacao.clone());
		e.isHorizontal = isHorizontal;
		return e;
	}
}
