/**
 * Tuan Anh Ngo
 * BoardTests.java: a class to test the functionality of the Board class
 */
public class BoardTests {
    public static void main(String[] args) {
    
        //test the constructors
        {
            Board newBoard1 = new Board();
            System.out.println("number of locked cells:" + newBoard1.numLocked());
            assert newBoard1.numLocked() == 0: "Error in Board::Board() or Board::numLocked()";
            System.out.println("rows: " + newBoard1.getRows() + "cols: " + newBoard1.getCols());
            assert newBoard1.getRows() == 9: "Error in Board::Board() or Board::getRows()";
            assert newBoard1.getCols() == 9: "Error in Board::Board() or Board::getCols()";

            Board newBoard2 = new Board("board1.txt");
            System.out.println("number of locked cells:" + newBoard2.numLocked());
            assert newBoard2.numLocked() == 10: "Error in Board::Board() or Board::numLocked()";
            System.out.println("rows: " + newBoard2.getRows() + "cols: " + newBoard1.getCols());
            assert newBoard2.getRows() == 9: "Error in Board::Board() or Board::getRows()";
            assert newBoard2.getCols() == 9: "Error in Board::Board() or Board::getCols()";

            Board newBoard3 = new Board(8);
            System.out.println("number of locked cells:" + newBoard2.numLocked());
            assert newBoard3.numLocked() == 8: "Error in Board::Board() or Board::numLocked()";
            System.out.println("rows: " + newBoard2.getRows() + "cols: " + newBoard1.getCols());
            assert newBoard3.getRows() == 9: "Error in Board::Board() or Board::getRows()";
            assert newBoard3.getCols() == 9: "Error in Board::Board() or Board::getCols()";
        }

        //test other getters: get value/locked status of cell
        {
            Board newBoard = new Board("board1.txt");
            System.out.println(newBoard);
            Cell cell = newBoard.get(4, 8);
            System.out.println("row: "+ cell.getRow() + " col: " + cell.getCol() + " value: " + cell.getValue() + " locked: " + cell.isLocked());
            assert cell.getRow() == 4: "Error in Board:get(row, col)";
            assert cell.getCol() == 8: "Error in Board:get(row, col)";
            assert cell.getValue() == 7: "Error in Board:get(row,col)";
            assert cell.isLocked() == true: "Error in Board:get(row,col)";

            
            Cell anotherCell = newBoard.get(6,4);
            System.out.println("row: "+ anotherCell.getRow() + " col: " + anotherCell.getCol() + " value: " + anotherCell.getValue() + " locked: " + anotherCell.isLocked());
            assert anotherCell.getRow() == 6: "Error in Board:get(row, col)";
            assert anotherCell.getCol() == 4: "Error in Board:get(row, col)";
            assert anotherCell.getValue() == 5: "Error in Board:get(row,col)";
            assert anotherCell.getValue() == 5: "Error in Board:get(row,col)";
            assert anotherCell.isLocked() == true: "Error in Board:get(row,col)";
        }

        //test the setters: set cell (value/locked/value+locked)
        {
            Board board = new Board("board1.txt");
            Cell cell1 = board.get(1,1);
            System.out.println("locked: " + cell1.isLocked());
            System.out.println("numLocked: " + board.numLocked());
            board.set(1,1,true);
            System.out.println("locked: " + cell1.isLocked());
            System.out.print("numLocked: " + board.numLocked());
            assert cell1.isLocked() == true: "Error in Board::set(row,col,locked)";
            assert board.numLocked() == 11: "Error in Board:: set(row,col,locked)";

            System.out.println("value: " + cell1.getValue());
            board.set(1,1,2);
            System.out.println("value: " + cell1.getValue());
            assert cell1.getValue() == 2: "Error in Board::set(row,col,value)";

            board.set(1,1,3, true);
            assert cell1.getValue() == 3: "Error in Board::set(row,col,value, locked) or Board::set(row,col,value)";
            assert cell1.isLocked() == true: "Error in Board::set(row,col,value,locked) or Board::set(row,col,locked)";
            assert board.numLocked() == 11: "Error in Board:: set(row,col,locked)";
        }

        //test the isValidValue method on a solved board 
        {
            Board board = new Board("solved.txt");
            for (int i = 0; i<9; ++i){
                for (int j =0; j<9; ++j){
                    assert board.validValue(i,j,board.get(i,j).getValue()) == true: "Error in Board::ValidValue(row,col)";
                }
            }
            System.out.println("all cells are valid!");
        }

        //test the isValidValue method on an empty board
        {
            Board board = new Board("board3.txt");
            for (int i = 0; i<9; ++i){
                for (int j =0; j<9; ++j){
                    assert board.validValue(i,j,board.get(i,j).getValue()) == false: "Error in Board::ValidValue(row,col)";
                }
            }
            System.out.println("all cells are invalid!");
        }

        //test the isValidSolution method on a solved board, then try change the values of some cells on it to 0 and check again
        {
            Board board = new Board("solved.txt");
            System.out.println(board);
            assert board.validSolution() == true: "Error in Board::validSolution()";
            board.set(1,3,6);
            board.set(2,5,8);
            board.set(4,6,1);
            board.set(3,7,4);
            System.out.println(board);
            assert board.validSolution() == false: "Error in Board:set(row,col,value) or validSolution()";
        }
        System.out.println("Done testing Board!");
    }
}
