package ua.griddynamics.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game("_________");
        System.out.println(game.getGrid());

        while (!game.isFinished()) {
            playStep(scanner, game, Game.X);
            if (game.isWinner(Game.X)) {
                break;
            }
            if (game.isFinished()) {
                break;
            }
            playStep(scanner, game, Game.O);
            if (game.isWinner(Game.O)) {
                break;
            }
        }
        System.out.println(game.getResult());
    }

    private static void playStep(Scanner scanner, Game game, char symbol) {
        Coordinate coordinate = null;
        do {
            System.out.println("Enter the coordinates: ");
            String strAnswer = scanner.nextLine();
            try {
                coordinate = Game.parse(strAnswer, game);
            } catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
        } while (coordinate == null);
        game.putAnswer(symbol, coordinate);
        System.out.println(game.getGrid());
    }
}
