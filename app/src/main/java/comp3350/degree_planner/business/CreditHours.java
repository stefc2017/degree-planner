package comp3350.degree_planner.business;

import java.util.List;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.persistence.DataAccess;

/**
 * Created by Matt on 6/4/2017.
 *
 * Generates number of credit hours taken by a student
 */

public class CreditHours {
    private DataAccess dataAccess;

    public CreditHours() { dataAccess = Services.getDataAccess(); }
    public CreditHours( DataAccess dataAccess ){ this.dataAccess = dataAccess; }

    public int calculateCreditHours( List<Course> courses ){
        int cHours = 0;
        for( Course taken : courses ){
                cHours += taken.getCreditHours();
        }

        return cHours;
    }

    public int getCreditHoursTaken( int studentId ){
        List<Course> courses = dataAccess.getCoursesTaken( studentId );

        return calculateCreditHours( courses );
    }
    public int getRequiredCreditHoursTaken( int studentId, int degreeId ){
        List<Course> courses = dataAccess.getDegreeCoursesTaken( studentId, degreeId );

        return calculateCreditHours( courses );
    }
}
