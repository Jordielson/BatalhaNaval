package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import modelo.Jogador;
import visualizacao.JanelaPerfil;
import visualizacao.PanelPerfil;

public class OuvinteBtPerfil implements ActionListener {
	private JFrame janela;
	private Jogador jogador;
	
	public OuvinteBtPerfil(JFrame janela, Jogador jogador, PanelPerfil perifil) {
		this.janela = janela;
		this.jogador = jogador;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		janela.setVisible(false);
		new JanelaPerfil(jogador, janela);
	}
}
