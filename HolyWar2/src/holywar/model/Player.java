
package holywar.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import holywar.model.Level;

public class Player implements ActionListener {

	private int x, y;
	private int dx, dy;
	private Image bible;
	private Image imagem;
	private int altura, largura;
	private List<Tiro> tiros;
	private boolean isVisible, isShield;
	private Timer timer;

	public Player() {

		this.x = 100;
		this.y = 100;

		isVisible = true;
		isShield = false;

		tiros = new ArrayList<Tiro>();

		timer = new Timer(5000, this);
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Level.qtdMana > 0) {
			if (isShield == true) {
				Level.qtdMana --;
				shield();
				isShield = false;
			}
		}

		if (isShield == false) {
			load();
		}
	}

	public void load() {

		if (isShield == false) {
			ImageIcon imagem = new ImageIcon("resource\\bible.png");
			bible = imagem.getImage();
		}
		altura = bible.getHeight(null);
		largura = bible.getWidth(null);
	}

	public void update() {

		x += dx;
		y += dy;

	}

	public void tiroSimples() {

		this.tiros.add(new Tiro(x + largura, y + (altura / 10)));
	}

	public void shield() {

		isShield = true;
		ImageIcon imagem = new ImageIcon("resource\\bible-shield.png");
		bible = imagem.getImage();

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);

	}

	public void keyPressed(KeyEvent tecla) {

		int code = tecla.getKeyCode();

		if (code == KeyEvent.VK_W) {
			dy = -5;
		}
		if (code == KeyEvent.VK_S) {
			dy = 5;
		}
		if (code == KeyEvent.VK_A) {
			dx = -5;
		}
		if (code == KeyEvent.VK_D) {
			dx = 5;
		}
		if (code == KeyEvent.VK_SPACE) {
			tiroSimples();
		}
		if (code == KeyEvent.VK_E) {
			if (Level.qtdMana > 0) {
				shield();
			}else {
				isShield = false;
				
				
				
				
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			Level.inMenu = false;
			Level.inGame = true;
		}
	}

	public void keyRelease(KeyEvent tecla) {

		int code = tecla.getKeyCode();

		if (code == KeyEvent.VK_W) {
			dy = 0;
		}
		if (code == KeyEvent.VK_S) {
			dy = 0;
		}
		if (code == KeyEvent.VK_A) {
			dx = 0;
		}
		if (code == KeyEvent.VK_D) {
			dx = 0;
		}
		if (code == KeyEvent.VK_SPACE) {

		}

	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getBible() {
		return bible;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isShield() {
		return isShield;
	}

	public void setShield(boolean isShield) {
		this.isShield = isShield;
	}

}
