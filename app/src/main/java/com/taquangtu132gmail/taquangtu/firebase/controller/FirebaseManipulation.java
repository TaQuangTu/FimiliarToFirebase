package com.taquangtu132gmail.taquangtu.firebase.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.taquangtu132gmail.taquangtu.firebase.model.User;
import com.taquangtu132gmail.taquangtu.firebase.view.activity.MainActivity;

public class FirebaseManipulation
{
    public static void addUser(DatabaseReference mDatabase, User user)
    {
        mDatabase.child("users").child(""+user.getmId()).setValue(user);
    }
    public static void addUser(DatabaseReference mDatabase, int id, String name, String addtionalInformation)
    {
        User user = new User(id,name,addtionalInformation);
        mDatabase.child("users").child(""+id).setValue(user);
    }
    public static void setValueEventListener(final MainActivity mainActivity,DatabaseReference mDatabase)
    {
       mDatabase.child("users").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               User user = dataSnapshot.getValue(User.class);
               mainActivity.getmUserArrayList().add(user);
               mainActivity.getmUserAdapter().notifyDataSetChanged();
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
}
