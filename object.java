import java.util.ArrayList;

public class object {
  public ArrayList<point> main = new ArrayList<point>();

}

class point {
  public float[] position = new float[3];
  public float[] _2D_position = new float[2];
  public camera cam;

  point(float[] pos) {
    if (pos.length != 3) {
      throw new IllegalArgumentException("Invalid parameters");
    } else {
      this.position = pos;
    }
  }

  void transform(float[] point) {
    if (point.length != 3) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    for (int i = 0; i < 3; i++) {
      this.position[i] = this.position[i] + point[i];
    }
  }

  void rotate(float a, int ax) {
    // any one of three axis 0,1,2
    float[][] m = new float[3][3];
    float rad = (float) Math.toRadians(a);
    float c = (float) Math.cos(rad);
    float s = (float) Math.sin(rad);

    if (ax == 0) {
      m = new float[][] {
          { 1, 0, 0 },
          { 0, c, -s },
          { 0, s, c }
      };
    } else if (ax == 1) {
      m = new float[][] {
          { c, 0, s },
          { 0, 1, 0 },
          { -s, 0, c }
      };
    } else if (ax == 2) {
      m = new float[][] {
          { c, -s, 0 },
          { s, c, 0 },
          { 0, 0, 1 }
      };
    }

    float[] np = new float[3];
    for (int i = 0; i < 3; i++)
      np[i] = m[i][0] * this.position[0] + m[i][1] * this.position[1] + m[i][2] * this.position[2];
    this.position = np;
  }

  void render() {
    if (this.cam != null) {
      float dx = this.position[0] - this.cam.position[0];
      float dy = this.position[1] - this.cam.position[1];
      float dz = this.position[2] - this.cam.position[2];

      if (dz != 0) {
        this._2D_position[0] = (dx * this.cam.focal_length) / dz;
        this._2D_position[1] = (dy * this.cam.focal_length) / dz;
      }
    }
  }
}
