package ua.griddynamics.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] field = new char[]{'_', '_', '_', '_', '_', '_', '_', '_', '_'};
        Game game = new Game(field);
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
        boolean answerSet = false;
        while (!answerSet) {
            System.out.println("Enter the coordinates: ");
            String userAnswer = scanner.nextLine();
            try {
                Coordinate coordinate = Coordinate.parse(userAnswer);
                game.putAnswer(symbol, coordinate);
                System.out.println(game.getGrid());
                answerSet = true;
            } catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
