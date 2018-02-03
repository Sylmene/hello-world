package com.example.luchi.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        String todo = getIntent().getStringExtra("todo");
        Integer position = getIntent().getIntExtra("position",0);
        EditText etdit = (EditText)findViewById(R.id.etNewItem);
        etdit.setText(todo);



        String etedittex;
        etedittex = etdit.getText().toString();

      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      //  fab.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View view) {
            //    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                     //   .setAction("Action", null).show();




               // String inReplyTo = getIntent().getStringExtra("in_reply_to");
               // int code = getIntent().getIntExtra("code", 0);
       //     }
     //   });
    }


    public void onSubmit(View v) {
        // closes the activity and returns to first screen
        EditText etName = (EditText) findViewById(R.id.etNewItem);
        // Prepare data intent

      //  String todo = getIntent().getStringExtra("todo");
        Integer position = getIntent().getIntExtra("position",0);


        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("todo", etName.getText().toString());
        data.putExtra("position",position); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }

    public void onCancel(View v) {
        // closes the activity and returns to first screen
        this.finish();
    }
}
