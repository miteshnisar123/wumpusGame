/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.javamumbaigroup.player;

import in.javamumbaigroup.cell.Cell;
import in.javamumbaigroup.cell.CellContent;
import in.javamumbaigroup.cell.GameBoard;
import in.javamumbaigroup.cell.Position;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author bansidhai
 */
public class Player {
    
    private GameBoard gameBoard;
    private Cell currentCell;
    private int score=0;

    public Cell getCurrentCell() {
        return currentCell;
    }
    
    public Player(Cell cell){
        this.currentCell=cell;
        gameBoard=GameBoard.getBoard();
    }
    
    
    public boolean isMoveAllowed(Position position){
        int x=currentCell.getPosition().getX(); int offsetX=position.getX();
        int y=currentCell.getPosition().getY(); int offsetY=position.getY();
        if(x+offsetX<0||x+offsetX>=gameBoard.getWidth()){
            return false;
        }
        if(y+offsetY<0||y+offsetY>=gameBoard.getHeigth()){
            return false;
        }
        return true;
    }
    
    public void movePlayer(Position position){
        if(isMoveAllowed(position)){
            int moveX=currentCell.getPosition().getX()+position.getX();
            int moveY=currentCell.getPosition().getY()+position.getY();
            this.currentCell=currentCell.getCellAtPosition(moveX, moveY);
            System.out.println("Your current position "+moveX+" "+moveY);
            System.out.println("Current position contains "+currentCell.getCellContent());
            if(currentCell.getCellContent().contains(CellContent.NONE)){
                System.out.println("Nothing found. Score decreased by 1");
                score=score+1;
            }
            if(currentCell.getCellContent().contains(CellContent.BREEZE)){
                System.out.println("Beware PIT is nearby!!!");
                score=score+1;
            }
            if(currentCell.getCellContent().contains(CellContent.STENCH)){
                System.out.println("Beware WUMPUS is nearby!!!");
                score=score+1;
            }
            if(currentCell.getCellContent().contains(CellContent.GLITTER)){
                System.out.println("GOLD is nearby!!!");
                score=score+1;
            }
            if(currentCell.getCellContent().contains(CellContent.PIT)){
                System.out.println("You have fallen into PIT. 10 points deducted from your score.");
                score=score-10;
            }
            if(currentCell.getCellContent().contains(CellContent.WUMPUS)){
                System.out.println("You are eaten by the wumpus.");
                System.exit(0);
            }
            if(currentCell.getCellContent().contains(CellContent.GOLD)){
                System.out.println("You won.");
                score=score+100;
            }
            System.out.println("Your score "+score);
        }else{
            System.out.println("Move not allowed");
        }
    }
    
    public void play() throws IOException{
        System.out.println("Use a, d, w, s to move LEFT, RIGHT, UP and DOWN");
        System.out.println("You'll get +1 for every move you survive. -10 for falling into PIT. 100 for finding GOLD.");
        Position position=new Position();
        String command;
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        while(!(command=input.readLine()).equals("stop")){
            if(command.equals("a")){
                position.setX(0);
                position.setY(-1);
                movePlayer(position);
            }
            if(command.equals("s")){
                position.setX(1);
                position.setY(0);
                movePlayer(position);
            }
            if(command.equals("w")){
                position.setX(-1);
                position.setY(0);
                movePlayer(position);
            }
            if(command.equals("d")){
                position.setX(0);
                position.setY(1);
                movePlayer(position);
            }
        }
    }
    
}
