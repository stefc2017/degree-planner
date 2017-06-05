package comp3350.degree_planner.application;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Tiffany on 2017-06-04
 */

public class Main {

    public static void main(String[] args)
    {
		startUp();
//		shutDown();
        System.out.println("All done");
    }

    public static void startUp()
	{
		Services.createDataAccess();
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}
}
