import java.util.*;

public class ComparatorTree<E extends Comparable<E>> implements Tree<E> {
	protected TreeNode<E> root;
	protected int size = 0;
	// lambda strategy: default comparator is by the natural order;
	private Comparator<E> comparison = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);

	// Create constructors: default
	public ComparatorTree() {
	}

	public ComparatorTree(Comparator<E> comparison) {
		this.comparison = comparison; // Use a specified comparator
	}

	/** Create a binary tree from an array of objects */
	public ComparatorTree(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	/** Create a binary tree from an array of objects */
	public ComparatorTree(E[] objects, Comparator<E> comparison) {
		this.comparison = comparison;
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean insert(E e) {
		if (root == null)
			root = createNewNode(e); // if no root - creates new
		else {
			// Locate the parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null)
				if (comparison.compare(e, current.data) < 0) {
					parent = current;
					current = current.left;
				} else if (comparison.compare(e, current.data) > 0) {
					parent = current;
					current = current.right;
				} else
					return false; // Duplicate node not inserted

			// Create the new node and attach it to the parent node
			if (comparison.compare(e, parent.data) < 0)
				parent.left = createNewNode(e);
			else
				parent.right = createNewNode(e);
		}

		size++;
		return true; // Element inserted successfully
	}

	@Override
	public boolean delete(E e) {
		// finding the node to delete
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		while (current != null) {
			if (comparison.compare(e, current.data) < 0) {
				parent = current;
				current = current.left;
			} else if (comparison.compare(e, current.data) > 0) {
				parent = current;
				current = current.right;
			} else
				break;
		}

		if (current == null)
			return false;

		// Case 1: current has no left child (parent to right child)
		if (current.left == null) {
			if (parent == null) {
				root = current.right;
			} else {
				if (comparison.compare(e, parent.data) < 0)
					parent.left = current.right;
				else
					parent.right = current.right;
			}
		} else {
			// Case 2: The current node has a left child (current element replaced by
			// rightmost)
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;

			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}
			current.data = rightMost.data;

			// Eliminate rightmost node
			if (parentOfRightMost.right == rightMost)
				parentOfRightMost.right = rightMost.left;
			else
				// Special case: parentOfRightMost == current
				parentOfRightMost.left = rightMost.left;
		}

		size--;
		return true;
	}

	@Override
	public int getSize() {
		return size;
	}

	// defines our Nodes and implements methods to create new and search
	// also a .path method which was in 25_14 exercise
	public static class TreeNode<E> {
		protected E data;
		protected TreeNode<E> left;
		protected TreeNode<E> right;

		public TreeNode(E e) {
			this.data = e;
			left = right = null;
		}

		boolean isLeaf() {
			return left == null ? right == null : false;
		}

	}

	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<>(e);
	}

	@Override
	public boolean search(E e) {
		TreeNode<E> current = root;

		while (current != null) {
			if (comparison.compare(e, current.data) < 0) {
				current = current.left;
			} else if (comparison.compare(e, current.data) > 0) {
				current = current.right;
			} else
				return true;
		}
		return false;
	}

	public ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		TreeNode<E> current = root;

		while (current != null) {
			list.add(current);
			if (comparison.compare(e, current.data) < 0) {
				current = current.left;
			} else if (comparison.compare(e, current.data) > 0) {
				current = current.right;
			} else
				break;
		}
		return list;
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	@Override
	public Iterator<E> iterator() {
		return new InorderIterator();
	}

	// InorderIterator (nested class)
	private class InorderIterator implements Iterator<E> {

		private ArrayList<E> list = new ArrayList<>();
		private int currentElement = 0;

		public InorderIterator() {
			inorder();
		}

		private void inorder() {
			inorder(root);
		}

		public ArrayList<E> toList() {
			inorder();
			return list;
		}

		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.data);
			inorder(root.right);
		}

		@Override
		public boolean hasNext() {
			if (currentElement < list.size())
				return true;
			else
				return false;
		}

		@Override /** Get the current element and move to the next */
		public E next() {
			return list.get(currentElement++);
		}

		@Override /** Remove the current element */
		public void remove() {
			delete(list.get(currentElement)); // Delete the current element
			list.clear(); // Clear the list
			inorder(); // Rebuild the list
		}
	}
	
	
    //printing methods

	public void toPrint(){
		this.printingTree(root);
	}
	
	public void printingTree(TreeNode<E> node) {
		if (node == null) {
			return;
		}
		printingTree(node.left);
		System.out.printf("%s ", node.data);
		printingTree(node.right);
	}

}
