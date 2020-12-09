package ouvinte;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import modelo.Embarcacao;
import visualizacao.LabelEmabarcacao;


public class OuvintePosicionarFrota implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent event) {
		if(SwingUtilities.isRightMouseButton(event)) {
			LabelEmabarcacao panel = ((LabelEmabarcacao) event.getComponent());
			panel.getEmbarcacao().girar();
			panel.atualizarPosicao();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent evento) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if(SwingUtilities.isLeftMouseButton(event)) {
			LabelEmabarcacao panel = (LabelEmabarcacao) event.getComponent();
			Embarcacao e = panel.getEmbarcacao();
			Point point = panel.getLocation();
			int px = (int) point.getX();
			int py = (int) point.getY();
			int vezesX = 5;
			int vezesY = 5;
			if(e.isHorizontal()) {
				vezesX = 5;
				if(e.getBarcos() == 3) {
					vezesY = 3;
				} else {
					vezesY = 4;
				}
			} else {
				vezesY = 5;
				if(e.getBarcos() == 3) {
					vezesX = 3;
				} else {
					vezesX = 4;
				}
			}
			int x = converter(py, 90, vezesX);
			int y = converter(px, 20, vezesY);
			
			if(x != -1 && y != -1)
				e.configurarEmbarcacao(x, y);
			panel.atualizarPosicao();
		}
	}
	
	private int converter(int p, int i, int vezes) {
		for(int f = -1; f < vezes; f++) {
			if(p < i) {
				return f;
			}
			i+=50;
		}
		return -1;
	}

}
