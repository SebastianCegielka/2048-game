import game2048.controller.GameController;
import game2048.model.Board;
import game2048.model.Move;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

public class GameTest {

    @Test
    public void ifMovedToTheRightSideNumbersShouldBeOnTheRightSide() {
        Board testBoard = new Board();
        testBoard.addToBoard(2, 2, 2);
        testBoard.addToBoard(2, 0, 2);
        testBoard.addToBoard(3, 1, 2);
        testBoard.addToBoard(0, 3, 4);

        testBoard.move(Move.RIGHT);

        Board resultBoard = new Board();
        resultBoard.addToBoard(0, 3, 4);
        resultBoard.addToBoard(2, 3, 4);
        resultBoard.addToBoard(3, 3, 2);

        assertThat(testBoard.testToString())
                .isEqualToIgnoringCase(resultBoard.testToString());
    }

    @Test
    public void ifMovedToTheLeftSideNumbersShouldBeOnTheLeftSide() {
        Board testBoard = new Board();
        testBoard.addToBoard(2, 2, 2);
        testBoard.addToBoard(2, 0, 2);
        testBoard.addToBoard(3, 1, 2);
        testBoard.addToBoard(0, 3, 4);

        testBoard.move(Move.LEFT);

        Board resultBoard = new Board();
        resultBoard.addToBoard(0, 0, 4);
        resultBoard.addToBoard(2, 0, 4);
        resultBoard.addToBoard(3, 0, 2);

        assertThat(testBoard.testToString())
                .isEqualToIgnoringCase(resultBoard.testToString());
    }

    @Test
    public void ifMovedToTheBottomNumbersShouldBeOnTheBottom() {
        Board testBoard = new Board();
        testBoard.addToBoard(2, 2, 2);
        testBoard.addToBoard(2, 0, 2);
        testBoard.addToBoard(3, 1, 2);
        testBoard.addToBoard(0, 3, 4);

        testBoard.move(Move.DOWN);

        Board resultBoard = new Board();
        resultBoard.addToBoard(3, 0, 2);
        resultBoard.addToBoard(3, 1, 2);
        resultBoard.addToBoard(3, 2, 2);
        resultBoard.addToBoard(3, 3, 4);

        assertThat(testBoard.testToString())
                .isEqualToIgnoringCase(resultBoard.testToString());
    }

    @Test
    public void ifMovedToTheTopNumbersShouldBeOnTheTop() {
        Board testBoard = new Board();
        testBoard.addToBoard(2, 2, 2);
        testBoard.addToBoard(2, 0, 2);
        testBoard.addToBoard(3, 1, 2);
        testBoard.addToBoard(0, 3, 4);

        testBoard.move(Move.UP);

        Board resultBoard = new Board();
        resultBoard.addToBoard(0, 0, 2);
        resultBoard.addToBoard(0, 1, 2);
        resultBoard.addToBoard(0, 2, 2);
        resultBoard.addToBoard(0, 3, 4);

        assertThat(testBoard.testToString())
                .isEqualToIgnoringCase(resultBoard.testToString());
    }

    @Test
    public void ifMovedShouldntMergeNewValueWithSameOldValueInOneMove() {
        Board testBoard = new Board();
        testBoard.addToBoard(0, 0, 2);
        testBoard.addToBoard(0, 1, 2);
        testBoard.addToBoard(0, 2, 4);
        testBoard.move(Move.LEFT);

        assertThat(testBoard.getValue(0, 0)).isEqualTo(4);
        assertThat(testBoard.getValue(0, 1)).isEqualTo(4);
        assertThat(testBoard.getValue(0, 2)).isEqualTo(0);
    }

    @Test
    public void shouldCreateOneTileIfGenerateNewTileMethodCalled() {
        Board testBoard = new Board();
        testBoard.generateNewTile();
        int count = 0;
        for (int i = 0; i < testBoard.getSize(); i++) {
            for (int j = 0; j < testBoard.getSize(); j++) {
                if (testBoard.getValue(i, j) != 0) {
                    count++;
                }
            }
        }
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void shouldStopTheGameWhenNotASingleMoveIsPossible() {
        Board testBoard = new Board();
        testBoard.addToBoard(0, 0, 2);
        testBoard.addToBoard(0, 1, 4);
        testBoard.addToBoard(0, 2, 2);
        testBoard.addToBoard(0, 3, 4);
        testBoard.addToBoard(1, 0, 4);
        testBoard.addToBoard(1, 1, 2);
        testBoard.addToBoard(1, 2, 4);
        testBoard.addToBoard(1, 3, 2);
        testBoard.addToBoard(2, 0, 2);
        testBoard.addToBoard(2, 1, 4);
        testBoard.addToBoard(2, 2, 2);
        testBoard.addToBoard(2, 3, 4);
        testBoard.addToBoard(3, 0, 4);
        testBoard.addToBoard(3, 1, 2);
        testBoard.addToBoard(3, 2, 4);
        testBoard.addToBoard(3, 3, 2);

        assertThat(testBoard.isThereAPossibleMove()).isEqualTo(false);
    }

    @Test
    public void shouldntAddANewTileWhenBoardHasNoEmptyTile() {
        Board testBoard = new Board();
        testBoard.addToBoard(0, 0, 2);
        testBoard.addToBoard(0, 1, 4);
        testBoard.addToBoard(0, 2, 2);
        testBoard.addToBoard(0, 3, 4);
        testBoard.addToBoard(1, 0, 4);
        testBoard.addToBoard(1, 1, 2);
        testBoard.addToBoard(1, 2, 4);
        testBoard.addToBoard(1, 3, 2);
        testBoard.addToBoard(2, 0, 2);
        testBoard.addToBoard(2, 1, 4);
        testBoard.addToBoard(2, 2, 2);
        testBoard.addToBoard(2, 3, 4);
        testBoard.addToBoard(3, 0, 4);
        testBoard.addToBoard(3, 1, 2);
        testBoard.addToBoard(3, 2, 4);
        testBoard.addToBoard(3, 3, 2);

        assertThat(testBoard.generateNewTile()).isEqualTo(false);
    }

    @Test
    public void ifNoPossibleMoveGameShouldStop() {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Board testBoard = new Board();
        testBoard.addToBoard(0, 0, 2);
        testBoard.addToBoard(0, 1, 4);
        testBoard.addToBoard(0, 2, 2);
        testBoard.addToBoard(0, 3, 4);
        testBoard.addToBoard(1, 0, 4);
        testBoard.addToBoard(1, 1, 2);
        testBoard.addToBoard(1, 2, 4);
        testBoard.addToBoard(1, 3, 2);
        testBoard.addToBoard(2, 0, 2);
        testBoard.addToBoard(2, 1, 4);
        testBoard.addToBoard(2, 2, 2);
        testBoard.addToBoard(2, 3, 4);
        testBoard.addToBoard(3, 0, 4);
        testBoard.addToBoard(3, 1, 2);
        testBoard.addToBoard(3, 2, 4);
        testBoard.addToBoard(3, 3, 2);
        GameController testPilot = new GameController(testBoard);

        testPilot.runGame();
        assertThat(outputStream.toString()).contains("Game finished with score:");
    }
}