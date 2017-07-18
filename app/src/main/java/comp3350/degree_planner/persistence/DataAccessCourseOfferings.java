package comp3350.degree_planner.persistence;

import java.sql.SQLException;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public interface DataAccessCourseOfferings {
    boolean courseOffered (int courseId, int termTypeId) throws SQLException;
}
