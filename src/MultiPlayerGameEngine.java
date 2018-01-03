import java.awt.event.KeyEvent;

/**
 * @brief The game engine that supports two player mode with two 
 * 		  characters playing together
 * @author hao
 * @file MultiPlayerGameEngine.java
 * @date 03/01/2018 hao: Created MultiPlayerGameEngine.java
 * 					hao: Added Witch controller with key event
 * 					hao: Updated jump level to reset witch's status
 */
public class MultiPlayerGameEngine extends GameEngine {

	private Witch woman; /**< The info and behavior of witch character */ 
	
	/**
	 * @brief Constructor, construct the normal game engine and initialise the witch hero
	 */
	public MultiPlayerGameEngine() {
		
		/** call the parent constructor */
		super();
		
		/** Initialise the witch hero */
		woman = new Witch(super.getMap());
	}
	
	/**
	 * @brief Detect the pressed key and set each key as instruction of
	 * characters' movement
	 * @param e the pressed key
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		
		/** save the current status of map */
		super.getMap().backUpMap();
		
		/** check if the keycode is a movement of warrior */
		checkWarriorMovement(keycode);
		
		/** check if the keycode is a movement of witch */
		checkWitchMovement(keycode);
		
		/** check if the current level is passed */
		checkGameStatus();
		
		/** Repaint the objects onto the panel */
		repaint();
	}
	
	/**
	 * @brief Check if the pressed key is controlling th witch's movement
	 * @param keycode The key pressed by the user
	 */
	private void checkWarriorMovement(int keycode) {
		/** check if the hero is still alive */
		if (!super.getHero().isAlive()) return;
		
		/** Choose the right reaction according to the key pressed */
		switch (keycode) {
			
			/** Player Warrior Movement */
			case KeyEvent.VK_UP:
				super.getHero().moveUp();
				break;
			case KeyEvent.VK_DOWN:
				super.getHero().moveDown();
				break;
			case KeyEvent.VK_LEFT:
				super.getHero().moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				super.getHero().moveRight();
				break;
			default:
				break;
		}
	}
	
	/**
	 * @brief Check if the pressed key is controlling th witch's movement
	 * @param keycode The key pressed by the user
	 */
	private void checkWitchMovement(int keycode) {
		/** check if the hero is still alive */
		if (!woman.isAlive()) return;
		
		/** Choose the right reaction according to the key pressed */
		switch (keycode) {
			
			/** Player Witch Movement */
			case KeyEvent.VK_W:
				woman.moveUp();
				break;
			case KeyEvent.VK_S:
				woman.moveDown();
				break;
			case KeyEvent.VK_A:
				woman.moveLeft();
				break;
			case KeyEvent.VK_D:
				woman.moveRight();
				break;
			default:
				break;
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
		
		/** reset the witch's status */
		woman.reset();
		
		repaint();
	}

	/**
	 * @brief locate the hero's position from the map
	 */
	@Override
	public void locateHero() {
		/** call the parent's locate hero */
		super.locateHero();;
		
		/** locate the witch's position */
		woman.locateHero();
	}
	
	/**
	 * @brief Get the player2 hero
	 * @return the hero status of player2.
	 */
	public Hero getHeroine() {
		return woman;
	}
	
}
