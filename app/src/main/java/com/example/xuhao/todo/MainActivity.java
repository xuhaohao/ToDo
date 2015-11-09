package com.example.xuhao.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lbContent = (ListView) this.findViewById(R.id.lvContent);
        final EditText etInput = (EditText) findViewById(R.id.etInput);

        final List<String> myData = new ArrayList<String>();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myData);
        lbContent.setAdapter(aa);

        etInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||(keyCode == KeyEvent.KEYCODE_DPAD_CENTER)){
                        myData.add(etInput.getText().toString());
                        aa.notifyDataSetChanged();
                        etInput.setText("");
                    }
                return false;
            }
        });
    }


}
