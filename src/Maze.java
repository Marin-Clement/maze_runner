public class Maze {
    private int width;
    private int height;
    private char[][] mazeMap;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Maze(int width, int height, char[][] mazeMap) {
        this.width = width;
        this.height = height;
        this.mazeMap = mazeMap;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMazeMap() {
        return mazeMap;
    }
}