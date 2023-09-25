import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

// ! depth-first search algorithm to generate a perfect maze
public class SimplePerfectMazeGenerator implements MazeGenerator {

    public SimplePerfectMazeGenerator(int width, int height) {
        long startTime = System.nanoTime();
        Maze maze = generateMaze(height, width);
        setEntranceAndExit(maze);
        printMaze(maze.getMazeMap());
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        printInfo(executionTime, width, height);
    }


    @Override
    public Maze generateMaze(int height, int width) {
        char[][] mazeMap = initializeMaze(height, width);

        Stack<int[]> stack = new Stack<>();

        stack.push(new int[]{1, 1});

        while (!stack.isEmpty()) {
            int[] currentCell = stack.peek();
            List<int[]> neighbors = getNeighbors(currentCell, height, width);
            List<int[]> unvisitedNeighbors = new ArrayList<>();

            // ? Iterate through the neighbors to find unvisited cells.
            for (int[] neighbor : neighbors) {
                int row = neighbor[0];
                int col = neighbor[1];
                // ! Check if the cell is unvisited (represented by a space character).
                if (mazeMap[row][col] == ' ') {
                    unvisitedNeighbors.add(neighbor);
                }
            }

            // ? If there are unvisited neighbors, choose one randomly and connect it to the current cell.
            if (!unvisitedNeighbors.isEmpty()) {
                int[] randomNeighbor = unvisitedNeighbors.get(new Random().nextInt(unvisitedNeighbors.size()));
                int row = randomNeighbor[0];
                int col = randomNeighbor[1];
                // ! Mark the chosen neighbor as visited with a dot character.
                mazeMap[row][col] = '.';
                // ! Create a wall between the chosen neighbor and the current cell.
                int wallRow = (row + currentCell[0]) / 2;
                int wallCol = (col + currentCell[1]) / 2;
                mazeMap[wallRow][wallCol] = '.';
                // ! Push the chosen neighbor onto the stack to continue the generation from there.
                stack.push(randomNeighbor);
            } else {
                // ! If all neighbors are visited, pop the current cell from the stack.
                stack.pop();
            }
        }

        // Create a Maze object and return it.
        return new Maze(width, height, mazeMap);
    }

    @Override
    public void printMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char cell : row) {
                if (cell == '#') {
                    System.out.print("\u001B[31m" + cell + " ");
                } else {
                    System.out.print("\u001B[32m" + cell + " ");
                }
            }
            System.out.println("\u001B[0m");
        }
        System.out.println();
    }

    public void printInfo(long executionTime, int width, int height) {
        System.out.println("\u001B[36mYou have generated a " + width + "x" + height + " maze.");
        System.out.println("\u001B[35mUsing the simple Perfect Maze generation algorithm.");
        if (executionTime < 1000000) {
            System.out.println("\u001B[33mExecution time: " + executionTime + " nanoseconds.");
        } else if (executionTime < 1000000000) {
            System.out.println("\u001B[33mExecution time: " + executionTime / 1000000 + " milliseconds.");
        } else {
            System.out.println("\u001B[33mExecution time: " + executionTime / 1000000000 + " seconds.");
        }
        System.out.println("\u001B[0m");
    }


    public static char[][] initializeMaze(int height, int width) {
        char[][] maze = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // ! Fill even rows and even columns with walls, and leave odd rows and columns as spaces.
                maze[i][j] = (i % 2 == 0 || j % 2 == 0) ? '#' : ' ';
            }
        }
        return maze;
    }

    public static List<int[]> getNeighbors(int[] cell, int height, int width) {
        List<int[]> neighbors = new ArrayList<>();
        int row = cell[0];
        int col = cell[1];

        // ? Check and add neighbors above, below, to the left, and to the right if they are within bounds.
        if (row >= 2) {
            neighbors.add(new int[]{row - 2, col});
        }
        if (row <= height - 3) {
            neighbors.add(new int[]{row + 2, col});
        }
        if (col >= 2) {
            neighbors.add(new int[]{row, col - 2});
        }
        if (col <= width - 3) {
            neighbors.add(new int[]{row, col + 2});
        }

        return neighbors;
    }

    public static void setEntranceAndExit(Maze maze) {
        // ? Set the entrance in the top-left corner and the exit in the bottom-right corner of the maze.
        maze.getMazeMap()[0][1] = '.';
        maze.getMazeMap()[maze.getHeight() - 1][maze.getWidth() - 2] = '.';
    }
}
