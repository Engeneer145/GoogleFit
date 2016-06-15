package googlefit.dmyroromaniuk.googlefit.data;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.result.SessionReadResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dmyroromaniuk on 15.06.16.
 */
public class SessionDataReader {
    private static final String TAG = "data";

    private List<DayStats> list;

    private Context mContext;

    private volatile static SessionDataReader instance;

    private SessionDataReader(Context context) {
        mContext = context;

        list = new ArrayList<>();
    }

    public static SessionDataReader getInstance(Context context) {
        if (instance == null)
            instance = new SessionDataReader(context);

        return instance;
    }

    public List<DayStats> getStepsArray(GoogleApiClient mGoogleApiFitnessClient) {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.YEAR, 2000);
        long startTime = cal.getTimeInMillis();

        /*
        // Build a session read request
        SessionReadRequest sessionReadRequest = new SessionReadRequest.Builder()
                .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS)
                .read(DataType.TYPE_SPEED)
                .setSessionName(SAMPLE_SESSION_NAME)
                .build();

        // Invoke the Sessions API to fetch the session with the query and wait for the result
        // of the read request. Note: Fitness.SessionsApi.readSession() requires the
        // ACCESS_FINE_LOCATION permission.
        SessionReadResult sessionReadResult =
                Fitness.SessionsApi.readSession(mGoogleApiFitnessClient, sessionReadRequest)
                        .await(1, TimeUnit.MINUTES);

        // Get a list of the sessions that match the criteria to check the result.
        Log.i(TAG, "Session read was successful. Number of returned sessions is: "
                + sessionReadResult.getSessions().size());
        for (Session session : sessionReadResult.getSessions()) {
            // Process the session
            dumpSession(session);

            // Process the data sets for this session
            List<DataSet> dataSets = sessionReadResult.getDataSet(session);
            for (DataSet dataSet : dataSets) {
                dumpDataSet(dataSet);
            }
        }

        */
        return list;
    }

    public List<DayStats> getStepsArray() {
        return list;
    }
}
