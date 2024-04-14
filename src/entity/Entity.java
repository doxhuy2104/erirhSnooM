package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int sx,sy;
    public int speed, cspeed;
    public String direction,atkDirection;
    public String collisionCheck;
    public BufferedImage[] up;
    public BufferedImage[] upl;
    public BufferedImage[] left;
    public BufferedImage[] right;
    public BufferedImage standr,standl,standU;
    public BufferedImage shadow;
    public BufferedImage[] attackR;
    public BufferedImage[] attackU;
    public BufferedImage[] attackL;
    String trcStand;
    public BufferedImage[] swordD;
    public BufferedImage[] swordU;
    public BufferedImage[] swordL;
    public BufferedImage[] swordR;
    public BufferedImage[] sliceD;
    public BufferedImage[] sliceU;
    public BufferedImage[] sliceR;
    public BufferedImage[] sliceL;
    public BufferedImage[] slimeR,slimeL;


    public int spriteCounter=0;
    public int spriteNum=0;
    public int attackCounter=0;
    public int attackNum=0;
    public String trc;
    public boolean isMoving = false;
    public boolean isAttack = false;
    public boolean isThink=false;
    public int sliceCounter =0;
    public int sliceNum =0;
    public Rectangle solidArea;
    public int screenX,screenY;

    public Rectangle attackAreaU,attackAreaD,attackAreaR,attackAreaL;

    public boolean collisionOn=false,collisionL=false,collisionR=false,collisionU=false,collisionD=false;
    BufferedImage[] thinkR;
    BufferedImage[] thinkL;
    public int thinkCounter=0;
    public int thinkNum=0;
    public boolean isRolling=false;
    public int rollingCounter=0;
    public int rollingNum=0;
    BufferedImage[] rollUpR;
    BufferedImage[] rollUpL;
    BufferedImage[] rollR;
    BufferedImage[] rollL;

    //ênmies
    public Rectangle bodyArea;
    public int eX,eY,eS=0,eSX=0,eSY=0;
    public boolean attacking=true,alive=true,xuong=true,daylui=false;
    public int eCounter=0,eNum=0,dlNum=0,dlS=6;
    public int hp=10;
    public int atkCounter;
    BufferedImage[] spark;
    public int sparkCounter=0,sparkNum=0;
    public String eD="L",eDirection;
    BufferedImage slimeI;
    public boolean sp=false;
    public boolean eCollision=false,eCollisionR=false,eCollisionL=false,eCollisionU=false,eCollisionD=false;
    public boolean saw=false;

    public boolean pToECU,pToECD,pToECR,pToECL;
    public boolean eToPCU,eToPCD,eToPCL,eToPCR;
}
