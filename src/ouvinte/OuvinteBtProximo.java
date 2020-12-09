package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import imagens.Imagens;
import modelo.Embarcacao;
import modelo.Mapa;
import modelo.Partida;
import visualizacao.JanelaPartida;
import visualizacao.PanelMapa;

public class OuvinteBtProximo implements ActionListener {
	private JanelaPartida janelaPartida;
	private Partida partida;
	
	public OuvinteBtProximo(JanelaPartida janelaPartida) {
		super();
		this.janelaPartida = janelaPartida;
		partida = janelaPartida.getPartida();
	}

	public void actionPerformed(ActionEvent evento) {
		PanelMapa panelMapa;
		Mapa mapa;
		int p;
		if(janelaPartida.isJogador()) {
			mapa = partida.getMapaAdversario();
			panelMapa = janelaPartida.getMapaAdversario();
			p = 320;
		} else {
			mapa = partida.getMapaJogador();
			panelMapa = janelaPartida.getMapaJogador();
			p = 20;
		}
		
		int ataque = mapa.proximoAtaque();
		if(ataque == -1) {
			janelaPartida.finalizarPartida();
			janelaPartida.getBtProximo().setVisible(false);
		} else {
			int x = mapa.obterResultadoNaPosicao(ataque);
			JLabel lb = (JLabel) panelMapa.getComponent(ataque);
			lb.setHorizontalAlignment(JLabel.CENTER);
			
			if (x == -1) {
				lb.setIcon(Imagens.ICON_MAR);
				janelaPartida.mudarturno();
			} else if(x == 2) {
				lb.setIcon(Imagens.ICON_EXPLOSION);
			} else if(x == 3) {
				Embarcacao e = mapa.getFrota().embarcacaoNaPosicao(ataque);
				janelaPartida.adicionarEmbarcacao(e, p, 130, true);
			}
		}
	}
	
}
