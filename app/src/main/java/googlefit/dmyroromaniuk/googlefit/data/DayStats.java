package googlefit.dmyroromaniuk.googlefit.data;

import java.util.Date;

/**
 * Created by dmyroromaniuk on 13.06.16.
 */
public class DayStats {
    private int steps;
    private Date date;

    public DayStats(int steps, Date date) {
        this.steps = steps;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getSteps() {
        return steps;
    }
}
