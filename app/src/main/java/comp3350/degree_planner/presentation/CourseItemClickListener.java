package comp3350.degree_planner.presentation;

/**
 * Created by Penny He on 7/7/2017.
 *
 * A custom click listener used in custom list adaptor
 */

public interface CourseItemClickListener {
    void onRemoveButtonClick(int courseId);
    void onMoveButtonClick(int courseId);
}
