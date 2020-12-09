package visualizacao;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ouvinte.OuvinteAtaque;

public class PanelMapa extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public PanelMapa() {
		setLayout(new GridLayout(5, 5, 2, 2));
	
		for (int i = 0; i < 25; i++) {
			JLabel lb = new JLabel();
			lb.setBackground(Color.BLUE);
			lb.setOpaque(true);
			add(lb);
		}
		alterarPosicao(20, 90);
	}
	
	public void alterarPosicao(int x, int y) {
		setBounds(x, y, 250, 250);

	}
	
	public void adicionarOuvinteMapa(OuvinteAtaque ouvinte) {
		for (Component c : getComponents()) {
			JLabel lb = (JLabel) c;
			lb.addMouseListener(ouvinte);
		}
	}

}
