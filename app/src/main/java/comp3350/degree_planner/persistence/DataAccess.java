package comp3350.degree_planner.persistence;

import java.util.ArrayList;
import comp3350.degree_planner.objects.CourseResult;

/**
 * Created by tiffanyjiang on 2017-06-06.
 */

public interface DataAccess {
    public void open();

    public void close();

    public ArrayList<CourseResult> getCourseResultsByStudentId (int studentId);
}
