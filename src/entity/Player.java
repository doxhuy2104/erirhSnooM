package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseClickListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    MouseClickListener mouseClick;
   // public final int screenX, screenY;
    int mouseX,mouseY;

    public static int abs(int x) {
        return x >= 0 ? x : -x;
    }


    public Player(GamePanel gp, KeyHandler keyH, MouseClickListener mouseC) {
        this.gp = gp;
        this.keyH = keyH;
        this.mouseClick=mouseC;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(8,32,48,32);

        attackAreaU = new Rectangle(screenX - 7 * gp.scale, screenY - 10 * gp.scale, 136, 76);
        attackAreaL = new Rectangle(screenX - 15 * gp.scale, screenY - 9 * gp.scale, 76, 136);
        attackAreaD = new Rectangle(screenX - 9 * gp.scale, screenY + 15 * gp.scale, 136, 76);
        attackAreaR = new Rectangle(screenX + 13 * gp.scale, screenY - 8 * gp.scale, 76, 136);

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 1000;
        y = 650;
        direction = "down";
        collisionCheck="down";
        trc = "right";
        trcStand="right";
        isAttack = false;
    }

    public void getPlayerImage() {
        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/entity/full.png"));
            up = new BufferedImage[6];
            upl = new BufferedImage[6];
            left = new BufferedImage[6];
            right = new BufferedImage[6];
            //standr = ImageIO.read(getClass().getResourceAsStream("/entity/icon.png"));
            //standl = ImageIO.read(getClass().getResourceAsStream("/entity/icon1.png"));

            BufferedImage full2=ImageIO.read(getClass().getResourceAsStream("/entity/full2.png"));
            standU=full2.getSubimage(90,129,16,16);

            thinkR =new BufferedImage[8];
            thinkR[0]=full2.getSubimage(0,150,16,16);

            rollUpR=new BufferedImage[7];
            for(int i=0;i<7;i++){
                rollUpR[i]=full2.getSubimage(18*i,108,16,16);
            }

            rollR=new BufferedImage[7];
            for (int i=0;i<7;i++){
                rollR[i]=full2.getSubimage(18*i,87,16,16);
            }

            rollL=new BufferedImage[7];
            for (int i=0;i<7;i++){
                rollL[i]=full2.getSubimage(108-18*i,338,16,16);
            }

            rollUpL=new BufferedImage[7];
            for (int i=0;i<7;i++){
                rollUpL[i]=full2.getSubimage(108-18*i,359,16,16);
            }

            thinkL=new BufferedImage[8];
            for(int i=0;i<8;i++){
                thinkL[i]=full2.getSubimage(128-18*i,317,16,16);
            }
            standr=thinkR[0];
            standl=thinkL[0];
            for(int i=1;i<8;i++) thinkR[i] = full2.getSubimage(18 * i, 150, 16, 16);

            shadow = ImageIO.read(getClass().getResourceAsStream("/entity/playerShadow.png"));

            BufferedImage attackSheet = ImageIO.read(getClass().getResourceAsStream("/attack/full.png"));
            attackR = new BufferedImage[3];
            attackR[0] = attackSheet.getSubimage(0, 45, 16, 16);
            attackR[1] = attackSheet.getSubimage(18, 45, 16, 16);
            attackR[2] = attackSheet.getSubimage(36, 45, 16, 16);

            attackU=new BufferedImage[3];
            attackU[0] = attackSheet.getSubimage(0, 66, 16, 16);
            attackU[1] = attackSheet.getSubimage(18, 66, 16, 16);
            attackU[2] = attackSheet.getSubimage(36, 66, 16, 16);

            attackL=new BufferedImage[3];
            attackL[2]=attackSheet.getSubimage(108,3,16,16);
            attackL[1]=attackSheet.getSubimage(126,3,16,16);
            attackL[0]=attackSheet.getSubimage(144,3,16,16);

            BufferedImage sliceDSheet = ImageIO.read(getClass().getResourceAsStream("/attack/sliceD.png"));
            sliceD = new BufferedImage[5];
            sliceD[4]=sliceD[3]=sliceD[0] = sliceDSheet.getSubimage(21, 21, 16, 16);
            sliceD[1] = sliceDSheet.getSubimage(3, 2, 34, 19);
            sliceD[2] = sliceDSheet.getSubimage(3, 25, 34, 19);

            BufferedImage sliceRSheet = ImageIO.read(getClass().getResourceAsStream("/attack/sliceR.png"));
            sliceR = new BufferedImage[5];
            sliceR[4]=sliceR[3]=sliceR[0] = sliceDSheet.getSubimage(21, 21, 16, 16);
            sliceR[1] = sliceRSheet.getSubimage(2, 2, 19, 34);
            sliceR[2] = sliceRSheet.getSubimage(25, 2, 19, 34);

            BufferedImage sliceUSheet = ImageIO.read(getClass().getResourceAsStream("/attack/sliceU.png"));
            sliceU = new BufferedImage[5];
            sliceU[4]=sliceU[3]=sliceU[0] = sliceDSheet.getSubimage(21, 21, 16, 16);
            sliceU[1] = sliceUSheet.getSubimage(2, 25, 34, 19);
            sliceU[2] = sliceUSheet.getSubimage(2, 2, 34, 19);

            BufferedImage sliceLSheet = ImageIO.read(getClass().getResourceAsStream("/attack/sliceL.png"));
            sliceL = new BufferedImage[5];
            sliceL[4]=sliceL[3]=sliceL[0] = sliceDSheet.getSubimage(21, 21, 16, 16);
            sliceL[1] = sliceLSheet.getSubimage(25, 3, 19, 34);
            sliceL[2] = sliceLSheet.getSubimage(2, 3, 19, 34);

            swordD=new BufferedImage[4];
            swordD[0]=sliceDSheet.getSubimage(21, 21, 16, 16);
            swordD[1]=ImageIO.read(getClass().getResourceAsStream("/attack/swordD.png"));
            swordD[2]=ImageIO.read(getClass().getResourceAsStream("/attack/swordD.png"));
            swordD[3]=ImageIO.read(getClass().getResourceAsStream("/attack/swordD.png"));


            swordU=new BufferedImage[4];
            swordU[0]=sliceDSheet.getSubimage(21, 21, 16, 16);
            swordU[1]=ImageIO.read(getClass().getResourceAsStream("/attack/swordU.png"));
            swordU[2]=ImageIO.read(getClass().getResourceAsStream("/attack/swordU.png"));
            swordU[3]=ImageIO.read(getClass().getResourceAsStream("/attack/swordU.png"));


            swordR=new BufferedImage[4];
            swordR[0]=sliceDSheet.getSubimage(21, 21, 16, 16);
            swordR[1]=ImageIO.read(getClass().getResourceAsStream("/attack/swordR.png"));
            swordR[2]=ImageIO.read(getClass().getResourceAsStream("/attack/swordR.png"));
            swordR[3]=ImageIO.read(getClass().getResourceAsStream("/attack/swordR.png"));


            swordL=new BufferedImage[4];
            swordL[0]=sliceDSheet.getSubimage(21, 21, 16, 16);
            swordL[1]=ImageIO.read(getClass().getResourceAsStream("/attack/swordL.png"));
            swordL[2]=ImageIO.read(getClass().getResourceAsStream("/attack/swordL.png"));
            swordL[3]=ImageIO.read(getClass().getResourceAsStream("/attack/swordL.png"));


            for (int i = 0; i < 6; i++) {
                int x = 2 + 18 * i;
                up[i] = spriteSheet.getSubimage(x, 21, 16, 16);
            }
            for (int i = 0; i < 6; i++) {
                int x = 2 + 18 * i;
                upl[i] = spriteSheet.getSubimage(x, 63, 16, 16);
            }
            for (int i = 0; i < 6; i++) {
                int x = 2 + 18 * i;
                left[i] = spriteSheet.getSubimage(x, 42, 16, 16);
            }
            for (int i = 0; i < 6; i++) {
                int x = 2 + 18 * i;
                right[i] = spriteSheet.getSubimage(x, 0, 16, 16);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.sprint) {
            speed = 7;
            cspeed = 5;
        } else {speed = 4;cspeed = 3;}
        mouseX=mouseClick.getMouseX()-gp.screenWidth/2;
        mouseY=mouseClick.getMouseY()-gp.getHeight()/2;

        if(!isAttack){
            if (keyH.upPressed) {isMoving=true;
            direction = "up";
            collisionCheck="up";
            if (keyH.rightPressed) {
                collisionCheck=direction = "upr";
                if(keyH.rolling) isRolling=true;
            } else if (keyH.leftPressed) {
                collisionCheck=direction = "upl";
                if(keyH.rolling) isRolling=true;
            } else if (keyH.rolling) {
                isRolling=true;
                collisionCheck="up";
            }
            } else if (keyH.downPressed) {isMoving=true;
                collisionCheck=direction = "down";
            if (keyH.rightPressed) {
                collisionCheck=direction = "downr";
                if(keyH.rolling) isRolling=true;
            } else if (keyH.leftPressed) {
                collisionCheck=direction = "downl";
                if(keyH.rolling) isRolling=true;
            }else if (keyH.rolling) {
                isRolling=true;
            }
            } else if (keyH.rightPressed) {isMoving=true;
                collisionCheck=direction = "right";
                if(keyH.rolling){
                isRolling=true;
                }
            } else if (keyH.leftPressed) {isMoving=true;
                collisionCheck=direction = "left";
            if (keyH.rolling) {
                    isRolling=true;
                }
            } else {
            isMoving = false;
//            isAttack = false;
            }
        }

        pToECU=false;
        pToECD=false;
        pToECL=false;
        pToECR=false;
        gp.collisionChecker.pToECo(this);

        if (mouseClick.isLeftClick()&&!isRolling) {
            isAttack = true;
            if(abs(mouseX)<abs(mouseY)&&mouseY<0){
                trcStand="up";
                atkDirection="attackUp";
                collisionCheck="up";
            }
            if(abs(mouseX)<abs(mouseY)&&mouseY>0){
                trcStand="down";
                atkDirection="attackDown";
                collisionCheck="down";
            }
            if(abs(mouseX)>abs(mouseY)&&mouseX<0){
                trcStand="left";
                atkDirection="attackL";
                collisionCheck="left";
            }
            if(abs(mouseX)>abs(mouseY)&&mouseX>0){
                trcStand="right";
                atkDirection="attackR";
                collisionCheck="right";
            }
        }
        collisionOn = false;
        collisionD=false;collisionL=false;collisionR=false;collisionU=false;
        gp.collisionChecker.checkTile(this);

        if (!isAttack&&!isRolling&&isMoving) {
            switch(direction){
                case"up":
                    if(!collisionOn&&!pToECU) y-=speed;
                    break;
                case"down":
                    if(!collisionOn&&!pToECD) y+=speed;
                    break;
                case"left":
                    if(!collisionOn&&!pToECL) x-=speed;
                    break;
                case"right":
                    if(!collisionOn&&!pToECR) x+=speed;
                    break;
                case"upl":
                    if(!collisionL) x-=cspeed;
                    if(!collisionU) y-=cspeed;
                    break;
                case"upr":
                    if(!collisionR) x+=cspeed;
                    if(!collisionU) y-=cspeed;
                    break;
                case"downl":
                    if(!collisionL) x-=cspeed;
                    if(!collisionD) y+=cspeed;
                    break;
                case"downr":
                    if(!collisionR) x+=cspeed;
                    if(!collisionD) y+=cspeed;
                    break;
            }
        }

        if(isRolling){
            rollingCounter++;
            if(rollingCounter%5==0&&rollingNum<6){
                rollingNum++;
            }
            if(rollingCounter==35){
                isRolling=false;
                rollingCounter=0;
                rollingNum=0;
                keyH.rolling=false;
            }
        }

        if (isMoving) {
            isThink=false;
            thinkCounter=0;
            spriteCounter++;
            if (keyH.sprint) {
                if (spriteCounter % 6 == 0) {
                    spriteNum = (spriteNum + 1) % (direction.equals("up") ? up.length : direction.equals("left") ? left.length : right.length);
                }
            } else if (spriteCounter % 8 == 0) {
                spriteNum = (spriteNum + 1) % (direction.equals("up") ? up.length : direction.equals("left") ? left.length : right.length);
            }
        }

        if (isAttack) {
            isThink=false;
            thinkCounter=0;
            isMoving = false;
            attackCounter++;sliceCounter++;
            if (sliceCounter % 5 == 0) {
                sliceNum++;
            }
            if (attackCounter % 5 == 0) {
                attackNum = (attackNum + 1) % attackR.length;}

            if (attackCounter >= 20) {
                isAttack = false;
                keyH.attack=false;
                attackCounter=0;
                    sliceCounter =0;sliceNum=0;
                    attackNum=0;
            }

        }
        if(!isAttack&&!isMoving){
            thinkCounter++;
            if(thinkCounter>=300){
                isThink=true;
                if(thinkCounter%5==0&&thinkNum<2){
                    thinkNum++;
                }
                if(thinkCounter>=400){
                    if(thinkCounter%10==0&&thinkNum<4){
                        thinkNum++;
                    }
                }
                if(thinkCounter>=500){
                    if(thinkCounter%10==0&&thinkNum<6){
                        thinkNum++;
                    }
                }
                if(thinkCounter>=600){
                    if(thinkCounter%10==0&&thinkNum<7){
                        thinkNum++;
                    }
                }
                if(thinkCounter>=611){
                    isThink=false;
                    thinkCounter=0;thinkNum=0;
                }
            }

        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if (isMoving) {
            switch (direction) {
                case "upr":
                    image = up[spriteNum];
                    break;
                case "upl":
                    image = upl[spriteNum];
                    break;
                case "up":
                    trcStand="up";
                    if (trc.equals("left")) {
                        image = upl[spriteNum];
                    } else if (trc.equals("right")) {
                        image = up[spriteNum];
                    }
                    break;
                case "down":
                    trcStand="down";
                    if (trc.equals("left")) {
                        image = left[spriteNum];
                    } else if (trc.equals("right")) {
                        image = right[spriteNum];
                    }
                    break;
                case "left":
                    trc = "left";
                    trcStand="left";
                    image = left[spriteNum];
                    break;
                case "right":
                    trc = "right";
                    trcStand="right";
                    image = right[spriteNum];
                    break;
                case "downr":
                    image = right[spriteNum];
                    break;
                case "downl":
                    image = left[spriteNum];
                    break;
            }
        } else if (trcStand.equals("right")) {
            image = thinkR[0];
        } else if (trcStand.equals("left")) {
            image = standl;
        } else if (trcStand.equals("up")) {
            image = standU;
        }else if (trcStand.equals("down")) {
            image = standr;
        }

        if (isAttack) {
            switch (atkDirection){
                case "attackUp":
                    image = attackU[attackNum];
                    if(!collisionOn) y-=3;
                    break;
                case "attackDown":
                    if (trc.equals("left")) {
                        image = attackL[attackNum];
                        if(!collisionOn)y+=3;
                    } else if (trc.equals("right")) {
                        image = attackR[attackNum];
                        if(!collisionOn)y+=3;
                    }
                    break;
                case "attackL":
                    if(!collisionOn)x-=3;
                    image = attackL[attackNum];
                    break;
                case "attackR":
                    if(!collisionOn)x+=3;
                    image=attackR[attackNum];
                    break;
            }

        }
        if(isThink){
            if(trc.equals("right"))
            image= thinkR[thinkNum];
            else if (trc.equals("left")) {
                image=thinkL[thinkNum];
            }
        }

        if(isRolling){
            if(direction.equals("up")){
                if(!collisionOn)y-=9;
                image=rollUpR[rollingNum];
            } else if (direction.equals("down")){
                if(!collisionOn)y+=9;
                image=rollR[rollingNum];
            }else if (direction.equals("right")){
                if(!collisionOn)x+=9;
                image=rollR[rollingNum];
            }else if (direction.equals("left")){
                if(!collisionOn)x-=9;
                image=rollL[rollingNum];
            }else if (direction.equals("upr")){
                if(!collisionR) x+=6;
                if(!collisionU) y-=6;
                image=rollUpR[rollingNum];
            }else if (direction.equals("upl")){
                if(!collisionL) x-=6;
                if(!collisionU) y-=6;
                image=rollUpL[rollingNum];
            }else if (direction.equals("downr")){
                if(!collisionR) x+=6;
                if(!collisionD) y+=6;
                image=rollR[rollingNum];
            }else if (direction.equals("downl")){
                if(!collisionL) x-=6;
                if(!collisionD) y+=6;
                image=rollL[rollingNum];
            }
        }

        g2.drawImage(shadow, screenX + gp.scale, screenY + 12 * gp.scale, gp.scale * shadow.getWidth(), gp.scale * shadow.getHeight(), null);
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void drawSliceUp(Graphics2D g2) {
        BufferedImage sliceImage=null;
        BufferedImage swordImage=null;
        if(isAttack) {
            switch (atkDirection) {
                case "attackUp":
                    swordImage=swordU[sliceNum];
                    sliceImage=sliceU[sliceNum];
                    g2.drawImage(sliceImage,screenX-7*gp.scale,screenY-10*gp.scale,gp.scale*sliceImage.getWidth(),gp.scale*sliceImage.getHeight(),null);
                    g2.drawImage(swordImage,screenX+11*gp.scale,screenY+9*gp.scale,gp.scale*swordImage.getWidth(), gp.scale*swordImage.getHeight(),null);
                    break;
                case "attackL":
                    swordImage=swordL[sliceNum];
                    sliceImage=sliceL[sliceNum];
                    g2.drawImage(sliceImage,screenX-15*gp.scale,screenY-9*gp.scale,gp.scale*sliceImage.getWidth(),gp.scale*sliceImage.getHeight(),null);
                    g2.drawImage(swordImage,screenX+3*gp.scale,screenY-9*gp.scale,gp.scale*swordImage.getWidth(), gp.scale*swordImage.getHeight(),null);
                    break;
            }

        }

    }
    public void drawSlice(Graphics2D g2) {
        BufferedImage sliceImage=null;
        BufferedImage swordImage=null;
        if(isAttack) {
            switch (atkDirection) {
                case "attackDown":
                    sliceImage = sliceD[sliceNum];
                    swordImage = swordD[sliceNum];
                    g2.drawImage(sliceImage,screenX-9*gp.scale,screenY+15*gp.scale,gp.scale*sliceImage.getWidth(),gp.scale*sliceImage.getHeight(),null);
                    g2.drawImage(swordImage,screenX-10*gp.scale,screenY+9*gp.scale,gp.scale*swordImage.getWidth(), gp.scale*swordImage.getHeight(),null);
                    break;

                case "attackR":
                    swordImage=swordR[sliceNum];
                    sliceImage=sliceR[sliceNum];
                    g2.drawImage(sliceImage,screenX+13*gp.scale,screenY-8*gp.scale,gp.scale*sliceImage.getWidth(),gp.scale*sliceImage.getHeight(),null);
                    g2.drawImage(swordImage,screenX+7*gp.scale,screenY+11*gp.scale,gp.scale*swordImage.getWidth(), gp.scale*swordImage.getHeight(),null);
                    break;
            }

        }

    }

    public void drawB(Graphics2D g2) {
        int entityLeft=x+solidArea.x;
        int entityRight=entityLeft+solidArea.width;
        int entityTop=y+solidArea.y;
        int entityBottom=entityTop+solidArea.height;
        g2.setColor(Color.WHITE);
//        g2.drawString("isMoving: " + isMoving, 10, 20);
//        g2.drawString("isAttack: " + isAttack, 10, 40);
//        g2.drawString("Direction: " + direction, 10, 60);
//        g2.drawString("collisionOn: " + collisionOn, 10, 80);
//        g2.drawString("trc: "+trc,10,100);
//        g2.drawString("trcStand: "+trcStand,100,100);
//        g2.drawString("isRolling: "+ isRolling, 10, 120);
//        g2.drawString("rollingCounter: "+ rollingCounter, 150, 120);
//        g2.drawString("rollingNum: "+ rollingNum, 400, 120);
//        g2.drawString("collisionCheck: "+ collisionCheck, 10, 140);
//        g2.drawString("x: "+mouseClick.getMouseX(),10, 160);
//        g2.drawString("y: "+mouseClick.getMouseY(),100, 160);
    }
}
