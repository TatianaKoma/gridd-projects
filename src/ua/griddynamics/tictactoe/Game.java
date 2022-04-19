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
    private final char[] field;

    public Game(char[] field) {
        this.field = field;
    }

    public String getGrid() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------");
        sb.append(System.lineSeparator());
        for (int i = 0; i < field.length; i++) {
            if (i % 3 == 0) {
                sb.append("| ");
            }
            sb.append(field[i]).append(" ");
            if (i % 3 == 2) {
                sb.append("|");
                sb.append(System.lineSeparator());
            }
        }
        sb.append("---------");
        return sb.toString();
    }

    public void putAnswer(char symbol, Coordinate coordinate) throws InvalidNumberException {
        if (!isCoordinateInRange(coordinate)) {
            throw new InvalidNumberException("Coordinates should be from 1 to 3");
        } else if (!isCellEmpty(coordinate)) {
            throw new InvalidNumberException("This cell is occupied! Choose another!");
        }
        int position = getPosition(coordinate);
        field[position] = symbol;
    }

    private int getPosition(Coordinate coordinate) {
        return (coordinate.getX() - 1) * SIZE_OF_GRID + (coordinate.getY() - 1);
    }

    public boolean isCoordinateInRange(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return (0 <= x && x <= SIZE_OF_GRID) && (0 <= y && y <= SIZE_OF_GRID);
    }


    public boolean isCellEmpty(Coordinate coordinate) {
        int position = getPosition(coordinate);
        return field[position] == EMPTY;
    }

    public boolean isWinner(char symbol) {
        int countOfSymbols = 0;
        for (int i = 0; i < WIN_INDEXES.length; i++) {
            for (int j = 0; j < WIN_INDEXES[i].length; j++) {
                if (field[(WIN_INDEXES[i][j])] == symbol) {
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
        for (int i = 0; i < field.length; i++) {
            if (field[i] == EMPTY) {
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
