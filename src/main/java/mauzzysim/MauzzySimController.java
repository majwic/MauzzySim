package mauzzysim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MauzzySimController {
    private MauzzySimModel model;
    private MauzzySimView view;
    private MousePositionUpdater mousePositionUpdater;

    public MauzzySimController(MauzzySimModel model, MauzzySimView view) {
        this.model = model;
        this.view = view;

        mousePositionUpdater = new MousePositionUpdater();
        mousePositionUpdater.execute();

        // Add action listeners
        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getStatusLabel().setText(" Status: Running");

                String[] commands = view.getTextArea().getText().split("\n");
                    for (String command : commands) {
                        String error = model.runCommand(command);
                        view.getErrorLabel().setText(error);
                        if (!error.isEmpty()) break;
                    }

                view.getStatusLabel().setText(" Status: Finished");
            }
        });
    }

    private class MousePositionUpdater extends SwingWorker<Void, String> {
        @Override
        protected Void doInBackground() throws Exception {
            try {
                Robot robot = new Robot();
                while (!isCancelled()) {
                    Point mousePos = MouseInfo.getPointerInfo().getLocation();
                    String positionText = "Mouse Position: X=" + mousePos.x + ", Y=" + mousePos.y + " ";
                    publish(positionText);
                    Thread.sleep(100); // Update every 100 milliseconds to reduce CPU load
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void process(java.util.List<String> chunks) {
            // Update the view with the latest mouse position text
            view.updateMousePositionLabel(chunks.get(chunks.size() - 1));
        }
    }
}
