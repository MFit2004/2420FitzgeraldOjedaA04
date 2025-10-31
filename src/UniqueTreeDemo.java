import java.util.List;

/**
 * The {@code UniqueTreeDemo} class serves as a driver program to test and validate 
 * the functionality of the {@link UniqueTree} class. It provides a sample tree structure 
 * using a list of integer arrays, where each array represents a node and its left and 
 * right child indices. This class ensures that the implemented methods in 
 * {@link UniqueTree} work for arbitrary tree structures.
 *
 * <p>The provided tree structure uses the following format for each node:
 * <ul>
 *   <li>Element at index 0: The value of the node</li>
 *   <li>Element at index 1: The index of the left child in the list (or {@code null} if no left child)</li>
 *   <li>Element at index 2: The index of the right child in the list (or {@code null} if no right child)</li>
 * </ul>
 */
class UniqueTreeDemo {


    public static void main(String[] args) {
        // This is starter code to help you test your method implementations.
        // Ensure your code works for any tree structure, not just the provided example.

        List<Integer[]> treeData = List.of(
                new Integer[]{52, 1, 2}, // root: 52; left child: 23 (index 1) right child: 87 (index 2)
                new Integer[]{23, 3, 4},
                new Integer[]{87, 5, 6},
                new Integer[]{34, 7, 8},
                new Integer[]{45, 9, 10},
                new Integer[]{67, 11, null},
                new Integer[]{78, 12, 13},
                new Integer[]{12, null, null},
                new Integer[]{19, null, null},
                new Integer[]{33, 14, 15},
                new Integer[]{49, null, null},
                new Integer[]{56, null, null},
                new Integer[]{69, 16, null},
                new Integer[]{85, null, null},
                new Integer[]{62, null, null},
                new Integer[]{13, null, null},
                new Integer[]{24, null, null}
        );

        UniqueTree tree = new UniqueTree(treeData);

        tree.doubleEvenNumbers();
        // Ensure the jGraps Visualizer is open so you can track the changes.
        System.out.printf("Count of leaf parents: %d%n", tree.countLeafParents());
        System.out.printf("Average of all odd numbers: %s%n", tree.averageOfOddNumbers());
        System.out.printf("Sum of all right children (excluding root): %d%n", tree.sumOfRightChildren());
        System.out.printf("Greatest difference between two numbers in the tree: %d%n", tree.greatestDifference());
    }
}