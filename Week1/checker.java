public class checker {
    static String errors = "";

    // Check whether the grid satisfies both row and column constraints
    public static String checkGrid(String[][] answer, String[] rowConstraints, String[] columnConstraints) {
        // System.out.println("checker");
        // for (int i = 0; i < rowConstraints.length ; i++){
        //     for (int j = 0; j < columnConstraints.length ; j++){
        //         System.out.print(answer[i][j] + "   ");
        //         }
        //     System.out.println("");
        //  }
        String correct = "";
        boolean rowCorrect = checkRows(answer, rowConstraints, columnConstraints); // Check each row against constraints
        boolean colCorrect = checkColumns(answer, columnConstraints, rowConstraints); // Check each column against constraints

        if (rowCorrect && colCorrect) {
            System.out.println("Puzzle is correct!");
             correct = "Puzzle is correct!";
        } else {
            System.out.println("Puzzle is incorrect!");
             correct = "<html>" + errors + "</html>";
        }
        return correct;
    }

    // Validate all rows in the grid against their corresponding constraints
    public static boolean checkRows(String[][] grid, String[] rowConstraints, String[] columnConstraints) {
        boolean allCorrect = true;

        for (int row = 0; row < rowConstraints.length; row++) {
            String[] constraintSet = rowConstraints[row].split(",");
            int constraintIndex = 0;
            int count = 0;

            for (int col = 0; col < columnConstraints.length; col++) {
                String cell = grid[row][col];
                String s = cell;
                
                //System.out.println("Constraint index at column " + col + ": " + constraintIndex);
                if (cell == null || cell.equals("EMPTY") || cell.equals("UNKNOWN")) {
                    // If we hit an EMPTY or UNKNOWN, check if a block ended before
                    if (count > 0) {
                        if (constraintIndex >= constraintSet.length || count != Integer.parseInt(constraintSet[constraintIndex].split(":")[0])) {
                            System.out.println("Row " + row + " invalid block length at column " + col);
                            errors = errors + "Row " + (row+1) + " invalid block length" + "<br/>";
                            
                            allCorrect = false;
                        }
                        constraintIndex++;
                        count = 0; // Reset block counter after encountering empty space
                    }
                } else if (cell.equals("FILLED") || cell.startsWith("COLOUR")) {
                    if(col > 0) {
                        if(cell.startsWith("COLOUR") && !cell.equals(grid[row][col-1]) && (!grid[row][col - 1].equals("EMPTY") && !grid[row ][col - 1].equals("UNKNOWN") && grid[row ][col - 1] != null)) {
                            
                            count = 0;
                            constraintIndex ++;
                        }
                    }
                    count++; // Count filled or colored cells
                }
                

                // else if (cell.startsWith("COLOUR") ){
                //     if (col > 0) {
                //         if(cell != grid[row - 1] [col - 1]) {
                //             count = 1;
                //             constraintIndex++;
                //             // String s = cell;
                //             // while (grid[row][col].equals(s)) {
                //             //     count ++;
                //             // }
                //         }
                //     }
                    

                // }
            }

            // Handle case if the last block ended at the last column
            if (count > 0) {
                if (constraintIndex >= constraintSet.length || count != Integer.parseInt(constraintSet[constraintIndex].split(":")[0])) {
                    System.out.println("Row " + row + " invalid final block length");
                    errors = errors + "Row " + (row+1) + " invalid final block length" + "<br/>";
                    allCorrect = false;
                }
                constraintIndex++;
            }

            if (constraintIndex != constraintSet.length) {
                System.out.println("Row " + row + " has wrong number of blocks");
                //errors = errors + "YRow " + (row+1) + " has wrong number of blocks" + "<br/>";
                allCorrect = false;
            }
        }

        return allCorrect;
    }

    // Validate all columns in the grid against their corresponding constraints
    public static boolean checkColumns(String[][] grid, String[] columnConstraints, String[] rowConstraints) {
        boolean allCorrect = true;

        for (int col = 0; col < columnConstraints.length; col++) {
            String[] constraintSet = columnConstraints[col].split(",");
            int constraintIndex = 0;
            int count = 0;

            for (int row = 0; row < rowConstraints.length; row++) {
                String cell = grid[row][col];
                if (cell == null || cell.equals("EMPTY") || cell.equals("UNKNOWN")) {
                    // If we hit an EMPTY or UNKNOWN, check if a block ended before
                    if (count > 0) {
                        if (constraintIndex >= constraintSet.length || count != Integer.parseInt(constraintSet[constraintIndex].split(":")[0])) {
                            System.out.println("DColumn " + col + " invalid block length at row " + row);
                            errors = errors + "Column " + (col+1) + " invalid block length" + "<br/>";
                            allCorrect = false;
                        }
                        constraintIndex++;
                        
                        count = 0; // Reset block counter after encountering empty space
                    }
                } else if (cell.equals("FILLED") || cell.startsWith("COLOUR")) {
                    if(row > 0) {
                        if(cell.startsWith("COLOUR")  && !cell.equals(grid[row-1][col]) && (!grid[row -1 ][col].equals("EMPTY") && !grid[row -1 ][col].equals("UNKNOWN") && grid[row -1 ][col] != null)) {
                            
                            count = 0;
                            constraintIndex ++;
                        }
                    }
                    count++; 
                }

                

                // else if (cell.startsWith("COLOUR") ){
                //     if (row > 0) {
                //         if(cell != grid[row - 1] [col - 1]) {
                //             count = 1;
                //             constraintIndex++;
                //             // String s = cell;
                //             // while (grid[row][col].equals(s)) {
                //             //     count ++;
                //             // }
                //         }
                //     }
                    

                // }
            }

            // Handle case if the last block ended at the last row
            if (count > 0) {
                if (constraintIndex >= constraintSet.length || count != Integer.parseInt(constraintSet[constraintIndex].split(":")[0])) {
                    System.out.println("Column " + col + " invalid final block length");
                    errors = errors + "Column " + (col+1) + " invalid final block length" + "<br/>";
                    allCorrect = false;
                }
                constraintIndex++;
            }

            if (constraintIndex != constraintSet.length) {
                System.out.println("Column " + col + " has wrong number of blocks");
                //errors = errors + "GColumn " + (col+1) + " has wrong number of blocks" + "<br/>";
                allCorrect = false;
            }
        }

        return allCorrect;
    }
}

