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
import java.util.Random;

/**
 *
 * @author MahiRaj Gosemath
 */

public class GameBoard {
    
    private static GameBoard board=new GameBoard();
    private ArrayList<Cell> cells;
    private ArrayList<Cell> neighbours;
    private int width=4;
    private int heigth=4;
    Random random=new Random();
    
    public static GameBoard getBoard() {
        return board;
    }

    public int getWidth() {
        return width;
    }

    public GameBoard setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeigth() {
        return heigth;
    }

    public GameBoard setHeigth(int heigth) {
        this.heigth = heigth;
        return this;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public GameBoard initGameBoard() {
        cells=new ArrayList();
        for(int i=0;i<width;i++) {
            for(int j=0;j<heigth;j++) {               
                cells.add(new Cell(i,j));
                cells.get(cells.size()-1).setCellStatus(CellContent.NONE);
            }
        }
        addWumpus();
        addPit();
        addGold();
        return this;
    }
    
    public void addWumpus() {
        Cell cell = cells.get(random.nextInt(64));
        if(!(cell.getCellContent().contains(CellContent.PIT)&&(cell.getCellContent().contains(CellContent.GLITTER)))){
            if(cell.getCellContent().contains(CellContent.NONE)){
                    cell.getCellContent().remove(CellContent.NONE);
            }
            cell.setCellStatus(CellContent.WUMPUS);
            addSignalToNeighbours(cell, CellContent.STENCH);
        }
    }
    
    public void addPit() {
        Cell cell = cells.get(random.nextInt(64));
        if(!(cell.getCellContent().contains(CellContent.WUMPUS)&&(cell.getCellContent().contains(CellContent.GLITTER)))){
            if(cell.getCellContent().contains(CellContent.NONE)){
                    cell.getCellContent().remove(CellContent.NONE);
            }
            cell.setCellStatus(CellContent.PIT);
            addSignalToNeighbours(cell, CellContent.BREEZE);
        }
    }
    
    public void addGold() {
        Cell cell = cells.get(random.nextInt(64));
        if(!(cell.getCellContent().contains(CellContent.PIT)&&(cell.getCellContent().contains(CellContent.WUMPUS)))){
            if(cell.getCellContent().contains(CellContent.NONE)){
                 cell.getCellContent().remove(CellContent.NONE);
            }
            cell.setCellStatus(CellContent.GOLD);
            addSignalToNeighbours(cell, CellContent.GLITTER);
        }
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
//
//    public ArrayList<Cell> getCellGrid() {
//        return cells;
//    }

    @SuppressWarnings("unchecked")
	private void addSignalToNeighbours(Cell cell, CellContent content) {
        neighbours=cell.getNeighbours();
        for(Cell neighbourCell : neighbours){
            if(neighbourCell!=null){
                if(neighbourCell.getCellContent().contains(CellContent.NONE)){
                    neighbourCell.getCellContent().remove(CellContent.NONE);
                }
                if(!neighbourCell.getCellContent().contains(content)){
                    neighbourCell.getCellContent().add(content);
                }
            }
        }
    }
    
}
