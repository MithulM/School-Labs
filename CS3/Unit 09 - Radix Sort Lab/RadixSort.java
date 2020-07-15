/**
 * A class that sorts a given array of numbers. The class takes an array of
 * unsorted integers and uses radix sort by converting the numbers to a queue.
 * @author Mithul Manivannan
 * Collaborators:
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 11/28/19
 */

import java.util.*;

public class RadixSort
{
	private static final int BASE = 10;

	/** Sorts the given array using the Radix Sort
	 *  @param nums the array of integers to be sorted
	 *  Precondition: nums.length > 0; all values in nums are non-negative
	 */
	public static void sort(int[] nums)
	{
		int numDigits = getMaxDigits(nums);

		for (int k = 0; k < numDigits; k++)
		{
			List<Queue<Integer>> qs = itemsToQueues(nums, k);
			queuesToArray(qs, nums);
		}
	}

	/**
	 * returns the highest number of digits in numbers
	 * @param numbers an array of numbers to be sorted
	 * @return the highest number of digits in numbers
	 */
	private static int getMaxDigits(int[] numbers)
	{
		int max = 0;
		for(int num: numbers)
			if(Integer.toString(num).length() > max)
				max = Integer.toString(num).length();
		return max;
	}

	/**
	 * returns the digit with the kth number from the right
	 * @param number a number to parse
	 * @param k the index from the right of number
	 * @return the digit with the kth number from the right
	 */
	private static int getDigit(int number, int k)
	{
		return (number / (int) Math.pow(BASE, k)) % 10;
	}

	/**
	 * adds numbers to a queue based on their Kth index
	 * @param nums an array of numbers to be sorted
	 * @param k the index from the right
	 * @return an arraylist of queues that store the numbers based on the Kth index from the right
	 */
	private static List<Queue<Integer>> itemsToQueues(int[] nums, int k)
	{
		Queue<Integer>[] queues = new LinkedList[BASE];
		for (int i = 0; i < BASE; i++)
			queues[i] = new LinkedList<Integer>();
		for(int num: nums)
			queues[getDigit(num, k)].offer(num);
		return new ArrayList<>(Arrays.asList(queues).subList(0, BASE));
	}

	/**
	 * @param queues the ArrayList from itemsToAueues
	 * @param nums an array of numbers that need to be added.
	 */
	private static void queuesToArray(List<Queue<Integer>> queues, int[] nums)
	{
		int index = 0;
		for(Queue<Integer> queue: queues) {
			for (int num : queue)
			{
				nums[endedLast] = num;
				index++;
			}
		}
	}
}

