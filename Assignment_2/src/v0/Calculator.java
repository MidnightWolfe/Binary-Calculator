package v0;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
	private ExpressionTree expTree;
	HashMap<String, Integer> VariableTable;
	HashMap<String, Integer> Operators;	
	
	public Calculator() {
		VariableTable = new HashMap<String, Integer>();
		expTree = null;
		Operators = new HashMap<String, Integer>();
		//Filling the HashMap for operators and precedence
		Operators.put("+", 1);		
		Operators.put("-", 1);
		Operators.put("*", 2);
		Operators.put("/", 2);
		Operators.put("%", 2);
		Operators.put("(", 3);
		Operators.put(")", 3);
		
	}
	//Add any other method that you need
	/* 
	 * Private method that checks to see if a String token is an operator
	 */
	private boolean isOperator(String token) {
		if (token.equals("*") || token.equals("/") || token.equals("%") || token.equals("+") || token.equals("-")) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * Private boolean method to check if String token is a variable 
	 */
	private boolean isVariable(String token) {		
		HashSet<String> letters = new HashSet<String>();
		letters.add("a");
		letters.add("b");
		letters.add("c");
		letters.add("d");
		letters.add("e");
		letters.add("f");
		letters.add("g");
		letters.add("h");
		letters.add("i");
		letters.add("j");
		letters.add("k");
		letters.add("l");
		letters.add("m");
		letters.add("n");
		letters.add("o");
		letters.add("p");
		letters.add("q");
		letters.add("r");
		letters.add("s");
		letters.add("t");
		letters.add("u");
		letters.add("v");
		letters.add("w");
		letters.add("x");
		letters.add("y");
		letters.add("z");
		letters.add("A");
		letters.add("B");
		letters.add("C");
		letters.add("D");
		letters.add("E");
		letters.add("F");
		letters.add("G");
		letters.add("H");
		letters.add("I");
		letters.add("J");
		letters.add("K");
		letters.add("L");
		letters.add("M");
		letters.add("N");
		letters.add("O");
		letters.add("P");
		letters.add("Q");
		letters.add("R");
		letters.add("S");
		letters.add("T");
		letters.add("U");
		letters.add("V");
		letters.add("W");
		letters.add("X");
		letters.add("Y");
		letters.add("Z");
		if (letters.contains(token)) {
			return true;
		}
		else {
			return false;
		}				
				
	}
	/*
	 * Private boolean method to check if String token is a Integer
	 * Found the logic for this method online
	 */	
	private boolean isInteger (String token) {
		try {
			Integer.parseInt(token);
			return true;
		}
		catch (NumberFormatException e){
			return false;
		}			
	}
	
	
	
	/*
	 *  Private boolean method that sees if the String token is an operand 
	 */
	private boolean isOperand (String token) {		
		if (isVariable(token) == true || isInteger(token) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/*
	 * This is the main method which will be called to evaluate an input file
	 * It should:
	 * 		read the file, 
	 *      store the variables, 
	 *      create the expression tree (  expTree = stringToExpressionTree() )
	 *      evaluate tree ( expTree.evaluate() )
	 *      print the final result
	 */
	public void run(String inputFile) throws FileNotFoundException {				
		int i = 0;
		String [] fileArray;
		String delimiter = " ";
		String s;
		Scanner fin = new Scanner(new FileReader(inputFile));
		while(fin.hasNext()) {
			String str = fin.nextLine();
			fileArray = str.split(delimiter);
			s = fileArray[i];
			if(fileArray[1].equals("=")) {
				VariableTable.put(s, Integer.parseInt(fileArray[2]));
			}			
			if(!fileArray[1].equals("=")) {
				expTree = stringToExpressionTree(fileArray);
				double result = expTree.evaluate();
				System.out.println("The expression tree is: ");
				expTree.printInOrder();
				System.out.println("\nThe expression = " + result);				
			}
			
		}
	}
	
	/*
	 * method to convert a string to an expression tree
	 */
	private ExpressionTree stringToExpressionTree(String[] infixExpression){
		Stack<ExpressionTree> treeStack = new Stack<ExpressionTree>();
		Stack<String> opStack = new Stack<String>();						
		// You can find the algorithm in the description of the assignment				
		int i = 0;				
		while (i < infixExpression.length){
			int x = 0;
			String e = infixExpression[i];		
			if(isOperand(e) == true) {				
				if(isVariable(e) == true) {						
					if (VariableTable.containsKey(e)) {
						x = VariableTable.get(e);						
					}
					else {				
						System.err.println("Undefined Variable: " + e + " is undefined!");
						System.exit(0);
					}
				}
				else if (isInteger(e) == true){					
					x = Integer.parseInt(e);
				}
				else {
					System.err.println("Syntax Error: invalid infix expression!");
					System.exit(0);
				}

				ExpressionTree subtree = new ExpressionTree(e, x);
				treeStack.push(subtree);
			}
			else if (e.equals("(")) {
				opStack.push(e);
			
			}
			else if (isOperator(e) == true) {
				if(opStack.isEmpty()) {
					opStack.push(e);
				}
				else {	
					while( !opStack.isEmpty() && !opStack.peek().equals("(") && Operators.get(opStack.peek()) >= Operators.get(e)) {
						String op = opStack.pop();
						if (treeStack.isEmpty()) {
							System.err.println("Syntax Error: treeStack is empty!");
							System.exit(0);
						}
						ExpressionTree right = treeStack.pop();
						if (treeStack.isEmpty()) {							
							System.err.println("Syntax Error: treeStack is empty!");
							System.exit(0);
						}
						ExpressionTree left = treeStack.pop();
						ExpressionTree internal = new ExpressionTree(op,0);
						internal.merge(op,left,right);
						treeStack.push(internal);
					}
					opStack.push(e);
				}				
			}
			else if (e.equals(")")) {				
				while(!opStack.isEmpty() && !opStack.peek().equals("(")) {
					String op = opStack.pop();
					if (treeStack.isEmpty()) {						
						System.err.println("Syntax Error: treeStack is empty!");
						System.exit(0);
					}
					ExpressionTree right = treeStack.pop();
					if (treeStack.isEmpty()) {						
						System.err.println("Syntax Error: treeStack is empty!");
						System.exit(0);
					}
					ExpressionTree left = treeStack.pop();
					ExpressionTree internal = new ExpressionTree(op,0);
					internal.merge(op,left,right);
					treeStack.push(internal);
				}
				if(opStack.isEmpty()) {
					System.err.println("Syntax Error: invalid infix expression!");
					System.exit(0);
				}
				opStack.pop(); //Popping "("
			}			
			else {		
				System.err.println("Syntax Error: invalid infix expression!");
				System.exit(0);
			}
			i++;
		}
		while(!opStack.isEmpty()) {
			String op = opStack.pop();
			if (treeStack.isEmpty()) {
				System.err.println("Syntax Error: treeStack is empty!");
				System.exit(0);
			}
			ExpressionTree right = treeStack.pop();
			if (treeStack.isEmpty()) {
				System.err.println("Syntax Error: treeStack is empty!");
				System.exit(0);
			}
			ExpressionTree left = treeStack.pop();
			ExpressionTree internal = new ExpressionTree(op,0);
			internal.merge(op,left,right);
			treeStack.push(internal);
		}
		expTree = treeStack.pop();
		if (!treeStack.isEmpty()) {
			System.err.println("Syntax Error: treeStack is not empty!");
			System.exit(0);
		}
		return expTree;					
	}
		
	
}
