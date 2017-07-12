package homework4;

public class Location{
	private int row;
	private int col;
	Location next;
	
	public Location(int row, int col){
		this.row = row;
		this.col = col;
		this.next = null;
	}
	
	public int getRow(){
		return this.row;
	}
	public int getCol(){
		return this.col;
	}
}