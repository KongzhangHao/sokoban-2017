
public class Hero {
	
	private int[] position; // the x,y position of the hero
	private Boolean alive; // if the hero is still alive
	private GameMap map;
	private Boolean onSlime;
	
	// Constructor. Initialise position and alive status
	public Hero(GameMap map) {
		position = new int[2];
		alive = true;
		this.map = map;
		onSlime = false;
	}
	
	// Move
	/*
	 * change original position to original object
	 * move position to new position
	 */
	public void moveUp() {
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]++;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	public void moveDown() {
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[1]--;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	public void moveLeft() {
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]--;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
	}
	
	public void moveRight() {
		if (onSlime) map.setPosition(position, GameObject.slime);
		else map.setPosition(position, GameObject.ground);
		position[0]++;
		onSlime = false;
		if (map.getPosition(position) == GameObject.slime) onSlime = true;
		map.setPosition(position, GameObject.player);
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
