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
		LinkedStack<Location> stack = new LinkedStack<Location>();								//��ο� ����� ����
		int[] length_path = new int[2];															//ª�� ���̿� ��ε��� ���� �迭
		System.out.println("Number of paths: "+findExit(stack,maze,row,col,length_path));
		System.out.println("Shortest Length of paths: "+length_path[0]);
		
	}
	
	public static int findExit(LinkedStack<Location> s,char[][] maze,int row,int col,int[] length_path){
		char[][] cMaze = copyMaze(maze);
		if(row>=0 && col >= 0 && row <maze.length && col<maze[0].length){
			Location newLoc = new Location(row,col);
			if(maze[row][col] == 'E'){
				s.push(newLoc);
				//s.printDistance();														//���ÿ� ����� ��ε� ���
				//printMaze(maze,maze.length,maze[0].length);								//��ΰ� ǥ�õ� ������ ���
				s.pop();
				if(length_path[0] == 0)
					length_path[0] = s.size();												//ó�� ã�� Ż������ ����
				else
					if(length_path[0]>s.size())
						length_path[0] = s.size();										//ª�� Ż������ ���̸� �迭�� ����
				length_path[1]++;
				return 1;
			}
			if(maze[row][col] !='#' && maze[row][col] !='��'){
				s.push(newLoc);															//���̳� ������ ��ΰ� �ƴϸ� ���ÿ� Ǫ��
				if(maze[row][col] != 'S')
					cMaze[row][col] = '��';
				int path = findExit(s,cMaze,row,col+1,length_path)+findExit(s,cMaze,row+1,col,length_path)
				+findExit(s,cMaze,row,col-1,length_path) + findExit(s,cMaze,row-1,col,length_path);
				s.pop();																//���� �����ų� Ȥ�� �� �ٸ� ��θ� ã�� ����
				return path;
			}
		}
		return 0;
	}
	
	public static char[][] copyMaze(char[][] maze){			//��� ǥ�ø� ���� ������ ����
		int row = maze.length;
		int col = maze[0].length;
		char[][] cMaze = new char[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				cMaze[i][j] = maze[i][j];
		return cMaze;
	}
	
}
