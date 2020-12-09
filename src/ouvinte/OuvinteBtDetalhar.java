package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import visualizacao.JanelaPartida;

public class OuvinteBtDetalhar implements ActionListener {
	private JanelaPartida janela;
	public OuvinteBtDetalhar(JanelaPartida janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent arg0) {
		janela.dispose();
		new JanelaPartida(janela.getPartida(), janela.getJogo(), janela.getJanela());
	}
}
