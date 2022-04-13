package ua.griddynamics.tictactoe;

public class Coordinate {
    private final int x;
    private final int y;
    static int MIN_RANGE = 1;
    static int MAX_RANGE = 3;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
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

    static boolean isDigit(String[] coordinates) {
        return coordinates.length == 2 && coordinates[0].matches("\\d+") && coordinates[1].matches("\\d+");
    }

    static boolean isInRange(int x, int y) {
        return (MIN_RANGE <= x && x <= MAX_RANGE) && (MIN_RANGE <= y && y <= MAX_RANGE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}