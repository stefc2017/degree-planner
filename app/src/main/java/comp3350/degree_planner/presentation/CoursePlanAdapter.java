package comp3350.degree_planner.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.business.Season;
import comp3350.degree_planner.objects.CoursePlan;

/**
 * Created by Penny He on 6/20/2017.
 *
 * Custom adaptor
 * Renders different layout for course and header
 */

public class CoursePlanAdapter extends BaseAdapter {
    private List coursePlansAndHeaders;
    private CourseItemClickListener listener;
    private static final int COURSEPLAN = 0;
    private static final int SECTION_HEADER = 1;
    private static final int VIEW_TYPE_COUNT = 2;
    private LayoutInflater inflater;
    private final Context myContext;
    private boolean deleteMode = false;
    private static int nextColor = 0; // 0 means blue, 1 means red color for displaying coursePlans headers

    public CoursePlanAdapter(Context c, List list, CourseItemClickListener listener){
        myContext = c;
        coursePlansAndHeaders = list;
        this.listener = listener;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position){
        if(coursePlansAndHeaders.get(position) instanceof CoursePlan){
            return COURSEPLAN;
        }else{
            return SECTION_HEADER;
        }
    }

    @Override
    public int getViewTypeCount() { return VIEW_TYPE_COUNT; }

    @Override
    public int getCount(){
        return coursePlansAndHeaders.size();
    }

    @Override
    public Object getItem(int position) { return coursePlansAndHeaders.get(position); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        String s;

        if(view == null){
            // Fill the basic layout
            switch (getItemViewType(position)){
                case COURSEPLAN:
                    view = inflater.inflate(R.layout.simple_list_item, null);
                    break;
                case SECTION_HEADER:
                    view = inflater.inflate(R.layout.listview_header, null);
                    // Alternate color between section headers
                    if(nextColor == 0){
                        view.setBackgroundResource(R.color.colorLightBlue);
                        nextColor++;
                    }else if(nextColor == 1){
                        view.setBackgroundResource(R.color.colorAccent);
                        nextColor = 0;
                    }
            }
        }

        // Fill in data
        switch (getItemViewType(position)){
            case COURSEPLAN:
                TextView courseName = (TextView)view.findViewById(R.id.text1);
                LinearLayout deleteButton = (LinearLayout) view.findViewById(R.id.deleteButton_courses);
                Button courseButton = (Button) deleteButton.findViewById(R.id.button_text1);
                final CoursePlan coursePlan = ((CoursePlan)coursePlansAndHeaders.get(position));

                // Display course name
                courseName.setText(((CoursePlan)coursePlansAndHeaders.get(position)).getCourse().getName());

                // Toggle delete button in each row
                if(deleteButton != null){
                    deleteButton.setVisibility(deleteMode? View.VISIBLE : View.INVISIBLE);
                }

                if(courseButton != null && coursePlan != null) {
                    courseButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if(listener != null) {
                                // Removal happens in CoursePlanActivity
                                listener.onRemoveButtonClick(coursePlan.getId());
                            }
                        }
                    });
                }
                break;
            case SECTION_HEADER:
                TextView headerTerm = (TextView)view.findViewById(R.id.sectionHeaderTerm);

                int term = (Integer)((ArrayList)coursePlansAndHeaders.get(position)).get(0);
                int year = (Integer)((ArrayList)coursePlansAndHeaders.get(position)).get(1);

                if(term == Season.FALL.getValue()){
                    headerTerm.setText(myContext.getText(R.string.fall).toString() + " " + year);
                }else if(term == Season.SUMMER.getValue()){
                    headerTerm.setText(myContext.getText(R.string.summer).toString() + " " + year);
                }else{
                    headerTerm.setText(myContext.getText(R.string.winter).toString() + " " + year);
                }
                break;
        }

        return view;
    }

    public void toggleDeleteMode() { deleteMode = !deleteMode; }

    public void refreshList(List items){
        coursePlansAndHeaders = items;
        // Notify adapter that the list has been updated, it should call getView again to refresh
        notifyDataSetChanged();
    }

}


