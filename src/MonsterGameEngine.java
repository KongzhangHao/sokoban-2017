import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * @brief The game engine which involves an enemy monster in two player mode
 * @author hao
 * @file MonsterGameEngine.java
 * @date 03/01/2018 hao: Created MonsterGameEngine.java
 * 					hao: Added Monster movement when player moves
 *		 04/01/2018 hao: Display "Game Over" when both heroes are killed by the monster
 */
public class MonsterGameEngine extends MultiPlayerGameEngine {
	
	private Monster enemy; /**< The info and behavior of witch character */ 
	
	/**
	 * @brief Constructor, construct the normal game engine and initialise the witch hero
	 */
	public MonsterGameEngine() {
		
		/** call the parent constructor */
		super();
		
		/** Initialise the witch hero */
		enemy = new Monster(super.getMap());
	}
	
	/** 
	 * @brief Display the objects from 2d map onto the panel using images.
	 * 		  Display "Game Over" when both heroes are killed by the monster
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		/** Display GameOver if game has ended */
		if (gameOver()) {
			g.setColor(Color.white);
			g.setFont(new Font("Garamond", Font.BOLD, 80));
			g.drawString("Game Over", 80, 220);
			g.setFont(new Font("Garamond", Font.BOLD, 80));
			g.drawString("You DIE!", 130, 320);
		}
	}
	
	/**
	 * @brief Check if the current game is over. "Over" means that the 
	 * 		  two heroes are both killed in this case.
	 * @return true current game is over
	 * @return false current game is not over
	 */
	protected boolean gameOver() {
		
		/** If both heroes are killed */
		if (!super.getHero().isAlive() && !super.getHeroine().isAlive()) return true;
		return false;
	}

	/**
	 * @brief Detect the pressed key and set each key as instruction of
	 * characters' movement
	 * @param e the pressed key
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (!validKeyPress(e.getKeyCode())) return;
		
		super.keyReleased(e);
		enemy.autoMove();
		
		updateAliveStatus();
	}
	
	/**
	 * @brief Check that the key pressed is valid. Check the hero controlled by a key is still alive
	 * @param keyCode the key pressed by the player
	 * @return true The key pressed is valid
	 * @return false The key pressed is not valid
	 */
	protected boolean validKeyPress(int keyCode) {
		if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN 
				|| keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
			return getHero().isAlive();
		} else if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S
				|| keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D) {
			return getHeroine().isAlive();
		} else {
			return true;
		}
	}
	
	
	/**
	 * @brief Jump the game to the given level.
	 * @level the level to jump to
	 */
	@Override
	public void jumpLevel(int level) {
		/** call the parent's jump level */
		super.jumpLevel(level);
		
		/** reset the monster's status */
		enemy.reset();
		
		repaint();
	}

	/**
	 * @brief locate the hero's position from the map
	 */
	@Override
	public void locateHero() {
		/** call the parent's locate hero */
		super.locateHero();;
		
		/** locate the monster's position */
		enemy.locateHero();
	}

	/**
	 * @brief Scan through the map to update the heroes' latest alive status
	 */
	protected void updateAliveStatus() {
		/** Check if the object on the hero's position is the player */
		if (!GameObject.isPlayer(getMap().getPosition(super.getHero().getPosition()))) {
			super.getHero().setAlive(false);
		}
		
		if (!GameObject.isPlayer(getMap().getPosition(super.getHeroine().getPosition()))) {
			super.getHeroine().setAlive(false);
		}
	}
}
