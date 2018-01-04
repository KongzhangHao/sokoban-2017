import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @brief The class that provides path algorithms needed in the game
 * @author hao
 * @file Algorithm.java
 * @date 03/01/2018 hao: Created PathAlgorithm.java
 * 				    hao: Created the method of finding shortest distance between two positions on the map.
 * 					hao: Fixed array index out of bound bug when the path found is empty
 */
public class PathAlgorithm {
	
	/**
	 * @brief Find the shortest distance of the valid path by given the starting position 
	 *        and destination
	 * @param map the map of the game
	 * @param posXa x axis of the start position
	 * @param posYa y axis of the start position
	 * @param posXb x axis of the dest position
	 * @param posYb y axis of the dest position
	 * @return the length of the found shortest path
	 */
	static int distanceBetween(GameMap map,
			int posXa, int posYa, int posXb, int posYb) {
		/** return 0 if the two points are on the same position */
		if (posXa == posXb && posYa == posYb) return 0;
		
		/** find the valid path using bfs */
		ArrayList<Position> resultPath = bfs(map, new Position(posXa, posYa), new Position(posXb, posYb));
		
		/** if not path is found then return -1 */
		if (resultPath == null) return -1;
		
		return resultPath.size() + 1;
	}
	
	/**
	 * @brief Find the next move by given the starting position and destination
	 * @param map the map of the game
	 * @param posXa x axis of the start position
	 * @param posYa y axis of the start position
	 * @param posXb x axis of the dest position
	 * @param posYb y axis of the dest position
	 * @return the position of the next move
	 */
	static int[] nextMove(GameMap map,
			int posXa, int posYa, int posXb, int posYb) {
		/** find the valid path using bfs */
		ArrayList<Position> resultPath = bfs(map, new Position(posXa, posYa), new Position(posXb, posYb));
		
		/** if not path is found then return null */
		if (resultPath == null) return null;
		if (resultPath.size() == 0) return new int[]{ posXb, posYb};
		/** return the next position as an array */
		Position next = resultPath.get(resultPath.size() - 1);
		return new int[]{ next.posX, next.posY};
	}
	
	/**
	 * @brief Find the path between the two points using euler's path
	 * @param posXa x axis of the starting position
	 * @param posYa y axis of the starting position
	 * @param posXb x axis of the end position
	 * @param posYb y axis of the end position
	 * @return the distance between the two points using euler's path
	 */
	static int blocksBetween(int posXa, int posYa, int posXb, int posYb) {
		return Math.abs(posXa - posXb) + Math.abs(posYa - posYb);
	}
	
	/**
	 * @brief Find the shortest distance of the valid path by given the starting position 
	 *        and destination
	 * @param map the map of the game
	 * @param posA the start position
	 * @param posB the end position
	 * @return the length of the found shortest path
	 */
	static int distanceBetween(GameMap map, int[] posA, int[] posB) {
		return distanceBetween(map, posA[0],  posA[1], posB[0], posB[1]);
	}
	
	/**
	 * @brief Breadth first search algorithm to find the shortest path on map between the two points
	 * @param map the current status of the game map
	 * @param start the starting position
	 * @param dest the destination position
	 * @return array of found shortest path between the two points
	 */
	static private ArrayList<Position> bfs(GameMap map, Position start, Position dest) {
		
		/** Initialse all the arrays used */
		Queue<Position> locations = new LinkedList<Position>();
		HashMap<Position, Position> prev = new HashMap<Position, Position>();
		ArrayList<Position> resultPath = new ArrayList<Position>();
		
		boolean found = false;
		
		/** Add starting point to the arrays */
		locations.add(start);
		prev.put(start, null);
		
		/** Start our bfs algorithm */
		while (!locations.isEmpty() && !found) {
			Position current = locations.poll();
			
			/** iterate through all blocks around the current position */
			for (Position p : current.blocksAround()) {
				
				if (invalidPosition(p.posX, p.posY) || prev.containsKey(p)) continue;
				
				prev.put(p, current);
				
				/** if the block is reachable and not added yet, then put it into the hash map */
				if (reachable(map.getPosition(p.posX, p.posY))) {
					locations.add(p);
					
					/** If the position is the destination, then the route is found */
					if (p.hashCode() == dest.hashCode()) found = true;
				}
			}
		}
		
		/** if no path is found, then return null */
		if (!found) return null;
		
		/** get the whole path from the hash map */
		Position current = prev.get(dest);
		
		while (current.hashCode() != start.hashCode()) {
			resultPath.add(current);
			current = prev.get(current);
		}
		
		return resultPath;
	}
	
	
	
	/**
	 * @brief Check that the position is valid on map
	 * @param posX x axis of the position
	 * @param posY y axis of the position
	 * @return true The checked position is valid
	 * @return false The checked position is invalid
	 */
	private static boolean invalidPosition(int posX, int posY) {
		if (posX < 0 || posX > 20 || posY < 0 || posY > 20) return true;
		return false;
	}

	/**
	 * @brief Check that the object's position is reachable on the map
	 * @param object the checked object's index
	 * @return true The place that the object is on is reachable
	 * @return false The place that the object is on is not reachable
	 */
	static public boolean reachable(int object) {
		if (object == GameObject.wall || object == GameObject.dye
				|| object == GameObject.dyedSlime || object == GameObject.fire) return false;
		return true;
	}
	
	/**
	 * @brief The position class used to store a position's index
	 */
	static class Position {
		
		int posX; /**< x axis of a position */
		int posY; /**< y axis of a position */
		
		/**
		 * @brief Constructor, constructs the position class
		 * @param x x axis of a position
		 * @param y y axis of a position
		 */
		public Position(int x, int y) {
			posX = x;
			posY = y;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return posX * 100 + posY;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object o) {
			return true;
		}
		
		/**
		 * @brief Get the 4 blocks around the current position: up, left, right and down.
		 * @return an array of the positions of the 4 blocks around.
		 */
		public ArrayList<Position> blocksAround() {
			/** create the empty arraylist to store all the blocks around */
			ArrayList<Position> blocks = new ArrayList<Position>();
			
			/** add in the blocks around the current position */
			blocks.add(new Position(posX - 1, posY));
			blocks.add(new Position(posX + 1, posY));
			blocks.add(new Position(posX, posY - 1));
			blocks.add(new Position(posX, posY + 1));
			
			return blocks;
		}
	}
}
