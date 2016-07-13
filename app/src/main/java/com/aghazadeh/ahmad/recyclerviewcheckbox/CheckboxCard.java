package com.aghazadeh.ahmad.recyclerviewcheckbox;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import jp.satorufujiwara.binder.recycler.RecyclerBinder;

/**
 * Created by 890683 on 7/13/2016.
 */
public class CheckboxCard extends RecyclerBinder<CardViewType> {

    String text;
    boolean checked;
    protected CheckboxCard(Activity activity, String text,boolean checked) {
        super(activity, CardViewType.CardCehckBox);
        this.checked=checked;
        this.text=text;
    }

    @Override
    public int layoutResId() {
        return R.layout.card_view_check;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutResId(), (ViewGroup) parent, false);
        BindingHolder holder = new BindingHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final BindingHolder holder = (BindingHolder) viewHolder;
        holder.checkBox.setText(text);
        holder.checkBox.setChecked(checked);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checked=b;
            }
        });
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public BindingHolder(View view) {
            super(view);
            checkBox= (CheckBox) view.findViewById(R.id.checkBox);
        }
    }
}
