package com.mynote.quoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CopyListener{
    RecyclerView recycler_home ;
    RequestManager manager;
    QuoteRecyclerAdapter adapter;
    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       recycler_home = findViewById(R.id.recycler_home) ;

       manager = new RequestManager(this);
       manager.GetAllQuotes(listener);
       dialog = new ProgressDialog(this);
       dialog.setTitle("loading...");
       dialog.show();
    }
    private final QuoteResponseListener listener = new QuoteResponseListener() {
        @Override
        public void didFetch(List<QuoteResponse> responses, String message) {
            showData(responses);
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(List<QuoteResponse> responses) {
        recycler_home.setHasFixedSize(true);
        recycler_home.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        adapter = new QuoteRecyclerAdapter(MainActivity.this, responses, this);
        recycler_home.setAdapter(adapter);
    }

    @Override
    public void onCopyClicked(String text) {

    }
}
