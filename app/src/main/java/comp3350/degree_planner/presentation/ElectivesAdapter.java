package comp3350.degree_planner.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.UserDefinedCourse;

/**
 * Created by Penny He on 7/9/2017.
 */

public class ElectivesAdapter extends BaseAdapter {
    private Context myContext;
    private List<Course> electives;
    private LayoutInflater inflater;
    private CourseItemClickListener listener;
    private boolean deleteMode = false;


    public ElectivesAdapter(Context c, List list, CourseItemClickListener listener){
        myContext = c;
        electives = list;
        this.listener = listener;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return electives.size(); }

    @Override
    public Object getItem(int position) { return electives.get(position); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        if(view == null){ view = inflater.inflate(R.layout.simple_list_item, null); }

        TextView courseName = (TextView)view.findViewById(R.id.text1);
        Button courseButton = (Button) view.findViewById(R.id.button_text1);
        final UserDefinedCourse elective = ((UserDefinedCourse)electives.get(position));

        courseName.setText(elective.getName());

        // Toggle delete button in each row
        if(courseButton != null){
            courseButton.setVisibility(deleteMode? View.VISIBLE : View.INVISIBLE);
        }

        if(courseButton != null && elective != null) {
            courseButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        // Removal happens in ViewElectivesActivity
                        listener.onRemoveButtonClick(elective.getId());
                    }
                }
            });
        }
        return view;
    }

    public void toggleDeleteMode() { deleteMode = !deleteMode; }

    public void refreshList(List items){
        electives = items;
        // Notify adapter that the list has been updated, it should call getView again to refresh
        notifyDataSetChanged();
    }
}
