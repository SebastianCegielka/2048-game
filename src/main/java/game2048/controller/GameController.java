package game2048.controller;

import game2048.model.Board;

public class GameController {
    private Board gameBoard;

    public GameController() {
        gameBoard = new Board();
    }

    public GameController(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void runGame() {
        Menu menu = new Menu();

        gameBoard.generateNewTile();
        gameBoard.generateNewTile();
        gameBoard.printGameBoard();

        while (gameBoard.isThereAPossibleMove()) {
            gameBoard.move(menu.userInput());
            gameBoard.generateNewTile();
            gameBoard.printGameBoard();
        }
        System.out.println("Game finished with score: " + gameBoard.getScore());
    }

}
