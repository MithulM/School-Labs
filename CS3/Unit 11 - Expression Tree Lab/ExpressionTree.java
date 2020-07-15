/**
 * Description: Creates a binary tree using ExpressionNode. It
 * recursivly solves the prefix expression given by user and built
 * using the treeFiller method.
 *
 * @author Mithul Manivannan
 * Teacher: Mrs. Ishman
 * Period: 2
 * Due Date: 3/6/2020
 */

import java.util.Scanner;

public class ExpressionTree
{
	/** Root of the expression tree */
	private ExpressionNode root;
	/** Used to parse a prefix expression */
	private Scanner strScan;

	/**
	 * initializes root to an empty binary tree
	 */
	public ExpressionTree()
	{
		root = null;
	}

	/**
	 * @param prefix is a string given by user
	 */
	public ExpressionTree(String prefix)
	{
		root = null;
		setExpression(prefix);
	}

	/**
	 * initializes strScan to prefix scanner and calls treeFiller method
	 * @param prefix a string given by user
	 */
	public void setExpression(String prefix)
	{
		strScan = new Scanner(prefix);
		root = treeFiller();
	}

	/**
	 * calls solve helper method
	 * @returns the final answer from a binary tree
	 */
	public double evaluate()
	{
		return solve(root);
	}

	/**
	 * @return a string in infix notation
	 */
	@Override
	public String toString()
	{
		if(root == null)
			return "0";
		return inOrder(root);
	}

	/**
	 * solves the binary tree expression
	 * @returns the final answer from a binary tree
	 * @param node is a binary tree
	 */
	private double solve(ExpressionNode node)
	{
		if(node == null)
			return 0.0;
		if(node.getType() == NodeType.NUMBER)
			return node.getValue();
		double left = solve(node.getLeft());
		double right = solve(node.getRight());
		if(node.getType() == NodeType.ADD)
			return left + right;
		if(node.getType() == NodeType.SUBTRACT)
			return left - right;
		if(node.getType() == NodeType.MULTIPLY)
			return left * right;
		if(node.getType() == NodeType.DIVIDE)
			return left / right;
		if(node.getType() == NodeType.EXPONENT)
			return Math.pow(left, right);
		if(node.getType() == NodeType.REMAINDER)
			return left % right;
		return 0.0;
	}

	// Private Methods
	/**
	 * creates a node based on if it is a number or a symbol
	 * @return a binary tree from a prefix string
	 */
	private ExpressionNode treeFiller() {
		if(!strScan.hasNext())	return null;
		if(strScan.hasNextInt()) return new ExpressionNode(Integer.parseInt(strScan.next()));
		return new ExpressionNode(getSign(strScan.next()), treeFiller(), treeFiller());
	}

	/**
	 * a helper method for the String method
	 * @return a string with an inOrder traversal of node
	 * @param node is the root of a binary tree
	 */
	private String inOrder(ExpressionNode node)
	{
		StringBuilder result = new StringBuilder();
		if(node != null) {
			result.append(node.getLeft() != null ? "(" : "");
			result.append(inOrder(node.getLeft()));
			result.append(node.getType() == NodeType.NUMBER ? node.getValue()
									: " " + node.getType().getSymbol() + " ");
			result.append(inOrder(node.getRight()));
			result.append(node.getRight() != null ? ")" : "");
		}
		return result.toString();
	}

	/**
	 * uses a switch case to find the symbol of val
	 * @return a NodeType indicating what symbol is being used
	 * @param val a string that gets the NodeType symbol
	 */
	private NodeType getSign(String val) {
		switch(val)
		{
			case "+":
				return NodeType.ADD;
			case "-":
				return NodeType.SUBTRACT;
			case "/":
				return NodeType.DIVIDE;
			case "%":
				return NodeType.REMAINDER;
			case "*":
				return NodeType.MULTIPLY;
			case "^":
				return NodeType.EXPONENT;
			default:
				return NodeType.NUMBER;
		}
	}

}

