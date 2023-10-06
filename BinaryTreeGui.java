import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**a
 * A GUI for interacting with a binary tree. Allows the user to input a string
 * representation of a binary tree,
 * and provides buttons for performing various operations on the tree, such as
 * checking if it is balanced or full,
 * getting its height, and traversing it in-order.
 * * *Claire Lindstrom CMSC 350 Project 3
 */
public class BinaryTreeGui {

    private JFrame frame;
    private JTextField inputField;
    private JTextArea outputArea;
    private BinaryTree binaryTree;

    public BinaryTreeGui() {
        // Set Nimbus Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fallback to the default look and feel
        }

        frame = new JFrame("Binary Tree GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10, 10)); // Add some padding between components

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Add padding
        inputField = new JTextField(30);
        JButton makeTreeButton = new JButton("Make Tree");
        makeTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle tree creation here
                String treeStr = inputField.getText(); // Get the string representation from the input field
                binaryTree = new BinaryTree();
                try {
                    binaryTree.constructTree(treeStr);
                    outputArea.append("Tree constructed successfully!\n");
                } catch (InvalidTreeSyntax ex) {
                    outputArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });
        inputPanel.add(inputField);
        inputPanel.add(makeTreeButton);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setMargin(new Insets(10, 10, 10, 10)); // Add padding inside the text area
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Add padding
        String[] buttonLabels = { "Is Balanced", "Is Full", "Is Proper", "Height", "Number of Nodes",
                "Inorder Traversal" };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (label) {
                        case "Is Balanced":
                            // Check if the tree is balanced and display the result
                            boolean isBalanced = binaryTree.isBalanced();
                            outputArea.append("Tree is " + (isBalanced ? "" : "not ") + "balanced.\n");
                            break;
                        case "Is Full":
                            // Check if the tree is full and display the result
                            boolean isFull = binaryTree.isFull();
                            outputArea.append("Tree is " + (isFull ? "" : "not ") + "full.\n");
                            break;
                        case "Is Proper":
                            // Check if the tree is proper and display the result
                            boolean isProper = binaryTree.isProper();
                            outputArea.append("Tree is " + (isProper ? "" : "not ") + "proper.\n");
                            break;
                        case "Height":
                            // Display the height of the tree
                            int height = binaryTree.getHeight();
                            outputArea.append("Tree height: " + height + "\n");
                            break;
                        case "Number of Nodes":
                            // Display the number of nodes in the tree
                            int nodeCount = countNodes(binaryTree.getRoot());
                            outputArea.append("Number of nodes: " + nodeCount + "\n");
                            break;
                        case "Inorder Traversal":
                            // Display the inorder traversal of the tree
                            String inorder = binaryTree.inorderTraversal();
                            outputArea.append("Inorder Traversal: " + inorder + "\n");
                            break;
                    }
                }
            });
            buttonPanel.add(button);
        }

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void show() {
        frame.setVisible(true);
    }

    public class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 1; // include the current node
        count += countNodes(root.left); // recursively count nodes in the left subtree
        count += countNodes(root.right); // recursively count nodes in the right subtree
        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BinaryTreeGui().show();
            }
        });
    }
}
