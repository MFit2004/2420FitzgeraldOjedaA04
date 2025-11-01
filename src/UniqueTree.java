import java.util.Set;
import java.util.HashSet;
import java.util.List;

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
        doubleEvenNumbers(root);
    }

    private void doubleEvenNumbers(Node x) {
        if (x == null) return;
        if (x.val % 2 == 0) x.val *= 2;
        doubleEvenNumbers(x.left);
        doubleEvenNumbers(x.right);
    }

    /**
     * Calculates the sum of all right children (excluding the root).
     * @return the sum of all right children
     */
    public int sumOfRightChildren() {
        if (root == null) return 0;
        return sumOfRightChildren(root);
    }

    private int sumOfRightChildren(Node x) {
        if (x == null) return 0;
        int sum = 0;
        if (x.right != null) sum += x.right.val;
        sum += sumOfRightChildren(x.left);
        sum += sumOfRightChildren(x.right);
        return sum;
    }

    /**
     * Counts how many nodes have at least one child that is a leave.
     *
     * @return the count of nodes with all children as leaves
     */
    public int countLeafParents() {
        return countLeafParents(root);
    }

    /**
     * count number of Nodes 1 generation away from leaves
     * @param x subject Node
     * @return recursive count
     */
    private int countLeafParents(Node x) {
        int count = 0;
        if (x == null) return 0;
        if (isLeaf(x.left) || isLeaf(x.right))
            count++;

        if (x.left != null)  count += countLeafParents(x.left);
        if (x.right != null) count += countLeafParents(x.right);
        return count;
    }

    /**
     * determine if node passed is a leaf
     * @param x subject Node
     * @return true if x has no children
     */
    private boolean isLeaf(Node x) {
        if (x == null) return false;
        return (x.left == null && x.right == null);
    }

    /**
     * Calculates the average of all odd numbers in the tree.
     *
     * @return the average of all odd numbers, or 0 if there are no odd numbers
     */
    public double averageOfOddNumbers() {
        if (root == null) return 0;
        double count = countOddNums(root);
        return count == 0 ? 0 : sumOddNums(root) / count;
    }


    /**
     * get the sum of odd node values
     * @param x subject node (determine if odd)
     * @return sum of all odd values
     */
    public double sumOddNums(Node x) {
        if (x == null) return 0d;
        double sum = 0;
        if (x.val % 2 == 1) sum += x.val;
        sum += sumOddNums(x.left);
        sum += sumOddNums(x.right);
        return sum;
    }

    /**
     * get the count of odd node values
     * @param x subject node (determine if odd)
     * @return count of all odd values
     */
    public double countOddNums(Node x) {
        if (x == null) return 0d;
        double count = 0d;
        if (x.val % 2 == 1) count++;
        count += countOddNums(x.left);
        count += countOddNums(x.right);
        return count;
    }

    /**
     * Finds the difference between the largest and smallest elements in the tree.
     *
     * @return the difference between the largest and smallest values
     */
    public int greatestDifference() {
        int max = max(root);
        int min = min(root);
        return max - min;
    }

    /**
     * recursively find the largest value in tree
     * @param x compair current max
     * with this Node's value
     * @return largest value
     */
    private int max(Node x) {
        if (x == null) return Integer.MIN_VALUE;
        int greatest = x.val;
        int leftmax = max(x.left);
        if (leftmax > greatest) greatest = leftmax;
        int rightmax = max(x.right);
        if (rightmax > greatest) greatest = rightmax;
        return greatest;
    }

    /**
     * recursively find the smallest value in tree
     * @param x compair current min
     * with this Node's value
     * @return smallest value
     */
    private int min(Node x) {
        if (x == null) return Integer.MAX_VALUE;
        int smallest = x.val;

        int leftmin = min(x.left);
        if (leftmin < smallest) smallest = leftmin;
        int rightmin = min(x.right);
        if (rightmin < smallest) smallest = rightmin;
        return smallest;
    }

}

