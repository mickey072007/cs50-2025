import java.io.*;

public class testPuzzleLoader {
    public static void main(String[] args){
        try {
            String[] testIncorrectNumberOfConstraints = {"2,1", "3,3", "4,4"};
            String[] testIncorrectColumnAndRowConstraints = {"1","2","3","4","5","6","7","8","9","10"};

            //test working file
            
            BufferedReader testCatFileReader = new BufferedReader(new FileReader("puzzleJsonFiles/horse.json"));
            PuzzleLoader testLoader = new PuzzleLoader(testCatFileReader);
            NonogramModel puzzle = testLoader.getLoadedPuzzle();

            System.out.println("Testing correct grid creation:");
            puzzle.outputGrid();
            testLoader.testGridCreation(puzzle, puzzle.getRowCons(), puzzle.getColCons());
            System.out.println();

            //test incompatible files :

            //missing name:
            System.out.println("Testing file with missing name element:");
            BufferedReader testMissingNameFileReader = new BufferedReader(new FileReader("puzzleJsonFiles/incorrect_format_missing_name.json"));
            PuzzleLoader testMissingName = new PuzzleLoader(testMissingNameFileReader);
            NonogramModel testName = testMissingName.getLoadedPuzzle();
            testLoader.testGridCreation(testName, testIncorrectNumberOfConstraints, testIncorrectNumberOfConstraints);
            System.out.println();

            //missing columns:
            System.out.println("Testing file with missing columns element:");
            BufferedReader testMissingColumnsFileReader = new BufferedReader(new FileReader("puzzleJsonFiles/incorrect_format_missing_columns.json"));
            PuzzleLoader testMissingColumns = new PuzzleLoader(testMissingColumnsFileReader);
            NonogramModel testCols = testMissingColumns.getLoadedPuzzle();
            testLoader.testGridCreation(testCols, testIncorrectNumberOfConstraints, testIncorrectNumberOfConstraints);
            System.out.println();

            //missing rows:
            System.out.println("Testing file with missing rows element:");
            BufferedReader testMissingRowsFileReader = new BufferedReader(new FileReader("puzzleJsonFiles/incorrect_format_missing_rows.json"));
            PuzzleLoader testMissingRows = new PuzzleLoader(testMissingRowsFileReader);
            NonogramModel testRow = testMissingRows.getLoadedPuzzle();
            testLoader.testGridCreation(testRow,testIncorrectNumberOfConstraints, testIncorrectNumberOfConstraints);
            System.out.println();


            //testing incompatible number of columns:
            System.out.println("Testing constraints with incorrect number of columns:");
            testLoader.testGridCreation(puzzle, puzzle.getRowCons(), testIncorrectNumberOfConstraints);
            System.out.println();

            //testing incompatible number of rows:
            System.out.println("Testing constraints with incorrect number of rows:");
            testLoader.testGridCreation(puzzle, testIncorrectNumberOfConstraints, puzzle.getColCons());
            System.out.println();

            //testing correct number of columns, but incorrect constraints
            System.out.println("Testing incorrect column constraints:");
            testLoader.testGridCreation(puzzle, puzzle.getRowCons(), testIncorrectColumnAndRowConstraints);
            System.out.println();

            //testing correct number of rows, but incorrect constraints
            System.out.println("Testing incorrect row constraints:");
            testLoader.testGridCreation(puzzle, testIncorrectColumnAndRowConstraints, puzzle.getColCons());
            System.out.println();

        }

        catch(FileNotFoundException e){
            System.out.println("FileNotFound: "+e.getMessage());
        }
       
    }
}
