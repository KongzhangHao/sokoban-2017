import java.io.IOException;

public class Test {

	
	public static void main(String[] args) throws IOException {
		GameMap map = new GameMap();
		map.loadMap("maps/0.map");
		Hero man = new Hero(map);
		map.printMap();
		man.moveLeft();
		map.printMap();
		man.moveLeft();
		map.printMap();
		man.moveUp();
		map.printMap();
		man.moveLeft();
		map.printMap();
		man.moveLeft();
		map.printMap();
		man.moveLeft();
		map.printMap();
	}
	

}
