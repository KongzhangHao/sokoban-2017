import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 * @brief The skill that heroes can use, with different purposes and different effects
 * @author hao
 * @file GameSkill.java
 * @date 04/01/2018 hao: created GameSkill.java
 * 					hao: Added flash method to temporarily display the skill on one block using timer
 * 					hao: Fixed Bug: Skill effect remains after the skill has finished
 * 		 08/01/2018 hao: Fixed Bug: Hero cannot be killed by another hero's skill
 * 		 09/01/2018 hao: Fixed Bug: Hero cannot kill the monster using skill
 * 					hao: Added method burnMap to burn the whole map with fire
 * 		 10/01/2018 hao: Fixed Bug: Jumping to another level while burning doesn't stop the fire
 * 		 14/01/2018 hao: Added limitation to the skills. A skill can only be used for once during the whole game.
 */
public class GameSkill {

	private int skillImage; /**< The image used in the skill */
	private GameEngine game; /**< The current state of the game */
	private int flashRounds; /**< The total number of flashes of the skill */
	private int flashTime; /**< The time that a single flash is lasting for */
	private int flashNum; /**< The sequence number of current flash */
	private int burnRadius; /**< The fire radius when the map is burning */
	private int numberLeft; /** The number of times the skill can be used */
	
	/**
	 * @brief Constructor, defines the behavior and info of the skill
	 * @param game The state of the game
	 * @param skillImage The image used in the skill
	 * @param flashRounds The total number of flashes of the skill
	 * @param flashTime The time that a single flash is lasting for
	 */
	public GameSkill(GameEngine game, int skillImage, int flashTime, int flashRounds) {
		this.skillImage = skillImage;
		this.game = game;
		flashNum = 0;
		this.flashRounds = flashRounds;
		this.flashTime = flashTime;
		burnRadius = 1;
		numberLeft = 1;
	}
	
	/**
	 * @brief Constructor, defines the behavior and info of the skill
	 * @param game The state of the game
	 * @param skillImage The image used in the skill
	 */
	public GameSkill(GameEngine game, int skillImage) {
		this.skillImage = skillImage;
		this.game = game;
		flashNum = 0;
		this.flashRounds = 1;
		this.flashTime = 1;
	}
	
	/**
	 * @brief Cast the skill by given the hero's position who used this skill
	 * @param heroPosition the hero's position on map
	 */
	public void castSkill(int[] heroPosition) {
		int heroPosX = heroPosition[0];
		int heroPosY = heroPosition[1];
		
		if (numberLeft == 0) return;
		
		numberLeft--;
		
		if (game.getMap().getPosition(heroPosX, heroPosY) == GameObject.warriorFront
				|| game.getMap().getPosition(heroPosX, heroPosY) == GameObject.witchFront) {
			flash(heroPosX, heroPosY + 1);
		} else if (game.getMap().getPosition(heroPosX, heroPosY) == GameObject.warriorBack
					|| game.getMap().getPosition(heroPosX, heroPosY) == GameObject.witchBack) {
			flash(heroPosX, heroPosY - 1);
		} else if (game.getMap().getPosition(heroPosX, heroPosY) == GameObject.warriorLeft
					|| game.getMap().getPosition(heroPosX, heroPosY) == GameObject.witchLeft) {
			flash(heroPosX - 1, heroPosY);
		} else if (game.getMap().getPosition(heroPosX, heroPosY) == GameObject.warriorRight
					|| game.getMap().getPosition(heroPosX, heroPosY) == GameObject.witchRight) {
			flash(heroPosX + 1, heroPosY);
		}
	}
	
	
	/**
	 * @brief Cast the skill at a single block, flash for once
	 * @param posX x-axis of skill position
	 * @param posY y-axis of skill position
	 */
	public void flash(int posX,int posY){
		/** Check special condition */
		checkSpecialCondition(posX, posY);
		
		/** Get the action to perform in each round of flashing */
		ActionListener taskPerformer = flashAction(posX, posY);
		
		/** Set and start the timer to perform the flash action */
		Timer timer = new Timer(400, taskPerformer);
		timer.setRepeats(true);
		timer.start();
	}
	
	/**
	 * @brief Check special condition on the map when skill is used
	 * @param posX x axis of skill position
	 * @param posY y axis of skill position
	 */
	protected void checkSpecialCondition(int posX, int posY) {
		/** Kill Monster if on the position */
		if (GameObject.isMonster(game.getMap().getPosition(posX, posY))) {
			killCharacter(posX, posY);
		}
		
		/** Burn the map if a wall beside the fire is broken */
		if (besideFire(posX, posY)) {
			game.getGameInfo().startBurning();
			
			burnMap(posX, posY);
		}
	}

