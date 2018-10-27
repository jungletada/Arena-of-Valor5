package Hero;
import org.apache.log4j.Logger;
import java.awt.*;

/**
 * This is an abstract class of character
 * @see Main
 * @see MAP
 * @see Pixel
 * @version 42.0
 */
public abstract class character {
    public int HP,MP,Exp,range;
    public double distance;
    public char name;
    public String Name;
    public int x,y,horde;
    public boolean beAttack;
    public static Logger LOG4J = Logger.getLogger(Hero.character.class);
    /**
     * Initial the character
     */
    public character() {
        Exp = 0;  HP = 100; MP = 50;
        beAttack = false;
    }

    /**
     * To judge the character whether alive or not
     * @param bf
     * @return true or false
     */
    public boolean IsAlive(battlefield bf){
        if (HP<=0)
        {
            bf.SetDied(x,y);
            System.out.println("Hero_"+name+" died!");
            return false;
        }
        else
            return true;

    }

    /**
     * check the HP,MP,Exp
     * they should below 100
     * @character
     */

    public void Restrict(){
        if(HP>100){
            HP=100;
        }

        if(MP>100){
            MP=100;
        }

        if(Exp>100){
            Exp=100;
        }

    }

    /**
     * Show the information of character
     * @param bf
     */
    public void ShowInfo(battlefield bf){
        IsAlive(bf);
        Restrict();
        System.out.println("Hero "+Name +" --- HP: "+HP
                +" MP: "+MP+" Exp: "+Exp);
        if(horde==1)
            System.out.println("location:("+x+","+y+")---Horde: HoNoO");
        else
            System.out.println("location:("+x+","+y+")---Horde: MiZu");
    }

    /**
     * Calculate the distance between two characters
     * @param p
     * @return distance
     */
    public double getDistance(character p){
        distance=(x-p.x)*(x-p.x)+(y-p.y)*(y-p.y);
        distance=Math.sqrt(distance);
        return distance;
    }

    /**
     * Move the character
     * @param direction
     * @param f
     */
    public void Move(char direction,battlefield f) {
        switch (direction){
            case 'A':  //move to the left
                if(y-1>=0 && f.field[x][y-1]=='·') {
                    f.field[x][y]='·';y--;f.field[x][y]=name;
                }
                else  {
                    //System.out.println("Illegal move!");
                    LOG4J.error("Illegal move!");
                }
                    break;
            case 'W'://move up
                if(x-1>=0 && f.field[x-1][y]=='·'){
                f.field[x][y]='·';x--; f.field[x][y]=name;
            }
                else {
                     LOG4J.error("Illegal move!");
                     //System.out.println("Illegal move!");
            }
            break;
            case 'D': //move to the right
                if(y+1<11 && f.field[x][y+1]=='·'){
                f.field[x][y]='·';y++; f.field[x][y]=name;
            }
                else{
                    //System.out.println("Illegal move!");
                    LOG4J.info("Illegal move!");

            }
            break;
            case 'S': //move down
                if(x+1<11 && f.field[x+1][y]=='·'){
                    f.field[x][y]='·';x++; f.field[x][y]=name;
                }
                else{
                    LOG4J.error("Illegal move!");
                    System.out.println("Illegal move!");
                }
            break;
        }
    }
}
//--------------------------------------------------------------------
interface skill{
    void ActiveSkill(character p);
    void PassiveSkill();
    void SetLocation(int i,int j);
    void Attacking(character be);
    void SpecialSkill(battlefield f)throws AWTException;
}
//------------------------------------------------
class HeroA extends character implements skill{
    public HeroA(){
        name='A';
        range=16;
        Name="Abdie";
    }

    /**
     * Set the location of character
     * @param i
     * @param j
     */
    public void SetLocation(int i,int j){
       x=i; y=j;
    }

    /**
     * </p> When is your turn, you can use active skill <br>
     * </p> use your 10 Exp, then HP increases 20 points, <br>
     * MP increases 30 points
     * @param p
     */
    public void ActiveSkill(character p){
        if(Exp>=10){
            MP+=30;  HP+=20;Exp-=10;
            if(p.Exp>10)
                p.Exp-=10;
            else
                p.Exp=0;
        }
    }

