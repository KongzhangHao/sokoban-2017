import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @brief The game engine with Monster AI and player hero skills available
 * @author hao
 * @file MonsterGameEngineWithSkill.java
 * @date 08/01/2018 hao: Created MonsterGameEngineWithSkill.java
 * 		 			hao: Added initialiser of all the skills to be used in the game
 */
public class MonsterGameEngineWithSkill extends MonsterGameEngine {
	
	private ArrayList<GameSkill> skills; /**< Array to stores all the skills to be used in the game */

	/**
	 * @brief Constructor, initialises the game engine with all the skills
	 * 		  to be used in the game
	 */
	public MonsterGameEngineWithSkill() {
		super();
		
		/** Generate all skills to be used in the game */
		generateAllSkills();
	}
	
	/**
	 * @brief Detect the pressed key and set each key as instruction of
	 * 		characters' movement
	 * @param e the pressed key
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (!validKeyPress(e.getKeyCode())) return;
		
		super.keyReleased(e);
		
		checkSkillPressed(e);
		
		updateAliveStatus();
	}
	
	/**
	 * @brief Check if the pressed key is controlling a hero's skill
	 * @param e the key pressed by the player
	 */
	protected void checkSkillPressed(KeyEvent e) {
		/** Check if it is warrior's skill */
		checkWarriorSkillPressed(e);
		
		/** Check if it is witch's skill */
		checkWitchSkillPressed(e);
	}

	/**
	 * @brief Check if the pressed key is controlling the warrior's skill
	 * @param e the key pressed by the player
	 */
	protected void checkWarriorSkillPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_J:
				skills.get(getWarriorSkill0()).castSkill(getHero().getPosition());
				break;
				
			case KeyEvent.VK_K:
				
				break;
				
			case KeyEvent.VK_L:
				
				break;
	
			default:
				break;
		}	
	}

	/**
	 * @brief Check if the pressed key is controlling the witch's skill
	 * @param e the key pressed by the player
	 */
	protected void checkWitchSkillPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Z:
				
				break;
				
			case KeyEvent.VK_X:
				
				break;
				
			case KeyEvent.VK_C:
				
				break;
	
			default:
				break;
			}	
	}
	
	/**
	 * @brief Check that the key pressed is valid. Check the hero controlled by a key is still alive
	 * @param keyCode the key pressed by the player
	 * @return true The key pressed is valid
	 * @return false The key pressed is not valid
	 */
	protected boolean validKeyPress(int keyCode) {
		if (keyCode == KeyEvent.VK_J || keyCode == KeyEvent.VK_K 
				|| keyCode == KeyEvent.VK_L) {
			return getHero().isAlive();
		} else if (keyCode == KeyEvent.VK_Z || keyCode == KeyEvent.VK_X
				|| keyCode == KeyEvent.VK_C) {
			return getHeroine().isAlive();
		} else {
			return super.validKeyPress(keyCode);
		}
	}
	
	/**
	 * @brief Generate all skills for the warrior and witch
	 */
	protected void generateAllSkills() {
		/** Generate warrior skills to be used in the game */
		generateWarriorSkills();
		
		/** Generate witch skills to be used in the game */
		generateWitchSkills();
	}
	
	/**
	 * @brief Generate all skills owned by the warrior
	 */
	protected void generateWarriorSkills() {
		skills.add(new GameSkill(this, GameObject.skillPickaxe));
		skills.add(new GameSkill(this, GameObject.skillSword));
		skills.add(new GameSkill(this, GameObject.skillExplode));
	}
	
	/**
	 * @brief Generate all skills owned by the witch
	 */
	protected void generateWitchSkills() {
		skills.add(new GameSkill(this, GameObject.skillFire));
		skills.add(new GameSkill(this, GameObject.skillIce));
		skills.add(new GameSkill(this, GameObject.skillThunder));
	}
	
	/**
	 * @brief Get warrior's first skill
	 * @return warrior's first skill
	 */
	protected int getWarriorSkill0() {
		return 0;
	}
	
	/**
	 * @brief Get warrior's second skill
	 * @return warrior's second skill
	 */
	protected int getWarriorSkill1() {
		return 1;
	}
	
	/**
	 * @brief Get warrior's third skill
	 * @return warrior's third skill
	 */
	protected int getWarriorSkill2() {
		return 2;
	}
	
	/**
	 * @brief Get witch's first skill
	 * @return witch's first skill
	 */
	protected int getWitchSkill0() {
		return 3;
	}
	
	/**
	 * @brief Get witch's second skill
	 * @return witch's second skill
	 */
	protected int getWitchSkill1() {
		return 4;
	}
	
	/**
	 * @brief Get witch's third skill
	 * @return witch's third skill
	 */
	protected int getWitchSkill2() {
		return 5;
	}
}
