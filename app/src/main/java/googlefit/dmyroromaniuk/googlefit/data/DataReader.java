package googlefit.dmyroromaniuk.googlefit.data;

import android.renderscript.Element;
import android.util.Log;

import com.google.android.gms.fitness.request.DataReadRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by dmyroromaniuk on 13.06.16.
 */
public class DataReader {
    private static final String TAG = "data";

    // Setting a start and end date using a range of 1 week before this moment.
    Calendar cal = Calendar.getInstance();
    Date now = new Date();
    cal.setTime(now);
    long endTime = cal.getTimeInMillis();
    cal.add(Calendar.WEEK_OF_YEAR, -1);
    long startTime = cal.getTimeInMillis();

    java.text.DateFormat dateFormat = getDateInstance();
    Log.e(TAG, "Range Start: " + dateFormat.format(startTime));
    Log.e(TAG, "Range End: " + dateFormat.format(endTime));

    DataReadRequest readRequest = new DataReadRequest.Builder()
            // The data request can specify multiple data types to return, effectively
            // combining multiple data queries into one call.
            // In this example, it's very unlikely that the request is for several hundred
            // datapoints each consisting of a few steps and a timestamp.  The more likely
            // scenario is wanting to see how many steps were walked per day, for 7 days.
            .aggregate(Element.DataType.TYPE_STEP_COUNT_DELTA, Element.DataType.AGGREGATE_STEP_COUNT_DELTA)
            // Analogous to a "Group By" in SQL, defines how data should be aggregated.
            // bucketByTime allows for a time span, whereas bucketBySession would allow
            // bucketing by "sessions", which would need to be defined in code.
            .bucketByTime(1, TimeUnit.DAYS)
            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
            .build();
}
