import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.Point;

class Enemy extends VulnerableObject {
    int wayPointIndex = 0;
    boolean check = true;
    public Point getNextWayPoint() {
        if (wayPointIndex < TowerDefense.wayPoints.length - 1)
            return TowerDefense.wayPoints[++wayPointIndex];
        return null;
    }

    @Override
    void render(GraphicsContext gc) {
        if (check == true) gc.drawImage(img, x, y);
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    void calculateDirection(){
        if (wayPointIndex >= TowerDefense.wayPoints.length) return;

        Point currentWP = TowerDefense.wayPoints[wayPointIndex];
        if (Math.abs(x- currentWP.x) <= speed*2 && Math.abs(y-currentWP.y) <= speed) {
            x = currentWP.x;
            y = currentWP.y;
            Point nextWayPoint = getNextWayPoint();
            if (nextWayPoint == null) {
                check = false;
                return;
            }
            double deltaX = nextWayPoint.x - x;
            double deltaY = nextWayPoint.y - y;
            if (deltaX > speed * 2 && deltaY < -speed) direction = Direction.UP;
            else if (deltaX < -speed * 2 && deltaY > speed) direction = Direction.DOWN;
            else if (deltaY < -speed && deltaX < -speed * 2) direction = Direction.LEFT;
            else if (deltaY > speed && deltaX > speed * 2) direction = Direction.RIGHT;
        }
    }

    @Override
    void update() {
        calculateDirection();

        switch (direction) {
            case UP:
                x += speed * 2;
                y -= speed;
                break;
            case DOWN:
                x -= speed * 2;
                y += speed;
                break;
            case LEFT:
                x -= speed * 2;
                y -= speed;
                break;
            case RIGHT:
                x += speed * 2;
                y += speed;
                break;
        }
    }

    public static Enemy createEnemy() {
        Enemy enemy = new Enemy();
        enemy.i = 6;
        enemy.j = 13;
        enemy.x = enemy.i * 62 + 62 - 46;
        enemy.y = enemy.j * 31 + 31 - 38;
        enemy.speed = 5;
        enemy.direction = Direction.UP;
        enemy.img = new Image("file:Isometric\\50.png");
        return enemy;
    }
}