/**
 * Tuan Anh Ngo
 * CellTests.java: a class to test the functionality of the Cell class
 */
public class CellTests{
    public static void main(String[] args){
        //test the constructor and getters
        {
            Cell newCell = new Cell(5,7, 2);
            System.out.println("Row: " + newCell.getRow() + "Column:" + newCell.getCol() + "Value:" + newCell.getValue());
            assert newCell.getRow() == 5: "Error in Cell::Cell() or Cell::getRow()";
            assert newCell.getCol() == 7: "Error in Cell::Cell() or Cell::getCol()";
            assert newCell.getValue() == 2: "Error in Cell::Cell() or Cell::getValue()";

            Cell newCell2 = new Cell(4,3, 6);
            System.out.println("Row: " + newCell.getRow() + "Column:" + newCell.getCol() + "Value:" + newCell.getValue());
            assert newCell.getRow() == 4: "Error in Cell::Cell() or Cell::getRow()";
            assert newCell.getCol() == 3: "Error in Cell::Cell() or Cell::getCol()";
            assert newCell.getValue() == 6: "Error in Cell::Cell() or Cell::getValue()";
        }

        //test the setters
        {
            Cell newCell = new Cell(5,7, 2);
            System.out.println("locked?:" + newCell.isLocked());
            assert newCell.isLocked() == false: "Error in Cell::Cell() or Cell::isLocked()";
            newCell.setLocked(true);
            System.out.println("locked?:" + newCell.isLocked());
            assert newCell.isLocked() == true: "Error in Cell::setLocked() or Cell::isLocked()";
            System.out.println("value:" + newCell.getValue());
            newCell.setValue(8);
            System.out.println("value:" + newCell.getValue());
            assert newCell.getValue() == 8: "Error in Cell::setValue() or Cell::getValue()";
        }
        
        System.out.println("Done testing cells!");
    }
}