package comp3350.degree_planner.application;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Tiffany on 2017-06-04
 */

public class Main {
	public static final String dbName = "Degree_Planner";
	private static String dbPathName = "database/Degree_Planner";

    public static void main(String[] args)
    {
		startUp();
//		shutDown();
//        System.out.println("All done");
    }

    public static void startUp()
	{
		Services.createDataAccess(dbName);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}

	public static String getDBPathName() {
		if (dbPathName == null)
			return dbName;
		else
			return dbPathName;
	}

	public static void setDBPathName(String pathName) {
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}
}
