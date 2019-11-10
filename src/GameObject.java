import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}

enum Direction {
    LEFT, UP, RIGHT, DOWN;
}

abstract class GameObject {
    int i, j;
    int x;
    int y;
    Image img;

    abstract void render(GraphicsContext gc);
    abstract void update();
}

abstract class MovableObject extends GameObject {
    double speed;
    Direction direction;
}

abstract class VulnerableObject extends MovableObject {
    int health;
    double reward;
}

abstract class AttackableObject extends VulnerableObject {
    double damage;
    double fireRate;
    double fireRange;
}
