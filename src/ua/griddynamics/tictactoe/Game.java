package ua.griddynamics.tictactoe;

public class Game {
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char EMPTY = '_';
    private static final int SIZE_OF_GRID = 3;
    private static final int[][] WIN_INDEXES = {{0, 1, 2}, {3, 4, 5},
            {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    private String input;

    public Game(String input) {
        this.input = input;
    }

    public String getGrid() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------");
        sb.append(System.lineSeparator());
        for (int i = 0; i < input.length(); i++) {
            if (i % 3 == 0) {
                sb.append("| ");
            }
            sb.append(input.charAt(i)).append(" ");
            if (i % 3 == 2) {
                sb.append("|");
                sb.append(System.lineSeparator());
            }
        }
        sb.append("---------");
        return sb.toString();
    }

    public static Coordinate parse(String input, Game game) throws InvalidNumberException {
        String[] coordinates = input.split(" ");
        if (isDigit(coordinates)) {
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            if (isInRange(x, y)) {
                Coordinate coordinate = new Coordinate(x, y);
                if (game.isEmpty(coordinate)) {
                    return new Coordinate(x, y);
                } else {
                    throw new InvalidNumberException("This cell is occupied! Choose another!");
                }
            } else {
                throw new InvalidNumberException("Coordinates should be from 1 to 3");
            }
        } else {
            throw new InvalidNumberException("You should enter numbers!");
        }
    }

    public static boolean isDigit(String[] coordinates) {
        return coordinates.length == 2 && coordinates[0].matches("\\d+") && coordinates[1].matches("\\d+");
    }

    public static boolean isInRange(int x, int y) {
        return (0 <= x && x <= SIZE_OF_GRID) && (0 <= y && y <= SIZE_OF_GRID);
    }

    public boolean isEmpty(Coordinate coordinate) {
        int position = getPosition(coordinate);
        return input.charAt(position) == EMPTY;
    }

    public void putAnswer(char symbol, Coordinate coordinate) {
        int position = getPosition(coordinate);
        StringBuilder sb = new StringBuilder(input);
        sb.replace(position, position + 1, String.valueOf(symbol));
        input = sb.toString();
    }

    private int getPosition(Coordinate coordinate) {
        return (coordinate.getX() - 1) * SIZE_OF_GRID + (coordinate.getY() - 1);
    }

    public boolean isWinner(char symbol) {
        int countOfSymbols = 0;
        for (int i = 0; i < WIN_INDEXES.length; i++) {
            for (int j = 0; j < WIN_INDEXES[i].length; j++) {
                if (input.charAt(WIN_INDEXES[i][j]) == symbol) {
                    countOfSymbols++;
                }
            }
            if (countOfSymbols == SIZE_OF_GRID) {
                return true;
            } else {
                countOfSymbols = 0;
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

    public String getResult() {
        if (isWinner(X)) {
            return "X wins";
        } else if (isWinner(O)) {
            return "O wins";
        } else {
            return "Draw";
        }
    }
}
