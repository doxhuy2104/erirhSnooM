package main;

import entity.Enemies;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setEnemies(){
        gp.slime[0] = new Enemies(gp);
        gp.slime[0].sx=15*gp.tileSize;
        gp.slime[0].sy=15*gp.tileSize;

        gp.slime[1] = new Enemies(gp);
        gp.slime[1].sx=25*gp.tileSize;
        gp.slime[1].sy=15*gp.tileSize;

        gp.slime[2] = new Enemies(gp);
        gp.slime[2].sx=30*gp.tileSize;
        gp.slime[2].sy=20*gp.tileSize;

        gp.slime[3] = new Enemies(gp);
        gp.slime[3].sx=20*gp.tileSize;
        gp.slime[3].sy=30*gp.tileSize;
    }
}
