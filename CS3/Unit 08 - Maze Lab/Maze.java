/**
 * This is a class that fills the grid with a random probability from .1 to .9
 * with walls with the populateMaze method. Then it solves the maze with the solve maze
 * method using BFS and finds one of the shortest path using yellow. The Magenta denotes
 * visited colors and the orange color denotes the boxes that were added to the stack
 * but not yet visited. A box is added to the stack when it is a possible path from a
 * neighbor. It checks all 8 neighbors before adding to stack.
 *
 * @author Mithul Manivannan
 * Collaborators:
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 11/16/19
 */

import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.*;

public class Maze extends AbstractMaze {
    private static final Color BLANK_COLOR = Color.WHITE;
    private static final Color WALL_COLOR = Color.DARK_GRAY;
    private static final Color INITIAL_COLOR = Color.GREEN;
    private static final Color VISITED_COLOR = Color.MAGENTA;
    private static final Color NOT_ADDED_NEIGHBORS = Color.ORANGE;
    private static final Color PATH_COLOR = Color.YELLOW;
    private static final Color END_COLOR = Color.RED;


    private static final int NUM_ROW = 19;
    private static final int NUM_COL = 19;

    private Location begin;
    private Location endLoc;

    /**
     * This maze constructor creates the grid and imports the
     * grid world graphics.
     */
    public Maze() {
    }


    /**
     * It uses BFS to check the path of the grid and marks the visited
     * box with magenta and orange with when added to the stack.
     * @return true if the end was reached and false otherwise.
     */
    @Override
    public boolean solveMaze(){
        Grid<Color> grid = getGrid();
        Deque<Location> stack = new LinkedList<>();
        stack.push(begin);
        ArrayList<Location> visited = new ArrayList<>();
        int[][][] path = new int[NUM_ROW][NUM_COL][2];
        Location currLoc = new Location(0, 0);
        while (!stack.isEmpty() && !currLoc.equals(endLoc)) {
            Location node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                if (grid.get(node) != INITIAL_COLOR)
                    grid.put(node, VISITED_COLOR);
                ArrayList<Location> AdjNode = new ArrayList<>();
                for (int c = -1; c < 2; c++)
                    for (int r = -1; r < 2; r++) {
                        try {
                        	Location newLoc = new Location(node.getRow() + r, node.getCol() + c);
                            if (grid.get(newLoc) == BLANK_COLOR && !visited.contains(newLoc)) {
                            	path[newLoc.getRow()][newLoc.getCol()][0] = node.getRow();
                            	path[newLoc.getRow()][newLoc.getCol()][1] = node.getCol();
                                AdjNode.add(newLoc);
                                grid.put(newLoc, NOT_ADDED_NEIGHBORS);}
                            if (grid.get(newLoc) == END_COLOR)
                                return end(path, node);
                            }
                          catch (IllegalArgumentException e) {}
                          }
                for (Location loc : AdjNode)
                    stack.addLast(loc);}}
        return false;
    }

    /**
     * It fills the map with grey boxes to make walls and
     * obstacles. It creates the maze and it creates the starting
     * point and ending point at random on the left and the right
     * respectively.
     */
    @Override
    public void populateMaze() {
        final double CHANCE = Math.random() * .5+ .4;
        Grid<Color> grid = getGrid();
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COL; j++)
                grid.put(new Location(i, j), BLANK_COLOR);
        }
        //Adds the 2 columns of walls
        for (int i = 0; i < NUM_ROW; i++) {
            Location loc = new Location(i, NUM_COL - 1);
            grid.put(loc, WALL_COLOR);
            loc = new Location(i, 0);
            grid.put(loc, WALL_COLOR);
        }
        //Adds the 2 rows of walls
        for (int i = 0; i < NUM_COL; i++) {
            grid.put(new Location(NUM_ROW - 1, i), WALL_COLOR);
            grid.put(new Location(0, i), WALL_COLOR);
        }
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COL; j++) {
                Location loc = new Location(i, j);
                if (Math.random() > CHANCE)
                    grid.put(loc, WALL_COLOR);
            }
        }
        begin = new Location((int) (Math.random() * NUM_COL), 0);
        endLoc = new Location((int) (Math.random() * NUM_COL), NUM_COL - 1);
        grid.put(begin, INITIAL_COLOR);
        grid.put(endLoc, END_COLOR);
    }


	/**
	 * Fills the grid with the path
	 * @returns true for reaching end
	 */
    private boolean end(int[][][] path, Location node)
    {
    	Grid<Color> grid = getGrid();
    	int testR = node.getRow();
    	int testC = node.getCol();
    	while(testR != 0 && testC != 0){
    		if(testR != 0 && testR != 0)
    			grid.put(new Location(testR, testC), PATH_COLOR);
    		int temp = path[testR][testC][0];
    		testC = path[testR][testC][1];
    		testR = temp;
    	}
    	return true;
    }
}
