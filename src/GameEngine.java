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

/**
 * @brief The game engine used to handle the overall game status and 
 * 	      do reaction to user's behavior.
 * @author hao
 * @file GameEngine.java
 * @date 28/12/2017 hao: Created GameEngine.
 * 						 Added paint method to paint the map to GUI using the game image.
 * 						 Added KeyListener to listen to user's keyboard input.
 * 
 */
public class GameEngine extends JPanel{
	
	private GameMap map;
	private Hero man;
	private GameImage images;
	
	/**
	 * Constructor initialises information of game
	 * create a new map, two characters and other elements 
	 */
	public GameEngine() {
		this.setBounds(0, 0, 600, 600);
		this.setVisible(true);
		images = new GameImage();
		map = new GameMap("maps/1.map");
		man = new Hero(map);
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
				g.drawImage(images.getImg(map.getPosition(i, j)), i * 30, j * 30, this);

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

}
