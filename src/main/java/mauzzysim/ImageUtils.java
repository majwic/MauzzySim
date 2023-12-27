package mauzzysim;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage captureScreenRegion(int x1, int y1, int x2, int y2, Robot bot) {
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        if (x1 < 0) x1 = 0;
        if (y1 < 0) y1 = 0;
        if (x2 >= screenWidth) x2 = screenWidth - 1;
        if (y2 >= screenHeight) y2 = screenHeight - 1;

        int captureWidth = x2 - x1 + 1;
        int captureHeight = y2 - y1 + 1;

        return bot.createScreenCapture(new Rectangle(x1, y1, captureWidth, captureHeight));
    }

    public static double calculateMSE(BufferedImage image1, BufferedImage image2) {
        if (image1 == null || image2 == null || image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            throw new IllegalArgumentException("Images must have the same dimensions");
        }

        int width = image1.getWidth();
        int height = image1.getHeight();

        long sumSquaredDiff = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y);
                int rgb2 = image2.getRGB(x, y);

                int diff = getRGBDifference(rgb1, rgb2);
                sumSquaredDiff += diff * diff;
            }
        }
        return (double) sumSquaredDiff / (width * height);
    }

    private static int getRGBDifference(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xFF;
        int g1 = (rgb1 >> 8) & 0xFF;
        int b1 = rgb1 & 0xFF;

        int r2 = (rgb2 >> 16) & 0xFF;
        int g2 = (rgb2 >> 8) & 0xFF;
        int b2 = rgb2 & 0xFF;

        int diffR = r1 - r2;
        int diffG = g1 - g2;
        int diffB = b1 - b2;

        return diffR * diffR + diffG * diffG + diffB * diffB;
    }
}
