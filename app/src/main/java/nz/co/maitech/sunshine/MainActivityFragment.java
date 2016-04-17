package nz.co.maitech.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String fakeData[] = new String[6];
        fakeData[0] = new String("Today - Sunny - 88 / 63");
        fakeData[1] = new String("Tomorrow - Foggy - 70 / 46");
        fakeData[2] = new String("Wednesday - Cloudy - 72 / 63");
        fakeData[3] = new String("Thursday - Rainy - 64 / 51");
        fakeData[4] = new String("Friday - Foggy - 70 / 46");
        fakeData[5] = new String("Saturday - Sunny - 76 / 68");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
