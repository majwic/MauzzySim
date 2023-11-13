package mauzzysim;

import javax.swing.*;

public class MauzzySimMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MauzzySimModel model = new MauzzySimModel();
                MauzzySimView view = new MauzzySimView();
                MauzzySimController controller = new MauzzySimController(model, view);

                view.setVisible(true);
            }
        });
    }
}