/**
 * @brief The information and behavior of a Witch
 * @author hao
 * @file Witch.java
 * @date 28/12/17 hao: Created Witch.java
 * 					   Updated constructor, which picks a suitable spawning position for the witch
 * 					   Updated locate hero method, automatically find a new place for the witch in every new game.
 * 		 11/01/18 hao: Fixed Bug: Back Step function creates multiple Witch model on map
 */
public class Witch extends Hero {

	/**
	 * @brief Constructor. Initialise position and alive status
	 * @param map the current map of the game
	 */
	public Witch(GameMap map) {
		super(map);
	}
	
	/**
	 * @brief Check if the hero can spawn on this place
	 * @param posX the place's x axis
	 * @param posY the place's y axis
	 * @return true The hero can spawn here
	 * @return false The hero cannot spawn here
	 */
	protected boolean spawnable(int posX, int posY) {
		
		/** if the place is ground, which is empty, then the place is spawnable */
		if (super.getMap().getPosition(posX, posY) == GameObject.ground) {
			return true;
		}
		return false;
	}
	
	/**
	 * @brief Locate the position of the hero by finding an empty place
	 */
	@Override
	public void locateHero() {
		/** Check if witch already exists on the map */
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				/** if the place is witch */
				if (isHero(getMap().getPosition(i, j))) {
					/** set place to be witch's position */
					setPosition(i, j);
					setAlive(true);
					return;
				}
			}
		}
		
		/** Check if the hero is still alive */
		if (!isAlive()) return;
		
		/** Find a place where the witch is spawnable */
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				/** if the hero can spawn on this place */
				if (spawnable(i, j)) {
					/** set the ground to be the spawning place of the witch */
					setPosition(i, j);
					super.getMap().setPosition(i, j, frontIndex());
					return;
				}
			}
		}
	}
	
	/**
	 * @brief Get the hero's front image index
	 * @return front image index
	 */
	protected int frontIndex() {
		return GameObject.witchFront;
	}
	
	/**
	 * @brief Get the hero's back image index
	 * @return front image index
	 */
	protected int backIndex() {
		return GameObject.witchBack;
	}
	
	/**
	 * @brief Get the hero's left image index
	 * @return front image index
	 */
	protected int leftIndex() {
		return GameObject.witchLeft;
	}
	
	/**
	 * @brief Get the hero's right image index
	 * @return front image index
	 */
	protected int rightIndex() {
		return GameObject.witchRight;
	}
	
}
