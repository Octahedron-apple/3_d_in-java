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
  }
