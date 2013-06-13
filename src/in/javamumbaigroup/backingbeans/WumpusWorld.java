package in.javamumbaigroup.backingbeans;



import java.util.ArrayList;
import java.util.Random;

import javax.faces.event.ValueChangeEvent;

public class WumpusWorld 
{
	private ArrayList<WumpusWorldVO> wwList=new  ArrayList<WumpusWorldVO>();
	private String no;
	private int boardSize;
	private int totalBoardSize;
	private int[] neighbours=new int[4];
	  Random random=new Random();
	  private int playerPosition;
	  private ArrayList<String> message=new ArrayList<String>();
	  private ArrayList<String> displayMessage=new ArrayList<String>();
	  private boolean gameOver;
	  private int totalPoints;
	  private String levelValue;
	  private boolean showLevel;
	  private String helpText="<ol><li> You can use arrow keys or A,W,S and D key to travel LEFT,TOP,DOWN and RIGHT respectively</li><li> You will come across PIT,WUMPUS and GOLD.</li><li> When a cell has PIT, then in neighbouring cell you will feel BREEZE.</li><li> When a cell has WUMPUS, then in neighbouring cell you will feel STENCH.</li><li>  When a cell has GOLD,then in neighbouring cell you will feel GLITTER.</li><li>  If you fall into PIT,-10 will be deducted.</li><li>  If you get GOLD, +100 will be added.</li><li>  If cell has nothing, then +1 will be added.</li><li>  If you come across WUMPUS, Game Over Boss!!!.</li><li>  Try to score maximum point.</li></ol>";
	  

	  
	public WumpusWorld()
	{
		levelValue="4";
		boardSize=Integer.parseInt(levelValue);
		message=new ArrayList<String>();
		displayMessage=new ArrayList<String>();
		message.add("Game Started!!!!....All the Best.");
		displayMessage.add("Game Started!!!!....All the Best.");
		playerPosition=1;
		totalPoints=0;
		totalBoardSize=boardSize*boardSize;
		showLevel=true;
		for(int i=0;i<totalBoardSize;i++)
		{
			if(i==0)
			{
			wwList.add(new WumpusWorldVO("images/rounded.png", "images/circle.png",true,i+1,false,false,false,false,false,false));
			}
			else
				wwList.add(new WumpusWorldVO("images/rounded.png", "images/rounded.png",false,i+1,false,false,false,false,false,false));
		}
		addWumpus();
		addPit();
		addGold();
	}
	public String startNewGame()
	{
		boardSize=Integer.parseInt(levelValue);
		message=new ArrayList<String>();
		displayMessage=new ArrayList<String>();
		gameOver=false;
		wwList=new ArrayList<WumpusWorldVO>();
		message.add("Game Started!!!!....All the Best.");
		displayMessage.add("Game Started!!!!....All the Best.");
		playerPosition=1;
		totalPoints=0;
		totalBoardSize=boardSize*boardSize;
		showLevel=true;
		for(int i=0;i<totalBoardSize;i++)
		{
			if(i==0)
			{
			wwList.add(new WumpusWorldVO("images/rounded.png", "images/circle.png",true,i+1,false,false,false,false,false,false));
			}
			else
				wwList.add(new WumpusWorldVO("images/rounded.png", "images/rounded.png",false,i+1,false,false,false,false,false,false));
		}
		addWumpus();
		addPit();
		addGold();
		return null;
	}
	public void addWumpus()
	{
		int randNum;

		for(int i=0;;i++)
		{
			randNum=random.nextInt(totalBoardSize);
			if(randNum>(boardSize*2))
				break;
		}
		
		findNeighbour(randNum);
		
		for(WumpusWorldVO w:wwList)
		{
			if(w.getBoardNo()==randNum)
				w.setWumpus(true);
		}
		for(WumpusWorldVO w:wwList)
		{
			for(int i=0;i<4;i++)
			{
				if(neighbours[i]==w.getBoardNo())
					w.setStench(true);
			}
		}
		
		
		
	}
	public boolean isCorrectPit(int numb)
	{
		boolean value=false;
		
		for(WumpusWorldVO w:wwList)
		{
			if(w.getBoardNo()==numb)
			{
				if(!w.isWumpus())
				{
					value=true;
					break;
				}
			}
		}
		 return value;
		
	}
	public boolean isCorrectGold(int numb)
	{
		boolean value=false;
		
		for(WumpusWorldVO w:wwList)
		{
			if(w.getBoardNo()==numb)
			{
				if(!w.isPit())
				{
					value=true;
					break;
				}
			}
		}
		 return value;
		
	}
	public void addPit()
	{
		int randNum;

		for(int i=0;;i++)
		{
			randNum=random.nextInt(totalBoardSize);
			if(randNum>(boardSize*2) && isCorrectPit(randNum))
				break;
		}
		
		findNeighbour(randNum);
		
		for(WumpusWorldVO w:wwList)
		{
			if(w.getBoardNo()==randNum)
				w.setPit(true);
		}
		for(WumpusWorldVO w:wwList)
		{
			for(int i=0;i<4;i++)
			{
				if(neighbours[i]==w.getBoardNo())
					w.setBreeze(true);
			}
		}
		
		
		
	}
	public void addGold()
	{
		int randNum;

		for(int i=0;;i++)
		{
			randNum=random.nextInt(totalBoardSize);
			if(randNum>(boardSize*2) && isCorrectPit(randNum) && isCorrectGold(randNum))
				break;
		}
		
		findNeighbour(randNum);
		
		for(WumpusWorldVO w:wwList)
		{
			if(w.getBoardNo()==randNum)
				w.setGold(true);
		}
		for(WumpusWorldVO w:wwList)
		{
			for(int i=0;i<4;i++)
			{
				if(neighbours[i]==w.getBoardNo())
					w.setGlitter(true);
			}
		}
		
		
	}
	public int[] findNeighbour(int no)
	{
		for(int i=0;i<4;i++)
			neighbours[i]=0;
		
		if(((no-1)%boardSize)!=0)
			neighbours[0]=no-1;
		if(((no+1)%boardSize)!=1)
			neighbours[1]=no+1;
		if((no+boardSize)<(totalBoardSize))
			neighbours[2]=no+boardSize;
		if((no-boardSize)>0)
			neighbours[3]=no-boardSize;
		for(int i=0,j=0;i<4;i++)
		{
			if(neighbours[i]!=0)
				neighbours[j++]=neighbours[i];
		}
		return neighbours;
	}
	public void calculatePoints(String action,int currPoint)
	{
		int a=0;
		String s="";
		for(WumpusWorldVO w:wwList)
		{
			if(w.getBoardNo()==currPoint)
			{
				
	            if(w.isBreeze()){
	                s=action+" : Beware PIT is nearby!!!";
	                totalPoints=totalPoints+1;
	                a=1;
	            }
	            if(w.isStench()){
	            	if(a==1)
	            		s=s+" and Beware WUMPUS is nearby!!!";
	            	else
	            	{
	            		s=action+" : Beware WUMPUS is nearby!!!";
	            		totalPoints=totalPoints+1;
	            	}
	            	 a=1;
	            }
	            if(w.isGlitter()){
	            	if(a==1)
	            		s=s+" and GOLD is nearby!!!";
	            	else
	            	{
		            	s=action+" : GOLD is nearby!!!";
		                totalPoints=totalPoints+1;
	            	}
	                a=1;
	            }
	            if(w.isPit()){
	            	if(a==1)
	            		s=s+" and You have fallen into PIT. 10 points deducted from your score.";
	            	else
	            	{
	            		s=action+" : You have fallen into PIT. 10 points deducted from your score.";
	            		totalPoints=totalPoints-10;
	            	}
	                a=1;
	            }
	            if(w.isWumpus()){
	            	if(a==1)
	            		s="";
	            	s=action+" : You are eaten by the wumpus. Game Over!!!!";
	                gameOver=true;
	                showLevel=false;
	                a=1;
	                
	            }
	            if(w.isGold()){
	            	if(a==1)
	            		s="";
	            	s=action+" : You found Gold. You won.";
	                totalPoints=totalPoints+100;
	                showLevel=false;
	                gameOver=true;
	                a=1;
	            }
	            if(a==0)
	            {
	            	s=action+" : Nothing found. Score increased by 1.";
	                totalPoints=totalPoints+1;
	            }
	            message.add(s);
	           
			}
		}
	}
	public ArrayList<String> reverse(ArrayList<String> message)
	{
		ArrayList<String> mess=new ArrayList<String>();
		for(int i=message.size()-1;i>=0;i--)
		{
			mess.add(message.get(i));
			System.out.println(i+" : "+message.get(i));
			
		}
		return mess;
	}
	public String clickedA()
	{
		if(!gameOver)
		{
			if((playerPosition-1)%boardSize==0)
				message.add("LEFT : Bump.No such move!!!");
			else 
			{
				playerPosition=playerPosition-1;
				 calculatePoints("LEFT",playerPosition);
				for(WumpusWorldVO w:wwList)
				{
					if(w.getBoardNo()==playerPosition)
					{
						w.setPlayerImage("images/circle.png");
						w.setShowPlayer(true);
					}
					else
					{
						w.setShowPlayer(false);
						w.setPlayerImage("images/rounded.png");
					}
				}
				
			}
			 displayMessage=reverse(message);
		}
		return null;
	}
	public String clickedW()
	{
		if(!gameOver)
		{
			if((playerPosition-boardSize)<1)
				message.add("UP : Bump.No such move!!!");
			else 
			{
				playerPosition=playerPosition-boardSize;
				 calculatePoints("UP",playerPosition);
				for(WumpusWorldVO w:wwList)
				{
					if(w.getBoardNo()==playerPosition)
					{
						w.setPlayerImage("images/circle.png");
						w.setShowPlayer(true);
					}
					else
					{
						w.setPlayerImage("images/rounded.png");
						w.setShowPlayer(false);
					}
				}
				
			}
			 displayMessage=reverse(message);
		}
		return null;
	}
	public String clickedS()
	{
		if(!gameOver)
		{

			if((playerPosition+boardSize)>totalBoardSize)
				message.add("DOWN : Bump.No such move!!!");
			else 
			{
				playerPosition=playerPosition+boardSize;
				 calculatePoints("DOWN",playerPosition);
				for(WumpusWorldVO w:wwList)
				{
					if(w.getBoardNo()==playerPosition)
					{
						w.setPlayerImage("images/circle.png");
						w.setShowPlayer(true);
					}
					else
					{
						w.setPlayerImage("images/rounded.png");
						w.setShowPlayer(false);
					}
				}
				
			}
			 displayMessage=reverse(message);
		}
		return null;
	}
	public String clickedD()
	{
		if(!gameOver)
		{
			if((playerPosition+1)%boardSize==1)
				message.add("RIGHT : Bump.No such move!!!");
			else 
			{
				playerPosition=playerPosition+1;
				calculatePoints("RIGHT",playerPosition);
				for(WumpusWorldVO w:wwList)
				{
					if(w.getBoardNo()==playerPosition)
					{
						w.setPlayerImage("images/circle.png");
						w.setShowPlayer(true);
					}
					else
					{
						w.setPlayerImage("images/rounded.png");
						w.setShowPlayer(false);
					}
				}
				
			}
			 displayMessage=reverse(message);
		}
		return null;
	}
	public String clickedFire()
	{
		if(!gameOver)
		{
			if((playerPosition+1)%boardSize==1)
				message.add("RIGHT : Bump.No such move!!!");
			else 
			{
				playerPosition=playerPosition+1;
				calculatePoints("RIGHT",playerPosition);
				for(WumpusWorldVO w:wwList)
				{
					if(w.getBoardNo()==playerPosition)
					{
						w.setPlayerImage("images/circle.png");
						w.setShowPlayer(true);
					}
					else
					{
						w.setPlayerImage("images/rounded.png");
						w.setShowPlayer(false);
					}
				}
				
			}
			 displayMessage=reverse(message);
		}
		return null;
	}
	public void levelChanged(ValueChangeEvent e)
	{
		levelValue=(String)e.getNewValue();
		
		
		startNewGame();
		
	}
	public ArrayList<WumpusWorldVO> getWwList() {
		return wwList;
	}
	public void setWwList(ArrayList<WumpusWorldVO> wwList) {
		this.wwList = wwList;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public int[] getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(int[] neighbours) {
		this.neighbours = neighbours;
	}
	public Random getRandom() {
		return random;
	}
	public void setRandom(Random random) {
		this.random = random;
	}
	public int getBoardSize() {
		return boardSize;
	}
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	public int getTotalBoardSize() {
		return totalBoardSize;
	}
	public void setTotalBoardSize(int totalBoardSize) {
		this.totalBoardSize = totalBoardSize;
	}
	public int getPlayerPosition() {
		return playerPosition;
	}
	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}
	public ArrayList<String> getMessage() {
		return message;
	}
	public void setMessage(ArrayList<String> message) {
		this.message = message;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public ArrayList<String> getDisplayMessage() {
		return displayMessage;
	}
	public void setDisplayMessage(ArrayList<String> displayMessage) {
		this.displayMessage = displayMessage;
	}
	public String getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}
	
	public boolean isShowLevel() {
		return showLevel;
	}
	public void setShowLevel(boolean showLevel) {
		this.showLevel = showLevel;
	}
	public String getHelpText() {
		return helpText;
	}
	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

}
