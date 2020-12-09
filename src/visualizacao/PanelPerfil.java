package visualizacao;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Jogador;

public class PanelPerfil extends JPanel {
	private static final long serialVersionUID = 1L;
	private Jogador jogador;
	private JFrame janela;
	private JLabel lbNome;
	private JLabel lbImagem;
	private JLabel lbPontuacao;

	public PanelPerfil(Jogador jogador, JFrame janela) {
		this.jogador = jogador;
		this.janela = janela;
		setLayout(null);
		
		alterarPosicao(0, 0);
		adicionarImagem();
		adicionarLabels();
	}
	
	public void alterarPosicao(int x, int y) {
		setBounds(x, y, 250, 70);
	}

	private void adicionarLabels() {
		Font font = new Font("Roboto", Font.BOLD, 14);
		lbNome = new JLabel(jogador.getApelido());
		lbPontuacao = new JLabel("Pontuação: " + jogador.getPontuacao());
		lbNome.setFont(font);
		lbPontuacao.setFont(font);
		
		lbNome.setBounds(100, 25, 150, 20);
		lbPontuacao.setBounds(100, 50, 150, 20);
		
		add(lbNome);
		add(lbPontuacao);
	}
	
	public void atualizar() {
		lbNome.setText(jogador.getApelido());
		lbPontuacao.setText("Pontuação: " + jogador.getPontuacao());
	}

	private void adicionarImagem() {
		lbImagem = new JLabel();
		lbImagem.setBounds(20, 6, 64, 64);
		lbImagem.setIcon(new ImageIcon(jogador.getIcon()));
		add(lbImagem);
	}
	
	public void adicionarOuvinte() {
		lbImagem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lbImagem.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new JanelaPerfil(jogador, janela);
			}
		});
	}
	
	public void setJanela(JFrame janela) {
		this.janela = janela;
	}
}
