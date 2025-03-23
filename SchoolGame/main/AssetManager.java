package main;

import object.OBJ_Test;



public class AssetManager
{
GamePanel gp;
   
    public AssetManager(GamePanel gp){
    
    this.gp=gp;
    
    }
    
    public void setObject(){
    
    gp.obj[0] = new OBJ_Test();
    gp.obj[0].worldX = 2* gp.tileSize;
    gp.obj[0].worldY = 2 * gp.tileSize;
    
    }
    
}
