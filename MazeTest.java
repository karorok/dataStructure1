package homework4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MazeTest {
	public static void main(String[] args) throws IOException{
		Scanner inFile = new Scanner(new File("maze4.txt"));
		int mazeRow = inFile.nextInt();
		int mazeCol = inFile.nextInt();
		char[][] maze = new char[mazeRow][mazeCol];
		int lineRowNumber = 0;
		String garbage = inFile.nextLine();
		Location startLoc=null;
		
		while(lineRowNumber<mazeRow){
			String line = inFile.nextLine();
			for(int i=0;i<mazeCol;i++){
				maze[lineRowNumber][i] = line.charAt(i);
				if(line.charAt(i)=='S')
					startLoc = new Location(lineRowNumber,i);
			}
			lineRowNumber++;
		}
		printMaze(maze,mazeRow,mazeCol);
		findExit(maze,startLoc.getRow(),startLoc.getCol());
	}
	
	public static void printMaze(char[][] maze,int row,int col){
		for(int i=0; i<row;i++){
			for(int j=0;j<col;j++){
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void findExit(char[][] maze,int row, int col){
		LinkedStack<Location> stack = new LinkedStack<Location>();								//경로에 사용할 스택
		int[] length_path = new int[2];															//짧은 길이와 경로들을 위한 배열
		System.out.println("Number of paths: "+findExit(stack,maze,row,col,length_path));
		System.out.println("Shortest Length of paths: "+length_path[0]);
		
	}
	
	public static int findExit(LinkedStack<Location> s,char[][] maze,int row,int col,int[] length_path){
		char[][] cMaze = copyMaze(maze);
		if(row>=0 && col >= 0 && row <maze.length && col<maze[0].length){
			Location newLoc = new Location(row,col);
			if(maze[row][col] == 'E'){
				s.push(newLoc);
				//s.printDistance();														//스택에 저장된 경로들 출력
				//printMaze(maze,maze.length,maze[0].length);								//경로가 표시된 메이즈 출력
				s.pop();
				if(length_path[0] == 0)
					length_path[0] = s.size();												//처음 찾은 탈출경로의 길이
				else
					if(length_path[0]>s.size())
						length_path[0] = s.size();										//짧은 탈출경로의 길이를 배열에 저장
				length_path[1]++;
				return 1;
			}
			if(maze[row][col] !='#' && maze[row][col] !='·'){
				s.push(newLoc);															//벽이나 지나간 경로가 아니면 스택에 푸쉬
				if(maze[row][col] != 'S')
					cMaze[row][col] = '·';
				int path = findExit(s,cMaze,row,col+1,length_path)+findExit(s,cMaze,row+1,col,length_path)
				+findExit(s,cMaze,row,col-1,length_path) + findExit(s,cMaze,row-1,col,length_path);
				s.pop();																//벽에 막히거나 혹은 또 다른 경로를 찾기 위해
				return path;
			}
		}
		return 0;
	}
	
	public static char[][] copyMaze(char[][] maze){			//경로 표시를 위한 메이즈 복사
		int row = maze.length;
		int col = maze[0].length;
		char[][] cMaze = new char[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				cMaze[i][j] = maze[i][j];
		return cMaze;
	}
	
}
