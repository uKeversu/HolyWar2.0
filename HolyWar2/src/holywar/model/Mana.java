package holywar.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Mana {

	private Image imagem;
	private int x, y;
	private int altura, largura;
	private boolean isVisible;

	private static final int LARGURA = 938;
	private static int VELOCIDADE = 2;

	public Mana(int x, int y) {

		this.x = x;
		this.y = y;
		isVisible = true;

	}

	public void load() {

		ImageIcon referencia = new ImageIcon("resource\\bread.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

	}

	public void update() {

		if (this.y < 0) {
			//this.y = largura;
			
			Random a = new Random();
			int m = a.nextInt(2500);
			this.y = m - 1000;

			Random r = new Random();
			int n = r.nextInt(1000);
			this.x = n;
		} else {
			this.y += VELOCIDADE;
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
