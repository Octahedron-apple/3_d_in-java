public class camera{
    public float[] position = new float[3];
    // for x,y,z pos
    public float[] rotation = new float[3];
    // for x,y,z rotation
    public float focal_length;
    // focal length of the camera
    camera(float[] pos,float[] rot, float focal){
        if (focal<=0 && pos.length!=3 && rot.length!=3){
            throw new IllegalArgumentException("Invalid parameters");
        }
        else{
            
        }

    }
}