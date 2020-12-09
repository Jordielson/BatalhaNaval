package visualizacao;

import imagens.Imagens;
import modelo.Embarcacao;

public class LabelEmbarcacaoA extends LabelEmabarcacao {
	private static final long serialVersionUID = 1L;
	
	public LabelEmbarcacaoA(Embarcacao e) {
		super(e);
	}
	
	public void atualizar() {
		if(getEmbarcacao().isHorizontal()) {
			setIcon(Imagens.ICON_BARCO_A_H);
		} else {
			setIcon(Imagens.ICON_BARCO_A_V);
		}
	}
	
	@Override
	public void dead() {
		if(getEmbarcacao().isHorizontal())
			setIcon(Imagens.ICON_BARCO_A_H_D);
		else
			setIcon(Imagens.ICON_BARCO_A_V_D);
		super.dead();
	}
}

