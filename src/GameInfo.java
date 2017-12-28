
/**
 * @brief Store the general information and data of the game
 * @author hao
 * @file GameInfo.java
 * @date 28/12/2017 hao: Created GameInfo.java
 */
public class GameInfo {
	
	private boolean levelPassed; /**< store if the current level is passed */
	
	/**
	 * @brief Constructor, initialise the state of the game
	 */
	public GameInfo() {
		levelPassed = false;
	}
	
	/**
	 * @brief Check if level is passed
	 * @return true level is passed
	 * @return false level is not passed yet
	 */
	public boolean isLevelPassed() {
		return levelPassed;
	}
	
	/**
	 * @brief Reset the level passed to be false
	 */
	public void resetLevelPassed() {
		levelPassed = false;
	}
	
	
}
