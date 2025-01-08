package v0;


public class ExpressionTree {
	private BinaryNode root;
	
	// constructor to create an empty tree
	public ExpressionTree() {
        root = null;
    }

	// constructor to build an expression with just one node 
    public ExpressionTree( String token , int value){
        root = new BinaryNode( token, null, null, value );
    }
    
    // merge method: it takes two other subtrees and a token as root
    // root will be an operator
    public void merge( String token, ExpressionTree t1, ExpressionTree t2 ) {
        if( t1.root == t2.root && t1.root != null ) {
            System.err.println( "leftTree==rightTree; merge aborted" );
            return;
        }

        if (IsOperator(token)==false) {
            System.err.println( "root should be an operator" );
            return;
        }
        // Allocate new node (internal constructor)
        root = new BinaryNode( token, t1.root, t2.root , true);

        // Ensure that every node is in just one tree!
        if( this != t1 )
            t1.root = null;
        if( this != t2 )
            t2.root = null;
    }

    private boolean IsOperator(String token) {
    	if (token.equals("*") || token.equals("/") || token.equals("%") || token.equals("+") || token.equals("-")) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void printPreOrder() {
    	if( root != null ) {
            root.printPreOrder();
    	}
    }

    public void printInOrder(){
        if( root != null )
           root.printInOrder();
    }

    public void printPostOrder() {
    	if( root != null ) {
            root.printPostOrder();
    	}
    }

    /*
     * It evaluates the expression tree and return the result
     */
    public double evaluate() {           	
    	return evaluate_node(root);
    }
    /*
     * Evaluates each node recursively, and returns the value of each node
     */
    public double evaluate_node(BinaryNode node) {
    	if(!node.isOperator) {
    		return node.value;
    	}
    	
    	double leftValue = evaluate_node(node.left);
    	double rightValue = evaluate_node(node.right);
    	
    	if(node.token.equals("+")) {
    		return leftValue + rightValue;
    	}
    	else if(node.token.equals("-")) {
    		return leftValue - rightValue;
    	}
    	else if(node.token.equals("*")) {
    		return leftValue * rightValue;
    	}
    	else if(node.token.equals("/")) {
    		if(rightValue == 0) {
    			System.err.println("Cannot divide by 0");
    			System.exit(0);
    		}
    		return leftValue / rightValue;
    		
    	}
    	else{
    		if(rightValue == 0) {
    			System.err.println("Cannot mod by 0");
    			System.exit(0);
    		}
    		return leftValue % rightValue;
    	}

    }
}
