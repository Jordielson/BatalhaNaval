package listaantiga;

public class Jogo {
	private Jogador jogador;
	private Jogador adversario;
	private CentralDeInformacoes central;
	
	public Jogo(CentralDeInformacoes central) {
		this.central = central;
	}
	
	public void login() {
		while(jogador == null) {
			String email = Util.input("E-mail:");
			String senha = Util.input("Senha:");
			Jogador j = this.buscarJogador(email, senha);
			if(j == null) {
				System.out.println("Credenciais inválidas");
			} else {
				jogador = j;
			}
			
		}
	}
	
	public Jogador buscarJogador(String email, String senha) {
		Jogador j = central.recuperarJogador(email);
		if(j == null || ! j.getSenha().equals(senha)) {	
			return null;
		}
		return j;
	}
	
	public boolean configuracaoIniciaisDoJogo() {
		if(central.getTodosOsJogadores().size() < 2) {
			System.out.println("Não há jogadores cadastrados");
			return false;
		}
		this.login();
		this.sortearAdiversario();
		int x = this.validarPosicionamentoDosBarcos();
		if(x == 1) {
			System.out.println("Posicione os barcos");
			return false;
		}else if(x == 2) {
			System.out.println("Posicionamento dos barcos do adiversário inválidos");
			return false;
		}
		return true;
	}
	
	public void sortearAdiversario() {
		while(true) {
			int i = (int) (Math.random()*central.getTodosOsJogadores().size());
			Jogador j = central.getTodosOsJogadores().get(i);
			if(jogador.equals(j) == false) {
				adversario = j;
				break;
			}
		}
	}
	
	public int validarPosicionamentoDosBarcos() {
		if(jogador.getMapa().contarBarcosIntactos() != 10)
			return 1;
		else if(adversario.getMapa().contarBarcosIntactos() != 10)
			return 2;
		return 0;
	}
	
	public void exibirMapa(String s, Mapa m) {
		System.out.println(s);
		System.out.println(m);
	}
	
	public void play() {
		if(!this.configuracaoIniciaisDoJogo())
			System.exit(0);
		
		Mapa cloneJogador = jogador.getMapa().clonar();
		Mapa cloneAdversario = adversario.getMapa().clonar();
		Mapa viewMapaAdversario = new Mapa();
		viewMapaAdversario.setBarcos(cloneJogador.contarBarcosIntactos());
		Jogador vencedor;
		
		while(true) {
			this.exibirMapa("--Frota do Adversário--", viewMapaAdversario);
			System.out.println("Digite as coordenadas");
			int linha = Integer.parseInt(Util.input("Linha:"));
			int coluna = Integer.parseInt(Util.input("Coluna:"));
			int x = cloneAdversario.atacar(linha, coluna);
			viewMapaAdversario.getMatriz()[linha][coluna] = cloneAdversario.getMatriz()[linha][coluna];
			if(x == 1) {
				if(cloneAdversario.contarBarcosIntactos() <= 0) {
					vencedor = jogador;
					break;
				}
			} else if(x == 0) {
				this.exibirMapa("--Frota do Adversário--", viewMapaAdversario);
				while(true) {
				this.ataqueComputador(cloneJogador);
				if(cloneJogador.contarBarcosIntactos() <= 0) {
					vencedor = adversario;
					break;
				}
				}
				break;
			} else if(x == 2){
				System.out.println("Posição já foi atacada");
			} else {
				System.out.println("Posição inválida");
			}
		}
		this.exibirMapa("--Frota do Adversário--", viewMapaAdversario);
		System.out.println("Vencedor: " + vencedor.getApelido());
	}
	
	public void ataqueComputador(Mapa cloneJogador) {
		this.exibirMapa(" -----Sua Frota----- ", cloneJogador);
		while(cloneJogador.contarBarcosIntactos() > 0) {
			int linha = (int) (Math.random()*5);
			int coluna = (int) (Math.random()*5);
			if("BA".contains(cloneJogador.getMatriz()[linha][coluna])) {
				int x = cloneJogador.atacar(linha, coluna);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.exibirMapa(" -----Sua Frota----- ", cloneJogador);
				if(x == 0) {
					break;
				}
			}
		}
		
	}
}
