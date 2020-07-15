/**
 *  Description: This class has the main method to find the
 *  cheapest loan for the given amount of max loan amount and
 *  years for the loan.
 *
 *  Teacher: Mrs. Ishman
 *  Collab: None
 *	 Period: 2nd
 *  @author Mithul Manivannan
 *  Date: 9/26/19
 */

import java.util.*;
import java.io.*;

public class PickALoan
{
	public static void main(String[] args) throws IOException
	{
		try
		{
			boolean keepGoing = true;
			while (keepGoing)
			{
				String inputFileName = getInputFileName();

				Scanner in = (new Scanner(new File(inputFileName))).useDelimiter("\\s+");
				List<Lender> lenderList = readLenderData(in);

				determineBestLoan(in, lenderList, getOutputFileName(inputFileName));
				keepGoing = checkContinue();

				in.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/**
	 *	 Retrieves the name of an existing input file from the user
	 *  @return file name of an existing text file to read
	 */
	private static String getInputFileName() {
		File inFile;
		String fileName;
		while (true){
			Scanner userInput = new Scanner(System.in);
			System.out.print("Enter file with loan information: ");
			fileName = userInput.next();
			inFile = new File(fileName);
			if(inFile.exists())
				break;
			System.out.print("File not found. Try again.\n\n");
		}
		System.out.println("Recommendations written to " + fileName + "\n");
		return fileName;
	}

	/**
	 * gets output file name with .out as extention from input file
	 * @param inputName the name of the input file from user.
	 * @return the String name of the output file based on the input file name
	 */
	private static String getOutputFileName(String inputName)
	{
		return inputName.split("\\.")[0]+".out";
	}

	/**
	 * returns boolean if the user wants to continue
	 * @return boolean if the user wants to continue
	 */
	private static boolean checkContinue()
	{
		Scanner userInput = new Scanner(System.in);
		System.out.print("Would you like to continue <yes/no>? \n");
		return userInput.next().toLowerCase().startsWith("y");
	}

	/**
	 * reads lender data from the input
	 * @param scan the inputfile scanner file
	 * @return the list of lender offers
	 */
	private static List<Lender> readLenderData(Scanner scan)
	{
		ArrayList<Lender> lenderList = new ArrayList<>();

		int lenderNum = scan.nextInt();
		for (int i = 0; i < lenderNum; i++) {
			scan.nextLine();
			String lending_instit = scan.nextLine();
			double yearR = scan.nextDouble();
			double minMonPay = scan.nextDouble();
			double monFee = scan.nextDouble();
			lenderList.add(new Lender(lending_instit, yearR, minMonPay, monFee));
		}
		return lenderList;
	}

	/**
	 * Ouptup Writes the recommendation for each student to the output file.
	 * @param scan the scanner for the input file.
	 * @param lenders the arraylist of Lenders from input file
	 * @param outputFile the file for the outputfile
	 * @throws IOException catches any IOExceptions thrown
	 */
	private static void determineBestLoan(Scanner scan, List<Lender> lenders, String outputFile)
			throws IOException
	{
		PrintWriter output = new PrintWriter(outputFile);
		int lenderNum = scan.nextInt();
		for (int i = 0; i < lenderNum; i++)
		{
			scan.nextLine();
			String name = scan.nextLine();
			double loanAmount = scan.nextDouble();
			int years = scan.nextInt();

			Lender lender = lenders.get(0);
			for(int j = 1; j < lenders.size(); j++)
				if(lenders.get(j).getMonthlyPayment(loanAmount, years)
						< lender.getMonthlyPayment(loanAmount, years))
					lender = lenders.get(j);

			double cost = lender.getMonthlyPayment(loanAmount, years);
			String instit = lender.getName();
			output.printf("%s should choose the loan from %s for $%6.2f per month.\n", name, instit, cost);
		}
		output.close();
	}
}