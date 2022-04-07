package ua.griddynamics.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cells: ");
        String input = scanner.nextLine();

        Game game = new Game(input);
        game.showGrid();

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

        game.putAnswer('X', coordinate);
        game.showGrid();
    }
}
