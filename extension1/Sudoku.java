/**
 * Tuan Anh Ngo
 * Sudoku.java: a class to create a complete Sudoku game and solve it in a chronological order (going to every cell one by one and find its valid value to fill in)
 */
import java.util.Random;
public class Sudoku {
    //initialize the instance fields
    private Board board;
    private LandscapeDisplay ld;
    private int step;

    Random ran = new Random();

    /**
     * constructor for a sudoku game with a random number of locked values
     */
    public Sudoku(){
        step = 0;
        board = new Board(ran.nextInt(0,82));
        ld = new LandscapeDisplay(board);
    }

    /**
     * constructor for a sudoku object with a specified number of locked values
     * @param numLocked number of locked values
     */
    public Sudoku(int numLocked){
        step = 0;
        board = new Board(numLocked);
        ld = new LandscapeDisplay(board);
    }

    /**
     * constructor to create a sudoku object from a pre-written board file
     * @param filename the name of the file containing the board
     */
    public Sudoku(String filename){
        step = 0;
        board = new Board(filename);
        ld = new LandscapeDisplay(board);
    }

    /**
     * find the next valid value for a cell
     * @param cell the cell
     * @return the next valid value for the cell
     */
    public int findNextValue(Cell cell){
        int val = cell.getValue() + 1;
        while (val <= 9){
            if (board.validValue(cell.getRow(), cell.getCol(), val)){
                board.set(cell.getRow(), cell.getCol(), val);
                return val;
            }
            val++;
        }
        board.set(cell.getRow(), cell.getCol(), 0); 
        return 0;
    }
    
    /**
     * find the next cell whose value is 0 (unsolved)
     * @return the next cell to be processed
     */
    public Cell findNextCell(){
        Cell toReturn = null;
        boolean found = false;
        for (int r = 0; r<9; r++){
            for (int c = 0; c<9; c++){
                if (board.get(r, c).getValue()==0){
                    found = true;
                    board.set(r, c, findNextValue(board.get(r, c)));
                    toReturn = board.get(r, c);
                    if (toReturn.getValue()!=0){
                        return toReturn;
                    }
                    break;
                }
            }
            if (found){
                break;
            }
        }
        return null;
    }   
    
    /**
     * solve the sudoku game
     * @param delay the speed of solving the game
     * @return whether the game is solved
     * @throws InterruptedException
     */
    public boolean solve(int delay) throws InterruptedException{
        Stack<Cell> newStack = new LinkedList<>();
        while (newStack.size()<81 - board.numLocked() && step<100){
            if (delay > 0){
                Thread.sleep(delay);
                if (ld != null)
                ld.repaint();
            }
            Cell next = findNextCell();
            while (next==null && newStack.size()>0){
                Cell lastCell = newStack.pop();
                lastCell.setValue(findNextValue(lastCell));
                if (board.get(lastCell.getRow(),lastCell.getCol()).getValue()!=0){
                    next = lastCell;
                }
            }
            if (next==null){
                step+=1;
                ld.repaint();
                System.out.println(step);
                System.out.println("unsolvable");
                return false;
            }
            else{
                newStack.push(next);
            }
            step+=1;
        }
        if (step==100){
            System.out.println("timeout");
            return false;
        }
        board.setFinished(true);   
        System.out.println(step);
        return true;
    }

    /**
     * create a string presentation of the game
     * @return string presentation of the game
     */
    public String toString(){
        return board.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        Sudoku newGame = new Sudoku("board3.txt");
        System.out.println(newGame.toString());
        newGame.solve(1);
        System.out.println(newGame.toString());
    }
}