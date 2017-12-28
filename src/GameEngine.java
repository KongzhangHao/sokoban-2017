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
	private StatusBar bar;
	
	/**
	 * @brief Constructor, initialise information of game
	 */
	public GameEngine() {
		this.setBounds(0, 0, 600, 600);
		this.setVisible(true);
		images = new GameImage();
		map = new GameMap("maps/1.map");
		man = new Hero(map);
		bar = new StatusBar(this);
	}	
	
	/** 
	 * @brief Display the objects from map onto the panel using images.
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		boolean over=false;
		
		/** Display all objects */
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				g.drawImage(images.getImg(map.getPosition(i, j)), i * 30, j * 30, this);

			}
		}
		
		/** Display Level number */
		g.setColor(Color.white);
		g.setFont(new Font("Garamond", Font.BOLD, 24));
		g.drawString("Level  " , 50, 50);
		
		/** Display GameOver if game has ended */
		if (over==true) {
			g.setColor(Color.white);
			g.setFont(new Font("Garamond", Font.BOLD, 80));
			g.drawString("Game Over", 80, 220);
			g.setFont(new Font("Garamond", Font.BOLD, 80));
			g.drawString("You DIE!", 130, 320);
		}
	}
	
	/**
	 * @brief Detect the pressed key and set each key as instruction of
	 * characters' movement
	 * @param e the pressed key
	 */
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		
		/** Choose the right reaction according to the key pressed */
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
		
		/** Repaint the objects onto the panel */
		repaint();
	}
	
	/**
	 * @brief Get the status bar of the game
	 * @return Status bar of the game
	 */
	public StatusBar getStatusBar() {
		return bar;
	}

}
