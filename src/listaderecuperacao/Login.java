package listaderecuperacao;

public class Login {
	public Jogador login(CentralDeInformacoes central) {
		Jogador j = this.buscarJogador(central);
		this.validarSenha(j);
		return j;
	}
	
	public Jogador buscarJogador(CentralDeInformacoes central) {
		Jogador jogador = null;
		while(true) {
			String email = Util.input("E-mail:");
			jogador = central.recuperarJogador(email);
			if(jogador == null) {
				System.out.println("E-mail não cadastrado!");
			} else {
				return jogador;				
			}
		}
	}
	
	public void validarSenha(Jogador jogador) {
		boolean b = false;
		while(true) {
			String senha = Util.input("Senha:");
			b = jogador.getSenha().equals(senha);
			if(b == true)
				break;
			else
				System.out.println("Senha inválida");
		}
	}
}
