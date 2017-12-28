
/**
 * @brief The basic information and behavior of a hero
 * @author hao
 * @file Hero.java
 * @date 27/12/2017 hao: Created Hero class.
 * 						 Added Hero movement to empty ground and slime.
 * 					     Added Hero movement under condition of dye and dyed slime.
 * 						 Set initial position of the hero when loading map.
 * 	     28/12/2017 hao: Fixed hero position change during hero movements.
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
		alive = true;
		this.map = map;
		onSlime = false;
		getInitialPosition();
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
		if (!movable(destination)) return; 
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]--;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	/**
	 * @brief Move the hero down
	 */
	public void moveDown() {
		int[] destination = {position[0], position[1] + 1};
		if (!movable(destination)) return; 
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]++;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	/**
	 * @brief Move the hero towards left
	 */
	public void moveLeft() {
		int[] destination = {position[0] - 1, position[1]};
		if (!movable(destination)) return; 
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]--;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	/**
	 * @brief Move the hero towards right
	 */
	public void moveRight() {
		int[] destination = {position[0] + 1, position[1]};
		if (!movable(destination)) return; 
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]++;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
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
	 * @brief Set the initial position of the hero
	 * @explanation Get the initial position from the game map 
	 * 				and set the hero's position with it.
	 */
	private void getInitialPosition() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (map.getPosition(i, j) == 5) {
					position[0] = i;
					position[1] = j;
					return;
				}
			}
		}
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
	
}
