
/**
 * @brief the enum of difference objects in the game
 * @author hao
 * @file GameObject.java
 * @date 27/12/2017 hao: Created GameObject.java.
 * 		 28/12/2017 hao: Swapped object values of fire and ground.
 * 					     Added hero's images in four different directions
 * 		 03/01/2018 hao: Added the object Monster
 * 					hao: Added function to check if the object is a player
 * 		 08/01/2018 hao: Added Hero skill effect images
 * 		 09/01/2018 hao: Added method isMonster to check if object on given position is monster
 */
public class GameObject {
	
	/** Basic game object images*/
	public static final int wall = 1;
	public static final int ground = 2;
	public static final int fire = 0;
	public static final int slime = 4;
	public static final int dye = 3;
	public static final int dyedSlime = 9;
	
	/** Hero Warrior images */
	public static final int warriorFront = 5;
	public static final int warriorBack = 6;
	public static final int warriorLeft = 8;
	public static final int warriorRight = 7;
	
	/** Hero Witch images */
	public static final int witchRight = 14;
	public static final int witchFront = 12;
	public static final int witchBack = 15;
	public static final int witchLeft = 13;
	
	/** Monster image */
	public static final int monster = 22;
	
	/** Skill images */
	public static final int skillPickaxe = 10;
	public static final int skillFire = 16;
	public static final int skillIce = 17;
	public static final int skillThunder = 18;
	public static final int skillExplode = 20;
	public static final int skillSword = 21;
	
	/**
	 * @brief Check if the object is player
	 * @param object given object on the position
	 * @return true object is player
	 * @return false object is not player
	 */
	public static boolean isPlayer(int object) {
		if (object == warriorBack || object == warriorFront
			|| object == warriorLeft || object == warriorRight
			|| object == witchBack || object == witchFront
			|| object == witchLeft || object == witchRight) {
			
			return true;
		}
		return false;
	}
	
	/**
	 * @brief Check if the object is monster
	 * @param object given object on the position
	 * @return true object is monster
	 * @return false object is not monster
	 */
	public static boolean isMonster(int object) {
		if (object == monster) return true;
		return false;
	}
	
}
