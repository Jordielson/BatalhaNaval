package modelo;

import java.util.ArrayList;

import excecoes.*;

public class CentralDeInformacoes {
	private ArrayList<Jogador> contasExcluidadas = new ArrayList<Jogador>();
	private ArrayList<Jogador> todosOsJogadores = new ArrayList<Jogador>();
	
	public void adicionarJogador(Jogador j) throws EmailInvalidoException, SenhaInvalidaException, PessoaDuplicadaException, ApelidoInvalidoException {
		ValidacaoCentral validacao =  new ValidacaoCentral();
		validacao.validarApelido(j.getApelido());
		validacao.validarEmail(j.getEmail());
		validacao.validarSenha(j.getSenha());
		validacao.validarUsuario(this, j);
		
		todosOsJogadores.add(j);
	}

	public ArrayList<Jogador> getTodosOsJogadores() {
		return todosOsJogadores;
	}
	
	public Jogador getJogadorIndex(int index) {
		return todosOsJogadores.get(index);
	}

	public void setTodosOsJogadores(ArrayList<Jogador> todosOsJogadores) {
		this.todosOsJogadores = todosOsJogadores;
	}
	
	public void excluirConta(Jogador jogador) {
		if (todosOsJogadores.remove(jogador) == true) {
			contasExcluidadas.add(jogador);
		}
	}
	
	public Jogador recuperarJogador(String email) {
		for (Jogador jogador : todosOsJogadores) {
			if(jogador.getEmail().equals(email)) {
				return jogador;
			}
		}
		return null;
	}
}
