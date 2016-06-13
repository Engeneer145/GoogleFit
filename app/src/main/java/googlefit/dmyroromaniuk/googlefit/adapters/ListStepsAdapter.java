package googlefit.dmyroromaniuk.googlefit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import googlefit.dmyroromaniuk.googlefit.R;
import googlefit.dmyroromaniuk.googlefit.data.DayStats;

/**
 * Created by dmyroromaniuk on 13.06.16.
 */
public class ListStepsAdapter extends RecyclerView.Adapter<ListStepsAdapter.ViewHolder> {
    private ArrayList<DayStats> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvSteps;
        public TextView tvDate;
        public ViewHolder(TextView tvSteps, TextView tvDate) {
            super(tvSteps);
            this.tvSteps = tvSteps;
            this.tvDate = tvDate;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListStepsAdapter(ArrayList<DayStats> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListStepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_step, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder((TextView) v.findViewById(R.id.steps_text),
                (TextView) v.findViewById(R.id.date_text));
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvSteps.setText(mDataset.get(position).getSteps()+"");
        holder.tvDate.setText(mDataset.get(position).getDate().toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


