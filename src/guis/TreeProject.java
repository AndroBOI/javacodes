package guis;

import java.util.*;

class TreeNode {
    String value;
    TreeNode left;
    TreeNode right;

    public TreeNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public boolean isNull() {
        return value.equals("_");
    }
}

public class TreeProject {

    public static TreeNode buildBinaryTree(Scanner scanner) {
        int totalLevels = 0;

        // Validate number of levels
        while (true) {
            try {
                System.out.print("Enter number of levels (minimum 1): ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Error: Input cannot be empty!");
                    continue;
                }

                totalLevels = Integer.parseInt(input);

                if (totalLevels < 1) {
                    System.out.println("Error: Number of levels must be at least 1!");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer!");
            }
        }

        List<List<TreeNode>> levels = new ArrayList<>();

        // Read all levels with validation
        for (int level = 1; level <= totalLevels; level++) {
            int expectedNodes = (int) Math.pow(2, level - 1);

            while (true) {
                try {
                    System.out.print("Level " + level + " (" + expectedNodes + " nodes): ");
                    String line = scanner.nextLine().trim();

                    if (line.isEmpty()) {
                        System.out.println("Error: Level cannot be empty!");
                        continue;
                    }

                    List<TreeNode> currentLevel = new ArrayList<>();
                    String[] values = line.split("\\s+");

                    // Validate exact number of nodes for binary tree
                    if (values.length != expectedNodes) {
                        System.out.println();
                        System.out.println("Error: Level " + level + " must have exactly " + expectedNodes + " nodes!");
                        continue;
                    }

                    // Validate node values
                    boolean validInput = true;
                    for (String value : values) {
                        if (value.isEmpty()) {
                            System.out.println("Error: Node values cannot be empty!");
                            validInput = false;
                            break;
                        }
                        if (value.length() > 10) {
                            System.out.println("Error: Node values must be 10 characters or less!");
                            validInput = false;
                            break;
                        }
                        currentLevel.add(new TreeNode(value));
                    }

                    if (!validInput) continue;

                    // First level must have exactly one non-null node
                    if (level == 1) {
                        if (currentLevel.get(0).isNull()) {
                            System.out.println("Error: Root node cannot be null (_)!");
                            continue;
                        }
                    }

                    levels.add(currentLevel);
                    break;
                } catch (Exception e) {
                    System.out.println("Error: Invalid input! " + e.getMessage());
                }
            }
        }

        // Build binary tree structure
        for (int level = 0; level < levels.size() - 1; level++) {
            List<TreeNode> parents = levels.get(level);
            List<TreeNode> children = levels.get(level + 1);

            for (int i = 0; i < parents.size(); i++) {
                TreeNode parent = parents.get(i);

                // Skip null parents - they don't have children
                if (parent.isNull()) {
                    continue;
                }

                // Each parent has 2 children in binary tree
                int leftChildIndex = i * 2;
                int rightChildIndex = i * 2 + 1;

                if (leftChildIndex < children.size()) {
                    parent.left = children.get(leftChildIndex);
                }

                if (rightChildIndex < children.size()) {
                    parent.right = children.get(rightChildIndex);
                }
            }
        }

        return levels.isEmpty() ? null : levels.get(0).get(0);
    }

    // Preorder traversal: Root -> Left -> Right (skip null nodes)
    public static void preorder(TreeNode node, List<String> result) {
        if (node == null || node.isNull()) return;

        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    // Inorder traversal: Left -> Root -> Right (skip null nodes)
    public static void inorder(TreeNode node, List<String> result) {
        if (node == null || node.isNull()) return;

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    // Postorder traversal: Left -> Right -> Root (skip null nodes)
    public static void postorder(TreeNode node, List<String> result) {
        if (node == null || node.isNull()) return;

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        TreeNode root = buildBinaryTree(scanner);

        if (root == null || root.isNull()) {
            System.out.println("\nError: Failed to build tree!");
            scanner.close();
            return;
        }

        // Preorder traversal
        List<String> preorderResult = new ArrayList<>();
        preorder(root, preorderResult);
        System.out.print("\nPre Order: ");
        if (preorderResult.isEmpty()) {
            System.out.println("(empty)");
        } else {
            System.out.print(String.join(" ", preorderResult));
        }

        // Inorder traversal
        List<String> inorderResult = new ArrayList<>();
        inorder(root, inorderResult);
        System.out.print("\nIn Order: ");
        if (inorderResult.isEmpty()) {
            System.out.println("(empty)");
        } else {
            System.out.print(String.join(" ", inorderResult));
        }

        // Postorder traversal
        List<String> postorderResult = new ArrayList<>();
        postorder(root, postorderResult);
        System.out.print("\nPost Order: ");
        if (postorderResult.isEmpty()) {
            System.out.println("(empty)");
        } else {
            System.out.println(String.join(" ", postorderResult));
        }

        scanner.close();
    }
}