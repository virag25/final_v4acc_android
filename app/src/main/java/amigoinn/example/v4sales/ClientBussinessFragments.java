package amigoinn.example.v4sales;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.v4sales.R;

/**
 * Created by Virag kuvadia on 17-04-2016.
 */
public class ClientBussinessFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.client_bussiness_fragmants,container,false);
        return v;
    }


}
