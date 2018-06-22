import processing.core.PApplet;

import java.util.ArrayList;

public class Window extends PApplet {

    private ArrayList<Box> fractal;
    private float rotateTick = 0;

    @Override
    public void settings() {
        size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, P3D);
        Box b = new Box(this,0,0,0,Constants.BOX_LENGTH);
        fractal = new ArrayList<>();
        fractal.add(b);
    }

    @Override
    public void setup() {
        background(50); 

    }

    @Override
    public void draw() {
        background(50);
        stroke(255);
        noFill();
        lights();
        translate(width / 2, height / 2);
        rotateX(rotateTick);
        rotateY(rotateTick * 0.5f);
        rotateZ(rotateTick * 2f);
        fractal.forEach(Box::render);
        rotateTick += 0.01;
    }

    @Override
    public void mousePressed() {
        ArrayList<Box> next = new ArrayList<>();
        fractal.stream().map(Box::generate).forEach(next::addAll);
        fractal = next;
    }
}
