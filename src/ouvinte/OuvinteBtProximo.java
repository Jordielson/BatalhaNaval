package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

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
			OuvinteAtaque.ataque(janelaPartida, lb, mapa.getFrota(), x, ataque, p);
		}
	}
	
}
