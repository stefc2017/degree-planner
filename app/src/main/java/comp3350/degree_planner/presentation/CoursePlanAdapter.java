package comp3350.degree_planner.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.degree_planner.R;
import comp3350.degree_planner.objects.Course;

/**
 * Created by Penny He on 6/20/2017.
 *
 * Custom adaptor
 * Renders different layout for course and header
 */

public class CoursePlanAdapter extends BaseAdapter {
    private ArrayList coursePlansAndHeaders;
    private static final int COURSE = 0;
    private static final int SECTION_HEADER = 1;
    private static final int VIEW_TYPE_COUNT = 2;
    private LayoutInflater inflater;

    public CoursePlanAdapter(Context c, ArrayList list){
        coursePlansAndHeaders = list;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position){
        if(coursePlansAndHeaders.get(position) instanceof Course){
            return COURSE;
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
        if(view == null){
            // Fill the basic layout
            switch (getItemViewType(position)){
                case COURSE:
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
            case COURSE:
                TextView courseName = (TextView)view.findViewById(android.R.id.text1);
                // Display course name
                courseName.setText(((Course)coursePlansAndHeaders.get(position)).getName());
                break;
            case SECTION_HEADER:
                TextView header = (TextView)view.findViewById(R.id.sectionHeader);
                // Display term
                header.setText(((String)coursePlansAndHeaders.get(position)));
                break;
        }

        return view;
    }
}
