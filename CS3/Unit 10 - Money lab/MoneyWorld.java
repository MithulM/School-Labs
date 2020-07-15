/**
 * This class fills the grid world randomly with
 * +, -, 5, 10, 20, and 50. When a user clicks on a cell,
 * the grid updates and replaces all adjacent cells to the
 * clicked cell with * and adds the amount to the total and
 * displays it on the screen.
 *
 * @author Mithul Manivannan
 * Collaborators:
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 2/12/20
 */
import info.gridworld.grid.*;
import info.gridworld.world.World;

import java.util.*;

public class MoneyWorld extends World<String>
{
	private static final String HELP_MSG
			= "Click on grid cell to pick-up money; STEP to reset world";
	private static final String PICKEDUP_MESSAGE = "Total amount picked up: $";
	private static final int NUM_ROW = 10;
	private static final int NUM_COL = 10;
	private static final double[] PROB = {.3, .5, .6, .8, .9};
	private static final List<String> CHARS = Arrays.asList("+", "-");
	private static final String VISTITED_CELL = "*";
	private static final List<String> MONEY = Arrays.asList("5", "10", "20", "50");
	private static final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	
	private static int totalMoney = 0;
	private Grid<String> grid;


	/**
	 * Create a world of letters of size rows x cols
	 * @param rows number of rows in world
	 * @param cols number of cols in world
	 */
	public MoneyWorld(int rows, int cols)
	{
		super(new BoundedGrid<>(rows, cols));
		grid = getGrid();
		fillWorld();
		setMessage(HELP_MSG);
	}

	/**
	 *  Refill world when the step button is pressed
	 */
	@Override
	public void step()
	{
		fillWorld();
	}


	/**
	 * Suppresses menu from appearing when any Location is clicked
	 * @param loc the grid location that the user selected
	 * @return true to indicate click was processed
	 */
	@Override
	public boolean locationClicked(Location loc)
	{
		totalMoney += pickupMoney(loc);
		this.setMessage(PICKEDUP_MESSAGE + totalMoney + "\n" + HELP_MSG);
		return true;
	}
	
	/**
	 * fills the world randomly with +, -, 5, 10, 20, and 50
	 */
	private void fillWorld()
	{
		grid = getGrid();
		for (int i = 0; i < NUM_ROW; i++)
			for (int j = 0; j < NUM_COL; j++)
			{
				Location loc = new Location(i, j);
				double rand = Math.random();
				if (rand <= PROB[0])
					grid.put(loc, CHARS.get(0));
				else if (rand <= PROB[1])
					grid.put(loc, CHARS.get(1));
				else if (rand <= PROB[2])
					grid.put(loc, MONEY.get(0));
				else if (rand <= PROB[3])
					grid.put(loc, MONEY.get(1));
				else if (rand <= PROB[4])
					grid.put(loc, MONEY.get(2));
				else if (rand > PROB[4])
					grid.put(loc, MONEY.get(3));
			}
	}

	/**
	 * gets money from the grid using DFS and replaces the cell with an *
	 * @param loc the root location clicked by user
	 * @return the amount of money picked up by the user
	 */
	private int pickupMoney(Location loc)
	{
		int moneyCollected = 0;
		if(grid.get(loc).matches("\\d+"))
		{
			moneyCollected += Integer.parseInt(grid.get(loc));
			grid.put(loc, VISTITED_CELL);
			for(Location neighbor: getNeighbors(loc))
				moneyCollected += pickupMoney(neighbor);
		}
		return moneyCollected;
	}

	/**
	 * helper method for dfs in pickUpMoney
	 * @param loc gets the neighbors of the loc cell that are valid
	 * @return an arrayList of neighbors for loc
	 */
	private ArrayList<Location> getNeighbors(Location loc)
	{
		ArrayList<Location> moneyCells = new ArrayList<>();
		for(int[] dir: dirs) {
			Location newLoc = new Location(loc.getRow() + dir[0],
					loc.getCol() + dir[1]);
			if (!valid(newLoc))
				continue;
			if (grid.get(newLoc).matches("\\d+"))
				moneyCells.add(newLoc);
		}
		return moneyCells;
	}

	/**
	 * checks if newLoc is a valid location
	 * @param newLoc the loc to check if it is a valid loc
	 * @returns if the location is on the gird
	 */
	private boolean valid(Location newLoc) {
		return newLoc.getCol() >= 0 &&
				newLoc.getCol() < NUM_COL &&
				newLoc.getRow() >= 0 &&
				newLoc.getRow() < NUM_ROW;
	}

	/**
	 * Run the money world
	 */
	public static void main(String[] args)
	{
		MoneyWorld myWorld = new MoneyWorld(NUM_ROW, NUM_COL);
		myWorld.show();
	}
}


