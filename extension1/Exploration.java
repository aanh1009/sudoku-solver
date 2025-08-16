/**
 * Tuan Anh Ngo
 * Exploration.java: Similar to Sudoku.java, also a class for solving a Sudoku game, but in a human-inspired approach (starting from cells with the fewest viable options)
 */
import java.util.ArrayList;
import java.util.Random;
public class Exploration {
    //initialize the instance fields
    private Board board;
    private LandscapeDisplay ld;
    private int step;

    Random ran = new Random();

    /**
     * constructor for an exploration with a random number of locked values
     */
    public Exploration(){
        board = new Board(ran.nextInt(0,82));
        ld = new LandscapeDisplay(board);
        step = 0;
    }

    /**
     * constructor for an exploration object with a specified number of locked values
     * @param numLocked the number of locked values
     */
    public Exploration(int numLocked){
        board = new Board(numLocked);
        ld = new LandscapeDisplay(board);
        step = 0;
    }

    /**
     * constructor for an exploration object on a pre-written board
     * @param filename the name of the file containing the board
     */
    public Exploration(String filename){
        board = new Board(filename);
        ld = new LandscapeDisplay(board);
        step = 0;
    }

    /**
     * get the number of locked values
     * @return the number of locked values
     */
    public int countLocked(){
        return board.numLocked();
    }

    /**
     * find the next valiud value for a cell
     * @param cell the cell
     * @return the next valid value
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
     * find the cell with the fewest options left
     * @return the number of options of the cell with the fewest options
     */
    public int findLeastOptions(){
        int numLeast = Integer.MAX_VALUE;
        for (int i = 0; i<9; ++i){
            for (int j = 0; j<9; j++){
                if (board.get(i,j).getValue()==0){
                    int numOptions = board.validValues(board.get(i,j)).size();
                    numLeast = Math.min(numOptions, numLeast);
                }
            }
        }
        return numLeast;
    }
    
    /**
     * find the next cell to process
     * @return the next cell
     */
    public Cell findNextCell(){
        Cell toReturn = null;
        boolean found = false;
        for (int r = 0; r<9; r++){
            for (int c = 0; c<9; c++){
                ArrayList<Integer> values = board.validValues(board.get(r, c));
                if (board.get(r, c).getValue()==0 && values.size() == findLeastOptions()){
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
     * solve the sudoku
     * @param delay the speed of solving
     * @return whether the game has been solved
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
     * create a string presentation of the exploration Sudoku
     * @return string presentation of the exploration Sudoku
     */
    public String toString(){
        return board.toString();
    }
    public static void main(String[] args) throws InterruptedException {
        //Run simulation with prewritten boards
        //Exploration newGame = new Exploration("board2.txt");
        //System.out.println(newGame.toString());
        //newGame.solve(2);
        //System.out.println(newGame.toString());

        //Run the simulation with random boards
        Exploration newGame = new Exploration(39);
        System.out.println(newGame.countLocked());
        System.out.println(newGame.toString());
        newGame.solve(2);
        System.out.println(newGame.toString());
    }
}
