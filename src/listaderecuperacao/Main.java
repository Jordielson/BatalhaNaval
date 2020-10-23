package listaderecuperacao;

public class Main {
	public static void main(String[] args) {
		String nomeDoArquivo = "central.xml";
		
		Persistencia p = new Persistencia();
		CentralDeInformacoes central = p.recuperarCentral(nomeDoArquivo);
		Login l = new Login();
		
		String op = "";
		while(!op.equals("S")) {
			Main.exibirOpcoes();
			op = Util.input("Escolha uma opção:");
			
			if(op.equals("1")) {
				String apelido = Util.input("Apelido:");
				String email = Util.input("E-mail:");
				String senha = Util.input("Senha:");
				int vitorias = Integer.parseInt(Util.input("Quantidade de Vitórias:"));
				if (central.adicionarJogador(new Jogador(apelido, email, senha, vitorias)))
					p.salvarCentral(central, nomeDoArquivo);
				else
					System.out.println("Dados inválidos");
			} else if(op.equals("2")) {
				for (Jogador j : central.getTodosOsJogadores())
					System.out.println(j);	
			} else if(op.equals("3")) {
				Jogador j = l.login(central);
				for (int i = 0; i < 10; i++) {
					System.out.println("Digite as coordenadas");
					int linha = Integer.parseInt(Util.input("Linha:"));
					int coluna = Integer.parseInt(Util.input("Coluna:"));
					if(j.getMapa().alocarBarco(linha, coluna) == false) {
						System.out.println("Não foi possível alocar o barco");
						break;
					}
				}
				p.salvarCentral(central, nomeDoArquivo);
			} else if(op.equals("4")) {
				Jogador j = l.login(central);
				System.out.println(j.getMapa());
			}
		}
	}
	
	public static void exibirOpcoes() {
		System.out.println("1-novo jogador \n"
				+ "2-listar jogadores\n"
				+ "3-cadastrar um novo mapa para um jogador\n" 
				+ "4-visualizar o mapa de um jogador\n"
				+ "S-sair");	
	}
}
