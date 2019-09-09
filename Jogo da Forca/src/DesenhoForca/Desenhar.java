package DesenhoForca;

import javax.swing.JLabel;

public class Desenhar {
	
	public void desenhaBoneco (int n, JLabel imgforca) {
		imgforca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DesenhoForca/" + n + ".png")));
	}
}
