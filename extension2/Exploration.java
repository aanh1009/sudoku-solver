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
    private int size;

    Random ran = new Random();

    /**
     * constructor for an exploration with a random number of locked values
     * @param size the size of the board
     */
    public Exploration(int size){
        this.size = size;
        board = new Board(size, ran.nextInt(0,82));
        ld = new LandscapeDisplay(board);
        step = 0;
    }

    /**
     * constructor for an exploration object with a specified number of locked values
     * @param size the size of the board
     * @param numLocked the number of locked values
     */
    public Exploration(int size, int numLocked){
        this.size = size;
        board = new Board(size, numLocked);
        ld = new LandscapeDisplay(board);
        step = 0;
    }

    /**
     * constructor for an exploration object on a pre-written board
     * @param size the size of the board
     * @param filename the name of the file containing the board
     */
    public Exploration(int size, String filename){
        this.size = size;
        board = new Board(size, filename);
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
        while (val <= size){
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
        for (int i = 0; i<size; ++i){
            for (int j = 0; j<size; j++){
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
        for (int r = 0; r<size; r++){
            for (int c = 0; c<size; c++){
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
        while (newStack.size()<size*size - board.numLocked()){
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
        
        board.setFinished(true);   
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
        if (args.length!=2){
            System.out.println(
                """
                    In order to run the simulation on a Board with tailored sizing using the human-inspired approach, compile the Exploration class and run it with 2 arguments in the Terminal, including:
                        - The first argument specifying the size of the board
                        - The second argument specifying the number of locked values on the board.
                    For exmaple, one can run a simulation on a 16x16 board with 40 locked values using these commands:
                        - javac Exploration.java
                        - java Exploration 16 40
                    """);
        }
        else{
        //Run the simulation with random boards
        Exploration newGame = new Exploration(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.println(newGame.countLocked());
        System.out.println(newGame.toString());
        newGame.solve(2);
        System.out.println(newGame.toString());
        }
    }
}
