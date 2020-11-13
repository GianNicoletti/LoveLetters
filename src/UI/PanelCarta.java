package UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanelCarta extends JPanel {
	private BufferedImage img;

	/**
	 * Create the panel.
	 */
	public PanelCarta() {

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		Dimension currentDimension = getSize();
		// g2.scale(currentDimension.getWidth() / WIDTH, currentDimension.getHeight() /
		// HEIGHT);
		g2.drawImage(img,0,0,currentDimension.width,currentDimension.height,null);
	}

	public void setImg(BufferedImage img) {
		this.img=img;
	}

}
