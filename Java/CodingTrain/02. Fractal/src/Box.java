import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

class Box {

    private PApplet pA;
    private PVector pos;
    private float r;


    Box(PApplet pA, float x, float y, float z, float r) {
        this.pA = pA;
        pos = new PVector(x, y, z);
        this.r = r;

    }

    ArrayList<Box> generate() {
        ArrayList<Box> boxArrayList = new ArrayList<>();
        int sum;
        float newR;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    newR = r / 3;
                    sum = Math.abs(i) + Math.abs(j) + Math.abs(k);
                    if (sum > 1) {
                        Box b = new Box(pA, pos.x + i * newR, pos.y + j * newR, pos.z + k * newR, newR);
                        boxArrayList.add(b);
                    }
                }
            }
        }
        return boxArrayList;
    }

    void render() {
        pA.pushMatrix();
        pA.translate(pos.x, pos.y, pos.z);
        pA.noStroke();
        pA.fill(100, 37, 74);
        pA.box(r);
        pA.popMatrix();
    }

}
