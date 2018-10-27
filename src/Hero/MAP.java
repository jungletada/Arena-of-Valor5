package Hero;
import org.apache.log4j.Logger;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
/**
 * </p> class battlefield <br>
 * @see character
 * @see Pixel
 * @see Main
 */
public abstract class MAP{
    public char effect='^';

}
interface Effect {
    void SetDied(int x,int y);
    void SetField();
    void PrintField()throws AWTException;
    void ShowEffect(character cr, character be)throws AWTException;
    void PrintEffect(char[][]temp)throws AWTException;
    void CalculatePath(character cr,character be)throws AWTException;
}

/**
 * @inheritDoc MAP
 * @see character
 * @see Pixel
 * @see Main
 */
class battlefield extends MAP implements Effect {
    public char[][] field = new char[11][11];
    public char[][] temp = new char[11][11];
    /**
     * @param x
     * @param y
     * @return void
     * Set the dead hero location
     */
    public void SetDied(int x,int y) {
        field[x][y]=' ';
    }
    /**
     * @return void
     * Initial the battle field map
     */
    public void SetField() {
        for(int i=0;i<11;i++)
            for(int j=0;j<11;j++)
                field[i][j]='·';
        field[2][4]='@';field[8][4]='#';
        field[2][6]='@';field[8][6]='#';
        for(int i=2;i<=4;i++) {
            field[i][2]='$';field[i][8]='$';
        }
        for(int i=6;i<=8;i++) {
            field[i][2]='%';field[i][8]='%';
        }
        field[3][3]='+';field[3][7]='+';
        field[7][3]='*';field[7][7]='*';
        field[5][4]='-';field[5][6]='-';
    }
    /**
     * @return void
     * Print the battle field map
     */
    public void Print_Field_single(){
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++)
                System.out.print(field[i][j]+" ");
            System.out.println();
        }
    }

    /**
     * <b>this function will stop program running</b>
     * <b>for 0.45 seconds, then will print the effect trace</b>
     * @throws AWTException
     */
    public void PrintField()throws AWTException{
        int t=3;// print three times
        while(t-->0)
        {
            Print_Field_single();
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_CONTROL);
            r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.delay(150);
        }
        Print_Field_single();
    }

    /**
     * @param cr
     * @param be
     * @return void
     * Show the effect of attacking character
     */
    public void ShowEffect(character cr, character be)throws AWTException{
        char t1=' ',t2=' ',t3=' ',t4=' ';
        cr.ShowInfo(this);
        be.ShowInfo(this);
        if(cr.x-1>=0){
            t1=field[cr.x-1][cr.y];
            field[cr.x-1][cr.y]=effect;
        }
        if(cr.x+1<=10){
            t2=field[cr.x+1][cr.y];
            field[cr.x+1][cr.y]=effect;
        }
        if(cr.y-1>=0){
            t3=field[cr.x][cr.y-1];
            field[cr.x][cr.y-1]=effect;
        }
        if(cr.y+1<=10){
            t4=field[cr.x][cr.y+1];
            field[cr.x][cr.y+1]=effect;
        }
        field[be.x][be.y]='x';
        PrintField();
        //---------------------------------------
        if(cr.x-1>=0){
            field[cr.x-1][cr.y]=t1;
        }
        if(cr.x+1<=10){
            field[cr.x+1][cr.y]=t2;
        }
        if(cr.y-1>=0){
            field[cr.x][cr.y-1]=t3;
        }
        if(cr.y+1<=10){
            field[cr.x][cr.y+1]=t4;
        }
        field[be.x][be.y]=be.name;
        PrintField();
        System.out.println("After Attack:");
    }

    public void PrintTemp(char[][]temp){
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++)
                System.out.print(temp[i][j]+" ");
            System.out.println();
        }
    }
    public void PrintEffect(char[][]temp)throws AWTException{
            PrintTemp(temp);
            try {
                Thread.sleep(400);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }/*
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_CONTROL);
            r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.delay(150);*/
    }

    /**
     * <p>Calculate the path between two characters</p>
     * find the path and show the effect trace
     * @param cr
     * @param be
     * @throws AWTException
     */
    public void CalculatePath(character cr,character be)throws AWTException{
        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 11; j++) {
                temp[i][j]='·';
            }
            int i=cr.x; int j =cr.y;
            //first if
        if(cr.x<=be.x) {
            if(cr.y<=be.y){  //case 1
                while(i<be.x || j<be.y){
                    if(i<be.x){
                            i++; temp[i][j]='O';
                            temp[cr.x][cr.y]=cr.name;
                            temp[be.x][be.y]=be.name;
                            PrintEffect(temp);
                    }
                    if(j<be.y){
                            j++;temp[i][j]='O';
                            temp[cr.x][cr.y]=cr.name;
                            temp[be.x][be.y]=be.name;
                            PrintEffect(temp);
                        }
                    }
                }
                else{  //case 2
                    while(i<be.x || j>be.y){
                        if(i<be.x){
                            i++; temp[i][j]='O';
                            temp[cr.x][cr.y]=cr.name;
                            temp[be.x][be.y]=be.name;
                            PrintEffect(temp);
                        }
                        if(j>be.y){
                            j--;temp[i][j]='O';
                            temp[cr.x][cr.y]=cr.name;
                            temp[be.x][be.y]=be.name;
                            PrintEffect(temp);
                        }
                    }
                }
            PrintTemp(temp);
            }
            //first else
        else{    //case 3
            if(cr.y<=be.y){
                while(i>be.x || j<be.y){
                    if(i>be.x){
                        i--; temp[i][j]='O';
                        temp[cr.x][cr.y]=cr.name;
                        temp[be.x][be.y]=be.name;
                        PrintEffect(temp);
                    }
                    if(j<be.y){
                        j++;temp[i][j]='O';
                        temp[cr.x][cr.y]=cr.name;
                        temp[be.x][be.y]=be.name;
                        PrintEffect(temp);
                    }
                }
            }
            else{  //case 4
                while(i>be.x || j>be.y){
                    if(i>be.x){
                        i--; temp[i][j]='O';
                        temp[cr.x][cr.y]=cr.name;
                        temp[be.x][be.y]=be.name;
                        PrintEffect(temp);
                    }
                    if(j>be.y){
                        j--;temp[i][j]='O';
                        temp[cr.x][cr.y]=cr.name;
                        temp[be.x][be.y]=be.name;
                        PrintEffect(temp);
                    }
                }
            }
            PrintTemp(temp);
        }
        Print_Field_single();
    }
}