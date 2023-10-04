package maze.generators;

import maze.objects.Cell;
import maze.MazeGenerator;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

public class SimpleMazeGenerator implements MazeGenerator {

    private final Random random = new Random();

    public SimpleMazeGenerator(Cell[] cell, int width, int height, boolean perfect) {
        generateMaze(cell, width, height, perfect);
    }

    @Override
    public void generateMaze(Cell[] cell, int width, int height, boolean perfect) {

        Stack<Integer> path = new Stack<>();
        path.push(0);

        while (!path.isEmpty()) {
            int id = path.peek();
            cell[id].setVisited();
            List<Integer> neighbors = getUnvisitedNeighbors(id, width, height, cell);

            if (!neighbors.isEmpty()) {
                int nextCell = neighbors.get(random.nextInt(neighbors.size()));
                int wallToRemove = getWallToRemove(id, nextCell, width);
                cell[id].removeWall(wallToRemove);
                cell[nextCell].setVisited();
                int oppositeWall = (wallToRemove + 2) % 4;
                cell[nextCell].removeWall(oppositeWall);
                path.push(nextCell);
            } else {
                path.pop();
            }
        }
        if (!perfect) {
            if (cell[0].hasWall(1)) {
                cell[0].removeWall(1);
                cell[1].removeWall(3);
            } else {
                cell[0].removeWall(2);
                cell[width].removeWall(0);
            }
        }
    }

    private List<Integer> getUnvisitedNeighbors(int id, int width, int height, Cell[] cell) {
        List<Integer> neighbors = new ArrayList<>();
        int x = id % width;
        int y = id / width;
        if (y > 0 && cell[id - width].isVisited()) {
            neighbors.add(id - width);
        }
        if (x < width - 1 && cell[id + 1].isVisited()) {
            neighbors.add(id + 1);
        }
        if (y < height - 1 && cell[id + width].isVisited()) {
            neighbors.add(id + width);
        }
        if (x > 0 && cell[id - 1].isVisited()) {
            neighbors.add(id - 1);
        }
        return neighbors;
    }

    private int getWallToRemove(int id, int nextCell, int width) {
        if (nextCell == id - width) {
            return 0;
        } else if (nextCell == id + 1) {
            return 1;
        } else if (nextCell == id + width) {
            return 2;
        } else {
            return 3;
        }
    }
}