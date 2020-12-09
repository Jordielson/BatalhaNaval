package ouvinte;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import imagens.Imagens;
import modelo.Embarcacao;
import modelo.Partida;
import visualizacao.JanelaPartida;
import visualizacao.PanelMapa;

public class OuvinteAtaque implements MouseListener {
	private JanelaPartida janela;
	private PanelMapa mapa;
	
	public OuvinteAtaque(JanelaPartida janela) {
		this.janela = janela;
		mapa = janela.getMapaAdversario();
	}

	public void mouseClicked(MouseEvent evento) {
		if(janela.isJogador()) {
			JLabel lb = (JLabel) evento.getComponent();
			lb.setHorizontalAlignment(JLabel.CENTER);
			int posicao = mapa.getComponentZOrder(lb);
			
			Partida partida = janela.getPartida();
			int x = partida.ataqueJogador(posicao);
			if(x == -1) {
				lb.setIcon(Imagens.ICON_MAR);
				lb.paintImmediately(lb.getVisibleRect());
				janela.mudarturno();
			} else if(x == 2) {
				lb.setIcon(Imagens.ICON_EXPLOSION);
			} else if(x == 3) {
				Embarcacao e = partida.getAdversario().getFrota().embarcacaoNaPosicao(posicao);
				janela.adicionarEmbarcacao(e, 320, 130, true);
				if(partida.getVencedor() != null) {
					janela.finalizarPartida();
					janela.salvarJogo();
				}
			}
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
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
