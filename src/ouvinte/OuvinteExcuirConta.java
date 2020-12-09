package ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelo.Jogo;
import modelo.Mensageiro;
import modelo.Partida;
import visualizacao.JanelaConfiguracao;
import visualizacao.JanelaLogin;

public class OuvinteExcuirConta implements ActionListener {
	private JanelaConfiguracao janela;
	
	public OuvinteExcuirConta(JanelaConfiguracao janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Jogo jogo = janela.getJogo();
		ArrayList<Partida> partidas = jogo.getJogador().getHistorico();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder str = new StringBuilder();
		str.append("Todas as partidas: \n");
		str.append("Data  |  Jogador X Adversário  | Vencedor  \n");
		for (Partida p : partidas) {
			str.append(p.getData().format(dtf) + "  |  ");
			str.append(p.getJogador() + " X " + p.getAdversario() + "  |  ");
			str.append(p.getVencedor() + "  \n");
		}
		Mensageiro.enviarEmailParaUmJogador(jogo.getJogador().getEmail(), "Conta Excluída. Informações sobre as partidas", str.toString());
		janela.getJanela().dispose();
		janela.dispose();
		jogo.excluirConta();
		jogo.salvar();
		new JanelaLogin();
	}
	
}
