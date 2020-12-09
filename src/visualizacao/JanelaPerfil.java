package visualizacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Jogador;
import modelo.Pontuacao;

public class JanelaPerfil extends JFrame {
	private static final long serialVersionUID = 1L;
	private Jogador jogador;
	private JFrame janela;
	private PanelPerfil perfil;
	
	public JanelaPerfil(Jogador jogador, JFrame janela) {
		this.jogador = jogador;
		janela.setVisible(false);
		this.janela = janela;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		perfil = new PanelPerfil(jogador, janela);
		add(perfil);
		adicionarPanelEstatisticas();
		adicionarBotoes();
		adicionarHitoricoPontos(jogador.getHistoricoPontuacao());
		
		setVisible(true);
	}

	private void adicionarHitoricoPontos(ArrayList<Pontuacao> pontos) {
		JLabel lbHistorico = new JLabel("Histórico de pontos");
		lbHistorico.setFont(new Font("Arial", Font.BOLD, 20));
		lbHistorico.setBounds(250, 100, 250, 20);
		add(lbHistorico);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Data");
		modelo.addColumn("Pontuação");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		for (int i = 0; i < pontos.size(); i++) {
			Pontuacao ponto = pontos.get(i);
			Object[] linha = new Object[2];
			linha[0] = ponto.getData().format(dtf);
			linha[1] = ponto.getPontuacao();
			modelo.addRow(linha);
		}
		
		JTable tabela = new JTable(modelo);
		JScrollPane pane = new JScrollPane(tabela);
		pane.setBounds(250, 130, 250, 150);
		add(pane);
	}

	private void adicionarBotoes() {
		JButton[] bt = new JButton[2];
		String[] str = {"Voltar", "Histórico"};
		
		int x = 50;
		for (int i = 0; i < str.length; i++) {
			bt[i] = new JButton(str[i]);
			bt[i].setBounds(x, 300, 150, 30);
			add(bt[i]);
			x+=280;
		}
		
		bt[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				janela.setVisible(true);
			}
		});
		
		bt[1].addActionListener(new OuvinteHistorico(this));
	}
	
	public class OuvinteHistorico implements ActionListener {
		private JanelaPerfil janela;
		
		public OuvinteHistorico(JanelaPerfil janela) {
			this.janela = janela;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			new JanelaHistorico(perfil, jogador.getHistorico(), janela);
			
		}
	}

	private void adicionarPanelEstatisticas() {
		JPanel panel = new JPanel();
		BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(box);
		panel.setBounds(20, 140, 200, 100);
		
		int v = jogador.getQuantidadeDeVitorias();
		int d = jogador.getQuantidadeDeDerrotas();
		int total = v + d;
		int taxa = 0;
		if(total > 0) {
			taxa = (int) (v/(total * 1f) * 100);
		}
		String[] str = {"Total de partidas jogadas: " + total,
						"Quantidade de vitórias: " + v,
						"Quantidade de derotas:" + d,
						"Taxa de vitória: " + taxa + " %"};
		
		for (int i = 0; i < str.length; i++) {
			JLabel lb = new JLabel(str[i]);
			lb.setForeground(Color.BLUE);
			panel.add(lb);
		}
		JLabel lb = new JLabel("Estatísticas");
		lb.setFont(new Font("Arial", Font.BOLD, 20));
		lb.setBounds(20, 100, 200, 20);
		add(lb);
		add(panel);
	}
	
	
	 
}
