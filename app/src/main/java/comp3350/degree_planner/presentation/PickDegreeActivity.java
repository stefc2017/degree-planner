package comp3350.degree_planner.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.R;
import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessDegrees;
import comp3350.degree_planner.objects.Degree;

public class PickDegreeActivity extends AppCompatActivity {

    private AccessDegrees accessDegrees;
    private List<Degree> degreeList;
    private ArrayAdapter<Degree> degreeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_degree);
        Toolbar toolbar = (Toolbar) findViewById(R.id.pick_degree_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        accessDegrees = new AccessDegrees(Services.getDataAccess());
        degreeList = new ArrayList<Degree>();
        try {
            degreeList = accessDegrees.getAllDegrees();
        } catch (SQLException e) {
            displayErrorMessage(e);
        }
        if(degreeList != null){
            degreeListAdapter = new ArrayAdapter<Degree>(this, android.R.layout.simple_list_item_1, android.R.id.text1, degreeList){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text = (TextView) view.findViewById(android.R.id.text1);

                    text.setText(degreeList.get(position).getName());
                    return view;
                }
            };
            ListView allDegrees = (ListView)findViewById(R.id.allDegrees);
            allDegrees.setAdapter(degreeListAdapter);

            allDegrees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the selected degree
                    Degree d = (Degree)parent.getItemAtPosition(position);
                    int degreeId = d.getId();
                    Intent intent = new Intent(PickDegreeActivity.this, DegreeProgressActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("degreeId", degreeId);
                    intent.putExtras(b);
                    PickDegreeActivity.this.startActivity(intent);
                }
            });
        }
    }

    private void displayErrorMessage(Exception e){
        if (e instanceof SQLException) {
            Toast.makeText(this, R.string.error_sql_exception, Toast.LENGTH_SHORT).show();
        }
    }
}
