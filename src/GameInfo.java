import com.sun.org.glassfish.external.statistics.annotations.Reset;

/**
 * @brief Store the general information and data of the game
 * @author hao
 * @file GameInfo.java
 * @date 28/12/2017 hao: Created GameInfo.java
 */
public class GameInfo {
	
	private boolean levelPassed; /**< store if the current level is passed */
	private int level; /**< current level of the game */
	
	/**
	 * @brief Constructor, initialise the state of the game
	 */
	public GameInfo() {
		setLevel(1);
		reset();
	}
	
	/**
	 * @brief Reset the game state.
	 */
	public void reset() {
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
	 * @param passed whether the level is passed
	 */
	public void setLevelPassed(boolean passed) {
		levelPassed = passed;
	}

	/**
	 * @brief Get the current game level
	 * @return level number
	 */
	public int getLevel() {
		return level;
	}

	
	/**
	 * @brief Set current game level
	 * @param level the level number to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
