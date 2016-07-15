import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class problemA {

    final static int UP = 0;
    final static int RIGHT = 1;
    final static int DOWN = 2;
    final static int LEFT = 3;

    public static class Point {
        int col, row;

        public Point(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (col != point.col) return false;
            return row == point.row;

        }

        @Override
        public int hashCode() {
            int result = col;
            result = 31 * result + row;
            return result;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "col=" + col +
                    ", row=" + row +
                    '}';
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        for (int t=1; t<=testCases; t++) {

            int fieldSize = scanner.nextInt();
            int numberOfFoodBlocks = scanner.nextInt();

            boolean[][] field = new boolean[fieldSize][fieldSize];

            int snakePosCol = scanner.nextInt()-1;
            int snakePosRow = scanner.nextInt()-1;

            List<Point> snakePositions = new ArrayList<>();
            snakePositions.add(new Point(snakePosCol, snakePosRow));

            int direction = RIGHT;

            for (int b=0; b<numberOfFoodBlocks; b++) {

                int foodStartCol = scanner.nextInt()-1;
                int foodStartRow = scanner.nextInt()-1;
                int foodWidth = scanner.nextInt();
                int foodHeight = scanner.nextInt();

                for (int i=foodStartCol; i<(foodStartCol+foodWidth); i++) {
                    for (int j=foodStartRow; j<(foodStartRow+foodHeight); j++) {
                        field[i][j] = true;
                    }
                }
            }

            field[snakePosCol][snakePosRow] = false;

            int numberOfLetters = scanner.nextInt();
            char[] letters = scanner.nextLine().trim().toCharArray();

            int pointsCount = 0;
            int lastStep = 0;
            for (int i=0; i<numberOfLetters; i++) {
                char currentLetter = letters[i];

                direction = updateDirection(direction, currentLetter);

                switch (direction) {
                    case UP:
                        snakePosRow = snakePosRow -1;
                        if (snakePosRow <0) snakePosRow += fieldSize;
                        break;

                    case RIGHT:
                        snakePosCol++;
                        snakePosCol %= fieldSize;
                        break;

                    case DOWN:
                        snakePosRow = snakePosRow +1;
                        snakePosRow %= fieldSize;
                        break;

                    case LEFT:
                        snakePosCol--;
                        if (snakePosCol <0) snakePosCol += fieldSize;
                        break;

                    default:
                        System.out.println("dafuq");
                }

                Point newSnakePosition = new Point(snakePosCol, snakePosRow);

                if (field[newSnakePosition.col][newSnakePosition.row]) {

                    // check if we bite our own tail in this case
                    if (newSnakePosition == snakePositions.get(0)) break;

                    pointsCount++;

                    // There is no more food afterwards
                    field[newSnakePosition.col][newSnakePosition.row] = false;

                } else {

                    snakePositions.remove(0);

                    // check if we bite ourselves somehow
                    if (snakePositions.contains(newSnakePosition)) break;
                }

                snakePositions.add(newSnakePosition);
                lastStep = i+1;
            }

            System.out.printf("Case #%d: %d %d\n", t, lastStep, pointsCount);
        }
    }

    public static void debug(boolean[][] field, List<Point> snakePositions) {

        char[][] mat = new char[field.length][field.length];

        for (int col=0; col<field.length; col++) {
            for (int row=0; row<field.length; row++) {
                if (field[col][row]) {
                    if (snakePositions.contains(new Point(col, row))) {
                        mat[row][col] = 'S';
                    } else {
                        mat[row][col] = 'F';
                    }
                } else {
                    if (snakePositions.contains(new Point(col, row))) {
                        mat[row][col] = 's';
                    } else {
                        mat[row][col] = '.';
                    }
                }
            }
        }

        for (char[] line : mat) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static int updateDirection(int direction, char letter) {

        int result = direction;

        switch (letter) {
            case 'F':
                return result;
            case 'R':
                result++;
                return result % 4;
            case 'L':
                result--;
                if (result <0) {
                    return result+4;
                } else {
                    return result;
                }
            default:
                //
        }

        return 42;
    }
}