package comp3350.degree_planner.persistence;

import java.sql.SQLException;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public interface DataAccessStudents {
    boolean isValidStudentId (int studentId) throws SQLException;
}
