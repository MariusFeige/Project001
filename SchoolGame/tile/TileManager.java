package tile;
import main.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
public class TileManager
{
  GamePanel gp;
  public Tile[] tile;
  public int mapTileNum [] [];
  
  
  
      public TileManager(GamePanel gp){
        
          this.gp = gp;
          
          tile = new Tile [10];
          mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];
           getTileImage();
           loadMap();
        
        }
        
        public void getTileImage(){
         
            try{
             tile[0]= new Tile();
             tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/grass.png"));
             
             tile[1]= new Tile();
             tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/wall.png"));
             tile[1].collision = true;
             
             tile[2]= new Tile();
             tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/water.png"));
             tile[2].collision = true;
                
            } catch(IOException e){
                e.printStackTrace();
            
            
            }
        
        
        }
        
        public void loadMap() {
        
                try{
                  InputStream is = getClass().getResourceAsStream("/res/map.txt");
                  BufferedReader br = new BufferedReader(new InputStreamReader(is));
                  
                  int col = 0;
                  int row = 0;
                  
                  while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                    
                      String line = br.readLine();
                
                       
                          while(col < gp.maxWorldCol){
                            
                              String numbers[] = line.split(" ");
                            
                            
                            int num = Integer.parseInt(numbers[col]);
                            
                            
                            mapTileNum [col] [row] = num;
                            col++;
                        }
                                if(col == gp.maxWorldCol){
                               
                                  col=0;
                                  row = row+1;
                                
                                }
                            
                            
                            }
                    
                    
           br.close();
                
            } catch(Exception e){
                
            
            
            }
        
        
        }
        
        
        public void draw(Graphics2D g2){
        
           //   g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null); 
             //   g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null); 
               //   g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null); 
               
               
               int worldCol = 0;
               int worldRow = 0;
            
               
               while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
                
                   int tileNum = mapTileNum [worldCol] [worldRow];
                   
                   int worldX = worldCol * gp.tileSize;
                   int worldY = worldRow * gp.tileSize;
                   int screenX = worldX - gp.player.worldX + gp.player.screenX;
                   int screenY = worldY - gp.player.worldY + gp.player.screenY;
                   if (worldX + gp.tileSize > gp.player.worldX -gp.player.screenX && 
                       worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
                       worldY + gp.tileSize> gp.player.worldY -gp.player.screenY &&
                       worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                       g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
                       worldCol++;
                      
                       
                       if(worldCol == gp.maxWorldCol) {
                        worldCol=0;
                       
                        worldRow++;
                       
                        
                        }
                
                }
        }
        
        
        
        }
        

