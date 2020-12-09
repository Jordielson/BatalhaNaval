package visualizacao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ouvinte.OuvinteLogin;
import ouvinte.OuvinteMouseLogin;

public class JanelaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tfEmail;
	private JPasswordField tfSenha;
	private JLabel lbDadosInvalidos;
	private JLabel lb5;
	
	public JanelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,350);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		adicionarLabels();
		adiconarTextFields();
		adicionarBotao();
//		adicionarCheckBox();
		
		setVisible(true);
	}

//	private void adicionarCheckBox() {
//		JCheckBox checkBox = new JCheckBox("Lembrar?",null);
//		checkBox.setBounds(75,150, 100,25);
//		add(checkBox);
//	}

	private void adicionarBotao() {
		JButton b;
		b = new JButton("Entrar");
		b.setForeground(Color.WHITE);
		b.setBackground(Color.BLUE);
		b.setBounds(75, 180, 350, 40);
		
		b.addActionListener(new OuvinteLogin(this));
		add(b);
	}

	private void adiconarTextFields() {
		tfEmail = new JTextField();
		tfSenha = new JPasswordField();
		tfEmail.setBounds(130,90,295,25);
		tfSenha.setBounds(130,120,295,25);
		add(tfEmail);
		add(tfSenha);
		
	}

	private void adicionarLabels() {
		JLabel lb,lb2,lb3,lb4;
		lb= new JLabel("LOGIN");
		lb2= new JLabel("Esqueceu a senha?");
		lb3= new JLabel("Não possui conta?");
		lb4= new JLabel("Recuperar senha");
		lb5= new JLabel("Criar conta");
		JLabel lbEmail = new JLabel("E-mail:");
		JLabel lbSenha = new JLabel("Senha:");
		lbDadosInvalidos = new JLabel();
		
		lbDadosInvalidos.setForeground(Color.RED);
		lb.setFont(new Font("Arial",Font.BOLD, 20));
		OuvinteMouseLogin ouvinteMouse = new OuvinteMouseLogin(this);
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		lb4.setForeground(Color.BLUE);
		lb4.setCursor(cursor);
		lb4.addMouseListener(ouvinteMouse);
		lb5.setForeground(Color.BLUE);
		lb5.setCursor(cursor);
		lb5.addMouseListener(ouvinteMouse);
		lb.setBounds(200, 30, 100, 50);
		lb2.setBounds(85, 225, 150, 30);
		lb3.setBounds(85, 255, 150, 30);
		lb4.setBounds(230, 230, 150,20);
		lb5.setBounds(230, 260, 150,20);
		lbEmail.setBounds(75, 90, 50, 25);
		lbSenha.setBounds(75, 120, 50, 25);
		lbDadosInvalidos.setBounds(200, 150, 200, 25);
		
		add(lb);
		add(lb2);
		add(lb3);
		add(lb4);
		add(lb5);
		add(lbEmail);
		add(lbSenha);
		add(lbDadosInvalidos);
	}
	
	public JTextField getTfEmail() {
		return tfEmail;
	}

	public JPasswordField getTfSenha() {
		return tfSenha;
	}
	
	public JLabel getLbDadosInvalidos() {
		return lbDadosInvalidos;
	}
	
	public JLabel getLb5() {
		return lb5;
	}
}
