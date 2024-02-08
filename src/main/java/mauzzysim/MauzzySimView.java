package mauzzysim;

import javax.swing.*;
import java.awt.*;

public class MauzzySimView extends JFrame {
    private JButton startButton;
    private JButton saveButton;
    private JTextArea textArea;
    private JLabel statusLabel;
    private JLabel mousePositionLabel;
    private JLabel errorLabel;

    public MauzzySimView() {
        this.setTitle("MauzzySim");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);

        this.initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        this.startButton = new JButton("Start");
        this.saveButton = new JButton("Save");
        this.statusLabel = new JLabel(" Status: Idle");
        this.mousePositionLabel = new JLabel("Mouse Position: ");
        this.errorLabel = new JLabel("");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.startButton);
        buttonPanel.add(this.saveButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        this.textArea = new JTextArea();
        this.textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel errorSubPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        errorSubPanel.add(this.errorLabel);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(this.statusLabel, BorderLayout.WEST);
        labelPanel.add(errorSubPanel, BorderLayout.CENTER);
        labelPanel.add(this.mousePositionLabel, BorderLayout.EAST);
        mainPanel.add(labelPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    public JButton getStartButton() {
        return this.startButton;
    }

    public JButton getSaveButton() {
        return this.saveButton;
    }

    public JTextArea getTextArea() {
        return this.textArea;
    }

    public JLabel getStatusLabel() {
        return this.statusLabel;
    }

    public JLabel getErrorLabel() {
        return this.errorLabel;
    }

    public void updateMousePositionLabel(String newLabel) {
        this.mousePositionLabel.setText(newLabel);
    }
}
