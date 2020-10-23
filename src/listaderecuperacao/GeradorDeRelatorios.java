package listaderecuperacao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {
	public static void gerarRelatorio(String conteudo) {
		Document doc = new Document(PageSize.A4);
		
		try {
			OutputStream os = new FileOutputStream("relatório.pdf");
			PdfWriter.getInstance(doc, os);
			
			doc.open();
			
			Font font = new Font(FontFamily.TIMES_ROMAN, 22, Font.BOLD);
			
			Paragraph pg = new Paragraph("Batalha Naval", font);
			pg.setSpacingAfter(5);
			pg.setAlignment(Element.ALIGN_CENTER);
			doc.add(pg);
			
			font.setSize(16);
			pg = new Paragraph("Resumo da Partida:", font);
			pg.setSpacingAfter(10);
			doc.add(pg);
			
			font.setSize(14);
			font.setStyle(Font.NORMAL);
			pg = new Paragraph(conteudo);
			doc.add(pg);

			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
