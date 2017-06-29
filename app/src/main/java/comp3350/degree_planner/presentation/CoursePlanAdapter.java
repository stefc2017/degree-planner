package comp3350.degree_planner.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.business.Season;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CoursePlan;

/**
 * Created by Penny He on 6/20/2017.
 *
 * Custom adaptor
 * Renders different layout for course and header
 */

public class CoursePlanAdapter extends BaseAdapter {
    private List coursePlansAndHeaders;
    private static final int COURSEPLAN = 0;
    private static final int SECTION_HEADER = 1;
    private static final int VIEW_TYPE_COUNT = 2;
    private LayoutInflater inflater;
    private final Context myContext;

    public CoursePlanAdapter(Context c, List list){
        myContext = c;
        coursePlansAndHeaders = list;
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
                    view = inflater.inflate(android.R.layout.simple_list_item_1, null);
                    break;
                case SECTION_HEADER:
                    view = inflater.inflate(R.layout.listview_header, null);
                    // Alternate color between section headers
                    if(position % 2 == 0){
                        view.setBackgroundResource(R.color.colorLightBlue);
                    }else{
                        view.setBackgroundResource(R.color.colorAccent);
                    }
            }
        }

        // Fill in data
        switch (getItemViewType(position)){
            case COURSEPLAN:
                TextView courseName = (TextView)view.findViewById(android.R.id.text1);
                // Display course name
                courseName.setText(((CoursePlan)coursePlansAndHeaders.get(position)).getCourse().getName());
                break;
            case SECTION_HEADER:
                TextView headerTerm = (TextView)view.findViewById(R.id.sectionHeaderTerm);
                TextView headerYear = (TextView)view.findViewById(R.id.sectionHeaderYear);
                int term = (Integer)((ArrayList)coursePlansAndHeaders.get(position)).get(0);
                int year = (Integer)((ArrayList)coursePlansAndHeaders.get(position)).get(1);
                System.out.println(term);
                System.out.println(year);
                if(term == Season.FALL.ordinal()){
                    headerTerm.setText(myContext.getText(R.string.fall));
                    headerYear.setText(year+"");
                }else if(term == Season.SUMMER.ordinal()){
                    headerTerm.setText(myContext.getText(R.string.summer));
                    headerYear.setText(year+"");
                }else{
                    headerTerm.setText(myContext.getText(R.string.winter));
                    headerYear.setText(year+"");
                }
                break;
        }

        return view;
    }
}


