package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Partida;
import visualizacao.JanelaPartida;

public class OuvinteBtSair implements ActionListener {
	private JanelaPartida janelaPartida;
	private JFrame janela;
	
	public OuvinteBtSair(JanelaPartida janelaPartida, JFrame janela) {
		this.janelaPartida = janelaPartida;
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Partida partida = janelaPartida.getPartida();
		if(partida.getVencedor() == null) {
			int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair. Você perderá o jogo!", "Sair do jogo", JOptionPane.YES_NO_OPTION);
			if(op == 0) {
				partida.setVencedor(partida.getAdversario());
				partida.finalizarPartida();
				janelaPartida.getJogo().salvar();
				janelaPartida.dispose();
				janela.setVisible(true);
			}
		} else {
			janelaPartida.dispose();
			janela.setVisible(true);
		}
	}
}
