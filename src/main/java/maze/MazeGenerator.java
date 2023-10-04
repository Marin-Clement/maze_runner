package maze;

import maze.objects.Cell;

public interface MazeGenerator {
    void generateMaze(Cell[] maze, int width, int height, boolean perfect);
}