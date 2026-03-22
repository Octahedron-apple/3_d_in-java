public class object
  {

    
  }
class point
  {
    public float[] position = new float[3]; 
    public float[] _2D_position = new float[2];
    point(float[] pos){
      if (pos.length!=3){
            throw new IllegalArgumentException("Invalid parameters");
        }
        else{
            this.position=pos;
        }
    }
    void transform(float[] point){
      if (point.length!=3){
            throw new IllegalArgumentException("Invalid parameters");
        }
      for (int i=0; i<3;i++){
        this.position[i]=this.position[i]+point[i];
      }
    }
  }
