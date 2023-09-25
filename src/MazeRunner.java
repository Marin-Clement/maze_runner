public class MazeRunner {
    public static void main(String[] args) {
        MazeRunner mazeRunner = new MazeRunner();
        if (!mazeRunner.AreArgumentsValid(args)) {
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        String perfect = args[2];
        String algorithm = args[3];
        if (perfect.equals("perfect")) {
            switch (algorithm) {
                case "simple" -> {
                    SimplePerfectMazeGenerator simplePerfectMazeGenerator = new SimplePerfectMazeGenerator(width, height);
                }
                case "graph" -> {
                    GraphBasedMazeGenerator graphBasedMazeGenerator = new GraphBasedMazeGenerator();
                }
                case "optimized" -> {
                    OptimizedMazeGenerator optimizedMazeGenerator = new OptimizedMazeGenerator(width, height);
                }
            }
        } else if (perfect.equals("imperfect")) {
            switch (algorithm) {
                case "simple" -> {
                    SimpleImperfectMazeGenerator simpleImperfectMazeGenerator = new SimpleImperfectMazeGenerator(width, height);
                }
                case "graph" -> {
                    GraphBasedMazeGenerator graphBasedMazeGenerator = new GraphBasedMazeGenerator();
                }
                case "optimized" -> {
                    OptimizedMazeGenerator optimizedMazeGenerator = new OptimizedMazeGenerator(width, height);
                }
            }
        }
    }

    public boolean IsWidthValid(String width) {
        try {
            int widthInt = Integer.parseInt(width);
            if (widthInt < 5) {
                System.out.println("The width must be greater or equal to 5.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("The width must be a number.");
            return false;
        }
    }

    public boolean IsHeightValid(String height) {
        try {
            int heightInt = Integer.parseInt(height);
            if (heightInt < 5) {
                System.out.println("The height must be greater or equal to 5.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("The height must be a number.");
            return false;
        }
    }

    public boolean IsPerfectValid(String perfect) {
        if (perfect.equals("perfect") || perfect.equals("imperfect")) {
            return true;
        }
        System.out.println("The maze must be perfect or imperfect.");
        return false;
    }

    public boolean IsAlgorithmValid(String algorithm) {
        if (algorithm.equals("simple") || algorithm.equals("graph") || algorithm.equals("optimized")) {
            return true;
        }
        System.out.println("The algorithm must be simple, graph or optimized.");
        return false;
    }

    public boolean AreArgumentsValid(String[] args) {
        if (args.length != 4) {
            System.out.println("The number of arguments must be 4.");
            return false;
        }
        if (!IsWidthValid(args[0])) {
            return false;
        }
        if (!IsHeightValid(args[1])) {
            return false;
        }
        if (!IsPerfectValid(args[2])) {
            return false;
        }
        if (!IsAlgorithmValid(args[3])) {
            return false;
        }
        return true;
    }
}
