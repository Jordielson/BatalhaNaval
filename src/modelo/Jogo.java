package modelo;

import java.util.ArrayList;
import java.util.Arrays;

import exceções.ApelidoInvalidoException;
import exceções.PessoaDuplicadaException;
import exceções.PosicaoInvalidaException;
import exceções.SenhaInvalidaException;

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
	
	public void ordenarPorNome() {
		ArrayList<Jogador> lista = central.getTodosOsJogadores();
		String[] nomes = new String[lista.size()];
		for (int i = 0; i < nomes.length; i++) {
			nomes[i] = lista.get(i).getApelido();
		}
		Arrays.sort(nomes);
		rank = new ArrayList<Jogador>();
		for (String string : nomes) {
			for (Jogador j : lista) {
				if(j.getApelido().equals(string)) {
					rank.add(j);
					break;
				}
			}
		}
	}

	public void ordenarPorPontuacao() {
		ArrayList<Jogador> lista = central.getTodosOsJogadores();
		rank = new ArrayList<Jogador>();
		for (Jogador j : lista) {
			boolean b = true;
			for (int i = 0; i < rank.size(); i++) {
				if(rank.get(i).getPontuacao() < j.getPontuacao()) {
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
	
	public Jogador filtar(String apelido) {
		for (Jogador jogador : central.getTodosOsJogadores()) {
			if(jogador.getApelido().equals(apelido)) {
				return jogador;
			}
		}
		return null;
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
