import java.awt.event.KeyEvent;

/**
 * @brief The game engine which involves an enemy monster in two player mode
 * @author hao
 * @file MonsterGameEngine.java
 * @date 03/01/2018 hao: Created MonsterGameEngine.java
 * 					hao: Added Monster movement when player moves
 *
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
