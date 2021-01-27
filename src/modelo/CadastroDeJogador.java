package modelo;

import excecoes.ApelidoInvalidoException;
import excecoes.EmailInvalidoException;
import excecoes.PessoaDuplicadaException;
import excecoes.SenhaInvalidaException;

public class CadastroDeJogador {
	private CentralDeInformacoes central;
	private Persistencia p = new Persistencia();
	
	public CadastroDeJogador() {
		central = p.recuperarCentral("central.xml");
	}
	
	public void cadastrar(String apelido, String email, String senha) throws EmailInvalidoException, SenhaInvalidaException, PessoaDuplicadaException, ApelidoInvalidoException {
		Jogador jogador = new Jogador(apelido, email, senha, 0);
		central.adicionarJogador(jogador);
		p.salvarCentral(central, "central.xml");
	}
}
