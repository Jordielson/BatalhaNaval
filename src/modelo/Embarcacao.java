package modelo;

import java.awt.Point;

public interface Embarcacao {
	public boolean configurarEmbarcacao(int posicao);
	public boolean configurarEmbarcacao(int x, int y);
	public int[] getEmbarcacao();
	public boolean isHorizontal();
	public void setHorizontal(boolean isHorizontal);
	public boolean atacar(int valor);
	public int getBarcos();
//	public void adicionarEmbarcacao();
	public Point getPoint();
	public void girar();
	public Embarcacao clonar();
	
}
