package maze.generators;

import maze.Objects.Cell;
import maze.MazeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Random;

public class GraphBasedMazeGenerator implements MazeGenerator {

    private final Random random = new Random();

    public GraphBasedMazeGenerator(Cell[] cell, int width, int height, boolean perfect) {
        generateMaze(cell, width, height, perfect);
    }

    @Override
    public void generateMaze(Cell[] cell, int width, int height, boolean perfect) {
        List<Integer> possibilities = new ArrayList<>(4);
        Stack<Integer> path = new Stack<>();
        path.push(0);

        while (!path.isEmpty()) {
            possibilities.clear();
            int id = path.peek();
            cell[id].setVisited();
            int x = id % width;
            int y = id / width;

            if (y != 0 && cell[id - width].isVisited()) {
                possibilities.add(0);
            }
            if (x != width - 1 && cell[id + 1].isVisited()) {
                possibilities.add(1);
            }
            if (y != height - 1 && cell[id + width].isVisited()) {
                possibilities.add(2);
            }
            if (x != 0 && cell[id - 1].isVisited()) {
                possibilities.add(3);
            }

            if (!possibilities.isEmpty()) {
                int direction = random.nextInt(possibilities.size());
                switch (possibilities.get(direction)) {
                    case 0 -> {
                        cell[id].removeWall(0);
                        cell[id - width].removeWall(2);
                        path.push(id - width);
                    }
                    case 1 -> {
                        cell[id].removeWall(1);
                        cell[id + 1].removeWall(3);
                        path.push(id + 1);
                    }
                    case 2 -> {
                        cell[id].removeWall(2);
                        cell[id + width].removeWall(0);
                        path.push(id + width);
                    }
                    case 3 -> {
                        cell[id].removeWall(3);
                        cell[id - 1].removeWall(1);
                        path.push(id - 1);
                    }
                }
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
}