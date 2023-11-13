package mauzzysim;

import javax.swing.*;
import java.awt.*;

public class MauzzySimView extends JFrame {
    private JButton startButton;
    private JButton stopButton;
    private JTextArea textArea;
    private JLabel statusLabel;
    private JLabel mousePositionLabel;

    public MauzzySimView() {
        this.setTitle("MauzzySim");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);

        this.initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.statusLabel = new JLabel(" Status: Idle");
        this.mousePositionLabel = new JLabel("Mouse Position: ");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.startButton);
        buttonPanel.add(this.stopButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        this.textArea = new JTextArea();
        this.textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(this.statusLabel, BorderLayout.WEST);
        labelPanel.add(this.mousePositionLabel, BorderLayout.EAST);
        mainPanel.add(labelPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    public JButton getStartButton() {
        return this.startButton;
    }

    public JButton getStopButton() {
        return this.stopButton;
    }

    public JTextArea getTextArea() {
        return this.textArea;
    }

    public JLabel getStatusLabel() {
        return this.statusLabel;
    }

    public void updateMousePositionLabel(String newLabel) {
        this.mousePositionLabel.setText(newLabel);
    }
}
