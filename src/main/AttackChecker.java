package main;

import entity.Enemies;
import entity.Entity;
import entity.Player;

public class AttackChecker {
    GamePanel gp;
    Entity entity;
    public AttackChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void attackChecker(Entity entity) {
        if(gp.player.isAttack){
            int atkU = gp.player.attackAreaU.y;
            int atkUR = gp.player.attackAreaU.x + gp.player.attackAreaU.width;
            int atkUL = gp.player.attackAreaU.x;
            int atkUD = gp.player.attackAreaU.y + gp.player.attackAreaU.height;

            int atkD = gp.player.attackAreaD.y + gp.player.attackAreaD.height;
            int atkDL = gp.player.attackAreaD.x;
            int atkDR = gp.player.attackAreaD.x + gp.player.attackAreaD.width;
            int atkDU = gp.player.attackAreaD.y;

            int atkL = gp.player.attackAreaL.x;
            int atkLU = gp.player.attackAreaL.y;
            int atkLD = gp.player.attackAreaL.y + gp.player.attackAreaL.height;
            int atkLR = gp.player.attackAreaL.x + gp.player.attackAreaL.width;

            int atkR = gp.player.attackAreaR.x + gp.player.attackAreaR.width;
            int atkRU = gp.player.attackAreaR.y;
            int atkRD = gp.player.attackAreaR.y + gp.player.attackAreaR.height;
            int atkRL = gp.player.attackAreaR.x;

            int bodyU = entity.bodyArea.y;
            int bodyD  =entity.bodyArea.y + entity.bodyArea.height;
            int bodyL = entity.bodyArea.x;
            int bodyR = entity.bodyArea.x + entity.bodyArea.width;

            switch (gp.player.atkDirection) {
                case "attackUp":
                    if (atkU < bodyD && atkUD > bodyD && atkUL < bodyR && atkUR > bodyL) {
                        entity.attacking = true;
                    }
                    break;
                case"attackDown":
                    if(atkD>bodyU&&atkDU<bodyU&&atkDL<bodyR&&atkDR>bodyL){
                        entity.attacking=true;
                    }
                    break;
                case"attackR":
                    if(atkR>bodyL&&atkRL<bodyL&&atkRU<bodyD&&atkRD>bodyU){
                        entity.attacking=true;
                    }
                    break;
                case"attackL":
                    if(atkL<bodyR&&atkLR>bodyR&&atkRU<bodyD&&atkRD>bodyU){
                        entity.attacking=true;
                    }
                    break;
            }
        }
    }
}