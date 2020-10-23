package listaantiga;

import java.util.ArrayList;

public class CentralDeInformacoes {
	private ArrayList<Jogador> todosOsJogadores = new ArrayList<Jogador>();
	
	public boolean adicionarJogador(Jogador j) {
		if(j.getApelido() == null || j.getEmail() == null || j.getSenha() == null)
			return false;
		for (Jogador jogador : todosOsJogadores) {
			if(jogador.getEmail().equals(j.getEmail())) {
				return false;
			} else if (jogador.getApelido().equals(j.getApelido()))
				return false;
		}
		todosOsJogadores.add(j);
		return true;
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
