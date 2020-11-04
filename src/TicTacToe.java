import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isError;
        boolean isX = true;
        boolean isOn = true;
        int turns = 0;

        char[] chars = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        char sl = '|';
        System.out.println("---------");
        System.out.print(sl + " ");
        for (int i = 0; i < chars.length; i++) {
            if (i != 0 && i % 3 == 0) {
                System.out.print(sl + " " + "\n" + sl + " ");
            }
            System.out.print(chars[i] + " ");
        }
        System.out.println(sl + "\n" + "---------");

        char[][] matrix = new char[3][3];
        int a = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = chars[a++];
            }
        }

        int x = 0;
        int y = 0;

        while (isOn) {
            do {
                isError = false;
                System.out.print("Enter the coordinates: ");
                try {
                    String xStr = sc.next();
                    String yStr = sc.next();
                    x = Integer.parseInt(xStr);
                    y = Integer.parseInt(yStr);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    isError = true;
                }
                if (!isError) {
                    if ((x < 1 || x > 3) || (y < 1 || y > 3)) {
                        System.out.println("Coordinates should be from 1 to 3!\n");
                        isError = true;
                    }
                }

                if (!isError) {
                    if (matrix[3 - y][x - 1] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                        isError = true;
                    } else {
                        if (isX) {
                            matrix[3 - y][x - 1] = 'X';
                            isX = false;
                        } else if (!isX) {
                            matrix[3 - y][x - 1] = 'O';
                            isX = true;
                        }
                        turns++;
                    }
                }

            } while (isError);
            if (!isError) {
                System.out.println("---------");
                for (int i = 0; i < matrix.length; i++) {
                    System.out.print(sl + " ");
                    for (int j = 0; j < matrix[i].length; j++) {
                        System.out.print(matrix[i][j] + " ");
                    }
                    System.out.println(sl);
                }
            }
            System.out.println("---------");

            int varVertical = 1, varHorizont = 1;
            boolean xWins = false;
            boolean oWins = false;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    for (int i = 0; i < 3; i++) {
                        if (row != i && matrix[row][col] == matrix[i][col]) {
                            varVertical++;
                        }
                        if (col != i && matrix[row][col] == matrix[row][i]) {
                            varHorizont++;
                        }
                    }

                    if (varHorizont == 3 || varVertical == 3) {
                        if (matrix[row][col] == 'X') {
                            xWins = true;
                        } else if (matrix[row][col] == 'O') {
                            oWins = true;
                        }
                    }
                    varHorizont = 1;
                    varVertical = 1;

                    if (matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) {
                        if (matrix[0][0] == 'X') {
                            xWins = true;
                        } else if (matrix[0][0] == 'O') {
                            oWins = true;
                        }
                    } else if (matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0]) {
                        if (matrix[0][2] == 'X') {
                            xWins = true;
                        } else if (matrix[0][2] == 'O') {
                            oWins = true;
                        }
                    }
                }
            }
            if (xWins) {
                System.out.println("X wins");
                return;
            } else if (oWins) {
                System.out.println("O wins");
                return;
            } else if (turns > 8) {
                System.out.println("Draw");
                return;
            }
        }
    }
}

