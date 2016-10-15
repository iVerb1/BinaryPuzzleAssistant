package bpa.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Test cases for {@code GridArrays}.
 * 
 * @author iVerb
 * @since 3-4-13
 */
public class GridArraysTest extends GridTestCases {
    
    @Override
    protected void setInstance(final String gridSpecification) {
        try {
            File file = new File(gridSpecification + ".txt");
            Scanner sc = new Scanner(file);
            List<Constraint> constraints = Arrays.asList();
            instance = new GridArrays(sc, constraints);
        }
        catch (FileNotFoundException e) {
            System.out.println("Test file " + gridSpecification + ".txt could "
                    + "not be found. Aborting all tests.");
            System.exit(1);
        }
    }    
    
    @Override
    protected Grid createInstance(final Scanner scanner) {
        List<Constraint> constraints = Arrays.asList();
        return new GridArrays(scanner, constraints);
    }
    
}