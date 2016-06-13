package googlefit.dmyroromaniuk.googlefit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import googlefit.dmyroromaniuk.googlefit.adapters.ListStepsAdapter;
import googlefit.dmyroromaniuk.googlefit.data.DataReader;

public class StepsActivity extends AppCompatActivity {

    private DataReader data;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        data = DataReader.getInstance(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.m_recycler_view);

    }

    private void setAdapter() {
        ListStepsAdapter mAdapter = new ListStepsAdapter(data.getStepsArray());
        RecyclerView.LayoutManager mLayoutManager;

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mRecyclerView.setAdapter(mAdapter);
    }
}
