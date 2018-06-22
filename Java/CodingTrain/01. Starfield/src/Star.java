import processing.core.PApplet;

class Star {
    private float x, y, z;
    private PApplet pA;
    private float pz;

    Star(PApplet pA) {
        this.pA = pA;
        x = pA.random(-pA.width / 2, pA.width / 2);
        y = pA.random(-pA.height / 2, pA.height / 2);
        z = pA.random(pA.width);
        pz = z;
    }

    void update() {
        z -= Constants.SPEED;
        if (z < 1) {
            x = pA.random(-pA.width, pA.width);
            y = pA.random(-pA.height, pA.height);
            z = pA.width;
            pz = z;
        }
    }

    void render() {
        pA.fill(255);
        pA.noStroke();

        float sx = PApplet.map(x / z, 0, 1.2f, 0, pA.width);
        float sy = PApplet.map(y / z, 0, 1.2f, 0, pA.width);
        float px = PApplet.map(x / pz, 0, 1.2f, 0, pA.width);
        float py = PApplet.map(y / pz, 0, 1.2f, 0, pA.height);

        pz = z;
        pA.stroke(255 - (pz + Constants.SPEED) % 255);
        pA.line(px, py, sx, sy);
    }
}

