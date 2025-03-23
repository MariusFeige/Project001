package main;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import tile.TileManager;
import object.SuperObject;


public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16; 
    final int scale  = 3;
    
    public int tileSize = originalTileSize * scale;
    
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public  final int screenWidth = tileSize * maxScreenCol;
    public   final int screenHeight = tileSize * maxScreenRow;
     
    //welt
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow; 
     
     
     
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetManager aManager = new AssetManager(this);
    
    // wir bereiten 10 slots für Objekte vor, die können wir modular verändern. Es können also nur 10 
    // Objekte auf einmal dargestellt werden
    public SuperObject obj [] = new SuperObject[10];
  
    
    int FPS = 60;
    
    
    public GamePanel(){
    
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    
    public void setupGame(){
    
     aManager.setObject();
    
    }
    
    public void startGameThread(){
    gameThread = new Thread(this);
       gameThread.start();
    
    }
    
 @Override 
 public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
    
     
      while(gameThread != null)
        {
           currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            //timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1){
         
         
      //update gamestate
        update();
            
      //redraw new scene
        repaint();
         delta--;
     
    }}

}

public void update(){

    player.update();
    
    
}

public void paintComponent(Graphics g){
    
    super.paintComponent(g);
    
    Graphics2D g2 = (Graphics2D)g;
    
    tileM.draw(g2);
    
    for(int i=0; i< obj.length; i++){
        if (obj[i] != null){
        obj[i].draw(g2, this);
        
        } 
    
    
    }
    
    player.draw(g2);
    
 
    g2.dispose();



}

  }