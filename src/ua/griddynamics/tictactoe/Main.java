package ua.griddynamics.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game("_________");
        System.out.println(game.showGrid());

        while (!game.isFinished()) {
            playStep(scanner, game, 'X');
            if (game.isWinner('X')) {
                break;
            }
            if (game.isFinished()) {
                break;
            }
            playStep(scanner, game, 'O');
            if (game.isWinner('O')) {
                break;
            }
        }
        System.out.println(game.showResult());
    }

    private static void playStep(Scanner scanner, Game game, char symbol) {
        Coordinate coordinate = null;
        String strAnswer;
        do {
            System.out.println("Enter the coordinates: ");
            strAnswer = scanner.nextLine();
            try {
                coordinate = Coordinate.parse(strAnswer, game);
            } catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
        } while (coordinate == null);
        game.putAnswer(symbol, coordinate);
        System.out.println(game.showGrid());
    }
}
