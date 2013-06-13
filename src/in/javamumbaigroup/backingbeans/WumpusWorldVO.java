package in.javamumbaigroup.backingbeans;

public class WumpusWorldVO 
{
	

	private String backgroundImage;
	private String playerImage;
	private boolean showPlayer;
	private int boardNo;
	private boolean isWumpus;
	private boolean isPit;
	private boolean isGold;
	private boolean isStench;
	private boolean  isBreeze;
	private boolean isGlitter;
	
	public String getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	public String getPlayerImage() {
		return playerImage;
	}
	public void setPlayerImage(String playerImage) {
		this.playerImage = playerImage;
	}
	
	
	public WumpusWorldVO(String backgroundImage, String playerImage,
			boolean showPlayer, int boardNo, boolean isWumpus, boolean isPit,
			boolean isGold, boolean isStench, boolean isBreeze,
			boolean isGlitter) {
		super();
		this.backgroundImage = backgroundImage;
		this.playerImage = playerImage;
		this.showPlayer = showPlayer;
		this.boardNo = boardNo;
		this.isWumpus = isWumpus;
		this.isPit = isPit;
		this.isGold = isGold;
		this.isStench = isStench;
		this.isBreeze = isBreeze;
		this.isGlitter = isGlitter;
	}
	public boolean isShowPlayer() {
		return showPlayer;
	}
	public void setShowPlayer(boolean showPlayer) {
		this.showPlayer = showPlayer;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public boolean isWumpus() {
		return isWumpus;
	}
	public void setWumpus(boolean isWumpus) {
		this.isWumpus = isWumpus;
	}
	public boolean isPit() {
		return isPit;
	}
	public void setPit(boolean isPit) {
		this.isPit = isPit;
	}
	public boolean isGold() {
		return isGold;
	}
	public void setGold(boolean isGold) {
		this.isGold = isGold;
	}
	public boolean isStench() {
		return isStench;
	}
	public void setStench(boolean isStench) {
		this.isStench = isStench;
	}
	public boolean isBreeze() {
		return isBreeze;
	}
	public void setBreeze(boolean isBreeze) {
		this.isBreeze = isBreeze;
	}
	public boolean isGlitter() {
		return isGlitter;
	}
	public void setGlitter(boolean isGlitter) {
		this.isGlitter = isGlitter;
	}
	

	

}
