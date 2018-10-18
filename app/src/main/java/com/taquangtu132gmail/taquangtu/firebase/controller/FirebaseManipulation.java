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
    public static void removeUser(DatabaseReference mDatabase,int id)
    {
        mDatabase.child("users").child(""+id).removeValue();
    }
    public static void updateUser(DatabaseReference mDatabase, User user)
    {
        String id = ""+user.getmId();
        String name = user.getmName();
        String info = user.getAdditionalInformation();
        mDatabase.child("users").child(""+id).child("mName").setValue(name);
        mDatabase.child("users").child(""+id).child("additionalInformation").setValue(info);
    }
    public static void setValueEventListener(final MainActivity mainActivity, final DatabaseReference mDatabase)
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
               User updatedUser= dataSnapshot.getValue(User.class);
               int id = updatedUser.getmId();
               int size = mainActivity.getmUserArrayList().size();
               for(int i=0;i<size;i++)
               {
                   User toBeUpdatedUser = mainActivity.getmUserArrayList().get(i);
                   if(toBeUpdatedUser.getmId()==id)
                   {
                       toBeUpdatedUser.setmName(updatedUser.getmName());
                       toBeUpdatedUser.setAddtionalInformation(updatedUser.getAdditionalInformation());
                       mainActivity.getmUserAdapter().notifyDataSetChanged();
                       return;
                   }
               }
           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                User removedUser = dataSnapshot.getValue(User.class);
                int id = removedUser.getmId();
                int size = mainActivity.getmUserArrayList().size();
                for(int i=0;i<size;i++)
                {
                    User toBeRemovedUser = mainActivity.getmUserArrayList().get(i);
                    if(toBeRemovedUser.getmId()==id)
                    {
                        mainActivity.getmUserArrayList().remove(i);
                        mainActivity.getmUserAdapter().notifyDataSetChanged();
                        return;
                    }
                }
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
