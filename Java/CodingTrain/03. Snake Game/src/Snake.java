import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

class Snake {

    private PApplet pA;
    private float x, y, vx, vy;
    private int total;
    private List<PVector> tail;

    Snake(PApplet pA) {
        this.pA = pA;
        x = 0;
        y = 0;
        vx = 1;
        vy = 0;
        total = 0;
        tail = new ArrayList<>();
    }

    void death() {
        if (x < 0 || x > pA.width - Constants.SIZE
                || y < 0 || y > pA.height - Constants.SIZE) {
            total = 0;
            tail.clear();
            return;
        }
        for (int i = 0; i < tail.size(); i++) {
            PVector pos = tail.get(i);
            float d = PApplet.dist(x, y, pos.x, pos.y);
            if (d < 1) {
                System.out.println("starting over");
                total = 0;
                tail.clear();
            }
        }
    }

    void update() {

        if (total > 0) {
            if (total == tail.size() && !tail.isEmpty()) {
                tail.remove(0);
            }
            tail.add(new PVector(x, y));
        }

        x += vx * Constants.SIZE;
        y += vy * Constants.SIZE;

        x = PApplet.constrain(x, 0, pA.width - Constants.SIZE);
        y = PApplet.constrain(y, 0, pA.height - Constants.SIZE);


    }

    boolean eat(PVector food) {
        if (PApplet.dist(this.x, this.y, food.x, food.y) < 1) {
            total++;
            return true;
        } else return false;
    }

    void render() {
        for (PVector v : tail) {
            pA.fill(101, 106, 187);
            pA.rect(v.x, v.y, Constants.SIZE, Constants.SIZE);
        }
        pA.fill(101, 106, 187);
        pA.rect(x, y, Constants.SIZE, Constants.SIZE);
    }

    void setVelocity(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

}
