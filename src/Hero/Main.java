package Hero;

import java.awt.*;
import java.util.Scanner;
import org.apache.log4j.Logger;
public class Main {
    public int winner;
    public Main(){
        winner=0;
    }
    public void PrintInfo(){
        System.out.println("ヒーロー:\n      A-B-C-D-E-F-G-H-I-J");
        System.out.println("Hero-A:Abdi,      Hero-B:Bacheler\nHero-C:Cahill,    Hero-D:" +
                "Dahlia\nHero-E:Earle,     Hero-F:Fairchild\nHero-G:Gabrielle, Hero-H:"
                +"Hackett\nHero-I:Imogene,   Hero-J:Jacobson");
    }
    public static Logger LOGGER = Logger.getLogger(Hero.Main.class);


    //---------------------------------------------------------------------------
    public static void main(String[]args)throws AWTException
    {

        Scanner sc=new Scanner(System.in);
        Main Mainset=new Main();
        battlefield Field=new battlefield();
        Pixel pixel=new Pixel();
        Field.SetField();
        //-------------------------------------------------------------------------------------
        Mainset.PrintInfo();
        System.out.println("Please choose the HERO\n"+"ヒーロー");
        LOGGER.debug("This is debug message.");
        HeroA A_=new HeroA();HeroB B_=new HeroB();
        HeroC C_=new HeroC();HeroD D_=new HeroD();
        HeroE E_=new HeroE();HeroF F_=new HeroF();
        HeroG G_=new HeroG();HeroH H_=new HeroH();
        HeroI I_=new HeroI();HeroJ J_=new HeroJ();
        //----------------------------------------------------------------------------------
        for(int HeroNum=0;HeroNum<5;HeroNum++)
        {
            char in=sc.next().charAt(0);

            Field.field[0][2*HeroNum+1]=in;
            switch (in){
                case'A':A_.SetLocation(0,2*HeroNum+1);A_.horde=1;break;
                case'B':B_.SetLocation(0,2*HeroNum+1);B_.horde=1;break;
                case'C':C_.SetLocation(0,2*HeroNum+1);C_.horde=1;break;
                case'D':D_.SetLocation(0,2*HeroNum+1);D_.horde=1;break;
                case'E':E_.SetLocation(0,2*HeroNum+1);E_.horde=1;break;
                case'F':F_.SetLocation(0,2*HeroNum+1);F_.horde=1;break;
                case'G':G_.SetLocation(0,2*HeroNum+1);G_.horde=1;break;
                case'H':H_.SetLocation(0,2*HeroNum+1);H_.horde=1;break;
                case'I':I_.SetLocation(0,2*HeroNum+1);I_.horde=1;break;
                case'J':J_.SetLocation(0,2*HeroNum+1);J_.horde=1;break;
            }
            LOGGER.info("You chose hero "+in+", location is (0,"+(2*HeroNum+1)+")");
        }

        System.out.println("Please choose the HERO\n"+"ヒーロー");
        for(int HeroNum=0;HeroNum<5;HeroNum++)
        {
            char in=sc.next().charAt(0);
            LOGGER.info("You chose hero "+in);
            Field.field[10][2*HeroNum+1]=in;
            switch (in){
                case'A':A_.SetLocation(10,2*HeroNum+1);A_.horde=2;break;
                case'B':B_.SetLocation(10,2*HeroNum+1);B_.horde=2;break;
                case'C':C_.SetLocation(10,2*HeroNum+1);C_.horde=2;break;
                case'D':D_.SetLocation(10,2*HeroNum+1);D_.horde=2;break;
                case'E':E_.SetLocation(10,2*HeroNum+1);E_.horde=2;break;
                case'F':F_.SetLocation(10,2*HeroNum+1);F_.horde=2;break;
                case'G':G_.SetLocation(10,2*HeroNum+1);G_.horde=2;break;
                case'H':H_.SetLocation(10,2*HeroNum+1);H_.horde=2;break;
                case'I':I_.SetLocation(10,2*HeroNum+1);I_.horde=2;break;
                case'J':J_.SetLocation(10,2*HeroNum+1);J_.horde=2;break;
            }
            LOGGER.info("You chose hero "+in+", location is (10,"+(2*HeroNum+1)+")");
        }
        Field.Print_Field_single();

        String option;
        while(Mainset.winner==0) {
            option=sc.next();
            if(option.equals("MOVE")) {
                char on=sc.next().charAt(0);
                char dir = sc.next().charAt(0);
                switch (on){
                    case'A':A_.Move(dir,Field);
                       A_.ShowInfo(Field);break;
                    case'B':B_.Move(dir,Field);
                       B_.ShowInfo(Field);break;
                    case'C':C_.Move(dir,Field);
                       C_.ShowInfo(Field);break;
                    case'D':D_.Move(dir,Field);
                       D_.ShowInfo(Field);break;
                    case'E':E_.Move(dir,Field);
                       E_.ShowInfo(Field);break;
                    case'F':F_.Move(dir,Field);
                       F_.ShowInfo(Field);break;
                    case'G':G_.Move(dir,Field);
                       G_.ShowInfo(Field);break;
                    case'H':H_.Move(dir,Field);
                       H_.ShowInfo(Field);break;
                    case'I':I_.Move(dir,Field);
                       I_.ShowInfo(Field);break;
                    case'J':J_.Move(dir,Field);
                       J_.ShowInfo(Field);break;
                }
                Field.Print_Field_single();
                Mainset.PrintInfo();
                //LOGGER.debug("You move hero "+on+" to a new location ");
            }
            else if(option.equals("ATTACK")){
                char ac=sc.next().charAt(0);
                char pa=sc.next().charAt(0);
                Mainset.PrintInfo();
                Field.PrintField();
                switch (ac){
                    case 'A':{
                        switch (pa){
                            case'B':A_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(A_,B_);B_.ShowInfo(Field);break;
                            case'C':A_.Attacking(C_);C_.PassiveSkill();
                                Field.CalculatePath(A_,C_);C_.ShowInfo(Field);break;
                            case'D':A_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(A_,D_);D_.ShowInfo(Field);break;
                            case'E':A_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(A_,E_);E_.ShowInfo(Field);break;
                            case'F':A_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(A_,F_);F_.ShowInfo(Field);break;
                            case'G':A_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(A_,G_);G_.ShowInfo(Field);break;
                            case'H':A_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(A_,H_);H_.ShowInfo(Field);break;
                            case'I':A_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(A_,I_);I_.ShowInfo(Field);break;
                            case'J':A_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(A_,J_);J_.ShowInfo(Field);break;
                        }
                        A_.ShowInfo(Field);
                        LOGGER.debug("You let hero "+ac+" to attack hero "+pa);
                        break;
                    }
                    case 'B':{
                        switch (pa){
                            case'A':B_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(B_,A_);A_.ShowInfo(Field);break;
                            case'C':B_.Attacking(C_);B_.PassiveSkill();
                                Field.CalculatePath(B_,C_);C_.ShowInfo(Field);break;
                            case'D':B_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(B_,D_);D_.ShowInfo(Field);break;
                            case'E':B_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(B_,E_);E_.ShowInfo(Field);break;
                            case'F':B_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(B_,F_);F_.ShowInfo(Field);break;
                            case'G':B_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(B_,G_);G_.ShowInfo(Field);break;
                            case'H':B_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(B_,H_);H_.ShowInfo(Field);break;
                            case'I':B_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(B_,I_);I_.ShowInfo(Field);break;
                            case'J':B_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(B_,J_);J_.ShowInfo(Field);break;
                        }
                        B_.ShowInfo(Field);
                        break;
                    }
                    case 'C':{
                        switch (pa){
                            case'B':C_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(C_,B_);B_.ShowInfo(Field);break;
                            case'A':C_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(C_,A_);A_.ShowInfo(Field);break;
                            case'D':C_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(C_,D_);D_.ShowInfo(Field);break;
                            case'E':C_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(C_,E_);E_.ShowInfo(Field);break;
                            case'F':C_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(C_,F_);F_.ShowInfo(Field);break;
                            case'G':C_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(C_,G_);G_.ShowInfo(Field);break;
                            case'H':C_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(C_,H_);H_.ShowInfo(Field);break;
                            case'I':C_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(C_,I_);I_.ShowInfo(Field);break;
                            case'J':C_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(C_,J_);J_.ShowInfo(Field);break;
                        }
                        C_.ShowInfo(Field);
                        break;
                    }
                    case 'D':{
                        switch (pa){
                            case'B':D_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(D_,B_);B_.ShowInfo(Field);break;
                            case'C':D_.Attacking(C_);C_.PassiveSkill();
                                Field.CalculatePath(D_,C_);C_.ShowInfo(Field);break;
                            case'A':D_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(D_,A_);A_.ShowInfo(Field);break;
                            case'E':D_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(D_,E_);E_.ShowInfo(Field);break;
                            case'F':D_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(D_,F_);F_.ShowInfo(Field);break;
                            case'G':D_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(D_,G_);G_.ShowInfo(Field);break;
                            case'H':D_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(D_,H_);H_.ShowInfo(Field);break;
                            case'I':D_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(D_,I_);I_.ShowInfo(Field);break;
                            case'J':D_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(D_,J_);J_.ShowInfo(Field);break;
                        }
                        D_.ShowInfo(Field);
                        break;
                    }
                    case 'E':{
                        switch (pa){
                            case'B':E_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(E_,B_);B_.ShowInfo(Field);break;
                            case'C':E_.Attacking(C_);C_.PassiveSkill();
                                Field.CalculatePath(E_,C_);C_.ShowInfo(Field);break;
                            case'D':E_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(E_,D_);D_.ShowInfo(Field);break;
                            case'A':E_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(E_,A_);A_.ShowInfo(Field);break;
                            case'F':E_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(E_,F_);F_.ShowInfo(Field);break;
                            case'G':E_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(E_,G_);G_.ShowInfo(Field);break;
                            case'H':E_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(E_,H_);H_.ShowInfo(Field);break;
                            case'I':E_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(E_,I_);I_.ShowInfo(Field);break;
                            case'J':E_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(E_,J_);J_.ShowInfo(Field);break;
                        }
                        E_.ShowInfo(Field);
                        break;
                    }
                    case 'F':{
                        switch (pa){
                            case'B':F_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(F_,B_);B_.ShowInfo(Field);break;
                            case'C':F_.Attacking(C_);C_.PassiveSkill();
                                Field.CalculatePath(F_,C_);C_.ShowInfo(Field);break;
                            case'D':F_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(F_,D_);D_.ShowInfo(Field);break;
                            case'E':F_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(F_,E_);E_.ShowInfo(Field);break;
                            case'A':F_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(F_,A_);A_.ShowInfo(Field);break;
                            case'G':F_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(F_,G_);G_.ShowInfo(Field);break;
                            case'H':F_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(F_,H_);H_.ShowInfo(Field);break;
                            case'I':F_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(F_,I_);I_.ShowInfo(Field);break;
                            case'J':F_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(F_,J_);J_.ShowInfo(Field);break;
                        }
                        F_.ShowInfo(Field);
                        break;
                    }
                    case 'G':{
                        switch (pa){
                            case'B':G_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(G_,B_);B_.ShowInfo(Field);break;
                            case'C':G_.Attacking(C_);C_.PassiveSkill();
                                Field.CalculatePath(G_,C_);C_.ShowInfo(Field);break;
                            case'D':G_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(G_,D_);D_.ShowInfo(Field);break;
                            case'E':G_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(G_,E_);E_.ShowInfo(Field);break;
                            case'F':G_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(G_,F_);F_.ShowInfo(Field);break;
                            case'A':G_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(G_,A_);A_.ShowInfo(Field);break;
                            case'H':G_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(G_,H_);H_.ShowInfo(Field);break;
                            case'I':G_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(G_,I_);I_.ShowInfo(Field);break;
                            case'J':G_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(G_,J_);J_.ShowInfo(Field);break;
                        }
                        G_.ShowInfo(Field);
                        break;
                    }
                    case 'H':{
                        switch (pa){
                            case'B':H_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(H_,B_);B_.ShowInfo(Field);break;
                            case'C':H_.Attacking(C_);C_.PassiveSkill();
                                Field.CalculatePath(H_,C_);C_.ShowInfo(Field);break;
                            case'D':H_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(H_,D_);D_.ShowInfo(Field);break;
                            case'E':H_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(H_,E_);E_.ShowInfo(Field);break;
                            case'F':H_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(H_,F_);F_.ShowInfo(Field);break;
                            case'G':H_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(H_,G_);G_.ShowInfo(Field);break;
                            case'A':H_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(H_,A_);A_.ShowInfo(Field);break;
                            case'I':H_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(H_,I_);I_.ShowInfo(Field);break;
                            case'J':H_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(H_,J_);J_.ShowInfo(Field);break;
                        }
                        H_.ShowInfo(Field);
                        break;
                    }
                    case 'I':{
                        switch (pa){
                            case'B':I_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(I_,B_);B_.ShowInfo(Field);break;
                            case'C':I_.Attacking(C_);C_.PassiveSkill();
                                Field.CalculatePath(I_,C_);C_.ShowInfo(Field);break;
                            case'D':I_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(I_,D_);D_.ShowInfo(Field);break;
                            case'E':I_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(I_,E_);E_.ShowInfo(Field);break;
                            case'F':I_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(I_,F_);F_.ShowInfo(Field);break;
                            case'G':I_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(I_,G_);G_.ShowInfo(Field);break;
                            case'H':I_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(I_,H_);H_.ShowInfo(Field);break;
                            case'A':I_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(I_,A_);A_.ShowInfo(Field);break;
                            case'J':I_.Attacking(J_);J_.PassiveSkill();
                                Field.CalculatePath(I_,J_);J_.ShowInfo(Field);break;
                        }
                        I_.ShowInfo(Field);
                        break;
                    }
                    case 'J':{
                        switch (pa){
                            case'B':J_.Attacking(B_);B_.PassiveSkill();
                                Field.CalculatePath(J_,B_);B_.ShowInfo(Field);break;
                            case'C':J_.Attacking(C_);C_.PassiveSkill();
                                Field.CalculatePath(J_,C_);C_.ShowInfo(Field);break;
                            case'D':J_.Attacking(D_);D_.PassiveSkill();
                                Field.CalculatePath(J_,D_);D_.ShowInfo(Field);break;
                            case'E':J_.Attacking(E_);E_.PassiveSkill();
                                Field.CalculatePath(J_,E_);E_.ShowInfo(Field);break;
                            case'F':J_.Attacking(F_);F_.PassiveSkill();
                                Field.CalculatePath(J_,F_);F_.ShowInfo(Field);break;
                            case'G':J_.Attacking(G_);G_.PassiveSkill();
                                Field.CalculatePath(J_,G_);G_.ShowInfo(Field);break;
                            case'H':J_.Attacking(H_);H_.PassiveSkill();
                                Field.CalculatePath(J_,H_);H_.ShowInfo(Field);break;
                            case'I':J_.Attacking(I_);I_.PassiveSkill();
                                Field.CalculatePath(J_,I_);I_.ShowInfo(Field);break;
                            case'A':J_.Attacking(A_);A_.PassiveSkill();
                                Field.CalculatePath(J_,A_);A_.ShowInfo(Field);break;
                        }
                        J_.ShowInfo(Field);
                        break;
                    }
                }
                LOGGER.debug("You let hero "+ac+" attack hero "+pa);
            }
            //-------------------------------------------------------------------------
            else if(option.equals("ACTIVE"))
            {
                char ac=sc.next().charAt(0);
                char pa=sc.next().charAt(0);
                switch (ac){
                    case 'A':{
                        switch (pa){
                            case'B':A_.ActiveSkill(B_);Field.CalculatePath(A_,B_);
                            B_.ShowInfo(Field);break;
                            case'C':A_.ActiveSkill(C_);Field.CalculatePath(A_,C_);
                            C_.ShowInfo(Field);break;
                            case'D':A_.ActiveSkill(D_);Field.CalculatePath(A_,D_);
                            D_.ShowInfo(Field);break;
                            case'E':A_.ActiveSkill(E_);Field.CalculatePath(A_,E_);
                            E_.ShowInfo(Field);break;
                            case'F':A_.ActiveSkill(F_);Field.CalculatePath(A_,F_);
                            F_.ShowInfo(Field);break;
                            case'G':A_.ActiveSkill(G_);Field.CalculatePath(A_,G_);
                            G_.ShowInfo(Field);break;
                            case'H':A_.ActiveSkill(H_);Field.CalculatePath(A_,H_);
                            H_.ShowInfo(Field);break;
                            case'I':A_.ActiveSkill(I_);Field.CalculatePath(A_,I_);
                            I_.ShowInfo(Field);break;
                            case'J':A_.ActiveSkill(J_);Field.CalculatePath(A_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        A_.ShowInfo(Field);
                        break;
                    }
                    case 'B':{
                        switch (pa){
                            case'A':B_.ActiveSkill(A_);Field.CalculatePath(B_,A_);
                            A_.ShowInfo(Field);break;
                            case'C':B_.ActiveSkill(C_);Field.CalculatePath(B_,C_);
                            C_.ShowInfo(Field);break;
                            case'D':B_.ActiveSkill(D_);Field.CalculatePath(B_,D_);
                            D_.ShowInfo(Field);break;
                            case'E':B_.ActiveSkill(E_);Field.CalculatePath(B_,E_);
                            E_.ShowInfo(Field);break;
                            case'F':B_.ActiveSkill(F_);Field.CalculatePath(B_,F_);
                            F_.ShowInfo(Field);break;
                            case'G':B_.ActiveSkill(G_);Field.CalculatePath(B_,G_);
                            G_.ShowInfo(Field);break;
                            case'H':B_.ActiveSkill(H_);Field.CalculatePath(B_,H_);
                            H_.ShowInfo(Field);break;
                            case'I':B_.ActiveSkill(I_);Field.CalculatePath(B_,I_);
                            I_.ShowInfo(Field);break;
                            case'J':B_.ActiveSkill(J_);Field.CalculatePath(B_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        B_.ShowInfo(Field);
                        break;
                    }
                    case 'C':{
                        switch (pa){
                            case'B':C_.ActiveSkill(B_);Field.CalculatePath(C_,B_);
                            B_.ShowInfo(Field);break;
                            case'A':C_.ActiveSkill(A_);Field.CalculatePath(C_,A_);
                            A_.ShowInfo(Field);break;
                            case'D':C_.ActiveSkill(D_);Field.CalculatePath(C_,D_);
                            D_.ShowInfo(Field);break;
                            case'E':C_.ActiveSkill(E_);Field.CalculatePath(C_,E_);
                            E_.ShowInfo(Field);break;
                            case'F':C_.ActiveSkill(F_);Field.CalculatePath(C_,F_);
                            F_.ShowInfo(Field);break;
                            case'G':C_.ActiveSkill(G_);Field.CalculatePath(C_,G_);
                            G_.ShowInfo(Field);break;
                            case'H':C_.ActiveSkill(H_);Field.CalculatePath(C_,H_);
                            H_.ShowInfo(Field);break;
                            case'I':C_.ActiveSkill(I_);Field.CalculatePath(C_,I_);
                            I_.ShowInfo(Field);break;
                            case'J':C_.ActiveSkill(J_);Field.CalculatePath(C_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        C_.ShowInfo(Field);
                        break;
                    }
                    case 'D':{
                        switch (pa){
                            case'B':D_.ActiveSkill(B_);Field.CalculatePath(D_,B_);
                            B_.ShowInfo(Field);break;
                            case'C':D_.ActiveSkill(C_);Field.CalculatePath(D_,C_);
                            C_.ShowInfo(Field);break;
                            case'A':D_.ActiveSkill(A_);Field.CalculatePath(D_,A_);
                            A_.ShowInfo(Field);break;
                            case'E':D_.ActiveSkill(E_);Field.CalculatePath(D_,E_);
                            E_.ShowInfo(Field);break;
                            case'F':D_.ActiveSkill(F_);Field.CalculatePath(D_,F_);
                            F_.ShowInfo(Field);break;
                            case'G':D_.ActiveSkill(G_);Field.CalculatePath(D_,G_);
                            G_.ShowInfo(Field);break;
                            case'H':D_.ActiveSkill(H_);Field.CalculatePath(D_,H_);
                            H_.ShowInfo(Field);break;
                            case'I':D_.ActiveSkill(I_);Field.CalculatePath(D_,I_);
                            I_.ShowInfo(Field);break;
                            case'J':D_.ActiveSkill(J_);Field.CalculatePath(D_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        D_.ShowInfo(Field);
                        break;
                    }
                    case 'E':{
                        switch (pa){
                            case'B':E_.ActiveSkill(B_);Field.CalculatePath(E_,B_);
                            B_.ShowInfo(Field);break;
                            case'C':E_.ActiveSkill(C_);Field.CalculatePath(E_,C_);
                            C_.ShowInfo(Field);break;
                            case'D':E_.ActiveSkill(D_);Field.CalculatePath(E_,D_);
                            D_.ShowInfo(Field);break;
                            case'A':E_.ActiveSkill(A_);Field.CalculatePath(E_,A_);
                            A_.ShowInfo(Field);break;
                            case'F':E_.ActiveSkill(F_);Field.CalculatePath(E_,F_);
                            F_.ShowInfo(Field);break;
                            case'G':E_.ActiveSkill(G_);Field.CalculatePath(E_,G_);
                            G_.ShowInfo(Field);break;
                            case'H':E_.ActiveSkill(H_);Field.CalculatePath(E_,H_);
                            H_.ShowInfo(Field);break;
                            case'I':E_.ActiveSkill(I_);Field.CalculatePath(E_,I_);
                            I_.ShowInfo(Field);break;
                            case'J':E_.ActiveSkill(J_);Field.CalculatePath(E_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        E_.ShowInfo(Field);
                        break;
                    }
                    case 'F':{
                        switch (pa){
                            case'B':F_.ActiveSkill(B_);Field.CalculatePath(F_,B_);
                            B_.ShowInfo(Field);break;
                            case'C':F_.ActiveSkill(C_);Field.CalculatePath(F_,C_);
                            C_.ShowInfo(Field);break;
                            case'D':F_.ActiveSkill(D_);Field.CalculatePath(F_,D_);
                            D_.ShowInfo(Field);break;
                            case'E':F_.ActiveSkill(E_);Field.CalculatePath(F_,E_);
                            E_.ShowInfo(Field);break;
                            case'A':F_.ActiveSkill(A_);Field.CalculatePath(F_,A_);
                            A_.ShowInfo(Field);break;
                            case'G':F_.ActiveSkill(G_);Field.CalculatePath(F_,G_);
                            G_.ShowInfo(Field);break;
                            case'H':F_.ActiveSkill(H_);Field.CalculatePath(F_,H_);
                            H_.ShowInfo(Field);break;
                            case'I':F_.ActiveSkill(I_);Field.CalculatePath(F_,I_);
                            I_.ShowInfo(Field);break;
                            case'J':F_.ActiveSkill(J_);Field.CalculatePath(F_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        F_.ShowInfo(Field);
                        break;
                    }
                    case 'G':{
                        switch (pa){
                            case'B':G_.ActiveSkill(B_);Field.CalculatePath(G_,B_);
                            B_.ShowInfo(Field);break;
                            case'C':G_.ActiveSkill(C_);Field.CalculatePath(G_,C_);
                            C_.ShowInfo(Field);break;
                            case'D':G_.ActiveSkill(D_);Field.CalculatePath(G_,D_);
                            D_.ShowInfo(Field);break;
                            case'E':G_.ActiveSkill(E_);Field.CalculatePath(G_,E_);
                            E_.ShowInfo(Field);break;
                            case'F':G_.ActiveSkill(F_);Field.CalculatePath(G_,F_);
                            F_.ShowInfo(Field);break;
                            case'A':G_.ActiveSkill(A_);Field.CalculatePath(G_,A_);
                            A_.ShowInfo(Field);break;
                            case'H':G_.ActiveSkill(H_);Field.CalculatePath(G_,H_);
                            H_.ShowInfo(Field);break;
                            case'I':G_.ActiveSkill(I_);Field.CalculatePath(G_,I_);
                            I_.ShowInfo(Field);break;
                            case'J':G_.ActiveSkill(J_);Field.CalculatePath(G_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        G_.ShowInfo(Field);
                        break;
                    }
                    case 'H':{
                        switch (pa){
                            case'B':H_.ActiveSkill(B_);Field.CalculatePath(H_,B_);
                            B_.ShowInfo(Field);break;
                            case'C':H_.ActiveSkill(C_);Field.CalculatePath(H_,C_);
                            C_.ShowInfo(Field);break;
                            case'D':H_.ActiveSkill(D_);Field.CalculatePath(H_,D_);
                            D_.ShowInfo(Field);break;
                            case'E':H_.ActiveSkill(E_);Field.CalculatePath(H_,E_);
                            E_.ShowInfo(Field);break;
                            case'F':H_.ActiveSkill(F_);Field.CalculatePath(H_,F_);
                            F_.ShowInfo(Field);break;
                            case'G':H_.ActiveSkill(G_);Field.CalculatePath(H_,G_);
                            G_.ShowInfo(Field);break;
                            case'A':H_.ActiveSkill(A_);Field.CalculatePath(H_,H_);
                            A_.ShowInfo(Field);break;
                            case'I':H_.ActiveSkill(I_);Field.CalculatePath(H_,I_);
                            I_.ShowInfo(Field);break;
                            case'J':H_.ActiveSkill(J_);Field.CalculatePath(H_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        H_.ShowInfo(Field);
                        break;
                    }
                    case 'I':{
                        switch (pa){
                            case'B':I_.ActiveSkill(B_);
                            Field.CalculatePath(I_,B_);
                            B_.ShowInfo(Field);break;
                            case'C':I_.ActiveSkill(C_);
                            Field.CalculatePath(I_,C_);
                            C_.ShowInfo(Field);break;
                            case'D':I_.ActiveSkill(D_);Field.CalculatePath(I_,D_);
                            D_.ShowInfo(Field);break;
                            case'E':I_.ActiveSkill(E_);Field.CalculatePath(I_,E_);
                            E_.ShowInfo(Field);break;
                            case'F':I_.ActiveSkill(F_);Field.CalculatePath(I_,F_);
                            F_.ShowInfo(Field);break;
                            case'G':I_.ActiveSkill(G_);Field.CalculatePath(I_,G_);
                            G_.ShowInfo(Field);break;
                            case'H':I_.ActiveSkill(H_);Field.CalculatePath(I_,H_);
                            H_.ShowInfo(Field);break;
                            case'A':I_.ActiveSkill(A_);Field.CalculatePath(I_,A_);
                            A_.ShowInfo(Field);break;
                            case'J':I_.ActiveSkill(J_);Field.CalculatePath(I_,J_);
                            J_.ShowInfo(Field);break;
                        }
                        I_.ShowInfo(Field);
                        break;
                    }
                    case 'J':{
                        switch (pa){
                            case'B':J_.ActiveSkill(B_);Field.CalculatePath(J_,B_);
                            B_.ShowInfo(Field);break;
                            case'C':J_.ActiveSkill(C_);Field.CalculatePath(J_,C_);
                            C_.ShowInfo(Field);break;
                            case'D':J_.ActiveSkill(D_);Field.CalculatePath(J_,D_);
                            D_.ShowInfo(Field);break;
                            case'E':J_.ActiveSkill(E_);Field.CalculatePath(J_,E_);
                            E_.ShowInfo(Field);break;
                            case'F':J_.ActiveSkill(F_);Field.CalculatePath(J_,F_);
                            F_.ShowInfo(Field);break;
                            case'G':J_.ActiveSkill(G_);Field.CalculatePath(J_,G_);
                            G_.ShowInfo(Field);break;
                            case'H':J_.ActiveSkill(H_);Field.CalculatePath(J_,H_);
                            H_.ShowInfo(Field);break;
                            case'I':J_.ActiveSkill(I_);Field.CalculatePath(J_,I_);
                            I_.ShowInfo(Field);break;
                            case'A':J_.ActiveSkill(A_);Field.CalculatePath(J_,A_);
                            A_.ShowInfo(Field);break;
                        }
                        J_.ShowInfo(Field);
                        break;
                    }
                }
                Mainset.PrintInfo();
                LOGGER.debug("You let hero "+ac+" launch active skill to attack hero "+pa);
            }
            else if(option.equals("CHECKMATE")) {
                char be=sc.next().charAt(0);
                switch(be){
                    case'A':pixel.BeAttcked(A_,Field);pixel.EndOfGame(Mainset);break;
                    case'B':pixel.BeAttcked(B_,Field);pixel.EndOfGame(Mainset);break;
                    case'C':pixel.BeAttcked(C_,Field);pixel.EndOfGame(Mainset);break;
                    case'D':pixel.BeAttcked(D_,Field);pixel.EndOfGame(Mainset);break;
                    case'E':pixel.BeAttcked(E_,Field);pixel.EndOfGame(Mainset);break;
                    case'F':pixel.BeAttcked(F_,Field);pixel.EndOfGame(Mainset);break;
                    case'G':pixel.BeAttcked(G_,Field);pixel.EndOfGame(Mainset);break;
                    case'H':pixel.BeAttcked(H_,Field);pixel.EndOfGame(Mainset);break;
                    case'I':pixel.BeAttcked(I_,Field);pixel.EndOfGame(Mainset);break;
                    case'J':pixel.BeAttcked(J_,Field);pixel.EndOfGame(Mainset);break;
                }
                LOGGER.debug("You let hero "+be+" attack Chrystal tower.");
            }
            else if(option.equals("SKILL")){
                char ch=sc.next().charAt(0);
                switch(ch){
                    case'A':A_.SpecialSkill(Field);break;
                    case'B':B_.SpecialSkill(Field);break;
                    case'C':C_.SpecialSkill(Field);break;
                    case'D':D_.SpecialSkill(Field);break;
                    case'E':E_.SpecialSkill(Field);break;
                    case'F':F_.SpecialSkill(Field);break;
                    case'G':G_.SpecialSkill(Field);break;
                    case'H':H_.SpecialSkill(Field);break;
                    case'I':I_.SpecialSkill(Field);break;
                    case'J':J_.SpecialSkill(Field);break;
                }
                LOGGER.debug("You let hero "+ch+" launch special skill.");
            }
        }
    }
}
