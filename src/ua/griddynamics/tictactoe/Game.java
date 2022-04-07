package ua.griddynamics.tictactoe;

public class Game {
    private static final int IMPOSSIBLE_DIFFERENCE = 2;
    private final char X = 'X';
    private final char O = 'O';
    private String input;

    public Game(String input) {
        this.input = input;
    }

    public void showGrid() {
        System.out.println("---------");
        for (int i = 0; i < input.length(); i++) {
            if (i % 3 == 0) {
                System.out.print("| ");
            }
            System.out.print(input.charAt(i) + " ");
            if (i % 3 == 2) {
                System.out.print("|");
                System.out.println();
            }
        }
        System.out.println("---------");
    }

    public void showResult() {
        if (isMoreSymbols() || isTwoWinners()) {
            System.out.println("Impossible");
        } else if (isWinner(X)) {
            System.out.println("X wins");
        } else if (isWinner(O)) {
            System.out.println("O wins");
        } else if (isNotFinished()) {
            System.out.println("Game not finished");
        } else {
            System.out.println("Draw");
        }
    }

    private boolean isMoreSymbols() {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == X) {
                countX++;
            }
            if (input.charAt(i) == O) {
                countO++;
            }
        }
        return Math.abs(countX - countO) >= IMPOSSIBLE_DIFFERENCE;
    }

    private boolean isWinner(char symbol) {
        int[][] winIndexes = {{0, 1, 2}, {3, 4, 5},
                {6, 7, 8}, {0, 3, 6},
                {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i < winIndexes.length; i++) {
            boolean firstWinIndex = input.charAt(winIndexes[i][0]) == symbol;
            boolean secondWinIndex = input.charAt(winIndexes[i][1]) == symbol;
            boolean thirdWinIndex = input.charAt(winIndexes[i][2]) == symbol;

            if (firstWinIndex && secondWinIndex && thirdWinIndex) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoWinners() {
        return isWinner(X) && isWinner(O);
    }

    private boolean isNotFinished() {
        int count_ = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != X && input.charAt(i) != O) {
                count_++;
            }
        }
        return count_ > 0;
    }

    public void putAnswer(char symbol, Coordinate coordinate) {
        int position = getPosition(coordinate);
        StringBuilder sb = new StringBuilder(input);
        sb.replace(position, position + 1, String.valueOf(symbol));
        input = sb.toString();
    }

    private int getPosition(Coordinate coordinate) {
        int position = 0;
        switch (coordinate.getX()) {
            case 1:
                position = -1;
                break;
            case 2:
                position = 2;
                break;
            case 3:
                position = 5;
                break;
        }
        return position + coordinate.getY();
    }

    public boolean isEmpty(Coordinate coordinate) {
        int position = getPosition(coordinate);
        return input.charAt(position) == '_';
    }
}
