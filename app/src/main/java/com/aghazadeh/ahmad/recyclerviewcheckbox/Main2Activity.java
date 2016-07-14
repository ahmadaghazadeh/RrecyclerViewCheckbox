package com.aghazadeh.ahmad.recyclerviewcheckbox;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {

    String reg = "#\\d+:\\d+#";
    Pattern pattern = Pattern.compile(reg);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            LinearLayout parent = new LinearLayout(this);

            parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            parent.setOrientation(LinearLayout.HORIZONTAL);

            String str = "It is #1:1# book. It is an #1:5#.";
            String split[] = str.split(reg);
            Matcher matcher = pattern.matcher(str);
            List<View> lstView=new LinkedList<>();

            for (String st : split) {
                TextView textView = new TextView(this);
                textView.setTextSize(21);
                textView.setText(st);
                lstView.add(textView);

                if(matcher.find()){
                    String te=str.substring( matcher.start(),matcher.end());
                    String num[] = te.replace("#", "").split(":");
                    String space = "";
                    for (int i = 0; i < Integer.parseInt(num[1]); i++) {
                        space += "_";
                    }
                    TextView textView1 = new TextView(this);
                    textView1.setText(space);
                    lstView.add(textView1);
                    textView1.setOnDragListener(new ChoiceDragListener());
                    textView1.setTextSize(21);

                }
            }
            Collections.reverse(lstView);
            for (View v:lstView) {
                parent.addView(v);

            }

            LinearLayout choose = new LinearLayout(this);

            choose.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            choose.setOrientation(LinearLayout.VERTICAL);
            choose.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            TextView a = new TextView(this);
            a.setText("a");
            a.setOnTouchListener(new ChoiceTouchListener());
            a.setTextSize(21);

            TextView apple = new TextView(this);
            apple.setText("apple");
            apple.setOnTouchListener(new ChoiceTouchListener());
            apple.setTextSize(21);

            choose.addView(a);
            choose.addView(apple);


            parent.addView(choose);
            setContentView(parent);
        }catch (Exception ex)
        {
            ex.getMessage();
        }


    }


    private final class ChoiceTouchListener implements View.OnTouchListener {
        @SuppressLint("NewApi")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            /*
             * Drag details: we only need default behavior
             * - clip data could be set to pass data as part of drag
             * - shadow can be tailored
             */
                ClipData data = ClipData.newPlainText("xxx", "yyy");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);

                return true;
            } else {
                return false;
            }
        }
    }

    @SuppressLint("NewApi")
    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:

                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;
                    //checking whether first character of dropTarget equals first character of dropped
                    if(dropTarget.getText().toString().charAt(0) == dropped.getText().toString().charAt(0))
                    {
                        //stop displaying the view where it was before it was dragged
                        view.setVisibility(View.INVISIBLE);
                        //update the text in the target view to reflect the data being dropped
                        dropTarget.setText(dropTarget.getText().toString() + dropped.getText().toString());
                        //make it bold to highlight the fact that an item has been dropped
                        dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                        //if an item has already been dropped here, there will be a tag
                        Object tag = dropTarget.getTag();
                        //if there is already an item here, set it back visible in its original place
                        if(tag!=null)
                        {
                            //the tag is the view id already dropped here
                            int existingID = (Integer)tag;
                            //set the original view visible again
                            findViewById(existingID).setVisibility(View.VISIBLE);
                        }
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        dropTarget.setOnDragListener(null);
                    }
                    else
                        //displays message if first character of dropTarget is not equal to first character of dropped
                        Toast.makeText(Main2Activity.this, dropTarget.getText().toString() + "is not " + dropped.getText().toString(), Toast.LENGTH_LONG).show();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}