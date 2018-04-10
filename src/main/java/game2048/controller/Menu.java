package game2048.controller;


import game2048.model.Board;
import game2048.model.Move;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    Move userInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which direction to move? \n up : down : left : right");
        String decision = sc.nextLine();
        switch (decision.toLowerCase()) {
            case "up":
                return Move.UP;
            case "down":
                return Move.DOWN;
            case "left":
                return Move.LEFT;
            case "right":
                return Move.RIGHT;
            default:
                throw new IllegalArgumentException("That's not a proper direction");
        }
    }

    public Move playerAI() {
        Random random = new Random();
        int decision = random.nextInt(4);
        switch (decision) {
            case 0:
                return Move.UP;
            case 1:
                return Move.DOWN;
            case 2:
                return Move.LEFT;
            case 3:
                return Move.RIGHT;
        }
        return null;
    }
}


