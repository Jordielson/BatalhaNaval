package visualizacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import imagens.Imagens;
import modelo.Jogador;
import modelo.Jogo;
import ouvinte.OuvinteConfiguracao;

public class JanelaRank extends JFrame {
	private static final long serialVersionUID = 1L;
	private Jogo jogo;
	private JButton[] bt;
	private JTable tabela;
	private PanelPerfil perfil;
	private JRadioButton rbRankPontos;
	private JScrollPane pane;
	
	public JanelaRank(PanelPerfil perfil, Jogo jogo) {
		this.jogo = jogo;
		this.perfil = perfil;
		perfil.setJanela(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		adicionarRadioButton();
		adicionarBotoes();
		add(perfil);
		criarTable(jogo.rank());
		criarScrollPane();
		
		setVisible(true);
	}
	
	private void adicionarRadioButton() {
		rbRankPontos = new JRadioButton("Ordenar pela pontuação");
		rbRankPontos.setBounds(50, 100, 200, 25);
		rbRankPontos.setSelected(true);
		add(rbRankPontos);
		JRadioButton rbOrdemNome = new JRadioButton("Ordenar pelo nome");
		rbOrdemNome.setBounds(300, 100, 200, 25);
		add(rbOrdemNome);
		
		OuvinteRb ouvinte = new OuvinteRb();
		rbRankPontos.addActionListener(ouvinte);
		rbOrdemNome.addActionListener(ouvinte);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rbRankPontos);
		group.add(rbOrdemNome);
	}

	private void adicionarBotoes() {
		JButton b = new JButton(Imagens.ICON_SETTINGS);
		b.setBounds(500, 15, 30, 30);
		add(b);
		OuvinteConfiguracao ouvinte = new OuvinteConfiguracao(jogo, this, perfil);
		b.addActionListener(ouvinte);
		
		bt = new JButton[4];
		String[] str = {"Voltar", "Ver Perfil", "Filtrar", "Desafiar"};
		
		int x = 40;
		for (int i = 0; i < str.length; i++) {
			bt[i] = new JButton(str[i]);
			bt[i].setBounds(x, 375, 100, 25);
			add(bt[i]);
			x+=125;
		}
		
		bt[0].addActionListener(new OuvinteBtVoltar());
		bt[1].addActionListener(new OuvinteBtPerfil(this));
		bt[2].addActionListener(new OuvinteBtFiltrar());
		bt[3].addActionListener(new OuvinteBtDesafiar(this));
	}
	
	public class OuvinteRb implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			atualizarTabela();
		}
		
	}
	
	public class OuvinteBtFiltrar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String apelido = JOptionPane.showInputDialog("Nome do jogador");
			
			ArrayList<Jogador> jogadores = jogo.filtar(apelido);
			if(jogadores.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nenhum jogador encontrado no sistema");
			} else {
//				ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
//				jogadores.add(j);
				pane.getViewport().remove(tabela);
				criarTable(jogadores);
				pane.getViewport().add(tabela);
			}
		}
		
	}
	
	public class OuvinteBtVoltar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new JanelaJogo(jogo);
		}
		
	}
	
	public class OuvinteBtDesafiar implements ActionListener {
		private JanelaRank janela;
		
		public OuvinteBtDesafiar(JanelaRank janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			int indiceLinha = tabela.getSelectedRow();
			
			if(indiceLinha == -1) {
				JOptionPane.showMessageDialog(janela.getContentPane(), "Selecione um jogador");
			} else {
				dispose();
				JanelaPartida janelaPartida = new JanelaPartida(jogo.play(jogo.rank().get(indiceLinha)), jogo, janela);
				janelaPartida.setJanelaRank(janela);
				tabela.clearSelection();
			}
		}
	}
	
	public class OuvinteBtPerfil implements ActionListener {
		private JFrame janela;
		
		public OuvinteBtPerfil(JFrame janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			int indiceLinha = tabela.getSelectedRow();
			
			if(indiceLinha == -1) {
				JOptionPane.showMessageDialog(janela.getContentPane(), "Selecione um jogador");
			} else {
				setVisible(false);
				new JanelaPerfil(jogo.rank().get(indiceLinha), janela);
			}
		}
	}
	
	public void atualizar() {
		atualizarTabela();
		perfil.atualizar();
	}
	
	private void atualizarTabela() {
		if(rbRankPontos.isSelected()) {
			jogo.ordenarPorPontuacao();
		} else {
			jogo.ordenarPorNome();
		}
		pane.getViewport().remove(tabela);
		criarTable(jogo.rank());
		pane.getViewport().add(tabela);
	}

	private void criarScrollPane() {
		pane = new JScrollPane(tabela);
		pane.setBounds(100, 150, 350, 200);
		add(pane);
	}
	
	private void criarTable(ArrayList<Jogador> jogadores) {
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Posição");
		modelo.addColumn("Nome");
		modelo.addColumn("Pontuação");
		
		for (int i = 0; i < jogadores.size(); i++) {
			Object[] linha = new Object[3];
			linha[0] = i+1;
			linha[1] = jogadores.get(i).getApelido();
			linha[2] = jogadores.get(i).getPontuacao();
			modelo.addRow(linha);
		}
		
		tabela = new JTable(modelo);
		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int indiceLinha = tabela.getSelectedRow();
				if(indiceLinha > -1) {				
					Jogador j = jogo.rank().get(indiceLinha);
					if(!jogo.getJogador().verificarJogador(j)) {
						bt[3].setEnabled(false);
					} else {
						bt[3].setEnabled(true);
					}					
				}
			}
		});
	}
}
