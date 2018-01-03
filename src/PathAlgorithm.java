import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javafx.geometry.Pos;

/**
 * @brief The class that provides algorithms needed in the game
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
	
	static int blocksBetween(int posXa, int posYa, int posXb, int posYb) {
		return Math.abs(posXa - posXb) + Math.abs(posYa - posYb);
	}
	
	static int distanceBetween(GameMap map, int[] posA, int[] posB) {
		return distanceBetween(map, posA[0],  posA[1], posB[0], posB[1]);
	}
	
	static private ArrayList<Position> bfs(GameMap map, Position start, Position dest) {
		System.out.println("Doing bfs!");
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
			System.out.println("Queue is: " + locations.size());
			Position current = locations.poll();
			
			/** iterate through all blocks around the current position */
			for (Position p : current.blocksAround()) {
				
				if (invalidPosition(p.posX, p.posY) || prev.containsKey(p)) continue;
				
				prev.put(p, current);
				
				/** if the block is reachable and not added yet, then put it into the hash map */
				if (reachable(map.getPosition(p.posX, p.posY))) {
					System.out.println("P is " + p.posX + " " + p.posY);
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
	
	
	
	private static boolean invalidPosition(int posX, int posY) {
		if (posX < 0 || posX > 20 || posY < 0 || posY > 20) return true;
		return false;
	}

	static public boolean reachable(int object) {
		if (object == GameObject.wall || object == GameObject.dye
				|| object == GameObject.dyedSlime || object == GameObject.fire) return false;
		return true;
	}
	
	static class Position {
		
		int posX;
		int posY;
		
		public Position(int x, int y) {
			posX = x;
			posY = y;
		}

		@Override
		public int hashCode() {
			return posX * 100 + posY;
		}
		
		@Override
		public boolean equals(Object o) {
			return true;
		}
		
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
