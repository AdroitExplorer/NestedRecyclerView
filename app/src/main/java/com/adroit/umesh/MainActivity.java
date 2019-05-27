package com.adroit.umesh;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.adroit.nestedrecyclerview.AdapterModel;
import com.adroit.nestedrecyclerview.NestedRecyclerView;
import com.adroit.nestedrecyclerview.ParentClickListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<AdapterModel<RecyclerView.Adapter<RecyclerView.ViewHolder>>> listAdapter = new ArrayList<>();
        ArrayList<String> listString = new ArrayList<>();
        listString.add("hooollla1");
        listString.add("hooollla2");
        listString.add("hooollla3");
        listString.add("hooollla4");
        listString.add("hooollla5");
        listString.add("hooollla6");
        listString.add("hooollla7");

        ChildAdapters childAdapter = new ChildAdapters(getBaseContext(), listString);
        for (int i = 0; i < 100; i++) {
            listAdapter.add(new AdapterModel(true, childAdapter, "hello " + i));
        }

        NestedRecyclerView recyclerView = (NestedRecyclerView) findViewById(R.id.rv);

        recyclerView.setChildAdapters(listAdapter);
        recyclerView.setParentClickListener(new ParentClickListener() {
            @Override
            public void onClick(int parentPosition, @NotNull AdapterModel<RecyclerView.Adapter<RecyclerView.ViewHolder>> adapter) {
                //perform action on View click
                Toast.makeText(getApplicationContext(),"clicked " + parentPosition,Toast.LENGTH_LONG).show();
            }
        });

    }
}
