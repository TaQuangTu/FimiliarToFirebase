package com.taquangtu132gmail.taquangtu.firebase.view.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
        setViewAction();
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
    public void setViewAction()
    {
        setPostButtonClick();
        setListViewItemOnclick();
    }
    public void setPostButtonClick() {
        this.mButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make new random user and add to database
              //  mDatabase.child("users").setValue("sadad");
                Random random = new Random();
                int id = random.nextInt(2140000000);
                String name = "User name " +id;
                String additionalInformation = "some info "+id;
                FirebaseManipulation.addUser(mDatabase,new User(id,name,additionalInformation));
            }
        });
    }
    public void setListViewItemOnclick()
    {
        mListViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l)
            {
                //open a dialog to choose Edit or Removed
                final int toBeDeletedIndex = index;
                final int toBeDeletedId = mUserArrayList.get(index).getmId();
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("What do you want?");
                alertDialog.setCancelable(true);
                alertDialog.setTitle("User "+mUserArrayList.get(index).getmId());
                alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseManipulation.removeUser(mDatabase,toBeDeletedId);
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       //open another dialog to edit user info
                        dialogInterface.dismiss();
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.edit_user_dialog);
                        dialog.show();
                        final User toBeUpdatedUser = mUserArrayList.get(toBeDeletedIndex);
                        String name = toBeUpdatedUser.getmName();
                        String info = toBeUpdatedUser.getAdditionalInformation();
                        dialog.setTitle(""+toBeUpdatedUser.getmId());

                        final EditText editTextName = dialog.findViewById(R.id.edt_name);
                        final EditText editTextInfo = dialog.findViewById(R.id.edt_info);
                        Button buttonUpdate = dialog.findViewById(R.id.btn_update);
                        Button buttonCancel = dialog.findViewById(R.id.btn_cancel);

                        editTextInfo.setText(info);
                        editTextName.setText(name);

                        buttonCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        buttonUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                User newUser = new User(toBeUpdatedUser.getmId(),editTextName.getText().toString(),editTextInfo.getText().toString());
                                FirebaseManipulation.updateUser(mDatabase,newUser);
                                dialog.dismiss();
                            }
                        });
                    }
                });
                alertDialog.show();
            }
        });
    }
    public void setValueEventListener()
    {
        FirebaseManipulation.setValueEventListener(this,this.mDatabase);
       // FirebaseManipulation.setListenerForSingleValueEvent(this,this.mDatabase);
    }
}
