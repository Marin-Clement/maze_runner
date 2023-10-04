package maze;

import maze.Objects.Cell;

public interface MazeGenerator {
    void generateMaze(Cell[] maze, int width, int height, boolean perfect);
}