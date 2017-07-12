package homework4;


public class LinkedStack<T> {
	
	
	private int size;
	private Location top;
	
	public LinkedStack(){
		this.size = 0;
		this.top = null;
	}
	
	public void push(Location loc){
		if(top == null)
			top = loc;
		else{
			loc.next = top;
			top = loc;
		}
		size++;
	}
	
	public Location pop(){
		if(isEmpty())
			throw new java.util.NoSuchElementException("pop() : list empty");
		Location exLocation = top;
		top = top.next;
		size--;
		return exLocation;
	}
	
	public Location peek() {
		if(isEmpty())
			throw new java.util.NoSuchElementException("pop() : list empty");
		return top;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void clear(){
		size = 0;
		top = null;
	}
	
	public void printDistance(){
		System.out.print("Distance: "+(size-1)+"     ");
		printDistance(top);
		System.out.println();
	}
	
	public void printDistance(Location loc){
		if(loc != null){
			printDistance(loc.next);
			if(loc.next != null)
				System.out.print(", ");
			System.out.print("("+(loc.getRow()+1)+","+(loc.getCol()+1)+")");
			
		}
	}
	
		
}
