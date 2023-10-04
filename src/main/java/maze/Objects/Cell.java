package maze.Objects;

public class Cell {
    public int id;
    private boolean visited = false;
    private final boolean[] wall = {true, true, true, true}; // * top, right, bottom, left

    public Cell(int id) {
        this.id = id;
    }

    // ? Getters and Setters

    public boolean isVisited() {
        return !visited;
    }
    public void setVisited() { visited = true; }
    public boolean hasWall(int direction) { return wall[direction]; }
    public void removeWall(int direction) {
        wall[direction] = false;
    }

    // ! Methods

    public String[] printCell() {
        String topRow = "#" + (wall[0] ? "#" : ".") + "#";
        String middleRow = (wall[3] ? "#" : ".") + "." + (wall[1] ? "#" : ".");
        String bottomRow = "#" + (wall[2] ? "#" : ".") + "#";

        return new String[]{topRow, middleRow, bottomRow};
    }
}