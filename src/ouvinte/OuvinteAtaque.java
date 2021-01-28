package ouvinte;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import imagens.Imagens;
import modelo.Embarcacao;
import modelo.Frota;
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
			OuvinteAtaque.ataque(janela, lb, partida.getAdversario().getFrota(), x, posicao, 320);
			if(partida.getVencedor() != null) {
				janela.finalizarPartida();
				janela.salvarJogo();
			}
		}
	}
	
	public static void ataque(JanelaPartida janela, JLabel lb, Frota frota, int ataque, int posicao, int p) {
		if(ataque == -1) {
			lb.setIcon(Imagens.ICON_MAR);
			lb.paintImmediately(lb.getVisibleRect());
			janela.mudarturno();
		} else if(ataque == 2) {
			lb.setIcon(Imagens.ICON_EXPLOSION);
		} else if(ataque == 3) {
			Embarcacao e = frota.embarcacaoNaPosicao(posicao);
			janela.adicionarEmbarcacao(e, p, 130, true);
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
