package visualizacao;

import java.awt.Point;

import javax.swing.JLabel;

import modelo.Embarcacao;
import ouvinte.OuvinteMouseMotion;
import ouvinte.OuvintePosicionarFrota;

public abstract class LabelEmabarcacao extends JLabel {
	private static final long serialVersionUID = 1L;
	private Embarcacao e;
	private int baseX = 20;
	private int baseY = 90;
	private boolean isDead = false;
	
	public LabelEmabarcacao(Embarcacao e) {
		this.e = e;
		atualizarPosicao();
	}
	
	public void adicionarOuvintes(OuvinteMouseMotion ouviteMotion, OuvintePosicionarFrota ouvinteFrota) {
		addMouseMotionListener(ouviteMotion);
		addMouseListener(ouvinteFrota);
	}
	
	public abstract void atualizar();
	
	public void atualizarPosicao() {
		Point point = e.getPoint();
		int x = (int) point.getX()*50 + baseX;
		int y = (int) point.getY()*50 + baseY;

		atualizar();
		int z = e.getEmbarcacao().length * 50;
		if(e.isHorizontal()) {
			setBounds(x, y, z, 50);
		} else
			setBounds(x, y, 50, z);
	}
	
	public Embarcacao getEmbarcacao() {
		return e;
	}

	public boolean isDead() {
		return isDead;
	}
	
	public void dead() {
		this.isDead = true;
	}
	
	public int getBaseX() {
		return baseX;
	}

	public void setBaseX(int baseX) {
		this.baseX = baseX;
	}

	public int getBaseY() {
		return baseY;
	}

	public void setBaseY(int baseY) {
		this.baseY = baseY;
	}

}