	/**
	 * @brief Burn the whole map using fire
	 * @param posX x axis of starting fire
	 * @param posY y axis of starting fire
	 */
	private void burnMap(int posX, int posY) {
		/** Set and start the timer to perform the flash action */
		Timer timer = new Timer(500, burnAction(posX, posY));
		timer.setRepeats(true);
		timer.start();
	}

	/**
	 * @brief Get the action to perform in each round of burning
	 * @param posX x axis of starting fire
	 * @param posY y axis of starting fire
	 * @return the action to perform in burning
	 */
	protected ActionListener burnAction(int posX, int posY) {
		
		ActionListener taskPerformer = new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				
				/** Stop the fire if it's out of the map bound */
				if (game.getMap().allFire()) {

					game.getGameInfo().endBurning();
					burnRadius = 1;
					
					Timer timer = (Timer)e.getSource();
					timer.stop();
				}
				
				/** Scan through the map to find the next place to burn */
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						
						/** Get the distance from source to current position */
						double distance = Math.sqrt(Math.pow(i - posX, 2) + Math.pow(j - posY, 2));
						
						/** Burn the place if it's under burning radius */
						if(distance <= burnRadius){
							game.getMap().setPosition(i, j, GameObject.fire);
						}
					}
				}
				
				burnRadius++;
				game.updateAliveStatus();
				game.repaint();
			}
		};
		
		return taskPerformer;
	}

	/**
	 * @brief Check if current position is next to fire
	 * @param posX x axis of checked position
	 * @param posY y axis of checked position
	 * @return true the position is next to fire
	 * @return false the position is not next to fire
	 */
	protected boolean besideFire(int posX, int posY) {
		/** If current skill is already burning, return false */
		if (skillImage == GameObject.fire) return false;
		
		/** Check the four positions on four directions */
		if (game.getMap().getPosition(posX - 1, posY) == GameObject.fire
				|| game.getMap().getPosition(posX + 1, posY) == GameObject.fire
				|| game.getMap().getPosition(posX, posY - 1) == GameObject.fire
				|| game.getMap().getPosition(posX, posY + 1) == GameObject.fire) {
			return true;
		}
		return false;
	}

	/**
	 * @brief Kill the character on the given position
	 * @param posX x axis of character
	 * @param posY y axis of character
	 */
	protected void killCharacter(int posX, int posY) {
		/** Store the original object on the position */
		int originalObject = game.getMap().getPosition(posX, posY);
		
		/** Set the place to a trivial object */
		game.getMap().setPosition(posX, posY, GameObject.ground);
		
		/** Update the status of all the characters and set back the object on the position */
		game.updateAliveStatus();
		game.getMap().setPosition(posX, posY, originalObject);
	}

	/**
	 * @brief Get the action to perform in each round of flashing
	 * @param posX x axis of the place to flash
	 * @param posY y axis of the place to flash
	 * @return the action to perform in flashing
	 */
	protected ActionListener flashAction(int posX, int posY) {
		
		ActionListener taskPerformer = new ActionListener() {
			
			private int prevObject; /**< The previous game object on the position */
			
			public void actionPerformed(ActionEvent e) {
				flashNum++;
				
				/** If all the flash rounds are done, then stop the timer */
				if (flashNum > flashRounds * 2){
					flashNum = 0;
					Timer timer = (Timer)e.getSource();
					timer.stop();
				}
				
				/** Store the original object on the position */
				if (flashNum == 1) {
					prevObject = game.getMap().getPosition(posX, posY);
				}
				
				/** Set the game object on the position according to the skill and previous image */
				if (flashNum % 2 == 1) {
					game.getMap().setPosition(posX, posY, skillImage);
				} else {
					game.getMap().setPosition(posX, posY, afterSkill(prevObject));
				}
				
				game.updateAliveStatus();
				game.repaint();
			}
		};
		
		return taskPerformer;
	}
	
	/**
	 * @brief Get the state of the game object after being affected by this skill 
	 * @param object index of the game object
	 * @return the game object after being affected by the skill
	 */
	protected int afterSkill(int object) {
		if (isDestroyable(object)) return GameObject.ground;
		return object;
	}

	/**
	 * @brief Check if the object is destroyable by hero skill
	 * @param object the game object on the affected position
	 * @return true the object is destroyable
	 * @return false the object is not destroyable
	 */
	private boolean isDestroyable(int object) {
		if (object == GameObject.ground || object == GameObject.fire) return false;
		return true;
	}
}
