import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Tower extends AttackableObject {
    Image gunImg;

    @Override
    void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        gc.drawImage(gunImg, x, y);
    }

    @Override
    void update() {

    }

    public static Tower createTower(int x, int y) {
        Tower tower = new Tower();
        tower.i = x;
        tower.j = y;
        tower.x = tower.j * 62 + 62 - 46;
        tower.y = tower.i * 31 + 31 - 38 - 31;
        //tower.speed = 3;
        //tower.direction = Direction.UP;
        tower.img = new Image("file:Isometric\\464.png");
        return tower;
    }
}
