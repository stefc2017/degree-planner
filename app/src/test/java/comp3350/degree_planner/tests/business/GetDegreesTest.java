package comp3350.degree_planner.tests.business;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.business.AccessDegrees;
import comp3350.degree_planner.objects.Degree;

import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.stub.DataAccessStub;

/**
 * Created by Penny He on 6/7/2017.
 *
 * Unit tests for getting degrees
 */

public class GetDegreesTest {
    private AccessDegrees degrees;

    // Sets up test data with the entries we need for all tests below
    @Before
    public void setUp() throws Exception {
        final DataAccess testData = new DataAccessStub() {
            private List<Degree> degrees;

            @Override
            public void open(String dbName) {
                // Create Degrees

                degrees = new ArrayList<Degree>();
                degrees.add(new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0));
            }

            @Override
            public List<Degree> getAllDegrees() {
                return degrees;
            }

            @Override
            public Degree getDegreeById(int degreeId) {
                List<Degree> allDegrees = degrees;
                int numberOfDegrees = allDegrees.size();
                Degree degree = null;
                int index = 0;

                while (index < numberOfDegrees && (allDegrees.get(index)).getId() != degreeId) {
                    index++;
                }

                if (index < numberOfDegrees && (allDegrees.get(index)).getId() == degreeId) {
                    degree = allDegrees.get(index);
                }

                return degree;
            }//end getDegreeById
        };

        degrees = new AccessDegrees(testData);
        testData.open(Main.dbName);
    }// end setUp

    @Test
    public void testGetAllDegrees() throws SQLException {
        System.out.println("\nStarting Get Degrees Test: Get all available degree programs");
        assertNotNull("Degree list should not be null", degrees.getAllDegrees());
        assertEquals("Degree list should have size one", 1, degrees.getAllDegrees().size());
        assertEquals("Wrong degree in degree list", "Computer Science Major", degrees.getAllDegrees().get(0).getName());
        System.out.println("Finished Get Degrees Test: Get all available degree programs");
    }

    @Test
    public void testGetDegreeByInvalidDegreeId() throws SQLException {
        System.out.println("\nStarting Get Degrees Test: Get degree by invalid degree Id");
        assertNull("Returned Degree should be null when degreeId is invalid", degrees.getDegreeById(-5));
        System.out.println("Finished Get Degrees Test: Get degree by invalid degree Id");

    }

    @Test
    public void testGetDegreeByValidDegreeId() throws SQLException {
        System.out.println("\nStarting Get Degrees Test: Get degree by valid degree Id");
        assertNotNull("Returned Degree should not be null", degrees.getDegreeById(1));
        assertEquals("Wrong degree returned", "Computer Science Major", degrees.getDegreeById(1).getName());
        System.out.println("Finished Get Degrees Test: Get degree by valid degree Id");
    }

}
