# Binary-Calculator
A binary calculator written using Java.
Note 1: I changed evaluate() from int to double, as any operation that is division
      could produce a decimal, depending on the expression.

Note 2: private boolean isInteger(String token) logic I found online, as I wasn't
	sure how to check if a String was an integer without producing an error.


The purpose of this program is to make a expression tree (binary tree)
from lines of a file, in the form of strings, and calculate the value of 
the expression by moving through the built tree.

The classes for this assignment are: BinaryNode, ExpressionTree, Calculator,
and test.

BinaryNode: This classes' purpose is to create the nodes for the expression
	    tree.
	Constructors: public BinaryNode(String token, BinaryNode lt, BinaryNode rt, boolean isOp)
		      public BinaryNode(String token, BinaryNode lt, BinaryNode rt, int value) 
	Methods: public void printInOrder()
		 public void printPreOrder()
		 public void printPostOrder()

ExpressionTree: This classes' purpose is to create the expression tree from
		the operators, integers, and variables given by the file.
		It calls BinaryNode to create nodes, and merges each node into
		a subtree, before creating the main tree. This class also
		evaluates the expression tree, returning the value of the
		calculation.
	Constructors: public ExpressionTree()
		      public ExpressionTree(String token, int value)
	Methods: public void merge(String token, ExpressionTree t1, ExpressionTree t2)
		 public boolean IsOperator(String token)
		 public void printInOrder()
		 public void printPreOrder()
		 public void printPostOrder()
		 public double evaluate()
		 public double evaluate_node(BinaryNode node)

Calculator: This classes' purpose is to take a file, store the variables,
	    convert each line to String, then to a String [], and convert that expression to 
	    the tree itself, before printing the final calculated result of the expression.
Constructor: Calculator()
Methods: private boolean isOperator(String token)
	 private boolean isVariable(String token)
	 private boolean isInteger(String token)
	 private boolean isOperand(String token)
	 public void run(String inputFile)
	 private ExpressionTree stringToExpressionTree(String[] infixExpression)

test: This is just the main() which calls Calculator after taking the file from
      the command line.
