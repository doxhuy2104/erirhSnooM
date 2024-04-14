package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemies extends Entity {
    GamePanel gp;
    public final int screenX, screenY;
    private float transparency = 1.0f;
    private final float TRANSPARENCY_STEP = 0.5f;

    public static int abs(int x) {
        return x >= 0 ? x : -x;
    }


    public Enemies(GamePanel gp) {
        this.gp = gp;

        getEnemiesImage();

        bodyArea = new Rectangle();

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        attackAreaU = new Rectangle(screenX - 7 * gp.scale, screenY - 10 * gp.scale, 136, 76);
        attackAreaL = new Rectangle(screenX - 15 * gp.scale, screenY - 9 * gp.scale, 76, 136);
        attackAreaD = new Rectangle(screenX - 9 * gp.scale, screenY + 15 * gp.scale, 136, 76);
        attackAreaR = new Rectangle(screenX + 13 * gp.scale, screenY - 8 * gp.scale, 76, 136);
    }


    public void getEnemiesImage() {
        try {
            slimeL = new BufferedImage[4];
            slimeR = new BufferedImage[4];
            BufferedImage slimeSheet = ImageIO.read(getClass().getResourceAsStream("/enemies/slimeRed.png"));
            for (int i = 0; i < 4; i++) {
                slimeR[i] = slimeSheet.getSubimage(i * 16, 16, 16, 16);
                slimeL[i] = slimeSheet.getSubimage(48 - i * 16, 32, 16, 16);
            }
            spark = new BufferedImage[3];
            BufferedImage sparkSheet = ImageIO.read(getClass().getResourceAsStream("/attack/spark.png"));
            for (int i = 0; i < 3; i++) {
                spark[i] = sparkSheet.getSubimage(28 * i, 0, 28, 34);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        sx = 1000;
        sy = 800;
        if (alive) {
            eX = -gp.player.x + gp.player.screenX + eSX + sx;
            eY = -gp.player.y + gp.player.screenY + eSY + sy;
        }
        attacking = false;
        gp.attackChecker.attackChecker(this);
        if (attacking && hp != 0) {
            daylui = true;
            if (atkCounter == 0) {
                hp--;
                atkCounter = 1;
                transparency -= TRANSPARENCY_STEP;
                if (transparency < 0.0f) {
                    transparency = 0.0f;
                }
            }
            sparkCounter++;
            if (sparkCounter % 7 == 0) {
                sparkNum++;
            }
            if (hp == 0) sp = true;
        } else {
            transparency += TRANSPARENCY_STEP;
            if (transparency > 1.0f) {
                transparency = 1.0f;
            }
        }
        if (!attacking) {
            sparkCounter = 0;
            sparkNum = 0;
            atkCounter = 0;
        }
        if (hp == 0) {
            alive = false;
        }
        if (sp) {
            sparkCounter++;
            if (sparkCounter % 7 == 0) {
                sparkNum++;
            }
            if (sparkNum == 2) {
                sparkCounter = 0;
                sparkNum = 0;
                sp = false;
            }
        }


        if (daylui) {
            switch (gp.player.atkDirection) {
                case "attackUp":
                    eD = "U";
                    break;
                case "attackDown":
                    eD = "D";
                    break;
                case "attackR":
                    eD = "R";
                    break;
                case "attackL":
                    eD = "L";
                    break;
            }
        }

        if (alive && !daylui) {
            if ((abs(eX - screenX)) < (gp.screenWidth / 2) && (abs(eY - screenY)) < (gp.screenHeight / 2)) {
                if (eX > screenX) {
                    eD = "L";
                    if (eY < screenY) eD = "LD";
                    else if (eY > screenY) eD = "LU";
                } else if (eX < screenX) {
                    eD = "R";
                    if (eY < screenY) eD = "RD";
                    else if (eY > screenY) eD = "RU";
                } else if (eX == screenX) {
                    if (eY < screenY) {
                        eD = "D";
                    } else if (eY > screenY) {
                        eD = "U";
                    }
                }
            }
        }

        eCollision = false;
        eCollisionR = false;
        eCollisionL = false;
        eCollisionU = false;
        eCollisionD = false;
        gp.collisionChecker.checkTileEnemies(this);
//        eCollision = true;
//        eCollisionR = true;
//        eCollisionL = true;
//        eCollisionU = true;
//        eCollisionD = true;

        if (daylui) {
            dlNum++;
            switch (gp.player.atkDirection) {
                case "attackUp":
                    //      eD="U";
                    if (!eCollision) eSY -= dlS;
                    break;
                case "attackDown":
                    //   eD="D";
                    if (!eCollision) eSY += dlS;
                    break;
                case "attackR":
                    //   eD="R";
                    if (!eCollision) eSX += dlS;
                    break;
                case "attackL":
                    //  eD="L";
                    if (!eCollision) eSX -= dlS;
                    break;
            }
            if (dlNum % 5 == 0) {
                dlS--;
                if (dlNum == 30) {
                    daylui = false;
                    dlNum = 0;
                    dlS = 6;
                }
            }
        }

        eToPCU = false;
        eToPCD = false;
        eToPCL = false;
        eToPCR = false;
        gp.collisionChecker.eToPCo(this);

        if ((abs(eX - screenX)) < (gp.screenWidth / 2) && (abs(eY - screenY)) < (gp.screenHeight / 2)) saw = true;
        if (alive && !daylui) {
            switch (eD) {
                case "U":
                    if (!eCollision && !eToPCU) eSY--;
                    break;
                case "D":
                    if (!eCollision && !eToPCD) eSY++;
                    break;
                case "L":
                    if (!eCollision && !eToPCL) eSX--;
                    break;
                case "R":
                    if (!eCollision && !eToPCR) eSX++;
                    break;
                case "RD":
                    if (!eCollisionR && !eToPCR) eSX++;
                    if (!eCollisionD && !eToPCD) eSY++;
                    break;
                case "RU":
                    if (!eCollisionR && !eToPCR) eSX++;
                    if (!eCollisionU&& !eToPCU) eSY--;
                    break;
                case "LU":
                    if (!eCollisionL && !eToPCL) eSX--;
                    if (!eCollisionU&& !eToPCU) eSY--;
                    break;
                case "LD":
                    if (!eCollisionL && !eToPCL) eSX--;
                    if (!eCollisionD && !eToPCD) eSY++;
                    break;
            }
            eCounter++;
            if (eCounter % 8 == 0 && eNum < 3) {
                eNum++;
            }
            if (eCounter == 32) {
                eNum = 0;
                eCounter = 0;
            }
        }
        bodyArea = new Rectangle(eX + 8, eY + 24, 48, 36);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        if (alive) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
//            switch (eD) {
//                case "R":
//                    slimeI=slimeR[eNum];
//                    break;
//                case "L":
//                    slimeI=slimeL[eNum];
//                    break;
//            }
            if (eD.equals("LU") || eD.equals("LD") || eD.equals("L") || eD.equals("U")) slimeI = slimeL[eNum];
            if (eD.equals("RU") || eD.equals("RD") || eD.equals("R") || eD.equals("D")) slimeI = slimeR[eNum];
            g2.drawImage(slimeI, eX, eY, gp.scale * slimeI.getWidth(), gp.scale * slimeI.getHeight(), null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
        if (attacking && alive || sp) {
            g2.drawImage(spark[sparkNum], eX - 24, eY - 36, gp.scale * spark[sparkNum].getWidth(), gp.scale * spark[sparkNum].getHeight(), null);
        }

////        g2.drawString(gp.player.x+" "+gp.player.y,200,10);
////        g2.drawString("attacking: "+attacking,300,10);
////        g2.drawString(" "+gp.player.atkDirection,400,10);
//////        g2.drawString("bodyD"+(bodyArea.y + bodyArea.height)+" bodyU"+bodyArea.y, 450,10);
//////        g2.drawString("atkU"+attackAreaU.y+" atkUR"+(attackAreaU.x + attackAreaU.width)+" atkUL"+attackAreaU.x+" atkUD"+(attackAreaU.y + attackAreaU.height),600,10);
//        g2.drawString("hp: "+hp, 200,20);
////        g2.drawString("FPS: "+gp.FPS, 400, 20);
////        g2.drawString("sparkCounter: "+sparkCounter+" sparkNum: "+sparkNum,400, 30);
////        g2.drawString("eD: "+eD,700,10);
////        g2.drawString("dayLui: "+daylui,800,10);
////        g2.drawString("eCollision: "+eCollision,800,20);
//        g2.drawString("eX: "+((abs(eX-screenX))-(gp.screenWidth/2))+" eY: "+((abs(eY-screenY))-(gp.screenHeight/2)),10,10);
//        g2.drawString("sparkNum: "+sparkNum+" "+sp,100,10);
        // g2.drawString("sT:"+enemieTop+" sB:"+enemieBottom+" sL:"+enemieLeft+" sR:"+enemieRight,10,30);
        //   g2.drawString("x: "+gp.player.x+"y: "+gp.player.y,10,40);
        //g2.drawString("eCollision: " + eCollision + " eD: " + eD, 10, 50);
        g2.drawString("eT:" + bodyArea.y + " eD:" + (bodyArea.y+bodyArea.height)+" eL:"+bodyArea.x+" eR:"+(bodyArea.x+bodyArea.width),10,10);
        g2.drawString("pT:"+screenY+" pD:"+(screenY+gp.player.solidArea.height)+" pL:"+screenX+" eR:"+(screenX+gp.player.solidArea.width),10,20);
    }
}
