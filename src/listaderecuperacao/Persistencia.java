package listaderecuperacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Persistencia {
	private XStream xstream = new XStream(new StaxDriver());
	
	public Persistencia() {
		XStream.setupDefaultSecurity(xstream);
		xstream.allowTypesByRegExp(new String [] { ".*" });
	}
	
	public void salvarCentral(CentralDeInformacoes central, String nomeAquivo) {
		String xml = xstream.toXML(central);
		File arquivo = new File(nomeAquivo);
		
		try {
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public CentralDeInformacoes recuperarCentral(String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		
		try {
			if(arquivo.exists()) {
				FileInputStream fis = new FileInputStream(arquivo);
				return (CentralDeInformacoes) xstream.fromXML(fis);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new CentralDeInformacoes();
	}
}
