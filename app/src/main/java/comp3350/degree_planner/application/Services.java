package comp3350.degree_planner.application;

import java.sql.SQLException;

import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessCourseOfferings;
import comp3350.degree_planner.persistence.DataAccessCoursePlans;
import comp3350.degree_planner.persistence.DataAccessCourses;
import comp3350.degree_planner.persistence.DataAccessStudents;
import comp3350.degree_planner.persistence.DataAccessTermTypes;
import comp3350.degree_planner.persistence.hsqldb.DataAccessCourseOfferingsObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessCoursePlansObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessCoursesObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessStudentsObject;
import comp3350.degree_planner.persistence.hsqldb.DataAccessTermTypesObject;

/**
 * Created by Kaleigh on 2017-06-01.
 *
 * This class contains methods for accessing the Database stub.
 *
 * Much of the code is from the sample project, srsys.
 */

public class Services {

    private static DataAccess dataAccessService = null;
    private static DataAccessCoursePlans dataAccessCoursePlans;
    private static DataAccessStudents dataAccessStudents;
    private static DataAccessTermTypes dataAccessTermTypes;
    private static DataAccessCourses dataAccessCourses;
    private static DataAccessCourseOfferings dataAccessCourseOfferings;

    //Default
    public static DataAccess createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessObject(dbName);
            try {
                String test = Main.getDBPathName();
                dataAccessService.open(Main.getDBPathName());
//
//                dataAccessCoursePlans = new DataAccessCoursePlansObject();
//                dataAccessStudents = new DataAccessStudentsObject();
//                dataAccessTermTypes = new DataAccessTermTypesObject();
//                dataAccessCourses = new DataAccessCoursesObject();
//                dataAccessCourseOfferings = new DataAccessCourseOfferingsObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataAccessService;
    }

    //Alternative
    public static DataAccess createDataAccess(final DataAccess alternateDataAccessService, final DataAccessCoursePlans alternateDataAccessCoursePlans, final DataAccessStudents alternateDataAccessStudents, final DataAccessTermTypes alternateDataAccessTermTypes, final DataAccessCourses alternateDataAccessCourses, final DataAccessCourseOfferings alternateDataAccessCourseOfferings)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            try {
                dataAccessService.open(Main.getDBPathName());

                dataAccessCoursePlans = alternateDataAccessCoursePlans;
                dataAccessStudents = alternateDataAccessStudents;
                dataAccessTermTypes = alternateDataAccessTermTypes;
                dataAccessCourses = alternateDataAccessCourses;
                dataAccessCourseOfferings = alternateDataAccessCourseOfferings;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataAccessService;
    }

    //Injection
    public static void setDataAccessService (final DataAccess newDataAccessService, final DataAccessCoursePlans newDataAccessCoursePlans, final DataAccessStudents newDataAccessStudents, final DataAccessTermTypes newDataAccessTermTypes, final DataAccessCourses newDataAccessCourses, final DataAccessCourseOfferings newDataAccessCourseOfferings) {
        dataAccessService = newDataAccessService;

        dataAccessCoursePlans = newDataAccessCoursePlans;
        dataAccessStudents = newDataAccessStudents;
        dataAccessTermTypes = newDataAccessTermTypes;
        dataAccessCourses = newDataAccessCourses;
        dataAccessCourseOfferings = newDataAccessCourseOfferings;
    }

    public static DataAccess getDataAccess()
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static DataAccessCoursePlans getDataAccessCoursePlans() {
        return dataAccessCoursePlans;
    }

    public static DataAccessStudents getDataAccessStudents() {
        return dataAccessStudents;
    }

    public static DataAccessTermTypes getDataAccessTermTypes() {
        return dataAccessTermTypes;
    }

    public static DataAccessCourses getDataAccessCourses() {
        return dataAccessCourses;
    }

    public static DataAccessCourseOfferings getDataAccessCourseOfferings() {
        return dataAccessCourseOfferings;
    }

    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            try {
                dataAccessService.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        dataAccessService = null;
    }
}
