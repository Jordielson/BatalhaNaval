package visualizacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import imagens.Imagens;
import modelo.Jogo;
import ouvinte.OuvinteConfiguracao;

public class JanelaJogo extends JFrame {
	private static final long serialVersionUID = 1L;
	private Jogo jogo;
	private PanelPerfil perfil;
	
	public JanelaJogo(Jogo jogo) {
		this.jogo = jogo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		adicionarPerfil();
		adicionarBotoes();
		setVisible(true);
	}

	private void adicionarPerfil() {
		perfil = new PanelPerfil(jogo.getJogador(), this);
		perfil.adicionarOuvinte();
		add(perfil);
	}

	private void adicionarBotoes() {
		JButton[] buttons = new JButton[4];
		String[] nomes = {"Jogar", "Frota", "Configuração", "Sair" };
		int y = 140;
		for (int i = 0; i < nomes.length; i++) {
			buttons[i] = new JButton(nomes[i]);
			buttons[i].setBounds(200, y, 150, 35);
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setBackground(Color.BLUE);
			add(buttons[i]);
			y+=40;
		}
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new JanelaRank(perfil, jogo);
			}
		});
		buttons[1].addActionListener(new OuvinteFrota());
		
		JButton b = new JButton(Imagens.ICON_SETTINGS);
		b.setBounds(500, 15, 30, 30);
		add(b);
		OuvinteConfiguracao ouvinte = new OuvinteConfiguracao(jogo, this, perfil);
		b.addActionListener(ouvinte);
		buttons[2].addActionListener(ouvinte);
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new JanelaLogin();
			}
		});
	}
	
	private class OuvinteFrota implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new JanelaFrota(perfil, jogo);
		}
	}
}
