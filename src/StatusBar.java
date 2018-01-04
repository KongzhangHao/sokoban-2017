
/**
 * @brief The functions linked to the buttons in the status bar
 * @author hao
 * @file StatusBar.java
 * @date 28/12/2017 hao: Created StatusBar.java.
 *  	 03/01/2018 hao: Updated the back track button	
 *  	 04/01/2018 hao: Fixed bug: the user can still go back after the game is over			 
 */
public class StatusBar {
	
	private GameEngine game; /**< The overall game controller */
	
	/**
	 * @brief Constructor, initialise the status bar.
	 * @param game the engine of the game
	 */
	public StatusBar(GameEngine game) {
		this.game = game;
	}
	
	/**
	 * @brief Go back to the previous level
	 */
	public void goBack() {
		game.jumpLevel(game.getLevel() - 1);
	}
	
	/**
	 * @brief Go to the next level
	 */
	public void goNext() {
		game.jumpLevel(game.getLevel() + 1);
	}

	/**
	 * @brief Go back to the previous state of the game
	 */
	public void back() {
		/** If the game is over, then do nothing */
		if (game.gameOver()) return;
		
		game.getMap().loadBackUp();
		
		game.locateHero();
		
		game.repaint();
	}
	
	/**
	 * @brief Restart the current level
	 */
	public void reStart() {
		game.jumpLevel(game.getLevel());
	}
	
	/**
	 * @brief Go to the chosen level
	 */
	public void choice(int level) {
		game.jumpLevel(level);
	}
	
}
