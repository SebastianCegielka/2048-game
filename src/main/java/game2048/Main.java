package game2048;

import game2048.controller.GameController;

public class Main {

    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.runGame();
    }
}
