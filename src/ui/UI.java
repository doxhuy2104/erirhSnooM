package ui;

import java.awt.image.BufferedImage;

public class UI {
    BufferedImage castle,moon,layer1,layer2,layer3,layer4,layer5,layer6,logo,sideCursorL,sideCursorR,checkBox,checkedBox,cursorBox,mSetting,mFill,mSettingC,menuBox;
    public int layer1X=0,layer2X=0,layer3X=0,layer4X=0, layer5X=0, layer6X =0;
    public int layer1x=0,layer2x=0,layer3x=0,layer4x=0, layer5x=0, layer6x =0,y2=0,y3=0,y4=0,y5=0,y6=0,ym=0,yc=0,x=0,xCB,yCB,xCedB=600,yCedB=270,xMB,yMB,mFillL=200,sFillL=200;
    public boolean inUI=true,isUI=true,exitUI=false,menu=true,side,inGame=false;
    public String mouseDirection;
    public int xL,yL,xR,yR,bX,bY;
    public Boolean CONTINUE=false,NEWGAME=false,SETTINGS=false,CREDITS=false,cB=false,mB=false,mF=false,sF=false,sB=false;
}
