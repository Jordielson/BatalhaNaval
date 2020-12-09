package visualizacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import exceções.ApelidoInvalidoException;
import exceções.PessoaDuplicadaException;
import exceções.SenhaInvalidaException;
import modelo.Jogo;
import ouvinte.OuvinteExcuirConta;

public class JanelaConfiguracao extends JFrame {
	private static final long serialVersionUID = 1L;
	private Jogo jogo;
	private JFrame janela;
	private PanelPerfil perfil;
	
	public JanelaConfiguracao(Jogo jogo, JFrame janela, PanelPerfil perfil) {
		this.jogo = jogo;
		this.janela = janela;
		this.perfil = perfil;
		setSize(300, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		adicionarBotoes();
		setVisible(true);
	}
	
	private void adicionarBotoes() {
		JButton[] buttons = new JButton[4];
		String[] nomes = {"Alterar Apelido", "Alterar Senha", "Excluir Conta", "Sobre"};
		int y = 60;
		for (int i = 0; i < nomes.length; i++) {
			buttons[i] = new JButton(nomes[i]);
			buttons[i].setBounds(80, y, 140, 30);
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setBackground(Color.BLUE);
			add(buttons[i]);
			y+=50;
		}
		
		buttons[0].addActionListener(new OuvinteAlterarApelido());
		buttons[1].addActionListener(new OuvinteAlterarSenha());
		buttons[2].addActionListener(new OuvinteExcuirConta(this));
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JanelaSobre();
			}
		});
		
	}
	
	private class OuvinteAlterarApelido implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			String apelido = JOptionPane.showInputDialog("Novo Apelido");
			try {
				jogo.alterarApelido(apelido);
				JOptionPane.showMessageDialog(null, "Apelido alterado com sucesso!");
				perfil.atualizar();
			} catch (ApelidoInvalidoException | PessoaDuplicadaException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
	}
	
	private class OuvinteAlterarSenha implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			String senha = JOptionPane.showInputDialog("Nova Senha");
			try {
				jogo.alterarSenha(senha);
				JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
			} catch (SenhaInvalidaException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
		
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	
	public JFrame getJanela() {
		return janela;
	}
}
