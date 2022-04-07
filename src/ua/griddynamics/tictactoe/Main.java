package ua.griddynamics.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game("_________");
        game.showGrid();

        while (!game.isWinner('X') && !game.isWinner('O') && game.isNotFinished()) {
            inputAndCheckCoordinates(scanner, game, 'X');
            if (game.isWinner('X')) {
                game.showResult();
                break;
            } else if (!game.isNotFinished()) {
                game.showResult();
                break;
            }
            inputAndCheckCoordinates(scanner, game, 'O');
            if (game.isWinner('O')) {
                game.showResult();
                break;
            } else if (!game.isNotFinished()) {
                game.showResult();
                break;
            }
        }
    }

    public static void inputAndCheckCoordinates(Scanner scanner, Game game, char symbol) {
        boolean isNotDigit = true;
        boolean isNotInRange = true;
        boolean isNotCellEmpty = true;
        Coordinate coordinate = null;
        while (isNotDigit || isNotInRange || isNotCellEmpty) {
            System.out.println("Enter the coordinates: ");
            String strAnswer = scanner.nextLine();

            if (!Coordinate.isDigit(strAnswer)) {
                System.out.println("You should enter numbers!");
                isNotDigit = true;
                continue;
            } else {
                isNotDigit = false;
            }
            coordinate = Coordinate.parse(strAnswer);

            if (!coordinate.isInRange()) {
                System.out.println("Coordinates should be from 1 to 3");
                isNotInRange = true;
                continue;
            } else {
                isNotInRange = false;
            }
            if (!game.isEmpty(coordinate)) {
                System.out.println("This cell is occupied! Choose another!");
                isNotCellEmpty = true;
            } else {
                isNotCellEmpty = false;
            }
        }
        game.putAnswer(symbol, coordinate);
        game.showGrid();
    }
}
