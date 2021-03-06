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

import sun.security.action.GetBooleanAction;

/**
 * @brief The game engine used to handle the overall game status and 
 * 	      do reaction to user's behavior.
 * @author hao
 * @file GameEngine.java
 * @date 28/12/2017 hao: Created GameEngine.java.
 * 						 Added paint method to display the map on GUI using provided images.
 * 						 Added KeyListener to listen to user's keyboard input.
 * 					     Added method to jump the game to a specific level and refresh all states
 * 		 04/12/2017 hao: Fixed bug: Jump to invalid levels create multiple hero characters on map.
 * 	     10/01/2018 hao: Added music to the game
 */
public class GameEngine extends JPanel{
	
	private GameMap map; /**< the current map of game */
	private Warrior man; /**< the player's hero in game */
	private GameImage images; /**< images to be used in game */
	private StatusBar bar; /**< game's status bar */
	private GameInfo info; /**< game state's information */
	private GameMusic music; /**< game music player */
	
	
	/**
	 * @brief Constructor, initialise information of game
	 */
	public GameEngine() {
		
		this.setBounds(0, 0, 600, 600);
		this.setVisible(true);
		
		images = new GameImage();
		map = new GameMap("maps/1.map");
		man = new Warrior(map);
		bar = new StatusBar(this);
		info = new GameInfo();
		music = new GameMusic("res/bg_quieter.wav", "res/game_over.wav");
		
		/** Start playing the bg music */
		music.playBGM();
	}	
	
	/** 
	 * @brief Display the objects from 2d map onto the panel using images.
	 * 		  Display "Game Over" when both heroes are killed by the monster
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		/** Display all objects */
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				g.drawImage(images.getImg(map.getPosition(i, j)), i * 30, j * 30, this);

			}
		}
		
		/** Display Level number */
		g.setColor(Color.white);
		g.setFont(new Font("Garamond", Font.BOLD, 24));
		g.drawString("Level  " + info.getLevel() , 50, 50);
		
		/** Display GameOver if game has ended */
		if (gameOver()) {
			g.setColor(Color.white);
			g.setFont(new Font("Garamond", Font.BOLD, 80));
			g.drawString("Game Over", 80, 220);
			g.setFont(new Font("Garamond", Font.BOLD, 80));
			g.drawString("You DIE!", 130, 320);
			
			/** Start playing game over music */
			music.playGameOverMusic();
		}
	}
	
	/**
	 * @brief Check if the current game is over. Always false in this case
	 * @return true current game is over
	 * @return false current game is not over
	 */
	protected boolean gameOver() {
		return false;
	}

	/**
	 * @brief locate the hero's position from the map
	 */
	public void locateHero() {
		man.locateHero();
	}
	
	/**
	 * @brief Detect the pressed key and set each key as instruction of
	 * characters' movement
	 * @param e the pressed key
	 */
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		
		map.backUpMap();
		
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
		
		checkGameStatus();
		
		/** Repaint the objects onto the panel */
		repaint();
	}
	
	/**
	 * @brief Check the current status of the game. If it has ended or passed
	 */
	protected void checkGameStatus() {
		/** check if the current level is passed */
		if (info.isLevelPassed() || map.levelPassed()) {
			info.setLevelPassed(true);
			jumpLevel(info.getLevel() + 1);
		}
	}
	
	
	/**
	 * @brief Jump the game to the given level.
	 * @level the level to jump to
	 */
	public void jumpLevel(int level) {
		/** Do nothing if the given level is invalid or burning hasn't ended */
		if (invalidLevel(level) || info.isBurning()) return;
		
		info.setLevel(level);
		
		String path = "maps/" + level + ".map";
		map.loadMap(path);
		
		info.reset();
		man.reset();
		
		music.playBGM();
		
		repaint();
	}
	
	/**
	 * @brief Scan through the map to update the heroes' latest alive status
	 */
	public void updateAliveStatus() {
		/** Check if the object on the hero's position is the player */
		if (!GameObject.isPlayer(getMap().getPosition(man.getPosition()))) {
			man.setAlive(false);
		}
	}
	
	/**
	 * @brief Check if the given level is valid
	 * @param level the level of game
	 * @return true The given level is valid
	 * @return false The given level is invalid
	 */
	protected boolean invalidLevel(int level) {
		if (level < 1 || level > 15) return true;
		return false;
	}

	/**
	 * @brief Get the status bar of the game
	 * @return Status bar of the game
	 */
	public StatusBar getStatusBar() {
		return bar;
	}

	/**
	 * @brief Get the current level of game
	 * @return level number
	 */
	public int getLevel() {
		return info.getLevel();
	}
	
	/**
	 * @brief Get the current game map
	 * @return game map
	 */
	public GameMap getMap() {
		return map;
	}
	
	/**
	 * @brief Get the hero status
	 * @return hero
	 */
	public Hero getHero() {	
		return man;
	}
	
	/**
	 * @brief Get game's info
	 * @return game's info
	 */
	public GameInfo getGameInfo() {
		return info;
	}
}
