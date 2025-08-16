/**
 * Tuan Anh Ngo
 * Cell.java: the class for Cell with associated getters and setters for its position, value, and locked status
 */
import java.awt.Color;
import java.awt.Graphics;
public class Cell {
    //initialize the instance fields
    private int rowIndex;
    private int colIndex;
    private int value;
    private boolean isLocked;

    /**
     * constructor for the cell object
     * @param row the row where the cell is located
     * @param col the column where the cell is located
     * @param value the cell's value
     */
    public Cell(int row, int col, int value){
        this(row,col,value,false);
    }

    /**
     * constructor for the cell object
     * @param row the row where the cell is located
     * @param col the column where the cell is located
     * @param value the cell's value
     * @param locked the locked status of the cell
     */
    public Cell(int row, int col, int value, boolean locked){
        this.rowIndex = row;
        this.colIndex = col;
        this.value = value;
        this.isLocked = locked;
    }

    /**
     * get the row the cell is located
     * @return the row the cell is located
     */
    public int getRow(){
        return rowIndex;
    }

    /**
     * get the column the cell is located
     * @return the column the cell is located
     */
    public int getCol(){
        return colIndex;
    }

    /**
     * get the value of the cell
     * @return value of the cell
     */
    public int getValue(){
        return this.value;
    }

    /**
     * set a new value for the cell
     * @param newval the cell's new value
     */
    public void setValue(int newval){
        value = newval;
    }

    /**
     * get the locked status of the cell
     * @return whether the cell is locked
     */
    public boolean isLocked(){
        return this.isLocked;
    }

    /**
     * set a new locked status for the cell
     * @param lock the locked status of the cell
     */
    public void setLocked(boolean lock){
        isLocked = lock;
    }

    /**
     * create a string presentation of the cell
     * @return the string presentation of the cell
     */
    public String toString(){
        return "" + getValue();
    }

    /**
     * draw the cell 
     * @param g the graphics object to draw the cell
     * @param x the horizontal position of the cell
     * @param y the vertical position of the cell
     * @param scale the drawing's scale
     */
    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }
}
