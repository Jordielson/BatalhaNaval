package visualizacao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import imagens.Imagens;
import modelo.Frota;
import modelo.Jogo;

import ouvinte.OuvinteConfiguracao;
import ouvinte.OuvinteMouseMotion;
import ouvinte.OuvintePosicionarFrota;

public class JanelaFrota extends JFrame {
	private static final long serialVersionUID = 1L;
	private Jogo jogo;
	private PanelPerfil perfil;
	private LabelEmabarcacao[] embarcacoes;
	
	public JanelaFrota(PanelPerfil perfil, Jogo jogo) {
		this.jogo = jogo;
		perfil.setJanela(this);
		this.perfil = perfil;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		add(perfil);
		adicionarMapa();
		adiconarBotoes();
		setVisible(true);
	}

	private void adiconarBotoes() {
		JButton[] buttons = new JButton[4];
		String[] nomes = {"Jogar", "Auto", "Salvar", "Voltar"};
		int y = 120;
		for (int i = 0; i < nomes.length; i++) {
			buttons[i] = new JButton(nomes[i]);
			buttons[i].setBounds(350, y, 120, 30);
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setBackground(Color.BLUE);
			add(buttons[i]);
			y+=50;
		}
		
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if (jogo.alterarFrota() == true) {
						dispose();
						new JanelaRank(perfil, jogo);
					} else {
						JOptionPane.showMessageDialog(null, "Formação da frota inválida!");
					}
			}
		});
		
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jogo.getFrota().sortearPosicionamentoDaFrota();
				for (int i = 0; i < embarcacoes.length; i++) {
					embarcacoes[i].atualizarPosicao();
				}
			}
		});
		
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (jogo.alterarFrota()== true) {
					JOptionPane.showMessageDialog(null, "Formação da frota salva com sucesso");
				} else
					JOptionPane.showMessageDialog(null, "Formação da frota inválida!");
			}
		});
		
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jogo.alterarFrota()== true) {
					dispose();
					new JanelaJogo(jogo);					
				} else
					JOptionPane.showMessageDialog(null, "Formação da frota inválida!");
				
			}
		});
		
		JButton b = new JButton(Imagens.ICON_SETTINGS);
		b.setBounds(500, 15, 30, 30);
		add(b);
		OuvinteConfiguracao ouvinte = new OuvinteConfiguracao(jogo, this, perfil);
		b.addActionListener(ouvinte);
	}

	private void adicionarMapa() {

		Frota frota = jogo.getFrota();
		embarcacoes = new LabelEmabarcacao[4];
		embarcacoes[0] = new LabelEmbarcacaoA(frota.getEmbarcacao(0));
		embarcacoes[1] = new LabelEmbarcacaoA(frota.getEmbarcacao(1));
		embarcacoes[2] = new LabelEmbarcacaoB(frota.getEmbarcacao(2));
		embarcacoes[3] = new LabelEmbarcacaoB(frota.getEmbarcacao(3));
		OuvinteMouseMotion ouviteMotion = new OuvinteMouseMotion();
		OuvintePosicionarFrota ouvinteFrota = new OuvintePosicionarFrota();
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		for (LabelEmabarcacao panelEmabarcacao : embarcacoes) {
			panelEmabarcacao.setCursor(cursor);
			panelEmabarcacao.adicionarOuvintes(ouviteMotion, ouvinteFrota);
			add(panelEmabarcacao);
		}
		
		add(new PanelMapa());
	}
}
