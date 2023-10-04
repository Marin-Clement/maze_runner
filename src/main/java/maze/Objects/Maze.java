package maze.Objects;

import maze.generators.GraphBasedMazeGenerator;
import maze.generators.OptimizedMazeGenerator;
import maze.generators.SimpleMazeGenerator;

public class Maze {
    public int width, height;
    public String generationType, generatorMethod;
    public Cell[] cell;

    public Maze(int width, int height, String generationType, String generatorMethod) {
        this.width = width;
        this.height = height;
        this.generationType = generationType;
        this.generatorMethod = generatorMethod;
        cell = new Cell[width * height];
        initializeCells();
        selectGenerator();
        displayMaze();
    }

    public void selectGenerator()  { // selects algorithm
        switch (generationType) {
            case "perfect" -> {
                switch (generatorMethod) {
                    case "simple" -> new SimpleMazeGenerator(cell, width, height, true);
                    case "graph" -> new GraphBasedMazeGenerator(cell, width, height, true);
                    case "optimized" -> new OptimizedMazeGenerator(cell, width, height, true);
                }
            }
            case "imperfect" -> {
                switch (generatorMethod) {
                    case "simple" -> new SimpleMazeGenerator(cell, width, height, false);
                    case "graph" -> new GraphBasedMazeGenerator(cell, width, height, false);
                    case "optimized" -> new OptimizedMazeGenerator(cell, width, height, false);
                }
            }
        }
    }

    private void initializeCells() { // initializes cells
        for (int id = 0; id < cell.length; id++) {
                cell[id] = new Cell(id);
            }
    }

    public void displayMaze() {

        cell[0].removeWall(0); // * Entry
        cell[cell.length - 1].removeWall(2); // ! Exit

        int id = 0;
        for (int y = 0; y < height; y++) {
            StringBuilder row1 = new StringBuilder();
            StringBuilder row2 = new StringBuilder();
            StringBuilder row3 = new StringBuilder();
            for (int x = 0; x < width; x++) {
                String[] strCell = cell[x + id].printCell();
                row1.append(strCell[0]);
                row2.append(strCell[1]);
                row3.append(strCell[2]);
            }
            id += width;
            System.out.println(row1 + "\n" + row2 + "\n" + row3);
        }
    }

    // prints the stats
    public void displayStats(double time) {
        System.out.println(" ");
        System.out.println("Maze dimensions: " + width + "x" + height);
        System.out.println("Algorithm: " + generatorMethod);
        System.out.println("Generation time: " + time / 1000 + " seconds");
    }
}
