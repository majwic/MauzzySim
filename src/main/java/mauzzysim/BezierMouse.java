package mauzzysim;

import java.awt.*;
import java.util.Random;

public class BezierMouse {
    private Robot bot;

    BezierMouse(Robot bot) {
        this.bot = bot;
    }

    public void bezierMoveTo(int x, int y) {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        bezeirMouseMove(new Point(mouseLocation.x, mouseLocation.y), new Point(x, y));
    }

    public void bezierMove(int x, int y) {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        int currentX = mouseLocation.x;
        int currentY = mouseLocation.y;

        bezeirMouseMove(new Point(currentX, currentY), new Point(currentX + x, currentY + y));
    }

    public void bezierMoveToZone(int x1, int y1, int x2, int y2) {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        int boundedX = getRandomBoundedInt(x1, x2);
        int boundedY = getRandomBoundedInt(y1, y2);

        bezeirMouseMove(new Point(mouseLocation.x, mouseLocation.y), new Point(boundedX, boundedY));
    }

    public void bezierMoveZone(int x1, int y1, int x2, int y2) {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        int currentX = mouseLocation.x;
        int currentY = mouseLocation.y;
        int nextX = getRandomBoundedInt(currentX + x1, currentX + x2);
        int nextY = getRandomBoundedInt(currentY  + y1, currentY + y2);

        bezeirMouseMove(new Point(currentX, currentY), new Point(nextX, nextY));
    }

    private void bezeirMouseMove(Point p1, Point p4) {
        if (p1.x == p4.x && p1.y == p4.y) return;

        Point p2 = generateControlPoint(p1, p4);
        Point p3 = generateControlPoint(p1, p4);
        int destinationPerMove = (int) (Math.ceil(p1.distance(p4)) / 3);

        double inverseDestinations = 1.0 / destinationPerMove;
        double t, x, y;

        for (int i = 0; i <= destinationPerMove; i++) {
            t = i * inverseDestinations;
            x = Math.pow(1 - t, 3) * p1.x + 3 * Math.pow(1 - t, 2) * t * p2.x
                    + 3 * (1 - t) * t * t * p3.x + t * t * t * p4.x;
            y = Math.pow(1 - t, 3) * p1.y + 3 * Math.pow(1 - t, 2) * t * p2.y
                    + 3 * (1 - t) * t * t * p3.y + t * t * t * p4.y;

            if (Double.isNaN(x) || Double.isNaN(y)) {
                bot.mouseMove(p4.x, p4.y);
                return;
            }

            bot.mouseMove((int) x, (int) y);
        }
    }

    private Point generateControlPoint(Point p1, Point p2) {
        return new Point(getRandomBoundedInt(p1.x, p2.x), getRandomBoundedInt(p1.y, p2.y));
    }

    private int getRandomBoundedInt(int b1, int b2) {
        int high, low;
        Random rand = new Random();

        if (b1 > b2) {
            high = b1;
            low = b2;
        } else if (b1 < b2) {
            high = b2;
            low = b1;
        } else {
            return b1;
        }

        return rand.nextInt(high - low) + low;
    }
}
