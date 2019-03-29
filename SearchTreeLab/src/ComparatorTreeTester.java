import java.util.*;

public class ComparatorTreeTester {

	public static void main(String[] args) {
		//setting up our tree
	    ComparatorTree<String> treeTest = new ComparatorTree<>();
	    treeTest.insert("Sophie");
	    treeTest.insert("Rusty");
	    treeTest.insert("Sally");
	    treeTest.insert("Jack");
	    treeTest.insert("Tiger");
	    treeTest.insert("Spot");
	    treeTest.insert("Clifford");
	    treeTest.insert("Remmy");
	    treeTest.insert("Princess");
	    
	    //natural (sorted) order .... wrote printingTree to print in current order, used multiple times
	    System.out.print("Natural Order (sorted): ");
	    treeTest.toPrint();
	    
//	    //testing size - should be 9
//	    System.out.println("The number of nodes is " + treeTest.getSize());
//	    System.out.println("");
//	    
//	    //testing search - should be true
//	    System.out.println("Is Sally in the tree? " + treeTest.search("Sally"));
//	    
//	    //testing path from the root to Princess	    
//	    System.out.println("A path from the root to Princess is: ");
//	    ArrayList<ComparatorTree.TreeNode<String>> path = treeTest.path("Princess");
//	    for (int i = 0; path != null && i < path.size(); i++)
//	      System.out.print(path.get(i).data + " ");	 
//	    System.out.println("");

		//use a Comparator to order String elements by their reversed value (hint: use a StringBuilder)
	    String[] strings = {"beta", "alpha", "zeta", "phi", "theta", "kappa", "omega"};
	    // i know the hint said use a string builder, but i used a comparator to pass through and then called my print statement
	    Comparator reverseOrdering = (string1, string2) -> ((String) string2).compareTo((String) string1);
	    ComparatorTree<String> stringTree = new ComparatorTree<>(strings,reverseOrdering);
	    System.out.print("\nInorder by reversed value (sorted): ");
	    stringTree.toPrint();
	    
		//use a Comparator to order Integer elements by their toString values.
	    Integer[] numbers = {5, -7, 8, 1, 9, 6, 3, 2, 4, 0};
	    Comparator numbersToString = (number1, number2) ->(number1.toString()).compareTo(number2.toString());
	    ComparatorTree<Integer> integerTree = new ComparatorTree<Integer>(numbers, numbersToString);
	    System.out.print("\nInorder by toString values (sorted): ");
	    integerTree.toPrint();
	    
		//use a Comparator order Integers by their negated values.  So if you wanted to order 5 and 6, you 
	    //would actually compare -5 to -6 to determine the order
	    Integer[] numbers2 = {5, -7, 8, 1, 9, 16, 3, -2, 4, -20};
	    Comparator numbersNegated = (number1, number2) ->((Integer)((Integer)number1 - (2 * (Integer)number1)))
	    		.compareTo((Integer)((Integer)number2 - (2 * (Integer)number2)));
	    ComparatorTree<Integer> integerNegatedTree = new ComparatorTree<Integer>(numbers2, numbersNegated);
	    System.out.print("\nInorder by negated values (sorted): ");
	    integerNegatedTree.toPrint();
	    

	}

}
