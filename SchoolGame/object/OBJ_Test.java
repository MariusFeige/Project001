package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Test extends SuperObject
{
       public OBJ_Test(){
        name= "Test";
        
        try{
        image = ImageIO.read(getClass().getResourceAsStream("/res/test.png"));
        }
        catch(IOException e){
        
        e.printStackTrace();
        }
        //Ist das Object undurchlässig?
        collision = true;
        
        }
}
