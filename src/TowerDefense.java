import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TowerDefense extends Application {
    GraphicsContext gc;
    static List<GameObject> gameObjects = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defense");

        // Tao Canvas
        Canvas canvas = new Canvas(1280, 600);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();

        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        scene.setFill(Color.color(0, 0, 0));

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (round1BG.st) {
                    round1BG.draw(gc);
                    scene.setOnMouseMoved(e -> {
                        //Point point = find(e.getSceneX(), e.getSceneY());
                        //System.out.println("i= "+ point.y + " " + "j= "+ point.x);
                        if (e.getSceneX() >= 1000 && e.getSceneX() <= 1108 && e.getSceneY() >= 100 && e.getSceneY() <= 148){
                            round1BG.sb = new Image("file:lineLight\\lineLight51.png");
                        }
                        else {
                            round1BG.sb = new Image("file:lineDark\\lineDark39.png");
                        }
                    });
                    scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getSceneX() >= 1000 && event.getSceneX() <= 1108 && event.getSceneY() >= 100 && event.getSceneY() <= 148){
                                round1BG.st = false;
                                startBG.st = true;
                                startBG.sb = new Image("file:lineDark\\lineDark37.png");
                            }
                        }
                    });
                }
                else if (startBG.st){
                    startBG.draw(gc);
                    scene.setOnMouseMoved(e -> {
                        if (e.getSceneX() >= 586 && e.getSceneX() <= 694 && e.getSceneY() >= 500 && e.getSceneY() <= 548){
                            startBG.sb = new Image("file:lineLight\\lineLight50.png");
                        }
                        else {
                            startBG.sb = new Image("file:lineDark\\lineDark37.png");
                        }
                    });
                    scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getSceneX() >= 586 && event.getSceneX() <= 694 && event.getSceneY() >= 500 && event.getSceneY() <= 548){
                                round1BG.st = true;
                                startBG.st = false;
                                round1BG.sb = new Image("file:lineDark\\lineDark39.png");
                            }
                        }
                    });

                }


            }
        };
        timer.start();
        gameObjects.add(Enemy.createEnemy());
        gameObjects.add(Tower.createTower(10, 5));

    }

    public static final String[][] MAP_SPRITES = new String[][] {
            { "000", "000", "000", "000", "000", "000", "000", "218", "000", "000", "000", "000", "000", "000", "000"},
            { "000", "000", "000", "000", "000", "000", "129", "000", "129", "000", "000", "000", "000", "000", "000"},
            { "000", "000", "000", "000", "000", "129", "000", "129", "000", "129", "000", "000", "000", "000", "000"},
            { "000", "000", "000", "000", "129", "000", "115", "000", "129", "000", "221", "000", "000", "000", "000"},
            { "000", "000", "000", "334", "000", "197", "000", "196", "000", "129", "000", "140", "000", "000", "000"},
            { "000", "000", "310", "000", "311", "000", "129", "000", "196", "000", "202", "000", "218", "000", "000"},
            { "000", "310", "000", "269", "000", "334", "000", "129", "000", "114", "000", "129", "000", "129", "000"},
            { "280", "000", "310", "000", "360", "000", "334", "000", "129", "000", "153", "000", "129", "000", "129"},
            { "000", "310", "000", "310", "000", "249", "000", "334", "000", "168", "000", "167", "000", "129", "000"},
            { "000", "000", "310", "000", "384", "000", "360", "000", "316", "000", "129", "000", "171", "000", "000"},
            { "000", "000", "000", "310", "000", "310", "000", "360", "000", "380", "000", "175", "000", "000", "000"},
            { "000", "000", "000", "000", "310", "000", "308", "000", "272", "000", "310", "000", "000", "000", "000"},
            { "000", "000", "000", "000", "000", "310", "000", "361", "000", "310", "000", "000", "000", "000", "000"},
            { "000", "000", "000", "000", "000", "000", "349", "000", "310", "000", "000", "000", "000", "000", "000"},
            { "000", "000", "000", "000", "000", "000", "000", "277", "000", "000", "000", "000", "000", "000", "000"},
    };

    public static final Point[] wayPoints = new Point[] {
            new Point(6 * 62 + 62 - 46, 13 * 31 + 31 - 38),
            new Point(8 * 62 + 62 - 46, 11 * 31 + 31 - 38),
            new Point(3 * 62 + 62 - 46, 6 * 31 + 31 - 38),
            new Point(6 * 62 + 62 - 46, 3 * 31 + 31 - 38),
            new Point(9 * 62 + 62 - 46, 6 * 31 + 31 - 38),
            new Point(11 * 62 + 62 - 46, 4 * 31 + 31 - 38),
    };

    private static void drawMap(GraphicsContext gc) {
        gc.drawImage(new Image("file:Pictures\\12.png"),0,0);

        for (int i = 0; i < MAP_SPRITES.length; i++) {
            for (int j = 0; j < MAP_SPRITES[i].length; j++) {
                Point d = find(gameObjects.get(0).x + 76, gameObjects.get(0).y + 92);
                Point f = find(gameObjects.get(1).x + 92, gameObjects.get(1).y + 141);

                System.out.println(d.y + " " + d.x);
                Image c = new Image("file:Isometric\\" + MAP_SPRITES[i][j] + ".png");
                double a = (j * 62 + 130 - c.getWidth());
                double b = (i * 31 + 49 + 81 - c.getHeight());
                if (j == 11 && i == 4)  gc.drawImage(new Image("file:Isometric\\140.png"), (double) 11 * 62, (double) 4 * 31 + 49 - (97 - 81));
                else gc.drawImage(c, a , b);
                if (d.x == 4 && d.y == 11) d = new Point(11, 5);
                if (i == d.x && j == d.y) gameObjects.get(0).render(gc);
                if (i == f.x && j == f.y) gameObjects.get(1).render(gc);
            }
        }
    }

    public static void update() {
        gameObjects.forEach(GameObject::update);
    }

    public static void render(GraphicsContext gc) {
        drawMap(gc);
    }

    static class round1BG{
        static boolean st = false;
        static Image sb = new Image("file:lineDark\\lineDark39.png");
        public static void draw(GraphicsContext gc){
            render(gc);
            update();
            gc.drawImage(sb, 1000,100);

        }

    }

    static class startBG{
        static boolean st = true;
        static Image sb = new Image("file:lineDark\\lineDark37.png");

        public static void draw(GraphicsContext gc){
            gc.drawImage(new Image("file:Pictures\\12.png"),0,0);
            gc.drawImage(sb, 586,500);
        }
    }

    public static Point find(double x, double y){
        Point point = new Point();
        int i = (int) x / 62;
        int j = (int) (y - 49) / 31;
        if (i >= 0 && i <= 15 && j >= 0 && j <= 15){
            if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))  {
                if (Math.atan((x % 62) / ((y - 49) % 31)) <= Math.atan(62 / 31)){
                    //return (i - 1) * 100 + j;
                    point.x = j;
                    point.y = i - 1;
                }
                else {
                    //return i * 100 + (j - 1);
                    point.x = j - 1;
                    point.y = i;
                }
            }
            else  {
                if (Math.atan((62 - x % 62) / ((y - 49) % 31)) <= Math.atan(62 / 31)){
                    //return i * 100 + j;
                    point.x = j;
                    point.y = i;
                }
                else {
                    //return (i - 1) * 100 + (j - 1);
                    point.x = j - 1;
                    point.y = i - 1;
                }
            }
        }
        return point;
    }
}

