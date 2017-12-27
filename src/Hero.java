
public class Hero {
	
	private int[] position; // the x,y position of the hero
	private Boolean alive; // if the hero is still alive
	
	// Constructor. Initialise position and alive status
	public Hero() {
		position = new int[2];
		alive = true;
	}
	
	// Move
	public void moveUp() {
		
	}
	
	public void moveDown() {
		
	}
	
	public void moveLeft() {
		
	}
	
	public void moveRight() {
		
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
