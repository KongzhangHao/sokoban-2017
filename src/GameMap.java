import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

/**
 * @brief The map representing the current game status
 * @author hao
 * @file GameMap.java
 * @date 27/12/2017 hao: Created GameMap.
 * 						 Added loading methods with file path as parameter.
 * 						 Added setters and getters and method to print map onto STDOUT.
 * 		 28/12/2017 hao: Added constructor with file path as parameter.
 * 						 Added a backUp which stores the previous state of the map. Used for backtrack.
 */
public class GameMap {

	private int[][] map; /**<the game map represented by a 2d array. Objects are stored by index*/
	private int[][] prevMap; /**<the backup of the previous stage of the game map*/
	
	/**
	 * @brief Constructor, initialise the game map as 2d array
	 */
	public GameMap() {
		map = new int[20][20];
		prevMap = null;
	}
	
	/**
	 * @brief Constructor, initialise the game map with the given path
	 * of txt file
	 * @param pathname the relative path name of the text map file 
	 */
	public GameMap(String pathname) {
		this.map = new int[20][20];
		loadMap(pathname);
		prevMap = null;
	}
	
	
	/**
	 * @brief Load the map from the given pathname's text file
	 * @param pathname the relative path name of the text map file 
	 */
	public void loadMap(String pathname) {
        try {
            File filename = new File(pathname); 
            InputStreamReader reader = new InputStreamReader(  
                    new FileInputStream(filename));  
            BufferedReader br = new BufferedReader(reader); 
            String line = "";  
            line = br.readLine();
            
            int j = 0;
            for(int i = 0; i < 20; i++) {
            	this.map[j][i] = Integer.valueOf(line.substring(i, i + 1));
            }
            
            while (j != 19) {  
            	j++;
                line = br.readLine();
                for(int i = 0; i < 20; i++) {
                	this.map[j][i] = Integer.valueOf(line.substring(i, i + 1));
                }
            }  
            
            prevMap = null;
            br.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}
	
	/**
	 * @brief Load map from a 2d array representing all elements of the map
	 * @param map The 2d array of a existing map
	 */
	public void loadMap(int[][] map) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; i < 20; j++) {
				this.map[i][j] = map[i][j];
			}
		}
		prevMap = null;
	}
	
	/**
	 * @brief Get the object on the quested position as x and y axis
	 * @param x position at x axis
	 * @param y position at y axis
	 * @return the GameObject on the position
	 */
	public int getPosition(int x, int y) {
		return map[x][y];
	}
	
	/**
	 * @brief Get the object on the quested position as array
	 * @param position the position of the quested object
	 * @return the GameObject on the position
	 */
	public int getPosition(int[] position) {
		System.out.println(position[0]);
		System.out.println(position[1]);
		return map[position[0]][position[1]];
	}
	
	/**
	 * @brief Set the object on a position specified by x and y axis
	 * @param x position at x axis
	 * @param y position at y axis
	 * @param object the GameObject to set on the position
	 */
	public void setPosition(int x, int y, int object) {
		map[x][y] = object;
	}
	
	/**
	 * @brief Set the object on a position specified by an array
	 * @param position the position of the object to set
	 * @param object the GameObject to set on the position
	 */
	public void setPosition(int[] position, int object) {
		map[position[0]][position[1]] = object;
	}
	
	/**
	 * @brief Print out the game map as 2d array to STDOUT
	 */
	public void printMap() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	/**
	 * @brief Check if current level is passed
	 * @return true Current level is passed
	 * @return false Current level is not passed yet
	 */
	public boolean levelPassed() {
		boolean ret = true;
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (map[i][j] == GameObject.slime) {
					ret = false;
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * @brief Back up the current map status and save it to the prev map.
	 */
	public void backUpMap() {
		if (prevMap == null) prevMap = new int[20][20];
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				prevMap[i][j] = map[i][j];
			}
		}
	}
	
	/**
	 * @brief Load the prev map in the backup
	 */
	public void loadBackUp() {
		if (prevMap == null) return;
		
		map = prevMap;
		prevMap = null;
	}
	
}
