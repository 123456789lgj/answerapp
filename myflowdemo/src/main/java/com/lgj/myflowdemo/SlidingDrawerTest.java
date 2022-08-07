package com.lgj.myflowdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SlidingDrawerTest extends AppCompatActivity {
    Button btnLeft, btnRight, btnContent;
    ClickableSlidingDrawer ctlSlidingDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_drawer_test);

        ctlSlidingDrawer = (ClickableSlidingDrawer)this.findViewById(R.id.ctlSlidingDrawer);
        ctlSlidingDrawer.setHandleId(R.id.ctlHandle);
        ctlSlidingDrawer.setTouchableIds(new int[]{R.id.btnLeft, R.id.btnRight});
        btnLeft = (Button)this.findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(onLeftClickListener);
        btnRight = (Button)this.findViewById(R.id.btnRight);
        btnRight.setOnClickListener(onButtonClickListener);
//        btnContent = (Button)this.findViewById(R.id.btnContent);
//        btnContent.setOnClickListener(onButtonClickListener);

    }

    private View.OnClickListener onLeftClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            Log.i("SlidingDrawerTest", "left button click");
        }
    };

    private View.OnClickListener onButtonClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            Button button = (Button)v;
            Log.i("SlidingDrawerTest", "Click button " + button.getText().toString());
        }
    };

}