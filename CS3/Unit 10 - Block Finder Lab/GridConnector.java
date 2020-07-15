/**
 * The class finds the highest group of adjacent block for a given value
 * using DFS.
 *
 * @author Mithul Manivannan
 * Collaborators:
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 2/18/20
 */

import java.io.*;
import java.util.*;

public class GridConnector
{
	private static final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

	/** Dimensions of grid */
	private int numRows;
	private int numCols;

	/** Grid of values */
	private String[][] grid;
	private boolean[][] visited;

	/** Set of all values in grid */
	private Set<String> allValues;

	/**
	 * calls loadFile to update the instance variables
	 */
	public GridConnector(String inputFile)
	{
		loadFile(inputFile);
	}

	/**
	 * Calss loadFile to update the instance variables
	 * @param newFile the file to read from
	 */
	public void changeGrid(String newFile)
	{
		loadFile(newFile);
	}

	/**
	 * returns the highest group of values
	 * @param value to check for in a group
	 * @return the highest goup of values
	 */
	public int getLargestBlock(String value)
	{
		int max = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (!visited[i][j] && grid[i][j].equals(value)) {
					int rootVal = groupFinder(i, j, value);
					if (rootVal > max)
						max = rootVal;
				}
			}
		}
		return max;
	}

	/**
	 *  Retrieve set of all values in grid
	 *  @return set of values in grid
	 */
	public String getAllValues()
	{
		if (allValues == null)
			return "";

		return allValues.toString();
	}

	/**
	 *  Retrieve grid as a string
	 *  @return grid as a string
	 */
	@Override
	public String toString()
	{
		if (grid == null)
			return "";

		StringBuilder str = new StringBuilder();
		for (String[] row : grid)
		{
			for (String ch : row)
				str.append(ch).append(" ");
			str.append("\n");
		}
		return str.toString();
	}

	/**
	 * Helper method that finds the number of vals adjacent in a group based on
	 * a row and col
	 *
	 * @param row the row of the root
	 * @param col the col of the root
	 * @param val the value to check
	 * @return the number of vals in a group
	 */
	private int groupFinder(int row, int col, String val)
	{
		if((row < 0 || row >= numRows || col < 0 || col >= numCols) ||
				!grid[row][col].equals(val) ||
				visited[row][col])
			return 0;
		visited[row][col] = true;
		int group = 1;
		for (int[] dir : dirs)
			group += groupFinder(row + dir[0], col + dir[1], val);
		return group;
	}


	/**
	 * parses file
	 * @param inputFile the file to parse
	 */
	private void loadFile(String inputFile)
	{
		try
		{
			Scanner scan = new Scanner(new File(inputFile));
			numRows = scan.nextInt();
			numCols = scan.nextInt();
			grid = new String[numRows][numCols];
			visited = new boolean[numRows][numCols];
			allValues = new HashSet<>();
			for(int i = 0; i < numRows; i++)
				for(int j = 0; j < numCols; j++) {
					String val = scan.next();
					grid[i][j] = val;
					allValues.add(val);
				}
		}
		catch(IOException err)
		{
			System.out.println("File not found");
		}
	}
}


