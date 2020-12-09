package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Jogo;
import modelo.Login;
import visualizacao.JanelaJogo;
import visualizacao.JanelaLogin;

public class OuvinteLogin implements ActionListener {
	private JanelaLogin janela;
	
	public OuvinteLogin(JanelaLogin janela) {
		this.janela = janela;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		Login login = new Login();
		try {
			Jogo jogo = login.login(janela.getTfEmail().getText(), new String(janela.getTfSenha().getPassword()));
			janela.dispose();
			new JanelaJogo(jogo);
		} catch (Exception e) {
			janela.getLbDadosInvalidos().setText(e.getMessage());
		}
		
	}
	
}
