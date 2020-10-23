package teste;

import java.util.ArrayList;

public class Estoque {
	private ArrayList<Produto> estoque = new ArrayList<Produto>();
	
	public void adicionarProduto(Produto p) {
		estoque.add(p);
	}

	public ArrayList<Produto> getEstoque() {
		return estoque;
	}
	
}
