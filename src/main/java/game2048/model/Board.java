package game2048.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {

    private static int size = 4;
    private int[][] gameBoard = new int[size][size];
    private int score;

    public Board() {
    }

    public void clear(){
        for (int i = 0; i < gameBoard[0].length ; i++) {
            Arrays.fill(gameBoard[i], 0);
        }
    }

    public boolean generateNewTile() {
        if (!(hasEmptyTile())) return false;

        Random random = new Random();

        while (true) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);

            if (gameBoard[x][y] == 0) {
                gameBoard[x][y] = getNewRandomTileValue();
                return true;
            }
        }
    }

    private boolean hasEmptyTile() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameBoard[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getNewRandomTileValue(){
        Random random = new Random();
        int rng = random.nextInt(100);
        if(rng>90){
            return 4;
        } else
            return 2;
    }

    public void move(Move move){
        for (int column = 0; column < size; column++) {
            List<Integer> moveTile = new ArrayList<>();
            if (move == Move.UP) {
                for (int row = 0; row < size; row++) {
                    addToMoveTile(row, column, moveTile);
                }
                mergeUpLeft(moveTile);

                cleaningUpMoveTile(moveTile);

                for (int row = 0; row < size; row++) {
                    gameBoard[row][column] = moveTile.get(row);
                }
            }
            if (move == Move.DOWN) {
                for (int row = size -1; row >= 0; row--) {
                    addToMoveTile(row, column, moveTile);
                }
                mergeDownRight(moveTile);

                cleaningUpMoveTile(moveTile);

                for (int row = 0; row < size; row++) {
                    gameBoard[row][column] = moveTile.get(moveTile.size() -1 - row);
                }
            }
            if (move == Move.LEFT) {
                for (int row = 0; row < size; row++) {
                    addToMoveTile(column, row, moveTile);
                }
                mergeUpLeft(moveTile);
                cleaningUpMoveTile(moveTile);
                for (int row = 0; row < size; row++) {
                    gameBoard[column][row] = moveTile.get(row);
                }
            }
            if (move == Move.RIGHT) {
                for (int row = size -1; row >= 0; row--) {
                    addToMoveTile(column, row, moveTile);
                }
                mergeDownRight(moveTile);
                cleaningUpMoveTile(moveTile);

                for (int row = 0; row < size; row++) {
                    gameBoard[column][row] = moveTile.get(moveTile.size() -1 - row);
                }
            }
        }
    }

    private void mergeUpLeft(List<Integer> moveTile) {
        int tileMultiplier = 2;
        for (int x = 0; x < moveTile.size()-1; x++) {
            if(moveTile.get(x).equals(moveTile.get(x+1))){
                moveTile.set(x, tileMultiplier * moveTile.get(x));
                score = score + (tileMultiplier * moveTile.get(x));
                moveTile.set(x+1, 0);
            }
        }
    }

    private void mergeDownRight(List<Integer> moveTile) {
        int tileMultiplier = 2 ;
        for (int x = moveTile.size()-1; x > 0; x--) {
            if(moveTile.get(x).equals(moveTile.get(x-1))){
                moveTile.set(x, tileMultiplier * moveTile.get(x));
                score = score + (tileMultiplier * moveTile.get(x));
                moveTile.set(x-1, 0);
            }
        }
    }

    private void addToMoveTile(int row, int column, List<Integer> moveTile) {
        if(gameBoard[row][column] != 0){
            moveTile.add(gameBoard[row][column]);
        }
    }

    private void cleaningUpMoveTile(List<Integer> moveTile) {
        for (int x = 0; x < moveTile.size(); x++) {
            if(moveTile.get(x) == 0){
                moveTile.remove(x);
            }
        }
        int size = moveTile.size();
        for (int x = 0; x < (Board.size - size); x++) {
            moveTile.add(0);
        }
    }

    public boolean isThereAPossibleMove(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameBoard[i][j] == 0) {
                    return true;
                } if(j < size -1){
                    if(gameBoard[i][j] == gameBoard[i][j+1]){
                        return true;
                    }
                } if (i < size -1){
                    if(gameBoard[i][j] == gameBoard[i+1][j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printGameBoard(){
        System.out.println("Score: " + score);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(gameBoard[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public void addToBoard(int x, int y, int value){
        gameBoard[x][y] = value;
    }

    public String testToString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(gameBoard[i][j]);
            }
        }
        return sb.toString();
    }

    public int getValue(int x, int y){
        return gameBoard[x][y];
    }

    public int getSize(){
        return size;
    }

    public int getScore() {
        return score;
    }
}
