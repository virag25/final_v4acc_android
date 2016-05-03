package amigoinn.example.v4sales;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import amigoinn.adapters.Custom_Home_Orders;


import com.example.v4sales.R;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import amigoinn.adapters.SectionedListActivityForFilters;
import amigoinn.adapters.SectionedListBeforeFilter;
import amigoinn.models.OverallPercentage;
import amigoinn.walkietalkie.Constants;
import amigoinn.walkietalkie.DatabaseHandler1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Manthan on 28/09/2015.
 */
public class FragmentCategoryItemRank extends Fragment implements DatePickerDialog.OnDateSetListener {
    View view;
    public List<String> list = new ArrayList<String>();
    ListView lv1;
    public static final String DATEPICKER_TAG = "datepicker";
    String monthName;
    int totalsum;
    Context context;
    DatabaseHandler1 handler1;
    ArrayList<BarEntry> entries = new ArrayList<>();
    int selectedPosition = 0;
    BarDataSet dataset;
    List<OverallPercentage> posts;
    ArrayList<String> labels = new ArrayList<String>();
    Custom_Home_Orders ordersadapter;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> extras = new ArrayList<String>();
    ArrayList<String> Product = new ArrayList<String>();
    ArrayList<String> Price = new ArrayList<String>();
    //    AutoCompleteTextView edtCode,edtProduct;
//    EditText edtQuantity,edtProduct1,edtPrice;
    TextView edtOrderDate, edtOrderdueDate;
    //        txtSubmit,txtSave,txtCancel,txtTotal;
    ImageView imgSearch;

    ImageView imgClientAdd, add_product;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.add_order_layout, container, false);
        context = view.getContext();
        final Calendar calendar = Calendar.getInstance();
        //	Calendar cal=Calendar.getInstance();
        imgSearch = (ImageView) view.findViewById(R.id.imgBrowse);


        imgClientAdd = (ImageView) view.findViewById(R.id.imgClientAdd);
        add_product = (ImageView) view.findViewById(R.id.add_product);

        imgClientAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Bundle args = new Bundle();
                FragmentManager frgManager = getFragmentManager();
                fragment = new ClientListSectionedActivity();
                fragment.setArguments(args);
                frgManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();
            }
        });

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Bundle args = new Bundle();
                FragmentManager frgManager = getFragmentManager();
                fragment = new ProductListSectionedActivity();
                fragment.setArguments(args);
                frgManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();
            }
        });

        imgSearch = (ImageView) view.findViewById(R.id.imgBrowse);
        handler1 = new DatabaseHandler1(context);
//        imgFilter=(ImageView)view.findViewById(R.id.imgBrowse1);
//        imgFilter=(ImageView)view.findViewById(R.id.imgBrowse1);
        SimpleDateFormat month_date = new SimpleDateFormat("yyyy-MM-dd");
        monthName = month_date.format(calendar.getTime());
        names.add("13");
        extras.add("111");
        Product.add("bicycle");
        Price.add("1000");
        lv1 = (ListView) view.findViewById(R.id.lvorder);
        Constants.PartyList.clear();
        Constants.addParty();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.sammplelistitemabsent, R.id.tv, Constants.PartyList);
        edtOrderDate = (TextView) view.findViewById(R.id.edtorderdate);
        edtOrderdueDate = (TextView) view.findViewById(R.id.edtOrderdue);
        Constants.Productlist.clear();
        Constants.addProducts();
        ordersadapter = new Custom_Home_Orders(getActivity());
        lv1.setAdapter(ordersadapter);
        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
        edtOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(false);
                datePickerDialog.setYearRange(2014, 2028);
                datePickerDialog.setCloseOnSingleTapDay(false);
                datePickerDialog.show(getFragmentManager(), DATEPICKER_TAG);
            }
        });
//        lv1.setAdapter(new Custom_Order(this, txttl1, imgtlbg, imgtlround, imgtlloc, txttl2, txttl3, txttl5, txttl6, txttl7, txttl4));

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, SectionedListBeforeFilter.class);
                Constants.countries = Constants.PartyList;
                Config.filterfrom = "party";
                context.startActivity(in);
            }
        });

        DatabaseHandler1 handler = new DatabaseHandler1(context);
        ArrayList<String> mastergrouplist = handler.getMastergroup();
        ArrayList<String> reportinggrouplist = handler.getReportingGroupcode();
        return view;
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        //Toast.makeText(context, "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();

        String newMonth = null;
        if ((month + 1) < 10) {
            newMonth = "0" + String.valueOf((month + 1));
        } else {
            newMonth = String.valueOf((month + 1));
        }
        //int montho=Integer.parseInt(newMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        monthName = year + "/" + newMonth + "/" + day;
        edtOrderDate.setText(monthName);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(monthName)); // Now use today date.
        c.add(Calendar.DATE, 7); // Adding 5 days
        String output = sdf.format(c.getTime());
//        System.out.println(output);
        edtOrderdueDate.setText(output);
//        tvDisplayDate.setText(year+"-"+newMonth+"-"+day);
//        new GetMarks12().execute();

    }

    public void filledittext() {
//        edtProduct1.setText(Product.get(Config.SELECTEDPOSITION).toString());
//        edtPrice.setText(Price.get(Config.SELECTEDPOSITION).toString());
//        edtQuantity.setText(extras.get(Config.SELECTEDPOSITION).toString());
//        edtCode.setText(names.get(Config.SELECTEDPOSITION).toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if (requestCode == 6 && resultCode == Activity.RESULT_OK) {
//            edtProduct.setText(Constants.selectedclient);

            //some code
        }
    }


    //////////////////////

    public class Custom_Home_Orders extends BaseAdapter {
        public Activity context;
        LayoutInflater inflater;

        public Custom_Home_Orders(Activity context)

        {
            super();
            this.context = context;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 25;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        class Holder {
            TextView name;
            TextView phone;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            // TODO Auto-generated method stub

            Holder hv;

            if (arg1 == null) {
                hv = new Holder();
                arg1 = inflater.inflate(R.layout.custom_order_home, null);
                arg1.setTag(hv);
            } else {
                hv = (Holder) arg1.getTag();
            }

            return arg1;
        }

    }

}



