
public class Hero {
	
	private int[] position; /**< the x,y position of the hero*/
	private Boolean alive; /**< if the hero is still alive*/
	private GameMap map;
	private Boolean onSlime;
	
	/* Constructor. Initialise position and alive status
	 * @param map the current map of the game
	 */
	public Hero(GameMap map) {
		position = new int[2];
		alive = true;
		this.map = map;
		onSlime = false;
		getInitialPosition();
	}
	
	// Move
	/*
	 * check if destination is movable
	 * change the hero's original position to its original object
	 * if on slime, then set the position to be slime, else set as ground
	 * if new position has slime on it, then set onSlime to be true
	 * set player to the new position
	 * update player's position
	 */
	
	/* Move the hero up */
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
	
	/* Move the hero down */
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
	
	/* Move the hero towards left */
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
	
	/* Move the hero towards right */
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
	
	// helper function, check if destination movable
	/*
	 * if the destination is ground or slime, can go straight away.
	 * if the destination is dye or dyedSlime, need to check the next step
	 * if next step is ground or slime, then move the dye to the right place
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
	
	/*
	 * Get the initial position from the game map 
	 * and set the hero's position with it.
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
	
	/* Setters and Getters */
	public int[] getPosition() {
		return position;
	}
	
	public Boolean isAlive() {
		return alive;
	}
	
	public void setAlive(Boolean alive) {
		this.alive = alive;	
	}
	
}
