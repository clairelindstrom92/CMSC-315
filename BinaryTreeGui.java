import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BinaryTreeGui {

    private JFrame frame;
    private JTextField inputField;
    private JTextArea outputArea;

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
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10)); // Add some padding between components

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Add padding
        inputField = new JTextField(30);
        JButton makeTreeButton = new JButton("Make Tree");
        makeTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle tree creation here
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
                    // Handle button actions here
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BinaryTreeGui().show();
            }
        });
    }
}
