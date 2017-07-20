package comp3350.degree_planner.persistence;

import java.sql.SQLException;

import comp3350.degree_planner.objects.Department;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public interface DataAccessDepartments {
    Department getDepartmentById(int departmentId) throws SQLException;
}
