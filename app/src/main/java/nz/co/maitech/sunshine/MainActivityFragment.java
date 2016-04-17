package nz.co.maitech.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String weekForecast[] = new String[6];
        weekForecast[0] = new String("Today - Sunny - 88 / 63");
        weekForecast[1] = new String("Tomorrow - Foggy - 70 / 46");
        weekForecast[2] = new String("Wednesday - Cloudy - 72 / 63");
        weekForecast[3] = new String("Thursday - Rainy - 64 / 51");
        weekForecast[4] = new String("Friday - Foggy - 70 / 46");
        weekForecast[5] = new String("Saturday - Sunny - 76 / 68");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayAdapter<String> mForecastAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, weekForecast);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);

        listView.setAdapter(mForecastAdapter);

        return rootView;
    }
}
