package main;

import entity.Enemies;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import ui.UIM;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    public final int originalTileSize = 16;
    public final int scale = 4;
    public final int tileSize= originalTileSize*scale;
    public final int maxScreenRow=9;
    public final int maxScreenCol=16;
    public final int screenWidth=tileSize*maxScreenCol;
    public final int screenHeight=tileSize*maxScreenRow;

    public final int maxWorldCol=200;
    public final int maxWorldRow=100;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;

    //System
    KeyHandler keyH=new KeyHandler();
    MouseClickListener mouseClick=new MouseClickListener(this);
    public AssetSetter assetSetter =new AssetSetter(this);
    public Sound sound=new Sound();

    Thread gameThread;

    //In game
    public Player player=new Player(this,keyH,mouseClick);
    TileManager tileManager =new TileManager(this);
    public UIM uiManager=new UIM(this,keyH,mouseClick);
    public Enemies slime[]=new Enemies[20];
    ArrayList<Entity> entityList=new ArrayList<>();
    Enemies enemies=new Enemies(this);

    public CollisionChecker collisionChecker=new CollisionChecker(this);
    public AttackChecker attackChecker=new AttackChecker(this);

    public int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.addMouseListener(mouseClick);

        player.getPlayerImage();
        enemies.getEnemiesImage();
    }

    public void setUpGame(){
        assetSetter.setEnemies();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - lastTime;
            if (elapsedTime >= 1000000000 / FPS) {
                repaint();
                update();
                lastTime = currentTime;
            }
        }
    }

    public void update(){
        if(keyH.escape){
            uiManager.inGame=false;
            uiManager.menu=true;
        }
        if(uiManager.inGame){
            FPS=60;
            player.update();
            tileManager.updateCampFire();
            enemies.update();
            for(int i=0;i<slime.length;i++){
                if(slime[i]!=null){
                    slime[i].update();
                }
            }
        }
        else {
            FPS=240;
            uiManager.updateUI();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        long drawStart=System.nanoTime();

        if(uiManager.inGame) {
            tileManager.drawGlass(g2);
            tileManager.drawWater(g2);
            tileManager.draw(g2);
            tileManager.drawCampFire(g2);
          //  enemies.draw(g2);
            player.drawSliceUp(g2);
            player.draw(g2);
            player.drawSlice(g2);
            tileManager.drawTree(g2);
            player.drawB(g2);
            for(int i=0;i<slime.length;i++){
                if(slime[i]!=null){
                    slime[i].draw(g2);
                }
            }
        }

        if(!uiManager.inGame){
            uiManager.draw(g2);
        }

        long drawEnd=System.nanoTime();
        long passed=drawEnd-drawStart;
        g2.setColor(Color.WHITE);
     //   g2.drawString("Draw Time: "+passed,10,400);
       // System.out.println("Draw Time: "+passed);
        g2.dispose();
    }

    //sound
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }
}
