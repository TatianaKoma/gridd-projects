package ua.griddynamics.tictactoe;

public class Coordinate {
    private int x;
    private int y;

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

    public static boolean isDigit(String input) {
        String[] coordinates = input.split(" ");
        return coordinates[0].matches("\\d+") && coordinates[1].matches("\\d+");
    }

    public static Coordinate parse(String input) {
        String[] coordinates = input.split(" ");
        return new Coordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }

    public boolean isInRange() {
        int x = getX();
        int y = getY();
        return (1 <= x && x <= 3) && (1 <= y && y <= 3);
    }
}
