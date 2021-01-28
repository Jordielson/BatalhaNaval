package modelo;

import java.util.ArrayList;
import java.util.Comparator;

import excecoes.ApelidoInvalidoException;
import excecoes.PessoaDuplicadaException;
import excecoes.PosicaoInvalidaException;
import excecoes.SenhaInvalidaException;

public class Jogo {
	private Jogador jogador;
	private CentralDeInformacoes central;
	private Persistencia p = new Persistencia();
	private ArrayList<Jogador> rank;
	
	public Jogo(Jogador jogador, CentralDeInformacoes central) {
		this.jogador = jogador;
		this.central = central;
		ordenarPorPontuacao();
	}
	
	private void atualizarRank(Comparator<Jogador> comparador) {
		ArrayList<Jogador> lista = central.getTodosOsJogadores();
		rank = new ArrayList<Jogador>();
		for (Jogador j : lista) {
			boolean b = true;
			for (int i = 0; i < rank.size(); i++) {
				if(comparador.compare(rank.get(i), j) > 0) {
					rank.add(i, j);
					b = false;
					break;
				}
			}
			if(b) {
				rank.add(j);
			}
		}
	}
	
	public void ordenarPorNome() {
		atualizarRank(new ComparacaoNome());
	}

	public void ordenarPorPontuacao() {
		atualizarRank(new ComparacaoPontuacao());
	}
	
	public ArrayList<Jogador> filtar(String apelido) {
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		for (Jogador jogador : central.getTodosOsJogadores()) {
			if(jogador.getApelido().contains(apelido)) {
				jogadores.add(jogador);
			}
		}
		return jogadores;
	}
	
	public void excluirConta() {
		central.excluirConta(jogador);
	}
	
	public Partida play(Jogador adversario) {
		Partida partida = null;
		try {
			partida = new Partida(jogador, adversario);
			partida.start();
		} catch (PosicaoInvalidaException e) {
			e.printStackTrace();
		}
		return partida;
	}
	
	public ArrayList<Jogador> rank() {
		return rank;
	}
	
	public Frota getFrota() {
		return jogador.getFrota();
	}
	
	public void salvar() {
		p.salvarCentral(central, "central.xml");
	}
	
	public boolean alterarFrota() {
		if (jogador.getFrota().isValido()== true) {
			for (Jogador j : rank) {
				if(j != jogador)
					j.removarJogador(jogador);
			}
			salvar();
			return true;
		}
		return false;
	}

	public void alterarSenha(String senha) throws SenhaInvalidaException {
		ValidacaoCentral v = new ValidacaoCentral();
		v.validarSenha(senha);
		jogador.setSenha(senha);
		p.salvarCentral(central, "central.xml");
	}
	
	public void alterarApelido(String apelido) throws ApelidoInvalidoException, PessoaDuplicadaException {
		ValidacaoCentral v = new ValidacaoCentral();
		v.validarApelido(apelido);
		for (Jogador j: central.getTodosOsJogadores()) {
			if(apelido.equals(j.getApelido()))
				throw new PessoaDuplicadaException("Apelido");
		}
		jogador.setApelido(apelido);
		p.salvarCentral(central, "central.xml");
	}
	
	public Jogador getJogador() {
		return jogador;
	}
}
