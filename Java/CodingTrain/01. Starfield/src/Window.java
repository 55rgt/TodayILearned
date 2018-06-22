import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    private List<Star> starList;

    @Override
    public void settings() {
        size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        starList = new ArrayList<>();
        for (int i = 0; i < Constants.NUMBER_OF_STARS; i++) starList.add(new Star(this));
    }

    @Override
    public void setup() {
        background(0);
    }

    @Override
    public void draw() {
        Constants.SPEED = map(mouseX, 0, width, 0, random(Constants.SPEED_MIN, Constants.SPEED_MAX));
        background(0);
        translate(width / 2, height / 2);

        starList.forEach(Star::update);
        starList.forEach(Star::render);
    }
}
