package Hero;
/**
 * </p> class battlefield <br>
 * @see character
 * @see Pixel
 * @see Main
 */
public abstract class MAP{
    public char Effect;
}
interface 
class battlefield {
    public char[][] field = new char[11][11];
    /**
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
        field[2][4]='@';field[8][4]='#';
        field[2][6]='@';field[8][6]='#';
        for(int i=2;i<=4;i++) {
            field[i][2]='$';field[i][8]='$';
        }
        for(int i=6;i<=8;i++) {
            field[i][2]='%';field[i][8]='%';
        }
        field[3][3]='$';field[3][7]='$';
        field[7][3]='%';field[7][7]='%';
    }
    /**
     * @return void
     * Print the battle field map
     */
    public void PrintField(){
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++)
                System.out.print(field[i][j]+" ");
            System.out.println();
        }
    }
}
//-------------------------------------------------------


