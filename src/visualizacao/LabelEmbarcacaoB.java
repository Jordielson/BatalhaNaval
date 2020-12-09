package visualizacao;

import imagens.Imagens;
import modelo.Embarcacao;

public class LabelEmbarcacaoB extends LabelEmabarcacao {
	private static final long serialVersionUID = 1L;
	
	public LabelEmbarcacaoB(Embarcacao e) {
		super(e);
	}
	
	public void atualizar() {
		if(getEmbarcacao().isHorizontal()) {
			setIcon(Imagens.ICON_BARCO_B_H);
		} else {
			setIcon(Imagens.ICON_BARCO_B_V);
		}
	}
	
	@Override
	public void dead() {
		if(getEmbarcacao().isHorizontal())
			setIcon(Imagens.ICON_BARCO_B_H_D);
		else
			setIcon(Imagens.ICON_BARCO_B_V_D);
		super.dead();
	}
}

