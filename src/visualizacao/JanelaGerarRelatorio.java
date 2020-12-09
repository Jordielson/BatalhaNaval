package visualizacao;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import modelo.Partida;

public class JanelaGerarRelatorio {
	public static void gerarRelatorio(Partida partida) {
		JFileChooser chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "Arquivo PDF";
			}
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory())
					return true;
				return (f.getName().toLowerCase().endsWith(".pdf"));
			}
		});
	    
	    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { 
	    	partida.gerarRelatorio(chooser.getSelectedFile().toString());
	    	JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");					
	    } else {
	    	JOptionPane.showMessageDialog(null, "Diretório não selecionado!");
	      
	    }
	}
}
