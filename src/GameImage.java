import java.awt.Image;
import java.awt.Toolkit;

/**
 * @brief The images used in the game
 * 		  for objects, buttons and status bar.
 * @author hao
 * @file GameImage.java
 * @date 28/12/2017 hao: created GameImage class to handle image loading and usage
 */
public class GameImage {
	
	private static Image[] imgs; /**< buffer storing all the object images*/
	private static Toolkit tk; /**< toolkit used to get the image*/
	
	
	/**
	 * @brief Constructor, loads the image from the file system and
	 *        sets their size using toolkit.
	 */
	public GameImage() {
		
	}
}
