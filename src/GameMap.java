
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
	
}
