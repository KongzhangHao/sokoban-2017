/**
 * @brief The information and behavior of a Witch
 * @author hao
 * @file Witch.java
 * @date 28/12/17 hao: Created Witch.java
 */
public class Witch extends Hero {

	/**
	 * @brief Constructor. Initialise position and alive status
	 * @param map the current map of the game
	 */
	public Witch(GameMap map) {
		super(map);
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (map.getPosition(i, j) == GameObject.ground) {
					setPosition(i, j);
					map.setPosition(i, j, frontIndex());
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
