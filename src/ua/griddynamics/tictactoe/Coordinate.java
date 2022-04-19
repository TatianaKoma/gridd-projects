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
        if (!isCoordinateDigit(coordinates)) {
            throw new InvalidNumberException("You should enter numbers!");
        } else {
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            return new Coordinate(x, y);
        }
    }

    public static boolean isCoordinateDigit(String[] coordinates) {
        return coordinates.length == 2 && coordinates[0].matches("\\d+") && coordinates[1].matches("\\d+");
    }
}