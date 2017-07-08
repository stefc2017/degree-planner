package comp3350.degree_planner.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.degree_planner.tests.business.AddToCoursePlanTest;
import comp3350.degree_planner.tests.business.CreditHoursTest;
import comp3350.degree_planner.tests.business.GetCompletedCoursesTest;
import comp3350.degree_planner.tests.business.MoveCourseTest;
import comp3350.degree_planner.tests.business.RemoveFromCoursePlanTest;
import comp3350.degree_planner.tests.business.GetCoursePlansAndHeadersTest;
import comp3350.degree_planner.tests.business.GetDegreeCoursesTest;
import comp3350.degree_planner.tests.business.GetDegreesTest;
import comp3350.degree_planner.tests.business.GetCourseOfferingsByTermTest;
import comp3350.degree_planner.tests.objects.CourseOfferingTest;
import comp3350.degree_planner.tests.objects.CoursePlanTest;
import comp3350.degree_planner.tests.objects.CoursePrerequisiteTest;
import comp3350.degree_planner.tests.objects.CourseResultTest;
import comp3350.degree_planner.tests.objects.DegreeCourseTest;
import comp3350.degree_planner.tests.objects.DegreeCourseTypeTest;
import comp3350.degree_planner.tests.objects.DegreeTest;
import comp3350.degree_planner.tests.objects.DepartmentTest;
import comp3350.degree_planner.tests.objects.GradeTypeTest;
import comp3350.degree_planner.tests.objects.RatingTest;
import comp3350.degree_planner.tests.objects.RatingTypeTest;
import comp3350.degree_planner.tests.objects.ScienceCourseTest;
import comp3350.degree_planner.tests.objects.StudentTest;
import comp3350.degree_planner.tests.objects.TermTypeTest;
import comp3350.degree_planner.tests.objects.UserDefinedCourseTest;
import comp3350.degree_planner.tests.persistence.DataAccessTest;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 *
 * Runs all unit tests. Add all tests you want to run in the SuiteClasses below (in order)
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetCompletedCoursesTest.class,
        AddToCoursePlanTest.class,
        MoveCourseTest.class,
        RemoveFromCoursePlanTest.class,
        GetDegreeCoursesTest.class,
        GetDegreesTest.class,
        CreditHoursTest.class,
        GetCourseOfferingsByTermTest.class,
        GetCoursePlansAndHeadersTest.class,

        CourseOfferingTest.class,
        CoursePlanTest.class,
        CoursePrerequisiteTest.class,
        CourseResultTest.class,
        DegreeCourseTest.class,
        DegreeCourseTypeTest.class,
        DegreeTest.class,
        DepartmentTest.class,
        GradeTypeTest.class,
        RatingTest.class,
        RatingTypeTest.class,
        ScienceCourseTest.class,
        StudentTest.class,
        TermTypeTest.class,
        UserDefinedCourseTest.class,
        DataAccessTest.class
})

public class AllUnitTests {
}
