package googlefit.dmyroromaniuk.googlefit.data;

import android.content.Context;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.text.format.DateFormat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessActivities;
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

import googlefit.dmyroromaniuk.googlefit.R;

/**
 * Created by dmyroromaniuk on 13.06.16.
 */
public class DataReader {
    private static final String TAG = "data";

    private ArrayList<DayStats> list;

    private Context mContext;

    private static DataReader instance;

    private DataReader(Context context) {
        mContext = context;
    }

    public static synchronized DataReader getInstance(Context context) {
        if (instance==null)
            instance = new DataReader(context);

        return instance;
    }

    public ArrayList<DayStats> getStepsArray(GoogleApiClient mGoogleApiFitnessClient) {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.YEAR, 2000);
        long startTime = cal.getTimeInMillis();

        final DataReadRequest readRequest = new DataReadRequest.Builder()
                .read(DataType.TYPE_STEP_COUNT_DELTA)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();

        DataReadResult dataReadResult =
                Fitness.HistoryApi.readData(mGoogleApiFitnessClient, readRequest).await(1, TimeUnit.MINUTES);

        DataSet stepData = dataReadResult.getDataSet(DataType.TYPE_STEP_COUNT_DELTA);

        for (DataPoint dp : stepData.getDataPoints()) {
            int totalSteps = 0;

            for(Field field : dp.getDataType().getFields()) {
                int steps = dp.getValue(field).asInt();
                totalSteps += steps;
            }

            list.add(new DayStats(totalSteps, new Date()));
        }

        return list;
    }

    public ArrayList<DayStats> getStepsArray() {
        return list;
    }
}
