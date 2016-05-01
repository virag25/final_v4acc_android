package amigoinn.example.v4sales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.v4sales.R;

import amigoinn.adapters.Custom_Route_Home;

import java.util.ArrayList;

/**
 * Created by Manthan on 28/09/2015.
 */
public class FragmentCategoryItemAttendance extends Fragment {
    View view;
    ListView lvroute;
    Context context;
    ArrayList<String> labels = new ArrayList<String>();
    public static String[] leftline = {"", "",""};
    public static String[] rightline = {"","",""};
    public static String[] leftbox = {"Places", "Places","Places"};
    public static String[] rightbox = {"55","78","13"};
    public static int [] middot = {R.drawable.tl1middot, R.drawable.tl1middot,R.drawable.tl1middot};
    public static int[] leftdot = {R.drawable.tl1dot,R.drawable.tl1dot,R.drawable.tl1dot};
    public static int[] rightdot = {R.drawable.tl1dot,R.drawable.tl1dot,R.drawable.tl1dot};
    TextView txtSubmit;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category_item_attendence, container, false);
        context=view.getContext();
        lvroute = (ListView) view.findViewById(R.id.lvroute);
        txtSubmit = (TextView) view.findViewById(R.id.txtSubmit);
        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    Intent in = new Intent(context, GoogleMapActivity.class);
                    context.startActivity(in);
                }catch (Exception ex)
                {
                    Log.e("Error", ex.toString());
                }
            }
        });
        ArrayList<String> routes=new ArrayList<>();
        for (int j=1;j<=10;j++)
        {
            routes.add("Route "+j);
        }
        lvroute.setAdapter(new Custom_Route_Home(view.getContext(),routes));

        return view;
    }



}
