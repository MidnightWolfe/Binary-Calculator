package v0;


public class BinaryNode {
	int value; // the associated value of this subtree
    String token; // 'a', '+', '3'
    boolean isOperator; // internal or leaf node
    
    BinaryNode right; // right child
    BinaryNode left; // left child


    //Constructor to create internal node (operators)
    public BinaryNode(String token, BinaryNode lt, BinaryNode rt , boolean isOp) {
        this.token = token;
        this.isOperator = true;
        this.left    = lt;
        this.right   = rt;
        this.value = 0;   //value should be calculated later
    }

    //Constructor to create leaf nodes (operands)
    public BinaryNode(String token, BinaryNode lt, BinaryNode rt , int value) {
        this.token = token;
        this.isOperator = false;
        this.left    = lt;
        this.right   = rt;
        this.value = value;
    }
    
    public void printInOrder() {
    	if( left != null )
            left.printInOrder( );            // Left
        System.out.print( token + " " );       // Node
        if( right != null )
            right.printInOrder( );  
    }
    
    public void printPreOrder () {
    	System.out.println(token + " ");
    	if(left != null) {
    		left.printPreOrder();
    	}
    	if(right != null) {
    		right.printPreOrder();
    	}
    }
    
    public void printPostOrder() {
    	if ( left != null) {
    		left.printPostOrder();   		
    	}
    	if (right != null) {
    		right.printPostOrder();
    	}   	
    	System.out.println(token + " ");
    }
}
