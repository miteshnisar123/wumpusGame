/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.javamumbaigroup.cell;

/**
 *
 * @author MahiRaj Gosemath
 */
public class Position {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object){
        if(object instanceof Position){
            Position position=(Position)object;
            if(this.getX()==position.getX()&&this.getY()==position.getY()){
                return true;
            }
        }
        return false;
    }

}
