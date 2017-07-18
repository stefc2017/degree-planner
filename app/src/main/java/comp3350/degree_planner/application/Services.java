package comp3350.degree_planner.application;

import java.sql.SQLException;

import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessObject;
import comp3350.degree_planner.persistence.DataAccessStub;

/**
 * Created by Kaleigh on 2017-06-01.
 *
 * This class contains methods for accessing the Database stub.
 *
 * Much of the code is from the sample project, srsys.
 */

public class Services {

    private static DataAccess dataAccessService = null;

    public static DataAccess createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessObject(dbName);
            try {
                dataAccessService.open(Main.getDBPathName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataAccessService;
    }

    public static DataAccess createDataAccess(final DataAccess alternateDataAccessService)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            try {
                dataAccessService.open(Main.getDBPathName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataAccessService;
    }

    public static void setDataAccessService (final DataAccess newDataAccessService) {
        dataAccessService = newDataAccessService;
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
