import java.util.ArrayList;

public class RerootTreeTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//setting up our tree
		RerootTree<String> treeTest = new RerootTree<>();
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
	    System.out.println("Original - Natural Order (sorted): ");
	    treeTest.toPrint();
	    System.out.println("");
	    System.out.println("\nCurrent Root: ");
	    System.out.println(treeTest.getRoot().element);
	    System.out.println("");
		
	    System.out.println("A path from the root to Princess is: ");
	    ArrayList<RerootTree.TreeNode<String>> path = treeTest.path("Princess");
	    for (int i = 0; path != null && i < path.size(); i++)
	      System.out.print(path.get(i).element + " ");	 
	    System.out.println("");
		
//		verify that the root element has changed
//		verify that the sorted order of the elements is not affected by the call to  establishNewRoot
//		verify that the structure of the tree (i.e., how the nodes are linked) is changed by the call to establishNewRoot
//		verify that if newRootElement  is not a member of the tree,  establishNewRoot does nothing
	    System.out.println("");
	    treeTest.establishNewRoot("Max");
	    System.out.println("New Root - Natural Order (sorted): ");
	    treeTest.toPrint();

	    System.out.println("");
		System.out.println("\nCurrent Root: ");
		System.out.println(treeTest.getRoot().element);
//		verify that the elements of the tree are the same before and after the call to establishNewRoot
	    System.out.println("");
	    //testing size - should be 10 now
	    System.out.println("The number of nodes is " + treeTest.getSize());
	    System.out.println("");
	    
	    //testing search - should be true
	    System.out.println("Is Sally in the tree? " + treeTest.search("Sally"));
	    
	    //testing path from the root to Princess	    
	    System.out.println("A path from the root to Princess is: ");
	    ArrayList<RerootTree.TreeNode<String>> path2 = treeTest.path("Princess");
	    for (int i = 0; path2 != null && i < path2.size(); i++)
	      System.out.print(path2.get(i).element + " ");	 
	    System.out.println("");
		
//		verify that if newRootElement  is not a member of the tree,  establishNewRoot does nothing
	    System.out.println("");
	    System.out.println("if newRootElement  is not a member of the tree,  establishNewRoot does nothing: ");
	    treeTest.establishNewRoot("Max");		
		
	}

}
