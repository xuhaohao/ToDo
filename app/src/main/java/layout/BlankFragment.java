package layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xuhao.todo.R;


public class BlankFragment extends Fragment {
    private static final String Name = "param1";
    private static final String Content = "param2";

    private String name;
    private String content;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(String name, String content) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(Name, name);
        args.putString(Content, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(Name);
            content = getArguments().getString(Content);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank,container);
        return view;
    }
}
