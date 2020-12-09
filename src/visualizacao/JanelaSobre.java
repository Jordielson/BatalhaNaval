package visualizacao;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import imagens.Imagens;


public class JanelaSobre extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JanelaSobre() {
		setSize(350, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		adicionarLabel();
		setVisible(true);
	}

	private void adicionarLabel() {
		JLabel lbIFPB = new JLabel(Imagens.ICON_IFPB);
		add(lbIFPB);
				
		JLabel lbCurso = new JLabel("Análise e Desenvolvimento de Sistemas");
		add(lbCurso);
		
		JLabel lbDisciplina = new JLabel("Disciplina: Programação II");
		add(lbDisciplina);

		JLabel lbAlunos = new JLabel("Alunos: Manoel Vandeildo, Jodielson Emanuel,");
		add(lbAlunos);
		lbAlunos = new JLabel(" Eduardo Moreira");
		add(lbAlunos);
	}
}
