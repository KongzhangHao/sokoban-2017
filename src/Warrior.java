/**
 * @brief The information and behavior of a warrior
 * @author hao
 * @file Warrior.java
 * @date 28/12/17 hao: Created Warrior.java
 */
public class Warrior extends Hero {

	/**
	 * @brief Constructor. Initialise position and alive status
	 * @param map the current map of the game
	 */
	public Warrior(GameMap map) {
		super(map);
	}
	
	/**
	 * @brief Get the hero's front image index
	 * @return front image index
	 */
	protected int frontIndex() {
		return GameObject.warriorFront;
	}
	
	/**
	 * @brief Get the hero's back image index
	 * @return front image index
	 */
	protected int backIndex() {
		return GameObject.warriorBack;
	}
	
	/**
	 * @brief Get the hero's left image index
	 * @return front image index
	 */
	protected int leftIndex() {
		return GameObject.warriorLeft;
	}
	
	/**
	 * @brief Get the hero's right image index
	 * @return front image index
	 */
	protected int rightIndex() {
		return GameObject.warriorRight;
	}

}
