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
	private boolean alive; /**<the hero's alive status*/
	private GameMap map; /**<the current game map*/
	private boolean onSlime; /**<store whether the hero is stepping on a slime*/ 
	
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
			map.setPosition(position, backIndex());
			return; 
		}
		
		/** If currently on slime, then set the current position to be a slime */
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]--;
		onSlime = false;
		
		/** Set hero to the new position */
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, backIndex());
	}
	
	/**
	 * @brief Move the hero down
	 */
	public void moveDown() {
		int[] destination = {position[0], position[1] + 1};
		
		/** If not movable, then change the facing direction */
		if (!movable(destination)) {
			map.setPosition(position, frontIndex());
			return; 
		}
		
		/** If currently on slime, then set the current position to be a slime */
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]++;
		onSlime = false;
		
		/** Set hero to the new position */
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, frontIndex());
	}
	
	/**
	 * @brief Move the hero towards left
	 */
	public void moveLeft() {
		int[] destination = {position[0] - 1, position[1]};
		
		/** If not movable, then change the facing direction */
		if (!movable(destination)) {
			map.setPosition(position, leftIndex());
			return; 
		} 
		
		/** If currently on slime, then set the current position to be a slime */
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]--;
		onSlime = false;
		
		/** Set hero to the new position */
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, leftIndex());
	}
	
	/**
	 * @brief Move the hero towards right
	 */
	public void moveRight() {
		int[] destination = {position[0] + 1, position[1]};
		
		/** If not movable, then change the facing direction */
		if (!movable(destination)) {
			map.setPosition(position, rightIndex());
			return; 
		} 
		
		/** If currently on slime, then set the current position to be a slime */
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]++;
		onSlime = false;
		
		/** Set hero to the new position */
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, rightIndex());
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
	protected boolean movable(int[] destination) {
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
	protected boolean isHero(int object) {
		if (object == rightIndex()
				|| object == backIndex()
				|| object == leftIndex()
				|| object == frontIndex()
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
	
	/**
	 * @brief Get the hero's front image index
	 * @return front image index
	 */
	protected int frontIndex() {
		return 0;
	}
	
	/**
	 * @brief Get the hero's back image index
	 * @return front image index
	 */
	protected int backIndex() {
		return 0;
	}
	
	/**
	 * @brief Get the hero's left image index
	 * @return front image index
	 */
	protected int leftIndex() {
		return 0;
	}
	
	/**
	 * @brief Get the hero's right image index
	 * @return front image index
	 */
	protected int rightIndex() {
		return 0;
	}
	
	/**
	 * @brief Get the game's map
	 * @return the game map
	 */
	protected GameMap getMap() {
		return map;
	}
	
}
