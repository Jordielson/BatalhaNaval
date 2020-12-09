package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.CadastroDeJogador;
import visualizacao.JanelaCadastroDeJogador;
import visualizacao.JanelaLogin;

public class OuvinteCadastro implements ActionListener {
	private JanelaCadastroDeJogador janela;

	public OuvinteCadastro(JanelaCadastroDeJogador janela) {
		super();
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String apelido = janela.getTfApelido().getText();
		String email = janela.getTfEmail().getText();
		String senha = new String(janela.getTfSenha().getPassword());
		
		CadastroDeJogador cadastro = new CadastroDeJogador();
		try {
			cadastro.cadastrar(apelido, email, senha);
			JOptionPane.showMessageDialog(janela, "Cadastro realizado!");
			janela.dispose();
			new JanelaLogin();
		} catch (Exception e) {
			janela.getLbDadoInvalido().setText(e.getMessage());
		}
		
	}
	
}
