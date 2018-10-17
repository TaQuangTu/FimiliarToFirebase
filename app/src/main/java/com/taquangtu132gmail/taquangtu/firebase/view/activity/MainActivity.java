package com.taquangtu132gmail.taquangtu.firebase.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.taquangtu132gmail.taquangtu.firebase.R;
import com.taquangtu132gmail.taquangtu.firebase.controller.FirebaseManipulation;
import com.taquangtu132gmail.taquangtu.firebase.model.User;
import com.taquangtu132gmail.taquangtu.firebase.view.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ListView mListViewUser;
    private Button mButtonPost,mButtonPut,mButtonDelete;
    private ArrayList<User> mUserArrayList;
    private UserAdapter mUserAdapter;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mapViews();
        setmListViewUser();
        setmButtonPostClick();
        setValueEventListener();
    }
    private void mapViews()
    {
        mButtonPost = findViewById(R.id.btn_add_user);
        mButtonDelete = findViewById(R.id.btn_delete);
        mButtonPut    = findViewById(R.id.btn_put);
        mListViewUser = findViewById(R.id.lv_users);
    }

    public ArrayList<User> getmUserArrayList() {
        return mUserArrayList;
    }

    public UserAdapter getmUserAdapter() {
        return mUserAdapter;
    }
    public void setmListViewUser()
    {
        mUserArrayList = new ArrayList<>();
        mUserAdapter = new UserAdapter(this, R.layout.item_user,mUserArrayList);
        mListViewUser.setAdapter(mUserAdapter);
    }

    public void setmButtonPostClick() {
        this.mButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make new random user and add to database
              //  mDatabase.child("users").setValue("sadad");
                Random random = new Random();
                int id = random.nextInt(2140000000);
                String name = "User name " +id;
                String additionalInformation = "some infor "+id;
                FirebaseManipulation.addUser(mDatabase,new User(id,name,additionalInformation));
            }
        });
    }
    public void setValueEventListener()
    {
        FirebaseManipulation.setValueEventListener(this,this.mDatabase);
       // FirebaseManipulation.setListenerForSingleValueEvent(this,this.mDatabase);
    }
}
