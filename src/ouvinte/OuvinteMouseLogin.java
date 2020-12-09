package ouvinte;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import exceções.EmailInvalidoException;
import modelo.Login;
import modelo.Mensageiro;
import modelo.Util;
import visualizacao.JanelaCadastroDeJogador;
import visualizacao.JanelaLogin;

public class OuvinteMouseLogin implements MouseListener {
	private JanelaLogin janela;
		
	public OuvinteMouseLogin(JanelaLogin janela) {
		this.janela = janela;
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getComponent() == janela.getLb5()) {
			janela.dispose();
			new JanelaCadastroDeJogador();
		} else {
			Login login = new Login();
			try {
				String email = JOptionPane.showInputDialog("Informe seu E-mail");
				login.validarEmail(email);
				String novaSenha = Util.gerarSenhaAleatoria();
				Mensageiro.enviarEmailParaUmJogador(email, "Nova Senha", "Senha temporária: " + novaSenha);
				login.getJogador().setSenha(novaSenha);
				login.salvar();
			} catch (EmailInvalidoException e) {
				JOptionPane.showMessageDialog(janela.getContentPane(), "E-mail não cadastrado!");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}