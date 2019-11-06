package com.kepss.expenseentry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.kepss.expenseentry.adapter.SampleRecyclerViewAdapter;
import com.kepss.expenseentry.globle.RecyclerViewSwipeDecorator;


public class ActivitySwip_RecycleView extends AppCompatActivity {

    private SampleRecyclerViewAdapter mAdapter;
    private MenuItem actionToggleLayout;
    private boolean isLinear = true;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip__recycle_view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // Set a layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        // Create and set an adapter
        mAdapter = new SampleRecyclerViewAdapter(this);
        recyclerView.setAdapter(mAdapter);
        // Create and add a callback
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                try {
                    final int position = viewHolder.getAdapterPosition();
                    final String item = mAdapter.removeItem(position);
                    Snackbar snackbar = Snackbar.make(viewHolder.itemView, "Item " + (direction == ItemTouchHelper.RIGHT ? "deleted" : "archived") + ".", Snackbar.LENGTH_LONG);
                    snackbar.setAction(android.R.string.cancel, new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            try {
                                mAdapter.addItem(item, position);
                            } catch(Exception e) {
                                Log.e("MainActivity", e.getMessage());
                            }
                        }
                    });
                    snackbar.show();
                } catch(Exception e) {
                    Log.e("MainActivity", e.getMessage());
                }
            }

            // You must use @RecyclerViewSwipeDecorator inside the onChildDraw method
            @Override
            public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(ActivitySwip_RecycleView.this, R.color.colorPrimary))
                        .addSwipeLeftActionIcon(R.drawable.ic_add)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(ActivitySwip_RecycleView.this, R.color.colorPrimaryDark))
                        .addSwipeRightActionIcon(R.drawable.ic_calender)
                        .addSwipeRightLabel("Delete")
                        .setSwipeRightLabelColor(Color.WHITE)
                        .addSwipeLeftLabel("Archive")
                        .setSwipeLeftLabelColor(Color.WHITE)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
