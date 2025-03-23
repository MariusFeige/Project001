package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Entity
{
  GamePanel gp;
  KeyHandler keyH;
  
    public final int screenX;
    public final int screenY;
    int hasObj = 0;

  public Player(GamePanel gp, KeyHandler keyH){
    this.gp = gp;
    this.keyH = keyH;
    
   screenX = gp.screenWidth/2 - (gp.tileSize/2);
   screenY = gp.screenHeight/2- (gp.tileSize/2);
   //spielercollision
   solidArea = new Rectangle();
   solidArea.x =8;
   solidArea.y =16;
   solidAreaDefaultX = solidArea.x;
   solidAreaDefaultY = solidArea.y;
   solidArea.width =32;
   solidArea.height =32;
   
    
    setDefaultValues();
    getPlayerImage();
    
    } 
    
    public void setDefaultValues(){
    worldX= gp.tileSize * 23;
    worldY= gp.tileSize * 21;
    speed = 4;
    direction = "down";
    
    }
    
    public void getPlayerImage(){
    
        try{
        
             up1 = ImageIO.read(getClass().getResourceAsStream("/res/up1.png"));
             up2 = ImageIO.read(getClass().getResourceAsStream("/res/up2.png"));
             down1 = ImageIO.read(getClass().getResourceAsStream("/res/down1.png"));
             down2 = ImageIO.read(getClass().getResourceAsStream("/res/down2.png"));
             left1 = ImageIO.read(getClass().getResourceAsStream("/res/left1.png"));
             left2 = ImageIO.read(getClass().getResourceAsStream("/res/left2.png"));
             right1 = ImageIO.read(getClass().getResourceAsStream("/res/right1.png"));
             right2 = ImageIO.read(getClass().getResourceAsStream("/res/right2.png"));

        }
        catch(IOException e){
         e.printStackTrace();
        
        }
    
    }
    
    
    
    
    public void update() {
        
   if(keyH.upPressed == true || keyH.downPressed == true || 
   keyH.leftPressed == true || keyH.rightPressed == true){
         
       if(keyH.upPressed == true){
    
    direction = "up";
   
    }
    else if(keyH.downPressed == true){
     
    direction = "down";
    }
    else if(keyH.leftPressed == true){
    
    direction = "left";
    }
    else if(keyH.rightPressed == true){
  
    direction = "right";
    }
    //collisioncheck
    collisionOn = false;
    gp.cChecker.checkTile(this);
    
    // Objekt Collision Check 
    int objIndex = gp.cChecker.checkObject(this, true);
    ObjectHandler(objIndex);

    //was wenn keine collision
    
    if (collisionOn == false){
    switch (direction){
    case "up":
        worldY = worldY-speed;
        break;
        case "down":
             worldY+= speed;
        break;
        case "left":
            worldX -= speed;
        break;
        case "right":
              worldX += speed;
        break;
    
    
    }
    
    
    }
    
    spriteCounter++;
    if(spriteCounter >10){
        if(spriteNum == 1){
        spriteNum=2;
        }
        else if(spriteNum == 2){
        spriteNum=1;
        }
    spriteCounter=0;
    }
    
    }     
   
    
    
    }
    // Was passiert bei Objectcollision
    public void ObjectHandler(int i){
     if(i != 999){
        //würde das Objekt löschen
       // System.out.println("test");
        //gp.obj[i]= null;
        String objectName = gp.obj[i].name;
        
        switch(objectName){
        
            case "Test":
                hasObj++;
                gp.obj[i]= null;
                System.out.println("test");
                gp.tileM.tile[2].collision = false;
                break;
        
        }
        
        }
    
    
    }
    
    
    
    public void draw(Graphics2D g2) {
    
    //g2.setColor(Color.blue);
    //g2.fillRect(x, y, gp.tileSize, gp.tileSize);    
    
    BufferedImage image = null;
    
    switch(direction){
    
    case "up":
        if(spriteNum == 1){
        image = up1;
        }
         if(spriteNum == 2){
        image = up2;
        }
                break;
        
    case "down":
          if(spriteNum == 1){
        image = down1;
        }
         if(spriteNum == 2){
        image = down2;
        }
        
        break;
        
    case "left":
      if(spriteNum == 1){
        image = left1;
        }
         if(spriteNum == 2){
        image = left2;
        }
        break;
    
    case "right":
          if(spriteNum == 1){
        image = right1;
        }
         if(spriteNum == 2){
        image = right2;
        }
        break;

    }
    
    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    
    }
}
