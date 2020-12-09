package visualizacao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Partida;

public class JanelaHistorico extends JFrame {
	private static final long serialVersionUID = 1L;
	private PanelPerfil perfil;
	private JTable tabela;
	private JanelaPerfil janela;
	private ArrayList<Partida> historico;
	
	public JanelaHistorico(PanelPerfil perfil, ArrayList<Partida> historico, JanelaPerfil janela) {
		perfil.setJanela(this);
		this.historico = historico;
		this.janela = janela;
		this.perfil = perfil;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		add(perfil);
		adicionarLabel();
		adicionarBotoes();
		criarTable(historico);
		setVisible(true);
	}

	private void adicionarBotoes() {
		JButton btVoltar = new JButton("Voltar");
		btVoltar.setBounds(15, 370, 160, 30);
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				janela.add(perfil);
				janela.setVisible(true);
			}
		});
		add(btVoltar);
		
		JButton btRelatorio = new JButton("Gerar relatório");
		btRelatorio.setBounds(195, 370, 160, 30);
		btRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indiceLinha = tabela.getSelectedRow();
				
				if(indiceLinha == -1) {
					JOptionPane.showMessageDialog(janela.getContentPane(), "Selecione uma partida");
				} else {
					JanelaGerarRelatorio.gerarRelatorio(historico.get(indiceLinha));
				}
				
			}
		});
		add(btRelatorio);
		
		JButton btDetalhar = new JButton("Detalhar partida");
		btDetalhar.setBounds(375, 370, 160, 30);
		btDetalhar.addActionListener(new OuvinteBtDetalhar(this));
		add(btDetalhar);
	}
	
	public class OuvinteBtDetalhar implements ActionListener {
		private JanelaHistorico janela;
		
		public OuvinteBtDetalhar(JanelaHistorico janela) {
			this.janela = janela;
		}

		public void actionPerformed(ActionEvent arg0) {
			int indiceLinha = tabela.getSelectedRow();
			
			if(indiceLinha == -1) {
				JOptionPane.showMessageDialog(janela.getContentPane(), "Selecione uma partida");
			} else {
				new JanelaPartida(historico.get(indiceLinha), null, janela);				
			}
		}
		
		
	}

	private void adicionarLabel() {
		JLabel lbHistorico = new JLabel("Histórico");
		lbHistorico.setFont(new Font("Arial",Font.BOLD, 20));
		lbHistorico.setHorizontalAlignment(JLabel.CENTER);
		lbHistorico.setBounds(175, 90, 200, 30);
		add(lbHistorico);
	}
	
	private void criarTable(ArrayList<Partida> partidas) {
		DefaultTableModel modelo = new DefaultTableModel();
		
		modelo.addColumn("Data");
		modelo.addColumn("Jogadores");
		modelo.addColumn("Vencedor");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (int i = 0; i < partidas.size(); i++) {
			Partida p = partidas.get(i); 
			Object[] linha = new Object[3];
			linha[0] = p.getData().format(dtf);
			linha[1] = p.getJogador().getApelido() + " X " + partidas.get(i).getAdversario().getApelido();
			linha[2] = p.getVencedor().getApelido();
			modelo.addRow(linha);
		}
		
		tabela = new JTable(modelo);
		JScrollPane pane = new JScrollPane(tabela);
		pane.setBounds(75, 130, 400, 230);
		add(pane);
	}
}
