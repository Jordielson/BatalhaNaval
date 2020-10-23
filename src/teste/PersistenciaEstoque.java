package teste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PersistenciaEstoque {
	private XStream xstream = new XStream(new DomDriver());
	private File arquivo = new File("estoque.xml");
	
	public void salvarEstoque(Estoque estoque) {
		String xml = xstream.toXML(estoque);
		
		try {
			if(!arquivo.exists())
				arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Estoque recuperarEstoque() {
		try {
			if(arquivo.exists()) {
				FileInputStream fis = new FileInputStream(arquivo);
				return (Estoque) xstream.fromXML(fis);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new Estoque();
	}
}
