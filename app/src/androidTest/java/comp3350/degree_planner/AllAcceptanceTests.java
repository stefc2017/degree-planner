package comp3350.degree_planner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.degree_planner.acceptance.ViewDegreeRequirementsTest;
import comp3350.degree_planner.acceptance.ViewDegreesTest;

/**
 * Created by Tiffany Jiang on 2017-07-08.
 *
 * Info for acceptance tests:
 * - Go to https://github.com/RobotiumTech/robotium/wiki/Getting-Started
 * and dl the example project under Android Studio, it has more up-to-date test examples (using JUnit4) compared to srsys
 * - Javadoc for Solo: http://recorder.robotium.com/javadoc/
 * - See ViewDegreesTest for setup
 * - Try to avoid using hardcoded string if possible - you can use
 * solo.getString(R.string.string_name) to get strings from strings.xml
 * - Don't forget to test for invalid/error cases, esp for user input
 * - Don't forget to revert your changes at the end of tests since we're dealing w/ real db
 * - When done, add to list of all acceptance tests below
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ViewDegreesTest.class,
        ViewDegreeRequirementsTest.class
})
public class AllAcceptanceTests {
}
