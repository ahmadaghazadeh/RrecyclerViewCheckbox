package com.aghazadeh.ahmad.recyclerviewcheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.Collections;
import java.util.List;

import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter;

public class MainActivity extends AppCompatActivity {

    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private final RecyclerBinderAdapter<TitleSection, CardViewType> adapter
            = new RecyclerBinderAdapter<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.add(TitleSection.SECTION_1, new CheckboxCard(this, "salam 1", false));
        adapter.add(TitleSection.SECTION_1, new CheckboxCard(this, "salam 2", true));
        adapter.add(TitleSection.SECTION_1, new CheckboxCard(this, "salam 3", false));
        adapter.add(TitleSection.SECTION_1, new CheckboxCard(this, "salam 4", true));
        adapter.add(TitleSection.SECTION_1, new CheckboxCard(this, "salam 5", false));
        adapter.add(TitleSection.SECTION_1, new CheckboxCard(this, "salam 6", true));
        findViewById(R.id.btnCalc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str="";
                List<CheckboxCard> ss= (List<CheckboxCard>) adapter.getAllItem(TitleSection.SECTION_1);
                for (CheckboxCard c:ss) {
                    if(c.checked)
                    {
                        str+=c.text;
                    }
                }
                setTitle(str);
            }
        });



// Extend the Callback class
        ItemTouchHelper.Callback _ithCallback = new ItemTouchHelper.Callback() {
            //and in your imlpementaion of
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // get the viewHolder's and target's positions in your adapter data, swap them
               Collections.swap(adapter.getAllItem(TitleSection.SECTION_1), viewHolder.getAdapterPosition(), target.getAdapterPosition());
                // and notify the adapter that its dataset has changed
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //TODO
            }

            //defines the enabled move directions in each state (idle, swiping, dragging).
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP );
            }
        };
        ItemTouchHelper ith = new ItemTouchHelper(_ithCallback);
        ith.attachToRecyclerView(recyclerView);
    }
}
