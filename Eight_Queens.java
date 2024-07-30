import java.util.*;

public class Eight_Queens {
    public static void main(String[] args) {
        Character[][] board = {
                {'.', '.', '.', 'Q', '.', '.', '.', '.'},
                {'Q', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'Q', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', 'Q', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'Q'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'Q', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'Q', '.', '.', '.'}
        };

        String correctSolution = "011000100110000111000100";//Example of a correct solution, with the fitness function being zero
        
        System.out.println("Is the board valid? " + isValidBoard(board));
        System.out.println("Binary representation of the board: " + generateBinaryString(board));
        System.out.println("Fitness of the solution: " + fitnessFunction(correctSolution));
        System.out.println("Generated initial solution: " + generateInitialSolution());
        System.out.println("Applying small change to solution: " + smallChange(correctSolution));
        System.out.println("Best solution after iterations: " + findBestSolution(1000));
    }

    // Method to check if the character is valid (either 'Q' or '.')
    public static boolean isValidCharacter(Character c) {
        return c != null && (c == 'Q' || c == '.');
    }

    // Method to check if the board is valid (8x8 and contains exactly 8 queens)
    public static boolean isValidBoard(Character[][] board) {
        if (board == null || board.length != 8) {
            return false;
        }
        int queenCount = 0;
        for (Character[] row : board) {
            if (row.length != 8) {
                return false;
            }
            for (Character c : row) {
                if (!isValidCharacter(c)) {
                    return false;
                }
                if (c == 'Q') {
                    queenCount++;
                }
            }
        }
        return queenCount == 8;
    }

    // Method to generate a binary string representation of the board
    public static String generateBinaryString(Character[][] board) {
        if (!isValidBoard(board)) {
            return null;
        }
        StringBuilder binaryString = new StringBuilder();
        for (Character[] row : board) {
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 'Q') {
                    binaryString.append(String.format("%3s", Integer.toBinaryString(j)).replace(' ', '0'));
                }
            }
        }
        return binaryString.toString();
    }

    // Method to generate an initial random solution
    public static String generateInitialSolution() {
        StringBuilder solution = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            int col = rand.nextInt(8);
            solution.append(String.format("%3s", Integer.toBinaryString(col)).replace(' ', '0'));
        }
        return solution.toString();
    }

    // Method to calculate the fitness of a given solution (number of non-clashing pairs)
    public static Double fitnessFunction(String solution) {
        if (solution == null || solution.length() != 24) {
            return Double.MAX_VALUE;
        }
        char[][] board = new char[8][8];
        for (int i = 0; i < solution.length(); i += 3) {
            int col = Integer.parseInt(solution.substring(i, i + 3), 2);
            board[i / 3][col] = 'Q';
        }
        return calculateFitness(board);
    }

    // Helper method to calculate the fitness from the board
    private static Double calculateFitness(char[][] board) {
        final double MAX_VALUE = 56.0;
        double clashes = 0.0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'Q') {
                    clashes += countClashes(board, i, j);
                }
            }
        }
        return MAX_VALUE - clashes;
    }

    // Helper method to count the number of clashes for a queen at position (row, col)
    private static double countClashes(char[][] board, int row, int col) {
        double clashes = 0.0;
        // Check for clashes in the same column
        for (int i = row + 1; i < 8; i++) {
            if (board[i][col] == 'Q') {
                clashes += 2.0;
            }
        }
        // Check for clashes in diagonals
        for (int i = 1; row + i < 8 && col + i < 8; i++) {
            if (board[row + i][col + i] == 'Q') {
                clashes += 1.0;
            }
        }
        for (int i = 1; row + i < 8 && col - i >= 0; i++) {
            if (board[row + i][col - i] == 'Q') {
                clashes += 1.0;
            }
        }
        return clashes;
    }

    // Method to apply a small change to the solution
    public static String smallChange(String solution) {
        if (solution == null || solution.length() != 24) {
            return null;
        }
        char[] bits = solution.toCharArray();
        Random rand = new Random();
        int index = rand.nextInt(24);
        bits[index] = (bits[index] == '0') ? '1' : '0';
        return new String(bits);
    }

    // Method to find the best solution after a given number of iterations
    public static String findBestSolution(int iterations) {
        String bestSolution = generateInitialSolution();
        for (int i = 0; i < iterations; i++) {
            double currentFitness = fitnessFunction(bestSolution);
            String newSolution = smallChange(bestSolution);
            double newFitness = fitnessFunction(newSolution);
            if (newFitness > currentFitness) {
                bestSolution = newSolution;
            }
        }
        return bestSolution;
    }
}
