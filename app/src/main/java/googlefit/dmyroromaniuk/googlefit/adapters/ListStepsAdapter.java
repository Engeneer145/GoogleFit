package googlefit.dmyroromaniuk.googlefit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import googlefit.dmyroromaniuk.googlefit.R;
import googlefit.dmyroromaniuk.googlefit.data.DayStats;

/**
 * Created by dmyroromaniuk on 13.06.16.
 */
public class ListStepsAdapter extends RecyclerView.Adapter<ListStepsAdapter.ViewHolder> {
    private List<DayStats> dataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListStepsAdapter(List<DayStats> myDataset) { dataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListStepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_step, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder((TextView) view.findViewById(R.id.steps_text),
                (TextView) view.findViewById(R.id.date_text));
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvSteps.setText( dataset.get(position).getSteps() + "");
        holder.tvDate.setText( dataset.get(position).getDate().toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : 0;
    }

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
}


