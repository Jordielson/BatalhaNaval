package listaderecuperacao;

import java.util.ArrayList;

public class CentralDeInformacoes {
	private ArrayList<Jogador> todosOsJogadores = new ArrayList<Jogador>();
	//Central
	
	public boolean adicionarJogador(Jogador j) {
		ValidarDadosDoJogador validar =  new ValidarDadosDoJogador(j);
		if(validar.validarDados() == true) {
			for (Jogador jogador : todosOsJogadores) {
				if(jogador.getEmail().equals(j.getEmail())) {
					return false;
				} else if (jogador.getApelido().equals(j.getApelido()))
					return false;
			}
			todosOsJogadores.add(j);
			return true;
		}
		return false;
	}

	public ArrayList<Jogador> getTodosOsJogadores() {
		return todosOsJogadores;
	}

	public void setTodosOsJogadores(ArrayList<Jogador> todosOsJogadores) {
		this.todosOsJogadores = todosOsJogadores;
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
