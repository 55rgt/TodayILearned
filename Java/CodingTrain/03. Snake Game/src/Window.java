import processing.core.PApplet;
import processing.core.PVector;

public class Window extends PApplet {

    private Snake snake;
    private PVector food;
    private int prevKeyCode = RIGHT;

    @Override
    public void settings() {
        size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    public void setup() {
        background(155, 124, 195);
        snake = new Snake(this);
        frameRate(10);
        pickLocation();
    }

    @Override
    public void draw() {
        background(155, 124, 195);
        snake.update();
        snake.render();

        if (snake.eat(food)) {
            pickLocation();
        }
        snake.death();
        fill(210, 142, 202);
        noStroke();
        rect(food.x, food.y, Constants.SIZE, Constants.SIZE);
    }


    private void pickLocation() {
        int cols = floor(width / Constants.SIZE);
        int rows = floor(height / Constants.SIZE);
        food = new PVector(floor(random(cols)), floor(random(rows)));
        food.mult(Constants.SIZE);
    }

    @Override
    public void keyPressed() {
        switch (keyCode) {
            case UP:
                if (prevKeyCode != DOWN) {
                    snake.setVelocity(0, -1);
                    prevKeyCode = UP;
                }
                break;
            case DOWN:
                if (prevKeyCode != UP) {
                    snake.setVelocity(0, 1);
                    prevKeyCode = DOWN;
                }
                break;
            case RIGHT:
                if (prevKeyCode != LEFT) {
                    snake.setVelocity(1, 0);
                    prevKeyCode = RIGHT;
                }
                break;
            case LEFT:
                if (prevKeyCode != RIGHT) {
                    snake.setVelocity(-1, 0);
                    prevKeyCode = LEFT;
                }
                break;
            default:
        }
    }
}
