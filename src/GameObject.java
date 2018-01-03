
/**
 * @brief the enum of difference objects in the game
 * @author hao
 * @file GameObject.java
 * @date 27/12/2017 hao: Created GameObject.java.
 * 		 28/12/2017 hao: Swapped object values of fire and ground.
 * 					     Added hero's images in four different directions
 * 		 03/01/2018 hao: Added the object Monster
 * 					hao: Added function to check if the object is a player
 */
public class GameObject {
	
	public static final int wall = 1;
	public static final int ground = 2;
	public static final int fire = 0;
	public static final int warriorFront = 5;
	public static final int slime = 4;
	public static final int dye = 3;
	public static final int dyedSlime = 9;
	public static final int warriorBack = 6;
	public static final int warriorLeft = 8;
	public static final int warriorRight = 7;
	public static final int witchRight = 14;
	public static final int witchFront = 12;
	public static final int witchBack = 15;
	public static final int witchLeft = 13;
	public static final int monster = 22;
	
	public static boolean isPlayer(int object) {
		if (object == warriorBack || object == warriorFront
			|| object == warriorLeft || object == warriorRight
			|| object == witchBack || object == witchFront
			|| object == witchLeft || object == witchRight) {
			
			return true;
		}
		return false;
	}
	
}
