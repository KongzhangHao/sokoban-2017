import java.nio.file.Path;
import java.util.ArrayList;


/**
 * @brief The movement and behavior of the AI controlled monster
 * @author hao
 * @file Monster.java
 * @date 03/01/2018 hao: Created Monster.java
 * 					hao: Added spawning place of monster, with reasonable starting distance from all heroes
 * 					hao: Added auto movement of the Monster towards the heroes.
 */
public class Monster extends Witch {

	private GameEngine game; /**< The status of the game engine */
	
	/**
	 * @brief Constructor. Initialise position and alive status
	 * @param map the current map of the game
	 */
	public Monster(GameMap map) {
		super(map);
	}
	
	/**
	 * @brief Auto movement of the monster towards the heroes.
	 */
	public void autoMove() {
		/** Get the location of all player heroes */
		ArrayList<Integer[]> allHeroes = getAllHeroPositions();
		int shortest = -1;
		int[] nextPos = null;
		
		/** Iterate through all hero positions and find the nearest hero */
		for(Integer[] hero : allHeroes) {
			int distance = PathAlgorithm.distanceBetween(getMap(), getPosition(), new int[]{hero[0], hero[1]});
			if (shortest == -1 || shortest > distance) {
				shortest = distance;
				nextPos = PathAlgorithm.nextMove(getMap(), getPosition()[0], getPosition()[1], hero[0], hero[1]);
			}
		}
		
		/** If no path found towards the nearest hero, then do nothing */
		if (shortest == -1) return;
		
		/** Move towards the nearest hero */
		if (nextPos[0] - 1 == getPosition()[0]) {
			moveLeft();
		} else if (nextPos[0] + 1 == getPosition()[0]) {
			moveUp();
		} else if (nextPos[1] + 1 == getPosition()[1]) {
			moveRight();
		} else if (nextPos[1] - 1 == getPosition()[1]) {
			moveDown();
		}
	}
	
	/**
	 * @brief Check if the destination of the mosnter is movable 
	 * @param destination The position that the monster is moving to
	 * @return true The monster can move towards the destination without 
	 * 		   any other concerns
	 * @return false The monster cannot move towards the destination
	 */
	@Override
	protected boolean movable(int[] destination) {
		/** movable if the destination is a player */
		if (GameObject.isPlayer(getMap().getPosition(destination))) return true;
		
		return super.movable(destination);
	}
	
	/**
	 * @brief Check if the hero can spawn on this place
	 * @param posX the place's x axis
	 * @param posY the place's y axis
	 * @return true The hero can spawn here
	 * @return false The hero cannot spawn here
	 */
	protected boolean spawnable(int posX, int posY) {
		
		/** if the place is ground, which is empty, then the place is spawnable */
		if (super.getMap().getPosition(posX, posY) == GameObject.ground) {
			return true;
		}
		return false;
	}
	
	/**
	 * @brief Locate the position of the monster by finding an suitable place,
	 * 		  which is at least n blocks away from the two heroes.
	 */
	@Override
	public void locateHero() {
		/** Get the location of all player heroes */
		ArrayList<Integer[]> allHeroes = getAllHeroPositions();
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				
				/** if the monster can spawn on this place */
				if (spawnable(i, j) && isStepsAway(allHeroes, spawnDistanceFromHero(), i, j)) {
					/** set the ground to be the spawning place of the monster */
					setPosition(i, j);
					super.getMap().setPosition(i, j, frontIndex());
					return;
				}
			}
		}
	}
	
	/**
	 * @brief Get the shortest distance to spawn from the heroes
	 * @return shortest distance to spawn from heroes
	 */
	protected int spawnDistanceFromHero() {
		return 5;
	}
	
	/**
	 * @brief Get the location of all player controlled heroes on the map
	 * @return an array of the locations of all heroes
	 */
	public ArrayList<Integer[]> getAllHeroPositions() {
		ArrayList<Integer[]> allHeroes = new ArrayList<Integer[]>();
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				
				/** if the object on the position is an hero */
				if (isPlayer(getMap().getPosition(i, j))) {
					
					/** Get all players on the map */
					allHeroes.add(new Integer[]{i, j});
				}
			}
		}
		
		return allHeroes;
	}
	
	/**
	 * @brief Check if all the heroes are at least n steps away from the given position
	 * @param allHeroes an array of the locations of all heroes
	 * @param steps the smallest number of steps away from the monster 
	 * @param posX x axis of the given position
	 * @param posY y axis of the given position
	 * @return true the place is at leave n steps from all heroes
	 * @return false the place is fewer than n steps from one or more heroes
	 */
	protected boolean isStepsAway(ArrayList<Integer[]> allHeroes, int steps, int posX, int posY) {
		for(Integer[] p : allHeroes) {
			if (PathAlgorithm.blocksBetween(p[0], p[1], posX, posY) < steps) {
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * @brief Check if the object is a player controlled hero
	 * @param object the given object's index
	 * @return true the object is a player controlled hero
	 * @return false the object is not a player controlled hero
	 */
	protected boolean isPlayer(int object) {
		if (object == GameObject.warriorFront || object == GameObject.witchFront) return true;
		return false;
	}
	
	/**
	 * @brief Get the monster's front image index
	 * @return front image index
	 */
	protected int frontIndex() {
		return GameObject.monster;
	}
	
	/**
	 * @brief Get the monster's back image index
	 * @return front image index
	 */
	protected int backIndex() {
		return GameObject.monster;
	}
	
	/**
	 * @brief Get the monster's left image index
	 * @return front image index
	 */
	protected int leftIndex() {
		return GameObject.monster;
	}
	
	/**
	 * @brief Get the monster's right image index
	 * @return front image index
	 */
	protected int rightIndex() {
		return GameObject.monster;
	}
}
