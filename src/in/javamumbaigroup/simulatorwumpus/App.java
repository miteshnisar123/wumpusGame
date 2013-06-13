/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.javamumbaigroup.simulatorwumpus;

import in.javamumbaigroup.cell.Cell;
import in.javamumbaigroup.cell.GameBoard;
import in.javamumbaigroup.player.Player;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author MahiRaj Gosemath
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        GameBoard gameBoard=GameBoard.getBoard();
        ArrayList<Cell> cellGrid=gameBoard.setWidth(8)
                                .setHeigth(8)
                                .initGameBoard()
                                .getCells();
        new Player(cellGrid.get(0)).play();
    }
}
