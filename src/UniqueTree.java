import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a binary tree with unique integer values. Provides various methods to
 * manipulate and analyze the tree structure, including doubling even numbers, counting
 * specific types of nodes, and calculating differences between values.
 *
 * The tree data is represented as a list of integer arrays, where each array contains
 * the value of a node and the indices of its left and right children. For example:
 *
 * [node_value, left_child_index, right_child_index]
 *
 * The root node is at index 0. A left or right child index of null indicates that
 * the node does not have a corresponding child.
 */
public class UniqueTree {

    private Node root;
    private Set<Integer> elements = new HashSet<>();  // To ensure uniqueness

    // Inner class representing a node in the tree
    private class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /**
     * Constructs a UniqueTree from a list of integer arrays.
     * Each array contains: [node_value, left_child_index, right_child_index]
     *
     * @param treeData the list representing the tree structure
     * @throws IllegalArgumentException if the tree contains duplicate values
     */
    public UniqueTree(List<Integer[]> treeData) {
        if (treeData == null || treeData.isEmpty()) {
            throw new IllegalArgumentException("Tree data cannot be null or empty.");
        }
        this.root = buildTree(treeData, 0);  // Start building from the root (index 0)
    }

    // Helper method to recursively build the tree from the list
    private Node buildTree(List<Integer[]> data, int index) {
        if (index == -1 || index >= data.size() || data.get(index) == null) {
            return null;
        }

        Integer[] nodeData = data.get(index);
        int value = nodeData[0];

        // Check for uniqueness
        if (!elements.add(value)) {
            throw new IllegalArgumentException("Duplicate value found: " + value);
        }

        Node node = new Node(value);

        // Build the left and right subtrees using the provided indices
        node.left = buildTree(data, nodeData[1] != null ? nodeData[1] : -1);
        node.right = buildTree(data, nodeData[2] != null ? nodeData[2] : -1);

        return node;
    }

    /**
     * Doubles the value of all even numbers in the tree.
     */
    public void doubleEvenNumbers() {
        if (root == null) return;
        if (root.val % 2 == 0) root.val *= 2;

        if (root.left == null) {
            Node temp = root;
            root = root.left;
            doubleEvenNumbers();
            root = temp;
        }


        if (root.right == null) {
            Node temp = root;
            root = root.right;
            doubleEvenNumbers();
            root = temp;
        }
    }

    /**
     * Calculates the sum of all right children (excluding the root).
     * @return the sum of all right children
     */
    public int sumOfRightChildren() {
        int sum = 0;
        if (root == null) return 0;
        Node temp = root;

        if (root.right == null) {
            sum += root.right.val;
            root = root.right;
            sum += sumOfRightChildren();
            root = temp;
        }

        if (root.left == null) {
            root = root.left;
            sum += sumOfRightChildren();
            root = temp;
        }
        return sum;
    }

    /**
     * Counts how many nodes have at least one child that is a leave.
     *
     * @return the count of nodes with all children as leaves
     */
    public int countLeafParents() {
        return 0; // TODO
    }

    /**
     * Calculates the average of all odd numbers in the tree.
     *
     * @return the average of all odd numbers, or 0 if there are no odd numbers
     */
    public double averageOfOddNumbers() {
        if (root == null) return 0;

        return 1; //TODO
    }

    /**
     * Finds the difference between the largest and smallest elements in the tree.
     *
     * @return the difference between the largest and smallest values
     */
    public int greatestDifference() {
        if (root == null) return 0;

        int max = 0;
        int min = 0;
        int mid = (min + (max - min) / 2);

        if (root.val > max) max = root.val;
        if (root.val < min) min = root.val;

        if (root.left == null) {
            Node temp = root;
            root = root.left;
            greatestDifference();
            root = temp;
        }

        if (root.right == null) {
            Node temp = root;
            root = root.right;
            greatestDifference();
            root = temp;
        }
        return max - min; // TODO
    }

}