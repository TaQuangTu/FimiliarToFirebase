package com.taquangtu132gmail.taquangtu.firebase.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taquangtu132gmail.taquangtu.firebase.R;
import com.taquangtu132gmail.taquangtu.firebase.model.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context mContext;
    private int mIdResourceLayout;
    private List<User> userList;

    public UserAdapter(Context mContext, int mIdResourceLayout, List<User> userList)
    {
        this.mContext = mContext;
        this.mIdResourceLayout = mIdResourceLayout;
        this.userList = userList;
    }
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return userList.get(i).getmId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder = null;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mIdResourceLayout,null);
            viewHolder = new ViewHolder();
            viewHolder.mTextViewName = view.findViewById(R.id.tv_name);
            viewHolder.mTextViewId   = view.findViewById(R.id.tv_id);
            viewHolder.mTextViewInfo = view.findViewById(R.id.tv_info);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTextViewId.setText("Id: "+userList.get(i).getmId());
        viewHolder.mTextViewName.setText("Name: "+userList.get(i).getmName());
        viewHolder.mTextViewInfo.setText(userList.get(i).getAdditionalInformation());
        return view;
    }
    private class ViewHolder
    {
        TextView mTextViewName,mTextViewId,mTextViewInfo;
    }
}
