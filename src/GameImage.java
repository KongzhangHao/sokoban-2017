import java.awt.Image;
import java.awt.Toolkit;

/**
 * @brief The images used in the game
 * 		  for objects, buttons and status bar.
 * @author hao
 * @file GameImage.java
 * @date 28/12/2017 hao: Created GameImage.java.
 * 						 Updated the constructor to load images from the file system.
 * 						 Created getImg method to get image.
 * 	     03/01/2018 hao: Added image of Monser
 */
public class GameImage {
	
	private Image[] imgs; /**< buffer storing all the object images*/
	private Toolkit tk; /**< toolkit used to get the image*/
	
	
	/**
	 * @brief Constructor, loads the image from the file system and
	 *        sets their size using toolkit.
	 */
	public GameImage() {
		tk = Toolkit.getDefaultToolkit();
		
		/** Load the images from the file system to the buffer using toolkit */
		imgs = new Image[] {
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/red_magma.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/wall.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/floor.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/red_dye.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/green_slime.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/red_role_face.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/red_role_back.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/red_role_right.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/red_role_left.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/red_slime.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/pickaxe.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/stairs.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/witch_face.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/witch_left.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/witch_right.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/witch_back.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/fire.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/ice.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/thunder.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/pickaxe_effect.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/ulti.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/kill_slime.gif")),
				tk.getImage(GameFrame.class.getClassLoader().getResource("imgs/kulou.gif"))
		};
	}
	
	
	/**
	 * @brief Get the image of the game object
	 * @param object the index of the game object 
	 * @return the image of the object
	 * @precondition The object index is valid
	 */
	public Image getImg(int object) {
		return imgs[object];
	}
}
