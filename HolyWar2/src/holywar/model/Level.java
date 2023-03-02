package holywar.model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Level extends JPanel implements ActionListener {

	private Image fundo;
	private Image winner;
	private Image menu;
	private Player bible;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Enemy2> enemy2;
	private List<Birds> bird;
	private List<Mana> mana;
	private List<Boss> boss;
	private List<BossAttack> trident;
	public static boolean inGame;
	public static boolean inMenu;
	public static boolean inBoss;
	private boolean inWin;
	private int score;
	public static int lives = 100;
	private int bossLife = 1000;
	public static int qtdMana = 3;

	public Level() {

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon imagem = new ImageIcon("resource\\fundoNuvens_resized.jpg");
		fundo = imagem.getImage();

		ImageIcon referencia = new ImageIcon("resource\\winner.jpg");
		winner = referencia.getImage();

		ImageIcon reference = new ImageIcon("resource\\menu.png");
		menu = reference.getImage();

		bible = new Player();
		bible.load();

		addKeyListener(new keyAdapter());

		timer = new Timer(5, this);
		timer.start();

		inicializaEnemy1();
		inicializaEnemy2();
		inicializaBoss();
		inicializaBirds();
		inicializaMana();

		if (inBoss = true) {
			inicializaTrident();
		}

		if (lives < 5 || lives == 0) {
			inGame = false;
			lives = 0;
		}

		if (qtdMana < 1) {
			qtdMana = 0;
		}

		inMenu = true;
		inGame = false;
		inWin = false;
		inBoss = false;

	}

	public void inicializaEnemy1() {

		int cordenadas[] = new int[61];
		enemy1 = new ArrayList<Enemy1>();

		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 2000);
			int y = (int) (Math.random() * 550 + 30);
			enemy1.add(new Enemy1(x, y));

		}
	}

	public void inicializaEnemy2() {

		int cords[] = new int[100];
		enemy2 = new ArrayList<Enemy2>();

		for (int t = 0; t < cords.length; t++) {
			int x = (int) (Math.random() * 10000 + 2500);
			int y = (int) (Math.random() * 500 + 30);
			enemy2.add(new Enemy2(x, y));
		}
	}

	public void inicializaBirds() {

		int cordenadas[] = new int[5];
		bird = new ArrayList<Birds>();

		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) (Math.random() * 15000 + 1024);
			int y = (int) (Math.random() * 768 + 0);
			bird.add(new Birds(x, y));
		}
	}

	public void inicializaMana() {

		int cordenada[] = new int[10];
		mana = new ArrayList<Mana>();

		for (int m = 0; m < cordenada.length; m++) {
			int x = (int) (Math.random() * 500 + 1);
			int y = (int) (Math.random() * (- 1000) - 500);
			mana.add(new Mana(x, y));
		}
	}

	public void inicializaBoss() {

		int cords[] = new int[1];
		boss = new ArrayList<Boss>();

		for (int q = 0; q < cords.length; q++) {
			int x = (int) (Math.random() * 1 + 7000);
			int y = (int) (Math.random() * 1 + 335);
			boss.add(new Boss(x, y));
		}
	}

	public void inicializaTrident() {

		int coords[] = new int[100];
		trident = new ArrayList<BossAttack>();

		for (int v = 0; v < coords.length; v++) {
			int x = (int) (Math.random() * 15000 + 35000);
			int y = (int) (Math.random() * 768 + 30);
			trident .add(new BossAttack(x, y));
		}
	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;

		if (inMenu == true) {
			graficos.drawImage(menu, -45, 0, null);
		} else {

			if (inGame == true) {

				graficos.drawImage(fundo, 0, 0, null);

				for (int p = 0; p < bird.size(); p++) {
					Birds q = bird.get(p);
					q.load();
					graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
				}

				for (int mn = 0; mn < mana.size(); mn++) {
					Mana nm = mana.get(mn);
					nm.load();
					graficos.drawImage(nm.getImagem(), nm.getX(), nm.getY(), this);
				}

				graficos.drawImage(bible.getBible(), bible.getX(), bible.getY(), this);

				List<Tiro> tiros = bible.getTiros();
				for (int i = 0; i < tiros.size(); i++) {
					Tiro m = tiros.get(i);
					m.load();
					graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
				}

				for (int o = 0; o < enemy1.size(); o++) {
					Enemy1 in = enemy1.get(o);
					in.load();
					graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
				}

				for (int u = 0; u < enemy2.size(); u++) {
					Enemy2 on = enemy2.get(u);
					on.load();
					graficos.drawImage(on.getImagem(), on.getX(), on.getY(), this);
				}

				for (int b = 0; b < boss.size(); b++) {
					Boss l = boss.get(b);
					l.load();
					graficos.drawImage(l.getImagem(), l.getX(), l.getY(), this);
				}

				Font font = new Font("SansSerif", Font.BOLD, 30);
				g.setFont(font);
				g.drawString("♡: " + getLives(), 900, 50);

				Font fonts = new Font("SansSerif", Font.BOLD, 30);
				g.setFont(fonts);
				g.drawString("Maná: " + qtdMana, 879, 80);

				Font fonte = new Font("SansSerif", Font.BOLD, 20);
				g.setFont(fonte);
				g.drawString("SCORE: " + score, 50, 50);

				if (inBoss == true) {

					for (int f = 0; f < trident.size(); f++) {
						BossAttack hp = trident.get(f);
						hp.load();
						graficos.drawImage(hp.getImagem(), hp.getX(), hp.getY(), this);

						Font letter = new Font("SansSerif", Font.BOLD, 30);
						g.setFont(letter);
						g.drawString("BOSS LIFE: " + bossLife, 400, 670);

					}
				}

			} else {

				if (inWin == true) {

					graficos.drawImage(winner, 0, 0, null);

					Font letra = new Font("SansSerif", Font.BOLD, 20);
					g.setFont(letra);
					g.drawString("SCORE: " + score, 425, 670);

				} else {

					ImageIcon gameOver = new ImageIcon("resource\\game-over.jpg");
					graficos.drawImage(gameOver.getImage(), -45, 0, null);

					Font let = new Font("SansSerif", Font.BOLD, 20);
					g.setFont(let);
					g.drawString("SCORE: " + score, 425, 670);

					g.drawString("♡: " + getLives(), 900, 50);
				}

			}
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		bible.update();

		for (int p = 0; p < bird.size(); p++) {
			Birds on = bird.get(p);
			if (on.isVisible()) {
				on.update();
			} else {
				bird.remove(p);
			}
		}

		for (int mn = 0; mn < mana.size(); mn++) {
			Mana nm = mana.get(mn);
			if (nm.isVisible()) {
				nm.update();
			} else {
				mana.remove(mn);
			}
		}

		List<Tiro> tiros = bible.getTiros();

		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisible()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}

		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			if (in.isVisible()) {
				in.update();
			} else {
				enemy1.remove(o);
			}
		}

		for (int j = 0; j < enemy1.size(); j++) {
			Enemy2 pi = enemy2.get(j);
			if (pi.isVisible()) {
				pi.update();
			} else {
				enemy2.remove(j);
			}
		}

		for (int h = 0; h < boss.size(); h++) {
			Boss em = boss.get(h);
			if (em.isVisible()) {
				em.update();
			} else {
				boss.remove(h);
			}
		}

		for (int hp = 0; hp < trident.size(); hp++) {
			BossAttack ph = trident.get(hp);
			if (ph.isVisible()) {
				ph.update();
			} else {
				trident.remove(hp);
			}
		}

		checkColisoes();
		repaint();
	}

	public void checkColisoes() {

		Rectangle shapeBible = bible.getBounds();
		Rectangle shapeEnemy1;
		Rectangle shapeEnemy2;
		Rectangle shapeTiro;
		Rectangle shapeBird;
		Rectangle shapeBirds;
		Rectangle shapeBoss;
		Rectangle shapeTrident;
		Rectangle shapeMana;

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			shapeEnemy1 = tempEnemy1.getBounds();
			if (shapeBible.intersects(shapeEnemy1)) {
				if (bible.isShield()) {
					tempEnemy1.setVisible(false);
					score += 25;
				} else {
					if (getLives() != 0) {
						setLives(getLives() - 25);
						tempEnemy1.setVisible(false);
					} else {
						setLives(0);
						bible.setVisible(false);
						tempEnemy1.setVisible(false);
						inGame = false;
					}
				}
			}
		}

		for (int mn = 0; mn < mana.size(); mn++) {
			Mana tempMana = mana.get(mn);
			shapeMana = tempMana.getBounds();
			if (shapeBible.intersects(shapeMana)) {
				qtdMana++;
				tempMana.setVisible(false);
			}
		}

		for (int r = 0; r < enemy2.size(); r++) {
			Enemy2 tempEnemy2 = enemy2.get(r);
			shapeEnemy2 = tempEnemy2.getBounds();
			if (shapeBible.intersects(shapeEnemy2)) {
				if (bible.isShield()) {
					tempEnemy2.setVisible(false);
					score += 25;
				} else {
					if (getLives() != 0) {
						setLives(getLives() - 15);
						tempEnemy2.setVisible(false);
					} else {
						setLives(0);
						bible.setVisible(false);
						tempEnemy2.setVisible(false);
						inGame = false;
					}
				}
			}
		}

		for (int k = 0; k < bird.size(); k++) {
			Birds tempBird = bird.get(k);
			shapeBird = tempBird.getBounds();
			if (shapeBird.intersects(shapeBible)) {
				tempBird.setVisible(false);
				score += 150;

			}
		}

		List<Tiro> shoots = bible.getTiros();
		for (int j = 0; j < shoots.size(); j++) {
			Tiro tempTiro = shoots.get(j);

			shapeTiro = tempTiro.getBounds();

			for (int w = 0; w < bird.size(); w++) {
				Birds tempBirds = bird.get(w);
				shapeBirds = tempBirds.getBounds();

				if (shapeTiro.intersects(shapeBirds)) {

					tempBirds.setVisible(false);
					tempTiro.setVisible(false);
					inGame = false;
					setLives(0);

				}
			}
		}

		List<Tiro> tiros = bible.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);

			shapeTiro = tempTiro.getBounds();

			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				shapeEnemy1 = tempEnemy1.getBounds();

				if (shapeTiro.intersects(shapeEnemy1)) {

					tempEnemy1.setVisible(false);
					tempTiro.setVisible(false);

					score += 25;

				}
			}
		}

		for (int h = 0; h < tiros.size(); h++) {
			Tiro tempTiro = tiros.get(h);

			shapeTiro = tempTiro.getBounds();

			for (int w = 0; w < enemy2.size(); w++) {
				Enemy2 tempEnemy2 = enemy2.get(w);
				shapeEnemy2 = tempEnemy2.getBounds();

				if (shapeTiro.intersects(shapeEnemy2)) {

					tempEnemy2.setVisible(false);
					tempTiro.setVisible(false);

					score += 50;

				}
			}
		}

		for (int g = 0; g < tiros.size(); g++) {
			Tiro tempTiro = tiros.get(g);

			shapeTiro = tempTiro.getBounds();

			for (int v = 0; v < boss.size(); v++) {
				Boss tempBoss = boss.get(v);
				shapeBoss = tempBoss.getBounds();

				if (shapeTiro.intersects(shapeBoss)) {
					if (bossLife > 25) {
						bossLife = bossLife - 25;
						tempTiro.setVisible(false);
					} else {
						bossLife = 0;

						bible.setVisible(false);
						tempBoss.setVisible(false);
						tempTiro.setVisible(false);
						inGame = false;
						inWin = true;
						inBoss = false;
						score += 1500;
					}
				}
			}
		}

		for (int l = 0; l < boss.size(); l++) {
			Boss tempBoss = boss.get(l);
			shapeBoss = tempBoss.getBounds();
			if (shapeBible.intersects(shapeBoss)) {
				if (bible.isShield()) {

				} else {
					setLives(0);
					bible.setVisible(false);
					tempBoss.setVisible(false);
					inGame = false;
				}
			}
		}

		for (int hp = 0; hp < trident.size(); hp++) {
			BossAttack tempTrident = trident.get(hp);
			shapeTrident = tempTrident.getBounds();
			if (shapeTrident.intersects(shapeBible)) {
				if (bible.isShield()) {
					tempTrident.setVisible(false);
				} else {
					if (getLives() > 5) {
						setLives(getLives() - 15);
						tempTrident.setVisible(false);
					} else {
						setLives(0);
						bible.setVisible(false);
						tempTrident.setVisible(false);
						inGame = false;
					}
				}
			}
		}

	
	}

	public static int getLives() {
		return lives;
	}

	public static void setLives(int lives) {
		Level.lives = lives;
	}

	private class keyAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			bible.keyPressed(e);

		}

		@Override
		public void keyReleased(KeyEvent e) {
			bible.keyRelease(e);

		}
	}
}
