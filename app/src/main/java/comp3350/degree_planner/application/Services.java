package comp3350.degree_planner.application;

import comp3350.degree_planner.persistence.DataAccessStub;

/**
 * Created by Kaleigh on 2017-06-01.
 *
 * This class contains methods for accessing the Database stub.
 *
 * Much of the code is from the sample project, srsys.
 */

public class Services {

    private static DataAccessStub dataAccessService = null;

    public static DataAccessStub createDataAccess()
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessStub();
            dataAccessService.open();
        }
        return dataAccessService;
    }

    public static DataAccessStub getDataAccess()
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
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
