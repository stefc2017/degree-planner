package comp3350.degree_planner.business;

import java.util.ArrayList;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.persistence.DataAccessStub;

/**
 * Created by Matt on 6/4/2017.
 *
 * Generates number of credit hours taken by a student
 */

public class CreditHours {
    private DataAccessStub dataAccess;

    public CreditHours() { dataAccess = (DataAccessStub) Services.getDataAccess(); }

    public int calculateCreditHours( ArrayList<Course> courses ){
        int cHours = 0;
        for( Course taken : courses ){
                cHours += taken.getCreditHours();
        }

        return cHours;
    }

    public int getCreditHoursTaken( int studentId ){
        ArrayList<Course> courses = dataAccess.getCoursesTaken( studentId );

        return calculateCreditHours( courses );
    }
    public int getRequiredCreditHoursTaken( int studentId, int degreeId ){
        ArrayList<Course> courses = dataAccess.getDegreeCoursesTaken( studentId, degreeId );

        return calculateCreditHours( courses );
    }
}
