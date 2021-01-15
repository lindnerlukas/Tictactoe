package tictactoe;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] arr = new char[3][3];
        for (char[] chars : arr) {
            Arrays.fill(chars, ' ');
        }
        tic(arr);
    }
    // function asks for input and returns input without spaces
    public static String getUserInput() {
        System.out.print("Enter the coordinates: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.replaceAll("\\s", "");
    }
    // function returns 1 if String contains non numerical input
    // returns 2 if String contains number != 1,2,3
    // returns 0 if no input mistake
    public static int checkUserInput(String input) {
        if (input.matches("\\D")) {
            return 1;
        } else if (!input.matches("[1-3]+")){
            return 2;
        } else {
            return 0;
        }
    }
    // function returns int array of String
    public static int[] makeIntArr(String input) {
        int[] arr = new int[2];
        for (int i = 0; i < 2; i++) {
            char c = input.charAt(i);
            arr[i] = Integer.parseInt(String.valueOf(c)) - 1;
        }
        return arr;
    }
    // function returns true if the first array is free
    public static boolean checkIfFree(char[][] arr, int[] arrInput) {
        return arr[arrInput[0]][arrInput[1]] == ' ';
    }
    // function returns char matrix with input placed X
    public static void getNewCharArr(char[][] arr, int[] arrUserInput, int counter) {
        if (counter % 2 == 0) {
            arr[arrUserInput[0]][arrUserInput[1]] = 'X';
        } else {
            arr[arrUserInput[0]][arrUserInput[1]] = 'O';
        }
    }
    // function prints matrix
    public static void printNewArr(char[][] newArr) {
        String dashes = "---------";
        String pipeBeginning = "| ";
        String pipeEnd = "|";
        System.out.println(dashes);
        for (int i = 0; i < 3; i++) {
            System.out.print(pipeBeginning);
            for (int j = 0; j < 3; j++) {
                System.out.print(newArr[i][j] + " ");
            }
            System.out.println(pipeEnd);
        }
        System.out.println(dashes);
    }
    public static void tic(char[][] grid) {
        printNewArr(grid);
        char[][] gridUpdated = new char[3][3];
        for (char[] chars : gridUpdated) {
            Arrays.fill(chars, ' ');
        }
        int count = 0;
        boolean input = false;
        while (!input) {
            String userInput = getUserInput();
            int check = checkUserInput(userInput);
            if (check == 0) {
                int[] getIntArrOfString = makeIntArr(userInput);
                if (checkIfFree(gridUpdated, getIntArrOfString)) {
                    getNewCharArr(gridUpdated, getIntArrOfString, count);
                    printNewArr(gridUpdated);
                    count++;
                    if(checkWin(gridUpdated)) {
                        if (count % 2 == 0) {
                            System.out.println("O wins");
                        } else {
                            System.out.println("X wins");
                        }
                        input = true;
                    } else if (count > 8) {
                        System.out.println("Draw");
                        input = true;
                    }
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                simpleOutput(check);
            }
        }
    }
    public static void simpleOutput(int n) {
        switch (n) {
            case 1:
                System.out.println("You should enter numbers!");
            case 2:
                System.out.println("Coordinates should be from 1 to 3!");
        }
    }
    public static boolean checkWin(char[][] grid) {
        // check horizontal
        boolean horizontalO = false;
        boolean horizontalX = false;
        for (int i = 0; i < 3; i++) {
            int getCountHorizontal0 = 0;
            int getCountHorizontalX = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    break;
                } else if (grid[i][j] == 'O') {
                    getCountHorizontal0++;
                    if (getCountHorizontal0 > 2) {
                        horizontalO = true;
                        break;
                    }
                } else {
                    getCountHorizontalX++;
                    if (getCountHorizontalX > 2) {
                        horizontalX = true;
                        break;
                    }
                }
            }
        }
        // check vertical
        boolean verticalO = false;
        boolean verticalX = false;
        for (int i = 0; i < 3; i++) {
            int getCountVerticalO = 0;
            int getCountVerticalX = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[j][i] == ' ') {
                    break;
                } else if (grid[j][i] == 'O') {
                    getCountVerticalO++;
                    if (getCountVerticalO > 2) {
                        verticalO = true;
                        break;
                    }
                } else {
                    getCountVerticalX++;
                    if (getCountVerticalX > 2) {
                        verticalX = true;
                        break;
                    }
                }
            }
        }
        // check diagonal
        boolean diagonalMajor = true;
        boolean diagonalMinor = true;
        boolean diagonalO = false;
        boolean diagonalX = false;
        char major = grid[0][0];
        char minor = grid[0][grid.length - 1];
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][i] != major) {
                diagonalMajor = false;
                break;
            }
        }
        for (int i = 0, j = grid.length - 1; i < grid.length; i++, j--) {
            if (grid[i][j] != minor) {
                diagonalMinor = false;
                break;
            }
        }
        if (diagonalMajor || diagonalMinor) {
            if (grid[1][1] == 'O') {
                diagonalO = true;
            } else if (grid[1][1] == 'X') {
                diagonalX = true;
            }
        }
        return (horizontalO || horizontalX || verticalO || verticalX || diagonalO || diagonalX);
    }
}
