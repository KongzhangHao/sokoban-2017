
public class Hero {
	
	private int[] position; // the x,y position of the hero
	private Boolean alive; // if the hero is still alive
	private GameMap map;
	private Boolean onSlime;
	
	// Constructor. Initialise position and alive status
	public Hero(GameMap map) {
		position = new int[2];
		position[0] = 10;
		position[1] = 10;
		map.setPosition(position, GameObject.player);
		alive = true;
		this.map = map;
		onSlime = false;
	}
	
	// Move
	/*
	 * check if destination movable
	 * change original position to original object
	 * if on slime, then set the position to be slime, else ground
	 * if new position has slime on it, then set onSlime to be true
	 * set new position to player
	 * update player's position
	 */
	public void moveUp() {
		int[] destination = {position[0] - 1, position[1]};
		if (!movable(destination)) return; 
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]--;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	public void moveDown() {
		int[] destination = {position[0] + 1, position[1]};
		if (!movable(destination)) return; 
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]++;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	public void moveLeft() {
		int[] destination = {position[0], position[1] - 1};
		if (!movable(destination)) return; 
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]--;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	public void moveRight() {
		int[] destination = {position[0], position[1] + 1};
		if (!movable(destination)) return; 
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]++;
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
	
	
	// Setters and Getters
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
