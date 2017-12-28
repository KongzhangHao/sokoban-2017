import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @brief The map representing the current game status
 * @author hao
 * @file GameMap.java
 * @date 28/12/2017
 */
public class GameMap {

	private int[][] map;
	
	/**
	 * Constructor, initialise the game map as 2d array
	 */
	public GameMap() {
		map = new int[20][20];
	}
	
	/**
	 * Constructor, initialise the game map with the given path
	 * of txt file
	 * @param pathname the relative path name of the text map file 
	 */
	public GameMap(String pathname) {
		this.map = new int[20][20];
		loadMap(pathname);
	}
	
	
	/**
	 * Load the map from the given pathname's text file
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
                line = br.readLine(); // 一次读入一行数据  
                for(int i = 0; i < 20; i++) {
                	this.map[j][i] = Integer.valueOf(line.substring(i, i + 1));
                }
            }  
            br.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}
	
	/**
	 * Load map from a 2d array representing all elements of the map
	 * @param map The 2d array of a existing map
	 */
	public void loadMap(int[][] map) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; i < 20; j++) {
				this.map[i][j] = map[i][j];
			}
		}
	}
	
	/**
	 * @param x position at x axis
	 * @param y position at y axis
	 * @return the GameObject on the position
	 */
	public int getPosition(int x, int y) {
		return map[x][y];
	}
	
	/**
	 * @param position the position of the quested object
	 * @return the GameObject on the position
	 */
	public int getPosition(int[] position) {
		return map[position[0]][position[1]];
	}
	
	/**
	 * @param x position at x axis
	 * @param y position at y axis
	 * @param object the GameObject to set on the position
	 */
	public void setPosition(int x, int y, int object) {
		map[x][y] = object;
	}
	
	/**
	 * @param position the position of the object to set
	 * @param object the GameObject to set on the position
	 */
	public void setPosition(int[] position, int object) {
		map[position[0]][position[1]] = object;
	}
	
	/**
	 * Print out the game map as 2d array onto STDOUT
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
	
}
