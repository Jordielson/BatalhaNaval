package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import modelo.Jogo;
import visualizacao.JanelaConfiguracao;
import visualizacao.PanelPerfil;

public class OuvinteConfiguracao implements ActionListener {
	private Jogo jogo;
	private JFrame janela;
	private PanelPerfil perfil;
	
	public OuvinteConfiguracao(Jogo jogo, JFrame janela, PanelPerfil perfil) {
		this.jogo = jogo;
		this.janela = janela;
		this.perfil = perfil;
	}

	public void actionPerformed(ActionEvent arg0) {
		new JanelaConfiguracao(jogo, janela, perfil);
	}
}
