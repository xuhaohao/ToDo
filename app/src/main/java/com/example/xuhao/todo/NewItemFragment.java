package com.example.xuhao.todo;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewItemFragment extends Fragment {

    private static final String TAG = "NewItemFragment";
    private static final String Name = "name";

    private String tag;
    private String name;


    public NewItemFragment() {
        // Required empty public constructor
    }

    private OnNewItemAddedListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnNewItemAddedListener)context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tag Parameter 1.
     * @param name Parameter 2.
     * @return A new instance of fragment NewItemFragment.
     */
    public static NewItemFragment newInstance(String tag, String name) {
        NewItemFragment fragment = new NewItemFragment();
        Bundle args = new Bundle();
        args.putString(TAG, tag);
        args.putString(Name, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tag = getArguments().getString(TAG);
            name = getArguments().getString(Name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_item, container, false);
        final EditText evNewItem = (EditText) view.findViewById(R.id.etNewItem);
        evNewItem.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER){
                        listener.onOnNewItemAdded(evNewItem.getText().toString());
                        evNewItem.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }

    public interface OnNewItemAddedListener{
        void onOnNewItemAdded(String item);
    }


}
