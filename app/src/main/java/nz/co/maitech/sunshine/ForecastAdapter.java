package nz.co.maitech.sunshine;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Grant on 18/05/2016.
 * {@link ForecastAdapter} exposes a list of weather forecasts from
 * a {@link android.database.Cursor} to a {@link android.widget.ListView}
 */
public class ForecastAdapter extends CursorAdapter{

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;
    private static final int VIEW_TYPE_COUNT = 2;

    public ForecastAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_TODAY : VIEW_TYPE_FUTURE_DAY;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

//    /**
//     * Prepare the weather high/lows for presentation
//     *
//     * @param high temperature
//     * @param low temperature
//     * @return high and low in a formatted string.
//     */
//    private String formatHighLows(double high, double low) {
//        boolean isMetric = Utility.isMetric(mContext);
//        String highLowStr = Utility.formatTemperature(high, isMetric) + "/" + Utility.formatTemperature(low, isMetric);
//        return highLowStr;
//    }

//    /**
//     * This is ported from FetchWeatherTask --- but now we go straight from the cursor to the string.
//     * @param cursor ??? Where is the cursor coming from?
//     * @return fully formatted string presenting the weather
//     */
//    private String convertCursorRowToUXFormat(Cursor cursor) {
//        String highAndLow = formatHighLows(cursor.getDouble(ForecastFragment.COL_WEATHER_MAX_TEMP), cursor.getDouble(ForecastFragment.COL_WEATHER_MIN_TEMP));
//
//        return Utility.formatDate(cursor.getLong(ForecastFragment.COL_WEATHER_DATE)) +
//                " - " + cursor.getString(ForecastFragment.COL_WEATHER_DESC) +
//                " - " + highAndLow;
//    }

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
        // Chose the layout
        int viewType = getItemViewType(cursor.getPosition());
        int layoutId = -1;
        switch (viewType) {
            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.list_item_forecast_today;
                break;
            }
            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.list_item_forecast;
                break;
            }
        }
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
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
        // Setup the ViewHolder
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        // Using placeholder image
        viewHolder.iconView.setImageResource(R.drawable.ic_launcher);

        // Get the date
        long dateInMillis = cursor.getLong(ForecastFragment.COL_WEATHER_DATE);
        String dayName = Utility.getFriendlyDayString(context, dateInMillis);
        // Grab the textview and set the data
        viewHolder.dateView.setText(dayName);

        // Read weather forecast from cursor
        String description = cursor.getString(ForecastFragment.COL_WEATHER_DESC);
        // Set the forecast description
        viewHolder.descriptionView.setText(description);

       // Check if user is using metric or imperial
        Boolean isMetric = Utility.isMetric(context);

        //Read the high temperature
        double highTemperature = cursor.getDouble(ForecastFragment.COL_WEATHER_MAX_TEMP);
        // Set the high temperature
        viewHolder.highTempView.setText(Utility.formatTemperature(context, highTemperature, isMetric));

        //Read the low temperature
        double lowTemperature = cursor.getDouble(ForecastFragment.COL_WEATHER_MIN_TEMP);
        // Set the low temperature
        viewHolder.lowTempView.setText(Utility.formatTemperature(context, lowTemperature, isMetric));
    }

    public class ViewHolder {
        public final ImageView iconView;
        public final TextView dateView;
        public final TextView descriptionView;
        private final TextView highTempView;
        private final TextView lowTempView;

        public ViewHolder(View view) {
            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            dateView = (TextView) view.findViewById(R.id.list_item_date_textview);
            descriptionView = (TextView) view.findViewById(R.id.list_item_forecast_textview);
            highTempView = (TextView) view.findViewById(R.id.list_item_high_textview);
            lowTempView = (TextView) view.findViewById(R.id.list_item_low_textview);
        }

    }
}
