import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameEngine extends JPanel{
	
	private GameMap map;
	private static Image[] imgs = null;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private Hero man;
	/**
	 * in constructor initializes information of game
	 * create a new map, two characters and other elements 
	 * @param level the start level(initial level is 1)
	 */
	public GameEngine() {
		this.setBounds(0, 0, 600, 600);
		this.setVisible(true);
		map = new GameMap();
		map.loadMap("maps/1.map");
		map.printMap();
		man = new Hero(map);
		imgs = new Image[] {
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/red_magma.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/wall.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/floor.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/red_dye.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/green_slime.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/red_role_face.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/red_role_back.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/red_role_right.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/red_role_left.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/red_slime.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/pickaxe.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/stairs.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/witch_face.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/witch_left.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/witch_right.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/witch_back.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/fire.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/ice.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/thunder.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/pickaxe_effect.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/ulti.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource(
						"imgs/kill_slime.gif"))};
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 * this method is used to print game map
	 * also the sentence for showing level or "game over" 
	 */
	public void paint(Graphics g) {
		boolean over=false;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				g.drawImage(imgs[map.getPosition(i, j)], i * 30, j * 30, this);

			}
		}
		//current level
		g.setColor(Color.white);
		g.setFont(new Font("Garamond", Font.BOLD, 24));
		g.drawString("Level  " , 50, 50);
		//if game over, show this in the centre of the screen
		if (over==true) {
			g.setColor(Color.white);
			g.setFont(new Font("Garamond", Font.BOLD, 80));
			g.drawString("Game Over", 80, 220);
			g.setFont(new Font("Garamond", Font.BOLD, 80));
			g.drawString("You DIE!", 130, 320);
		}
	}
	
	// Handle Keyboard inputs
	/**
	 * this method is used to detect the pressed key
	 * and set each key as instruction of
	 * two characters' movement, skills and "back"
	 * @param e the pressed key
	 * @Pre game starts, and the pressed key is related to one action
	 * @Post call the action method
	 */
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch (keycode) {
			case KeyEvent.VK_UP:
				man.moveUp();
				break;
			case KeyEvent.VK_DOWN:
				man.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				man.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				man.moveRight();
				break;
			default:
				break;
		}
		repaint();
	}
	
	public void goBack() {
		
	}
	
	public void goNext() {
		
	}

	public void back() {
	
	}
	
	public void reStart() {
		
	}
	
	public void choice(int level) {
		
	}
	
	public void setLevel(int level) {
		
	}
}
