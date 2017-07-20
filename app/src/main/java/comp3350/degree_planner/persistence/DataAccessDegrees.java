package comp3350.degree_planner.persistence;

import java.sql.SQLException;

import comp3350.degree_planner.objects.Degree;

/**
 * Created by tiffanyjiang on 2017-07-17.
 */

public interface DataAccessDegrees {
    Degree getDegreeById(int degreeId) throws SQLException;
}
