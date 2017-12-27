import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class GameMap {

	private int[][] map;
	
	// Constructor, initialise map or load the map
	public GameMap() {
		map = new int[20][20];
	}
	
	public GameMap(int[][] map) {
		this.map = new int[20][20];
		loadMap(map);
	}
	
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
	
	// Load the given map into our GameMap
	public void loadMap(int[][] map) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; i < 20; j++) {
				this.map[i][j] = map[i][j];
			}
		}
	}
	
	// Setter and Getter
	public int getPosition(int x, int y) {
		return map[x][y];
	}
	
	public int getPosition(int[] position) {
		return map[position[0]][position[1]];
	}
	
	public void setPosition(int x, int y, int object) {
		map[x][y] = object;
	}
	
	public void setPosition(int[] position, int object) {
		map[position[0]][position[1]] = object;
	}
	
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
