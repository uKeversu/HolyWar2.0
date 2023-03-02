package holywar.model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {

	private Image imagem;
	private int x, y;
	private int altura, largura;
	private boolean isVisible;

	private static final int LARGURA = 0;
	private static int VELOCIDADE = 3;

	public Enemy1(int x, int y) {

		this.x = x;
		this.y = y;
		isVisible = true;

	}

	public void load() {
		
		ImageIcon referencia = new ImageIcon("resource\\eye-of-death.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void update() {

		this.x -= VELOCIDADE;

		if (isVisible() == true) {
			if (this.x < LARGURA) {
				if (Level.getLives() > 5) {
					Level.setLives(Level.getLives() - 5);
					isVisible = false;
				} else {
					Level.lives = 0;
					Level.inGame = false;
				}
			}
		}
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, largura, altura);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

}
