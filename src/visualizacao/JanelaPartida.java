package visualizacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import imagens.Imagens;
import modelo.Embarcacao;
import modelo.Jogo;
import modelo.Partida;
import ouvinte.OuvinteAtaque;
import ouvinte.OuvinteBtDetalhar;
import ouvinte.OuvinteBtProximo;
import ouvinte.OuvinteBtSair;

public class JanelaPartida extends JFrame {
	private static final long serialVersionUID = 1L;
	private Partida partida;
	private Jogo jogo;
	private JLabel label;
	private PanelMapa mapaAdversario;
	private PanelMapa mapaJogador;
	private JanelaRank janelaRank;
	private JFrame janela;
	private JButton btProximo;
	private boolean isJogador = true;
	
	public JanelaPartida(Partida partida, Jogo jogo, JFrame janela) {
		this.partida = partida;
		this.jogo = jogo;
		this.janela = janela;
		janela.setVisible(false);
		setContentPane(new JLayeredPane());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 480);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		adicionarPerfil();
		adicionalLabel();
		adicionarMapas();
		adiconarBtSair();
		if(partida.getVencedor() == null) {
			mapaAdversario.adicionarOuvinteMapa(new OuvinteAtaque(this));
		} else {
			partida.detalharPartida();
			adicionarBotoes();
			adicionarBtProximo();
		}
		
		setVisible(true);
	}

	public void mudarturno() {
		if(isJogador) {
			isJogador = false;
			
			label.setText("Turno: " + partida.getAdversario().getApelido());
			label.paintImmediately(label.getVisibleRect());
			if(partida.getVencedor() == null) {
				ataqueBot();
			}
		} else {
			isJogador = true;
			label.setText("Turno: " + partida.getJogador().getApelido());
		}
	}

	private void ataqueBot() {
		int[] x;
		boolean vezBot = true;
		while(vezBot) {
        	try {
        		Thread.sleep(1000);
        	} catch (InterruptedException e) {}
        	
			x = partida.ataqueBot();
			if(x[0] == 3) {
				Embarcacao e = partida.getJogador().getFrota().embarcacaoNaPosicao(x[1]);
				adicionarEmbarcacao(e, 20, 130, true);
				if(partida.getVencedor() != null) {
					finalizarPartida();
					salvarJogo();
					vezBot = false;
				}
			} else {
				JLabel lb = (JLabel) mapaJogador.getComponent(x[1]);
				lb.setHorizontalAlignment(JLabel.CENTER);
				if(x[0] == -1) {
					lb.setIcon(Imagens.ICON_MAR);
					vezBot = false;
				} else if(x[0] == 2) {
					lb.setIcon(Imagens.ICON_EXPLOSION);
				}
				lb.paintImmediately(lb.getVisibleRect());
			}
			
		}
		mudarturno();
	}
	
	public void adicionarEmbarcacao(Embarcacao e, int x, int y, boolean isDead) {
		LabelEmabarcacao panel = null;
		if(e.getEmbarcacao().length == 2) {
			panel = new LabelEmbarcacaoA(e);
		} else {
			panel = new LabelEmbarcacaoB(e);
		}
		panel.setBaseX(x);
		panel.setBaseY(y);
		panel.atualizarPosicao();
		if(isDead)
			panel.dead();
		add(panel, JLayeredPane.DRAG_LAYER);
		panel.paintImmediately(panel.getVisibleRect());
	}


	public void finalizarPartida() {
		isJogador = false;
		label.setText("Vencedor: " + partida.getVencedor().getApelido());
		
		for(Embarcacao e : partida.getMapaJogador().getBarcosIntactos()) {
			adicionarEmbarcacao(e, 20, 130, false);
		}
		
		for(Embarcacao e : partida.getMapaAdversario().getBarcosIntactos()) {
			adicionarEmbarcacao(e, 320, 130, false);
		}
	}
	
	public void salvarJogo() {
		janelaRank.atualizar();
		jogo.salvar();
		adicionarBotoes();
		
	}

	private void adicionarBotoes() {
		JButton btRelatorio = new JButton("Relatório");
		btRelatorio.setBounds(315, 400, 120, 30);
		btRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaGerarRelatorio.gerarRelatorio(partida);				
			}
		});
		add(btRelatorio);
		
		JButton btDetalhar = new JButton("Detalhar");
		btDetalhar.setBounds(165, 400, 120, 30);
		btDetalhar.addActionListener(new OuvinteBtDetalhar(this));
		add(btDetalhar);
	}

	private void adiconarBtSair() {
		JButton btSair = new JButton("Sair");
		btSair.setBounds(15, 400, 120, 30);
		btSair.addActionListener(new OuvinteBtSair(this, janela));
		add(btSair);
	}

	private void adicionarBtProximo() {
		btProximo = new JButton("Próximo");
		btProximo.setBounds(465, 400, 120, 30);
		btProximo.addActionListener(new OuvinteBtProximo(this));
		add(btProximo);
	}

	private void adicionarMapas() {
		mapaJogador = new PanelMapa();
		mapaJogador.alterarPosicao(20, 130);
		add(mapaJogador);
		
		mapaAdversario = new PanelMapa();
		mapaAdversario.alterarPosicao(320, 130);
		add(mapaAdversario);
	}

	private void adicionalLabel() {
		label = new JLabel("Turno: " + partida.getJogador().getApelido());
		label.setBounds(100, 10, 400, 30);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		add(label);		
	}

	private void adicionarPerfil() {
		PanelPerfil perfilJogador = new PanelPerfil(partida.getJogador(), this);
		perfilJogador.adicionarOuvinte();
		perfilJogador.alterarPosicao(0, 40);
		add(perfilJogador);
		
		PanelPerfil perfilAdversario = new PanelPerfil(partida.getAdversario(), this);
		perfilAdversario.adicionarOuvinte();
		perfilAdversario.alterarPosicao(300, 40);
		add(perfilAdversario);
	}
	
	public PanelMapa getMapaJogador() {
		return mapaJogador;
	}
	
	public boolean isJogador() {
		return isJogador;
	}
	
	public JFrame getJanela() {
		return janela;
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	
	public JButton getBtProximo() {
		return btProximo;
	}
	
	public Partida getPartida() {
		return partida;
	}
	
	public PanelMapa getMapaAdversario() {
		return mapaAdversario;
	}
	
	public JanelaRank getJanelaRank() {
		return janelaRank;
	}
	
	public void setJanelaRank(JanelaRank janelaRank) {
		this.janelaRank = janelaRank;
	}
}
