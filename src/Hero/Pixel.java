package Hero;

import org.apache.log4j.Logger;

/**
 * This is the class of Crystal tower
 * @see MAP
 * @see character
 * @see Main
 * @version 19.0
 */
public class Pixel {
    public int HonoHP_1,HonoHP_2,MizuHP_1,MizuHP_2;
    public boolean Hsu1,Hsu2,Msu1,Msu2;

    public Pixel(){
        HonoHP_1=HonoHP_2=MizuHP_1=MizuHP_2=150;
        Hsu1=Hsu2=Msu1=Msu2=false;
    }

    /**
     * Show the different horde's hero attacking the Crystal tower
     * @param cr
     * @param f
     */
    public void BeAttcked(character cr,battlefield f){
        if(cr.horde==1) {
            int dis1 = (cr.x - 8) * (cr.x - 8) + (cr.y - 4) * (cr.y - 4);
            double Dis1 = Math.sqrt(dis1);
            int dis2 = (cr.x - 8) * (cr.x - 8) + (cr.y - 6) * (cr.y - 6);
            double Dis2 = Math.sqrt(dis2);
            if (Dis1 <= cr.range && Dis2 > cr.range) {
                MizuHP_1 -= cr.HP;cr.Exp+=10;
                IsExist(f);  Msu1=true;
                cr.ShowInfo(f);
            }
            if (Dis1 > cr.range && Dis2 < cr.range) {
                MizuHP_2 -= cr.HP;
                cr.Exp+=10;IsExist(f);Msu2=true;
                cr.ShowInfo(f);
            }
            if (Dis1 <= cr.range && Dis2 <= cr.range) {
                MizuHP_1 -= cr.HP / 2;
                MizuHP_2 -= cr.HP / 2;
                cr.Exp+=10;IsExist(f);
                Msu2=true;Msu1=true;
                cr.ShowInfo(f);
            }
        }
        else if(cr.horde==2) {
            int dis1 = (cr.x - 2) * (cr.x - 2) + (cr.y - 4) * (cr.y - 4);
            double Dis1 = Math.sqrt(dis1);
            int dis2 = (cr.x - 2) * (cr.x - 2) + (cr.y - 6) * (cr.y - 6);
            double Dis2 = Math.sqrt(dis2);
            if (Dis1 <= cr.range && Dis2 > cr.range) {
                HonoHP_1 -= cr.HP; IsExist(f);
                cr.Exp+=10; Hsu1=true; cr.ShowInfo(f);
            }
            if (Dis1 > cr.range && Dis2 < cr.range) {
                HonoHP_2 -= cr.HP;IsExist(f);
                cr.Exp+=10;Hsu1=true;cr.ShowInfo(f);
            }
            if (Dis1 <= cr.range && Dis2 <= cr.range) {
                HonoHP_1 -= cr.HP / 2;
                HonoHP_2 -= cr.HP / 2;
                cr.Exp+=10;Hsu1=true;Hsu2=true;
                IsExist(f);cr.ShowInfo(f);
            }
        }
        ShowSuccess(cr);
    }

    /**
     * Judge the Crystal whether exist or not
     * @param f
     * @return true or false
     */
    public boolean IsExist(battlefield f){
        if(MizuHP_1<=0) {
            System.out.println("The Mizu tower_1 lost!");
            f.field[8][4]='·';
            return false;
        }
        if(MizuHP_2<=0) {
            System.out.println("The Mizu tower_2 lost!");
            f.field[8][2]='·';
            return false;
        }
        if(HonoHP_1<=0) {
            System.out.println("The Hono tower_1 lost!");
            f.field[2][4]='·';
            return false;
        }
        if(HonoHP_2<=0) {
            System.out.println("The Hono tower_2 lost!");
            f.field[2][6]='·';
            return false;
        }
           return true;
    }

    /**
     * Shoe the success message
     * @param cr
     */
    public void ShowSuccess(character cr){
        if(Hsu1){
            System.out.println("Hero "+cr.Name+" successfully attack Hono_1 tower!");
            System.out.println("Hono_1 tower : HP "+HonoHP_1);
            Hsu1=false;
        }
        if(Hsu2){
            System.out.println("Hero "+cr.Name+" successfully attack Hono_2 tower!");
            System.out.println("Hono_2 tower : HP "+HonoHP_1);
            Hsu2=false;
        }
        if(Msu1){
            System.out.println("Hero "+cr.name+" successfully attack Mizu_1 tower!");
            System.out.println("Mizu_1 tower : HP "+MizuHP_1);
            Msu1=false;
        }
        if(Msu2){
            System.out.println("Hero "+cr.name+" successfully attack Mizu_2 tower!");
            System.out.println("Mizu_2 tower : HP "+MizuHP_1);
            Msu2=false;
        }
    }

    /**
     * Judge whether the game is over or not
     * when the tower's HP belows 0
     * @see MAP
     * @see character
     * @param m
     */
    public void EndOfGame(Main m){
        if(HonoHP_1<=0&&HonoHP_2<=0){
            System.out.println("country of MiZu win!");
            m.winner=1;
        }
        if(MizuHP_1<=0&&MizuHP_2<=0){
            System.out.println("Country of HoNoO win!");
            m.winner=2;
        }
    }
}
