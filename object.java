import java.util.ArrayList;

public class object {
  public ArrayList<point> main = new ArrayList<point>();

  public void unit_Sphere(float s)
  // for making a unit sphere
  {
    this.main.clear();
    for (int x = -2; x <= 2; x++) {
      for (int y = -2; y <= 2; y++) {
        for (int z = -2; z <= 2; z++) {
          float vx = x * 0.5f;
          float vy = y * 0.5f;
          float vz = z * 0.5f;

          float m = (float) Math.sqrt(vx * vx + vy * vy + vz * vz);
          if (m == 0.0f) {
            continue;
          }

          float px = (vx / m) * s;
          float py = (vy / m) * s;
          float pz = (vz / m) * s;

          this.main.add(new point(new float[] { px, py, pz }));
        }
      }
    }
  }

  public void transform(float[] point) {
    for (point p : main) {
      p.transform(point);
    }
  }

  public void rotate(float a, int ax) {
    for (point p : main) {
      p.rotate(a, ax);
    }
  }

  public void strech(int ax, float s) {
    for (point p : main) {
      if (ax >= 0 && ax <= 2) p.position[ax] *= s;
      else throw new IllegalArgumentException("Axis must be 0, 1, or 2");
    }
  }

  public void scale(float s) {
    for (point p : main) {
      for (int i = 0; i < 3; i++) {
        p.position[i] *= s;
      }
    }
  }

  public void render() {
    for (point p : main) {
      p.render();
    }
  }
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
      float[] d_pos = new float[3];
      d_pos[0] = this.position[0] - this.cam.position[0];
      d_pos[1] = this.position[1] - this.cam.position[1];
      d_pos[2] = this.position[2] - this.cam.position[2];

      for (int ax = 0; ax < 3; ax++) {
        float a = this.cam.rotation[ax];
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
          np[i] = m[i][0] * d_pos[0] + m[i][1] * d_pos[1] + m[i][2] * d_pos[2];
        d_pos = np;
      }

      float dx = d_pos[0];
      float dy = d_pos[1];
      float dz = d_pos[2];

      if (dz != 0) {
        this._2D_position[0] = (dx * this.cam.focal_length) / dz;
        this._2D_position[1] = (dy * this.cam.focal_length) / dz;
      }
    }
  }
}
