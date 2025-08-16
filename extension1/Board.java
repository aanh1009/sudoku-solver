/**
 * Tuan Anh Ngo
 * Board.java: a class for Board, which consists of 2D grid of cells with methods to access specific cells and check the validity of the sudoku grid
 */
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class Board {
    //initialize the class's instance fields
    private Cell[][] sudokuBoard;
    private int numLocked;
    private int numRows;
    private int numCols;
    private boolean finished;

    /**
     * constructor for a board object with all cells of value 0
     */
    public Board(){
        numRows = 9;
        numCols = 9;
        finished = false;
        sudokuBoard = new Cell[numRows][numCols];
        numLocked = 0;
        for (int i = 0; i<numRows;++i){
            for (int j =0; j<numCols;++j){
                sudokuBoard[i][j] = new Cell(i,j,0);
            }
        }
    }

    /**
     * constructor for the board object using a pre-written board
     * @param filename the name of the file containing the pre-written board
     */
    public Board(String filename){
        this();
        read(filename);
    }

    /**
     * constructor for the board object with a specified number of locked values
     * @param locked the number of locked values on the board
     */
    public Board(int locked){
        this();
        Random ran = new Random();
        while (numLocked<locked){
            int row = ran.nextInt(0,9);
            int col = ran.nextInt(0,9);
            if (sudokuBoard[row][col].getValue()==0 && !sudokuBoard[row][col].isLocked()){
                while (!validValue(row, col, sudokuBoard[row][col].getValue())){
                    sudokuBoard[row][col].setValue(ran.nextInt(1,10));
                }
            }
            set(row,col,true);
        }
    }

    /**
     * get the cell at a specified location
     * @param row the horizontal location of the cell
     * @param col the vertical location of the cell
     * @return the cell at the specified location
     */
    public Cell get(int row, int col){
        return sudokuBoard[row][col];
    }

    /**
     * get the value of the cell at a specified location
     * @param r the row of the cell
     * @param c the column of the cell
     * @return the value of the cell
     */
    public int value(int r, int c){
        return sudokuBoard[r][c].getValue();
    }

    /**
     * set a new value for a specified cell
     * @param row the row of the cell
     * @param col the column of the cell
     * @param value the cell's new value
     */
    public void set(int row, int col, int value){
        sudokuBoard[row][col].setValue(value);
    }

    /**
     * set a new locked status for a specified cell
     * @param row the row of the cell
     * @param col the column of the cell
     * @param locked the cell's new locked status
     */
    public void set(int row, int col, boolean locked){
        if(sudokuBoard[row][col].isLocked()==false & locked == true){
            numLocked +=1;
            sudokuBoard[row][col].setLocked(locked);
        }
    }

    /**
     * set a new value and locked status for a specified cell
     * @param r the cell's row
     * @param c the cell's column
     * @param value the cell's new value
     * @param locked the cell's new locked status
     */
    public void set(int r, int c, int value, boolean locked){
        set(r,c,value);
        set(r,c,locked);
    }

    /**
     * get the number of locked values
     * @return the number of locked values
     */
    public int numLocked(){
        return numLocked;
    }

    /**
     * set finished state for the board
     * @param isFinished the finished state of the board
     */
    public void setFinished(boolean isFinished){
        finished = isFinished;
    }

    /**
     * read a board in a file
     * @param filename the name of the file
     * @return whether the file has been read
     */
    public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
      FileReader fr = new FileReader(filename);
      // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
      BufferedReader br = new BufferedReader(fr);
      
      // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
      String line = br.readLine();
      // start a while loop that loops while line isn't null
      int row = 0;
      while(line != null){
          // assign to an array of Strings the result of splitting the line up by spaces (line.split("[ ]+"))
          String[] arr = line.split( "[ ]+" );
          // use the line to set various Cells of this Board accordingly
	      // TODO THIS IS WHAT NEEDS TO BE FILLED IN!
          for (int col=0; col<numCols;++col){
                sudokuBoard[row][col].setValue(Integer.parseInt(arr[col]));
                if (Integer.parseInt(arr[col])!=0){
                    set(row,col,true);
                }
          }
          // assign to line the result of calling the readLine method of your BufferedReader object.
          line = br.readLine();
          row+=1;
      }
      // call the close method of the BufferedReader
      br.close();
      return true;
    }
    catch(FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename );
    }
    catch(IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  /**
   * create a string presentation of the board
   * @return string presentation of the board
   */
  public String toString(){
    String output = "";
    for (int i =0; i<numRows;++i){
        for (int j = 0; j<numCols;++j){
            output += sudokuBoard[i][j] + " ";
        }
        output += "\n";
    }
    return output;
  }

  /**
   * check if the value of a specified cell is valid or not
   * @param row the cell's row
   * @param col the cell's column
   * @param value the cell's value
   * @return whether the cell's value is valid
   */
  public boolean validValue(int row, int col, int value){
    if (value<1 || value>9){
        return false;
    }
    for (int r = 0; r<numRows; r++){
        if (r!= row){
            if (sudokuBoard[r][col].getValue() == value){
                return false;
            }
        }
    }
    for (int c = 0; c<numCols; c++){
        if (c!= col){
            if (sudokuBoard[row][c].getValue() == value){
                return false;
            }
        }
    }
    int localRow = (row/3)*3;
    int localCol = (col/3)*3;
    for (int i=localRow; i<localRow + 3; ++i){ 
        for (int j=localCol; j<localCol + 3; ++j){ 
            if (i!=row && j!=col){
                if (sudokuBoard[i][j].getValue() == value){
                    return false;
                }
            }
        }
    }
    return true;
  }

  /**
   * check if the current board is a valid solution or not 
   * @return whether the current board is a valid solution
   */
  public boolean validSolution(){
    for(int r = 0; r<numRows; r++){
        for (int c = 0; c<numCols; c++){
            int cellValue  = sudokuBoard[r][c].getValue();
            if(cellValue ==0 || !validValue(r,c,cellValue)){
                return false;
            }
        }
    }
    return true;
  }

  /**
   * count the number of valid values at a cell on the board
   * @param cell the cell
   * @return the cell's number of valid values
   */
  public ArrayList<Integer> validValues(Cell cell){
    ArrayList<Integer> validValues = new ArrayList<>();
    for (int i = 1; i<10;++i){
        if (validValue(cell.getRow(), cell.getCol(), i)){
            validValues.add(i);
        }
    }
    return validValues;
  }

  /**
   * get the board's number of columns
   * @return number of columns
   */
  public int getCols(){
    return numCols;
  }

  /**
   * get the board's number of rows
   * @return number of rows
   */
  public int getRows(){
    return numRows;
  }
  public static void main(String[] args) {
      Board newGame = new Board();
      newGame.read(args[0]);
      System.out.println(newGame.validSolution());
  }
  public void draw(Graphics g, int scale){
    for(int i = 0; i<getRows(); i++){
        for(int j = 0; j<getCols(); j++){
            get(i, j).draw(g, j*scale+5, i*scale+10, scale);
        }
    } if(finished){
        if(validSolution()){
            g.setColor(new Color(0, 127, 0));
            g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
        } else {
            g.setColor(new Color(127, 0, 0));
            g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
        }
    }
}
}