    /**
     * </p> when you are attacked, your passive skill
     * will launch automatically
     */
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=5;
            System.out.println("Passive skill launched!");
        }
    }

    /**
     * </p> character attacks other character <br>
     * uses 20 MP, then makes 20 injury and gains 20 Exp;
     * @param be
     */
    public void Attacking(character be){
        if(MP>=20){
            be.HP-=20;
            MP-=20;
            Exp+=20;
            be.beAttack=true;
        }
    }

    /**
     * When the character get close to a certain point
     * the special skill w`ill launched
     * @param f
     * @throws AWTException
     */
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=10;f.field[3][3]='·';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=10;f.field[3][7]='·';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=10;f.field[7][3]='·';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp += 10;
                f.field[7][7] = '·';
            }
        }
        f.PrintField();
        ShowInfo(f);
    }
}
//-----------------------------------------------
class HeroB extends character implements skill{
    public HeroB(){ name='B'; range=2;Name="Bacheler";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=15){
            MP+=35;  HP+=10;  Exp-=15;
            if(p.HP>15)
                p.HP-=15;
            else
                p.HP=1;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=1;Exp+=7;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=5) MP-=5;
        if(getDistance(be)<=range){
            be.HP-=15;
            Exp+=15;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=12;f.field[3][3]=' ';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=12;f.field[3][7]=' ';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=12;f.field[7][3]=' ';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp += 12;f.field[7][7] = ' ';
            }
        }
        f.PrintField();
        ShowInfo(f);
    }
}
//-----------------------------------------------
class HeroC extends character implements skill{
    public HeroC(){ name='C';range=4;Name="Cahill";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=20){
            MP+=25;  HP+=10;Exp-=20;
            if(p.MP>10)
                p.MP-=10;
            else
                p.MP=0;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=3;Exp+=2;range=3;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=10) MP-=10;
        if(getDistance(be)<=range){
        be.HP-=20;
        Exp+=20;
        be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=9;f.field[3][3]=' ';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=9;f.field[3][7]=' ';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=9;f.field[7][3]=' ';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp += 9;
                f.field[7][7] = ' ';
            }
        }
        ShowInfo(f);
        f.PrintField();
    }
}
//----------------------------------------------------------
class HeroD extends character implements skill{
    public HeroD(){ name='D';range=1;Name="Dahlia";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=20){
            MP+=35;  HP+=5;Exp-=20;
            if(p.Exp>10)
                p.Exp-=10;
            else
                p.Exp=0;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=2;MP+=5;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=10) MP-=10;
        if(getDistance(be)<=range) {
            be.HP -= 30;
            Exp += 20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=10;f.field[3][3]='·';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=10;f.field[3][7]='·';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=10;f.field[7][3]='·';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp += 10;
                f.field[7][7] = '·';
            }
        }

        f.PrintField();ShowInfo(f);
    }
}
//----------------------------------------------------
class HeroE extends character implements skill{
    public HeroE(){ name='E';range=1;Name="Earle";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=50){
            MP=0;  HP+=0;Exp-=50;
            if(p.HP>40)
                p.HP-=40;
            else
                p.HP=40;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=5;MP+=2;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=20) MP-=20;
        if(getDistance(be)<=range) {
            be.HP -= 35;
            Exp += 20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=15;f.field[3][3]=' ';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=15;f.field[3][7]=' ';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=15;f.field[7][3]=' ';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=15;f.field[7][7] = ' ';
            }
        }

        f.PrintField(); ShowInfo(f);
    }
}
//----------------------------------------------------
class HeroF extends character implements skill{
    public HeroF(){ name='F';range=2;Name="Fairchild";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=10){
            MP+=30;  HP+=20;Exp-=10;
            if(p.Exp>10)
                p.Exp-=10;
            else
                p.Exp=0;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=5;range=3;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=20) MP-=20;
        if(getDistance(be)<=range) {
            be.HP -= 25;
            Exp += 25;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=7;f.field[3][3]=' ';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=7;f.field[3][7]=' ';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=7;f.field[7][3]=' ';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=7;f.field[7][7] = ' ';
            }
        }

        f.PrintField(); ShowInfo(f);
    }
}
//--------------------------------------------------
class HeroG extends character implements skill{
    public HeroG(){ name='G';range=3;Name="Gabrielle";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=30){
            MP+=20;  HP+=10; Exp-=30;
            range++;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=3;Exp+=3;MP=30;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=15) MP-=15;
        if(getDistance(be)<=range) {
            be.HP-=20;
            Exp+=20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=15;f.field[3][3]=' ';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=15;f.field[3][7]=' ';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=15;f.field[7][3]=' ';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=15;f.field[7][7] = ' ';
            }
        }

        f.PrintField();ShowInfo(f);
    }
}
//--------------------------------------------------------
class HeroH extends character implements skill{
    public HeroH(){ name='H';range=2;Name="Hackett";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=25){
            MP+=25;  HP+=10; Exp-=25;
            p.range=1;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=5;range=4;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=15) MP-=15;
        if(getDistance(be)<=range) {
            be.HP -= 20;
            Exp += 20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=15;f.field[3][3]=' ';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=15;f.field[3][7]=' ';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=15;f.field[7][3]=' ';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=15;f.field[7][7] = ' ';
            }
        }

        f.PrintField(); ShowInfo(f);
    }
}
//----------------------------------------------
class HeroI extends character implements skill{
    public HeroI(){ name='I';range=1;Name="Imogene";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=25){
            MP+=20;  HP+=10; Exp-=25;
            if(p.MP>10)
                p.MP-=10;
            else
                p.MP=0;
            p.range=2;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=2;MP=40;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=10) MP-=10;
        if(getDistance(be)<=range) {
            be.HP -= 20;
            Exp += 20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=15;f.field[3][3]='·';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=15;f.field[3][7]='·';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=15;f.field[7][3]='·';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=15;f.field[7][7] = '·';
            }
        }

        f.PrintField();ShowInfo(f);
    }
}
//----------------------------------------------
class HeroJ extends character implements skill{
    public HeroJ(){ name='J';range=4;Name="Jacobson";}
    public void SetLocation(int i,int j){
        x=i; y=j;
    }
    public void ActiveSkill(character p){
        if(Exp>=10){
            MP+=25;  HP+=10; Exp-=10;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=5;Exp+=5;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(character be){
        if(MP>=30) MP-=30;
        if(getDistance(be)<=range) {
            be.HP -= 20;
            Exp += 20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(battlefield f)throws AWTException{
        if(horde==1 && f.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=15;f.field[3][3]='·';
            }
        }
        if(horde==1 && f.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=15;f.field[3][7]='·';
            }
        }

        if(horde==2 && f.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=15;f.field[7][3]='·';
            }
        }
        if(horde==2 && f.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=15;f.field[7][7] = '·';
            }
        }

        f.PrintField(); ShowInfo(f);
    }
}
