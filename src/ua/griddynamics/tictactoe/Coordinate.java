package ua.griddynamics.tictactoe;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Coordinate parse(String userAnswer) throws InvalidNumberException {
        String[] coordinates = userAnswer.split(" ");
        if (coordinates.length == 2 && isDigit(coordinates[0]) && isDigit(coordinates[1])) {
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            return new Coordinate(x, y);
        } else {
            throw new InvalidNumberException("You should enter numbers!");
        }
    }

    public static boolean isDigit(String value) {
        return value.matches("\\d+");
    }
}
