package com.example.luchi.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<String> items;
ArrayAdapter<String> itemsAdapter;
ListView lvitems;
private final int REQUEST_CODE = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvitems = (ListView)findViewById(R.id.lvItems);
        items = new ArrayList<>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, items);
        lvitems.setAdapter(itemsAdapter);
      //  items.add("First item");
        //items.add("Second item");
        setupListViewListener();
      setupViewListener();
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
      // onActivityResult(REQUEST_CODE, RESULT_OK, i);
    }

    public void onAddItem(View view) {
        EditText etdit = (EditText)findViewById(R.id.etNewItem);
        String etedittex;
        etedittex = etdit.getText().toString();
        if (etedittex !="") {
            itemsAdapter.add(etedittex);
        }


        writeItems();
        etdit.setText("");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String name = data.getExtras().getString("todo");
            int code = data.getExtras().getInt("position", 0);
            // Toast the name to display temporarily on screen
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            items.set(code , name);
            itemsAdapter.notifyDataSetChanged();
            writeItems();

            //items.add(code, name);
        }
    }

    private void setupListViewListener(){
        lvitems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        items.remove(i);

                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                }
        );
}

    private void setupViewListener(){
        lvitems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int arg2, long arg3) {
                //le code à effectuer suite à un click
                EditText etdit = (EditText)findViewById(R.id.etNewItem);
               // items.get(arg2).toString();
              //  etdit.setText(items.get(arg2).toString());
               // items.remove(arg2);
                launchComposeView(items.get(arg2).toString() , arg2);

            }
        });
    }

    public void launchComposeView(String todo, int position) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("todo", todo);
        i.putExtra("position",position);
       // i.putExtra("in_reply_to", "george");
     //   i.putExtra("code", 400);
      //  startActivity(i); // brings up the second activity
        startActivityForResult(i, REQUEST_CODE);
     //   onActivityResult(REQUEST_CODE, RESULT_OK,i);
    }

    public void readItems(){
        File filesDir = getFilesDir();
        File todofile  = new File(filesDir , "todo.txt");
        try {
          items = new ArrayList<String>(FileUtils.readLines(todofile));
        } catch (IOException e){
            items = new ArrayList<String>();

        }
    }

    public void writeItems()
    {
        File filesDir = getFilesDir();
        File todofile  = new File(filesDir , "todo.txt");



            try {
                FileUtils.writeLines(todofile , items);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
