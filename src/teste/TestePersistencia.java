package teste;

public class TestePersistencia {
	public static void main(String[] args) {
		PersistenciaEstoque pe = new PersistenciaEstoque();
		
		Estoque e = pe.recuperarEstoque();
		
		for (Produto p : e.getEstoque()) {
			System.out.println(p.toString());
		}
	}
}
