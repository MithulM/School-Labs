/**
 * The MagicSquare class <replace this with description of your program>
 *
 * @author Mithul Manivannan
 * Collaborators: Mark
 * Teacher Name: Mrs. Ishman
 * Period: 2nd
 * Due Date: 8/20/2019
 */

public class MagicSquare
{
	public static final String MAGIC_SUCCESS = "successful";
	public static final String FAILED_NOT_ALL_VALUE = "not all values used";
	public static final String FAILED_ROW_SUM = "failed row sum";
	public static final String FAILED_COL_SUM = "failed column sum";
	public static final String FAILED_DIAG_SUM = "failed diagonal sum";

	private int[][] square;
	private int numAdded;
	private boolean bad;
	private int[] has;

	public MagicSquare(int sideLength)
	{
		square = new int[sideLength][sideLength];
		bad=false;
		numAdded= -1;
		has = new int[sideLength*sideLength];
	}

	/**
	 * adds numbers to the magic square
	 * @param number is the number that is being added to the square 2D array.
	 */
	public void add(int number)
	{
		boolean hasInArr=false;
		for(int i=0;i<numAdded;i++)
			if(has[i]==number)
			{
				hasInArr=true;
				break;
			}
		if(hasInArr||(number<=0||number>square.length*square.length))
			bad=true;
		square[++numAdded/square.length][numAdded%square.length]=number;
		has[numAdded]=number;
	}

	/**
	 * checks if the input from the user is a magic square
	 * @return returns a String that tell the user if the given numbers
	 *  make a magicSquare. First checks if all values are used, then
	 *  if all rows have equal sums, then same for column sums, and lastly
	 *  for diagonal sums.
	 */
	public String isMagic()
	{
		int first = rowSum(square[0]);
		if(bad)
			return FAILED_NOT_ALL_VALUE;
		for (int i = 1; i < square.length; i++)
			if (rowSum(square[i]) != first)
				return FAILED_ROW_SUM;
		for (int i = 0; i < square[0].length; i++)
			if (colSum(square, i) != first)
				return FAILED_COL_SUM;
		if(dia1Sum(square) != first)
			return FAILED_DIAG_SUM;
		if(dia2Sum(square) != first)
			return FAILED_DIAG_SUM;
		return MAGIC_SUCCESS;
	}

	/**
	 * returns a formated string of the magic square
	 * @return returns one 6spaces of formatted string from the square 2D Array.
	 * For every square sideLength it adds a new line char.
	 */
	@Override
	public String toString()
	{
		String result = "";
		for(int i=0;i<square.length;i++)
			for(int j=0;j<square[i].length;j++)
				if(j==square[i].length-1)
					result += String.format("%6d\n", square[i][j]);
				else
					result += String.format("%6d", square[i][j]);
		return result;
	}

	/**
	 * makes a magic square
	 * @param sideLength sets the length for a magic square to be generated
	 * @return returns a 2D array for a magic square.
	 */
	public static int[][] makeMagic(int sideLength)
	{
		int[][] newSquare = new int[sideLength][sideLength];
		int x = sideLength/2;
		int y = sideLength-1;
		if(sideLength==0)
			return new int[][] {};
		for(int i=0;i<sideLength*sideLength;i++)
		{
			newSquare[y%sideLength][x%sideLength]=i+1;
			x++;
			y++;
			if(newSquare[y%sideLength][x%sideLength]!=0)
			{
				x--;
				y-=2;
			}

		}
		return newSquare;
	}

	/**
	 * returns the sums of an 1D array
	 * @param arr is an 1D array.
	 * @return returns the sum of the given 1D array
	 */
	private int rowSum(int[] arr)
	{
		int total = 0;
		for(int num:arr)
			total+=num;
		return total;
	}

	/**
	 * returns the sum of a column from a 2D array
	 * @param arrs is a 2D array.
	 * @param col is the column need for sum
	 * @return returns the sum of a column in the given 2D array
	 */
	private int colSum(int[][] arrs, int col)
	{
		int total = 0;
		for(int[] arr:arrs)
			total+=arr[col];
		return total;
	}

	/**
	 * gets one sum of a diagonal of 2D array
	 * @param array is a 2D array
	 * @return the sum from top left to bottom right
	 */
	private int dia1Sum(int[][] array)
	{
		int total = 0;
		for (int row = 0; row < array.length; row++)
			total += array[row][row];
		return total;
	}

	/**
	 * gets one sum of a diagonal of 2D array
	 * @param array is a 2D array
	 * @return the sum from bottom left to top right
	 */
	private int dia2Sum(int[][] array) {
		int total = 0;
		for (int row = 0; row < array.length; row++)
			total += array[row][array.length - 1 - row];
		return total;
	}
}