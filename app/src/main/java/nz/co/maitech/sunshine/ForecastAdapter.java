package nz.co.maitech.sunshine;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.CursorAdapter;
import android.widget.TextView;

import nz.co.maitech.sunshine.data.WeatherContract;

/**
 * Created by Grant on 18/05/2016.
 * {@link ForecastAdapter} exposes a list of weather forecasts from
 * a {@link android.database.Cursor} to a {@link android.widget.ListView}
 */
public class ForecastAdapter extends CursorAdapter{

    public ForecastAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    /**
     * Prepare the weather high/lows for presentation
     *
     * @param high temperature
     * @param low temperature
     * @return high and low in a formatted string.
     */
    private String formatHighLows(double high, double low) {
        boolean isMetric = Utility.isMetric(mContext);
        String highLowStr = Utility.formatTemperature(high, isMetric) + "/" + Utility.formatTemperature(low, isMetric);
        return highLowStr;
    }

    /**
     * This is ported from FetchWeatherTask --- but now we go straight from the cursor to the string.
     * @param cursor ??? Where is the cursor coming from?
     * @return fully formatted string presenting the weather
     */
    private String convertCursorRowToUXFormat(Cursor cursor) {
        int idx_max_temp = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
        int idx_min_temp = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
        int idx_date = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATE);
        int idx_short_desc = cursor.getColumnIndex(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);

        String highAndLow = formatHighLows(cursor.getDouble(idx_max_temp), cursor.getDouble(idx_min_temp));

        return Utility.formatDate(cursor.getLong(idx_date)) +
                " - " + cursor.getString(idx_short_desc) +
                " - " + highAndLow;
    }

    /**
     * Remember that these views are reused as needed.
     *
     * @param context of the activity calling this class.
     * @param cursor  ???
     * @param parent The viewgroup this is attached to.
     * @return
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_forecast, parent, false);
        return view;
    }

    /**
     * This is where we fill-in the views with the contents of the cursor.
     * @param view The view we will be working with
     * @param context The context of the activity using this class.
     * @param cursor ???
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView) view;
        tv.setText(convertCursorRowToUXFormat(cursor));
    }
}
