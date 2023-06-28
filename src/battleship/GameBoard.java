package battleship;

import java.util.Arrays;

public class GameBoard {
    private final char sea = '~';
    private final int boardLength;
    private char[][] board;
    public GameBoard(int boardLength){
        this.boardLength = boardLength;
    }
    // Creates 10x10 board of empty sea
    public void createGameBoard(){
        board = new char[boardLength][boardLength];

        Arrays.stream(board).forEach(row -> Arrays.fill(row, sea));
    }
    // Updates board with user's hit or miss
    public void updateBoard(int[] guess, char targetUpdate){
        int row = guess[0];
        int column = guess[1];
        board[row][column] = targetUpdate;
    }
    // Prints out board, hides all ships
    public void printBoard(char hit, char miss) {
    	// Prints X axis
        System.out.print("  ");
        for (int i = 0 ; i < boardLength ; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int row = 0; row < boardLength; row++) {
        	// Prints Y axis
            System.out.print(row + " ");
            // Prints board and hides ships
            for (int column = 0; column < boardLength; column ++) {
                char position = board[row][column];
                if (position != sea && position != hit && position != miss) {
                    System.out.print(sea + " ");
                }
                else {
                    System.out.print(position + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    // Getters
    public char getSea() { return sea; }
    public int getBoardLength() { return boardLength; }
    public char[][] getBoard() { return board; }
}