package visualizacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import imagens.Imagens;
import ouvinte.OuvinteCadastro;

public class JanelaCadastroDeJogador extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tfEmail;
	private JTextField tfApelido;
	private JPasswordField tfSenha;
	private JLabel lbDadoInvalido;
	
	public JanelaCadastroDeJogador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,350);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		adicionarLabels();
		adicionarTextFields();
		adicionarBotoes();
		
		setVisible(true);
	}

	private void adicionarBotoes() {
		JButton b = new JButton("Registrar-se");
		b.setForeground(Color.WHITE);
		b.setBackground(Color.BLUE);
		b.setBounds(70, 220, 355, 40);
		b.addActionListener(new OuvinteCadastro(this));
		add(b);

		JButton btVoltar = new JButton(Imagens.ICON_VOLTAR);
		btVoltar.setBounds(25, 15, 28, 28);
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new JanelaLogin();
			}
		});
		add(btVoltar);
	}

	private void adicionarTextFields() {
		tfApelido = new JTextField();
		tfEmail = new JTextField();
		tfSenha = new JPasswordField();
		
		tfApelido.setBounds(135,90,290,25);
		tfEmail.setBounds(135,120,290,25);
		tfSenha.setBounds(135,150,290,25);
		
		add(tfApelido);
		add(tfEmail);
		add(tfSenha);
	}

	private void adicionarLabels() {
		JLabel lb = new JLabel("Criar nova conta");
		JLabel lbApelido = new JLabel("Apelido:");
		JLabel lbEmail = new JLabel("E-mail:");
		JLabel lbSenha = new JLabel("Senha:");
		lbDadoInvalido = new JLabel();
		lbDadoInvalido.setHorizontalAlignment(JLabel.CENTER);
		lbDadoInvalido.setForeground(Color.RED);
		
		lb.setFont(new Font("Arial",Font.BOLD, 20));
		lb.setBounds(150, 30, 200, 50);
		lbApelido.setBounds(70, 90, 60, 25);
		lbEmail.setBounds(70, 120, 60, 25);
		lbSenha.setBounds(70, 150, 60, 25);
		lbDadoInvalido.setBounds(70, 185, 355, 30);
		
		add(lb);
		add(lbApelido);
		add(lbEmail);
		add(lbSenha);
		add(lbDadoInvalido);
	}
	
	public JTextField getTfEmail() {
		return tfEmail;
	}

	public JTextField getTfApelido() {
		return tfApelido;
	}

	public JPasswordField getTfSenha() {
		return tfSenha;
	}
	
	public JLabel getLbDadoInvalido() {
		return lbDadoInvalido;
	}
}
