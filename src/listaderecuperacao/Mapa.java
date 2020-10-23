package listaderecuperacao;

public class Mapa {
	private String[][] matriz = new String[5][5];
	private int barcos = 0;

	public Mapa() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = "A";
			}
		}
	}
	
	public Mapa clonar() {
		Mapa m = new Mapa();
		m.matriz = this.matriz.clone();
		m.barcos = this.barcos;
		return m;
	}
	
	public boolean alocarBarco(int i, int j) {
		if(barcos >= 10)
			return false;
		if(-1 < i && i < 5 && -1 < j && j < 5) {
			if(matriz[i][j].equals("A")) {
				matriz[i][j] = "B";
				barcos++;
				return true;
			}			
		}
		return false;
	}
	
	public int atacar(int i, int j) {
		if(-1 < i && i < 5 && -1 < j && j < 5) {
			String s = matriz[i][j];
			if(s.equals("A")) {
				matriz[i][j] = "X";
				return 0;
			}else if(s.equals("B")) {
				matriz[i][j] = "D";
				barcos--;
				return 1;
			}
			return -1;//quando o jogador ou computudor já atacou essa posição
		}
		return -1;
	}
	
	public int contarBarcosIntactos() {
		return barcos;
	}
	
	public String[][] getMatriz() {
		return matriz;
	}
	public void setMatriz(String[][] matriz) {
		if(matriz.length == 5 && matriz[0].length == 5)
			this.matriz = matriz;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(" ---------------------\n");
		for (int i = 0; i < matriz.length; i++) {
			s.append(" | ");
			for (int j = 0; j < matriz[i].length; j++) {
				s.append(matriz[i][j] + " | ");
			}
			s.append("\n ---------------------\n");
		}
		return s.toString();
	}
}
