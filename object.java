public class object
  {

    
  }
class point
  {
    public float[] position = new float[3]; 
    point(float[] pos){
      if (pos.length!=3){
            throw new IllegalArgumentException("Invalid parameters");
        }
        else{
            this.position=pos;
        }
    }
  }
