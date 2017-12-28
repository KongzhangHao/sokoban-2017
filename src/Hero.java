import com.sun.org.glassfish.external.statistics.annotations.Reset;

/**
 * @brief The basic information and behavior of a hero
 * @author hao
 * @file Hero.java
 * @date 27/12/2017 hao: Created Hero class.
 * 						 Added Hero movement to empty ground and slime.
 * 					     Added Hero movement under condition of dye and dyed slime.
 * 						 Set initial position of the hero when loading map.
 * 	     28/12/2017 hao: Fixed hero position change during hero movements.
 * 						 Hero face direction changes when move to a different direction
 * 						 Fixed hero face direction change when the user tries to move to a blocked place
 */
public class Hero {
	
	private int[] position; /**<the x,y position of the hero*/
	private Boolean alive; /**<the hero's alive status*/
	private GameMap map; /**<the current game map*/
	private Boolean onSlime; /**<store whether the hero is stepping on a slime*/
	
	/**
	 * @brief Constructor. Initialise position and alive status
	 * @param map the current map of the game
	 */
	public Hero(GameMap map) {
		position = new int[2];
		this.map = map;
		reset();
	}
	
	/**
	 * @brief Reset the hero status
	 */
	public void reset() {
		alive = true;
		onSlime = false;
		locateHero();
	}
	
	/**
	 * @brief MOVE HERO
	 * @Explaination
	 * check if destination is movable
	 * change the hero's original position to its original object
	 * if on slime, then set the position to be slime, else set as ground
	 * if new position has slime on it, then set onSlime to be true
	 * set player to the new position
	 * update player's position
	 */
	/**
	 * @brief Move the hero up
	 */
	public void moveUp() {
		int[] destination = {position[0], position[1] - 1};
		
		/** If not movable, then change the facing direction */
		if (!movable(destination)) {
			map.setPosition(position, GameObject.playerBack);
			return; 
		}
		
		/** If currently on slime, then set the current position to be a slime */
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]--;
		onSlime = false;
		
		/** Set hero to the new position */
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.playerBack);
	}
	
	/**
	 * @brief Move the hero down
	 */
	public void moveDown() {
		int[] destination = {position[0], position[1] + 1};
		
		/** If not movable, then change the facing direction */
		if (!movable(destination)) {
			map.setPosition(position, GameObject.playerFront);
			return; 
		}
		
		/** If currently on slime, then set the current position to be a slime */
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]++;
		onSlime = false;
		
		/** Set hero to the new position */
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.playerFront);
	}
	
	/**
	 * @brief Move the hero towards left
	 */
	public void moveLeft() {
		int[] destination = {position[0] - 1, position[1]};
		
		/** If not movable, then change the facing direction */
		if (!movable(destination)) {
			map.setPosition(position, GameObject.playerLeft);
			return; 
		} 
		
		/** If currently on slime, then set the current position to be a slime */
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]--;
		onSlime = false;
		
		/** Set hero to the new position */
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.playerLeft);
	}
	
	/**
	 * @brief Move the hero towards right
	 */
	public void moveRight() {
		int[] destination = {position[0] + 1, position[1]};
		
		/** If not movable, then change the facing direction */
		if (!movable(destination)) {
			map.setPosition(position, GameObject.playerRight);
			return; 
		} 
		
		/** If currently on slime, then set the current position to be a slime */
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]++;
		onSlime = false;
		
		/** Set hero to the new position */
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.playerRight);
	}
	
	/**
	 * @brief Check if the destination of the hero is movable 
	 * @param destination The position that the hero is moving to
	 * @return true The hero can move towards the destination without 
	 * 		   any other concerns
	 * @return false The hero cannot move towards the destination
	 * @explanation if the destination is ground or slime, can go straight away.
	 * 			    if the destination is dye or dyedSlime, need to check the next step
	 * 				if next step is ground or slime, then move the dye to the right place
	 */
	private boolean movable(int[] destination) {
		boolean ret = false;
		if (map.getPosition(destination) == GameObject.ground 
				|| map.getPosition(destination) == GameObject.slime) {
			ret = true;
		}
		if (map.getPosition(destination) == GameObject.dye
			|| map.getPosition(destination) == GameObject.dyedSlime) {
			int[] nextStep = {2 * destination[0] - position[0]
					, 2 * destination[1] - position[1]};
			if (map.getPosition(nextStep) == GameObject.ground 
					|| map.getPosition(nextStep) == GameObject.slime) {
				ret = true;
				if (map.getPosition(nextStep) == GameObject.slime) {
					map.setPosition(nextStep, GameObject.dyedSlime);
				} else {
					map.setPosition(nextStep, GameObject.dye);
				}
				if (map.getPosition(destination) == GameObject.dye) {
					map.setPosition(destination, GameObject.ground);
				} else {
					map.setPosition(destination, GameObject.slime);
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * @brief Locate the position of the hero from the given 2d array map
	 * @explanation Get the hero position from the game map 
	 * 				and set the hero's position using it.
	 */
	public void locateHero() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (isHero(map.getPosition(i, j))) {
					setPosition(i, j);
					return;
				}
			}
		}
	}
	
	/**
	 * @brief Determine if the object on the position is a hero
	 * @param object the index of the object
	 * @return true the object is hero
	 * @return false the object is not hero
	 */
	private boolean isHero(int object) {
		if (object == GameObject.playerBack
				|| object == GameObject.playerFront
				|| object == GameObject.playerLeft
				|| object == GameObject.playerRight
			) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * @brief Get hero's current position
	 * @return array of hero's position
	 */
	public int[] getPosition() {
		return position;
	}
	
	/**
	 * @brief Check if hero is still alive
	 * @return true hero is alive
	 * @return false hero is dead
	 */
	public Boolean isAlive() {
		return alive;
	}
	
	
	/**
	 * @brief Set hero's alive status
	 * @param alive hero's alive status
	 */
	public void setAlive(Boolean alive) {
		this.alive = alive;	
	}
	
	/**
	 * @brief Set hero's position
	 * @param x position of x axis
	 * @param y position of y axis
	 */
	public void setPosition(int x, int y) {
		position[0] = x;
		position[1] = y;
	}
	
}
