package com.example.sharath.designtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtMessage;
    Toolbar toolbar;
    ImageButton ibCamera, ibVoice;
    RecyclerView recyclerView;
    ArrayList<Message> chat = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializesViews();
        bindData();
    }

    private void bindData() {
        chat.add(new Message("Hi :)","10:41 PM","IN"));
        chat.add(new Message("Hello dude, I badly missed your jokes. Tell me a joke now.","10:42 PM","OUT"));
        chat.add(new Message("Sure. Ok, tell me, how do you put an elephant in a fridge?","10:42 PM","IN"));
        chat.add(new Message("I don’t know","10:43 PM","OUT"));
        chat.add(new Message("It’s easy. You just open the fridge and put it in. I have another question.","10:43 PM","IN"));
        chat.add(new Message("Okay. Ask.","10:43 PM","OUT"));
        chat.add(new Message("How to put the donkey inside the fridge?","10:44 PM","IN"));
        chat.add(new Message("It’s easy. You just open the fridge and put it in.","10:44 PM","OUT"));
        chat.add(new Message("No. You just open the fridge, take out the elephant and put the donkey inside.","10:44 PM","IN"));
        chat.add(new Message("Oh ok.","10:45 PM","OUT"));
        chat.add(new Message("Let me ask another one. If all the animals went to the lion’s birthday party and one animal went missing. Which one would it be?","10:45 PM","IN"));
        chat.add(new Message("I don’t know.","10:45 PM","OUT"));
        chat.add(new Message("It’s the donkey. Because it’s still inside the fridge.","10:45 PM","IN"));
        chat.add(new Message("Are you kidding me?","10:46 PM","OUT"));
        chat.add(new Message("No Franklin. One last question.","10:47 PM","IN"));
        chat.add(new Message("Ok.","10:50 PM","OUT"));
        chat.add(new Message("If there is a river full of crocodiles and you wanted to cross, how would you?","10:52 PM","IN"));
        chat.add(new Message("There is no way. I would need a boat to cross.","10:55 PM","OUT"));
        chat.add(new Message("No man. You just swim and cross the river. Because all the animals including crocodiles went to the lion’s birthday party.","10:57 PM","IN"));
        chat.add(new Message("It’s enough buddy, I never ask you to tell a joke again.","11:00 PM","OUT"));
        chat.add(new Message("hahaha!! ;)","11:00 PM","IN"));

        recyclerView.setAdapter(new ChatAdapter(getApplicationContext(),chat));

    }

    private void initializesViews() {
        //inflate views
        toolbar = findViewById(R.id.toolbar);
        ibCamera = findViewById(R.id.ib_camera);
        ibVoice = findViewById(R.id.ib_voice);
        edtMessage = findViewById(R.id.edt_message);
        recyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(toolbar);
        //set back button
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ibVoice.setOnClickListener(this);
        ibCamera.setOnClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_video_call:
                Toast.makeText(this, "Video call", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_voice_call:
                Toast.makeText(this, "Voice call", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_camera:
                Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_voice:
                Toast.makeText(this, "Voice message", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
