/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.javamumbaigroup.cell;

/**
 *
 * @author bansidhai
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author MahiRaj Gosemath
 */
public class Cell {
    
    private ArrayList<CellContent> content;
    private ArrayList<Cell> neighbours;
    private Position position;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Cell(int x, int y)
    {
        position=new Position();
        position.setX(x);
        position.setY(y);
        content=new ArrayList();
        neighbours=new ArrayList();
    }
    
    public void setCellStatus(CellContent cellContent)
    {
        content.add(cellContent);
    }
    
    @SuppressWarnings("rawtypes")
	public ArrayList getCellContent()
    {
        return content;
    }
    
    @SuppressWarnings("rawtypes")
	public ArrayList getNeighbours(){
        neighbours.add(getNeighbour(0, 1));
        neighbours.add(getNeighbour(0, -1));
        neighbours.add(getNeighbour(1, 0));
        neighbours.add(getNeighbour(-1, 0));
        return neighbours;
    }
    
    public Cell getNeighbour(int offsetX, int offsetY){
        Cell cell=getCellAtPosition(this.position.getX()+offsetX, this.position.getY()+offsetY);
        return cell;
    }
    
    public Cell getCellAtPosition(int x, int y){
        GameBoard gameBoard=GameBoard.getBoard();
        for (Iterator<Cell> it = gameBoard.getCells().iterator(); it.hasNext();) {
            Cell cell = it.next();
            if(cell.position.getX()==x){
                if(cell.position.getY()==y){
                    return cell;
                }
            }
        }
        return null;
    }

    public Position getPosition() {
        return this.position;
    }
    
}
