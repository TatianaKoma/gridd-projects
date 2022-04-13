package ua.griddynamics.tictactoe;

public class Game {
    private final char X = 'X';
    private final char O = 'O';
    private final char EMPTY = '_';
    private String input;
    int[][] WIN_INDEXES = {{0, 1, 2}, {3, 4, 5},
            {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public Game(String input) {
        this.input = input;
    }

    public String showGrid() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------");
        sb.append(System.getProperty("line.separator"));
        for (int i = 0; i < input.length(); i++) {
            if (i % 3 == 0) {
                sb.append("| ");
            }
            sb.append(input.charAt(i) + " ");
            if (i % 3 == 2) {
                sb.append("|");
                sb.append(System.getProperty("line.separator"));
            }
        }
        sb.append("---------");
        return sb.toString();
    }

    public boolean isWinner(char symbol) {
        for (int[] win_index : WIN_INDEXES) {
            boolean firstWinIndex = input.charAt(win_index[0]) == symbol;
            boolean secondWinIndex = input.charAt(win_index[1]) == symbol;
            boolean thirdWinIndex = input.charAt(win_index[2]) == symbol;

            if (firstWinIndex && secondWinIndex && thirdWinIndex) {
                return true;
            }
        }
        return false;
    }

    public boolean isFinished() {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == EMPTY) {
                count++;
            }
        }
        return count == 0;
    }

    public void putAnswer(char symbol, Coordinate coordinate) {
        int position = getPosition(coordinate);
        StringBuilder sb = new StringBuilder(input);
        sb.replace(position, position + 1, String.valueOf(symbol));
        input = sb.toString();
    }

    private int getPosition(Coordinate coordinate) {
        return switch (coordinate.getX()) {
            case 1 -> coordinate.getY() - coordinate.getX();
            case 2 -> coordinate.getY() + coordinate.getX();
            case 3 -> coordinate.getY() + coordinate.getX() + 2;
            default -> 0;
        };
    }

    public boolean isEmpty(Coordinate coordinate) {
        int position = getPosition(coordinate);
        return input.charAt(position) == EMPTY;
    }

    public String showResult() {
        if (isWinner('X')) {
            return "X wins";
        } else if (isWinner('O')) {
            return "O wins";
        } else {
            return "Draw";
        }
    }
}
