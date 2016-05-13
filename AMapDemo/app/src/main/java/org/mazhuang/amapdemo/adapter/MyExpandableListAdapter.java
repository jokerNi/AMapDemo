package org.mazhuang.amapdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.mazhuang.amapdemo.R;
import org.mazhuang.amapdemo.activity.MapShowActivity;
import org.mazhuang.amapdemo.activity.NaviOverlayActivity;
import org.mazhuang.amapdemo.activity.NaviShowActivity;

/**
 * Created by mazhuang on 2016/5/13.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListView.OnChildClickListener {
    private Context mContext;

    private String[] mGroups = {
            "地图相关功能实现",
            "导航相关功能实现",
            "地图相关问题解决",
            "导航相关问题解决"
    };

    private ListItem[][] mChildren = {
            { // 地图相关功能实现
                    new ListItem("显示地图", MapShowActivity.class),
            },
            { // 导航相关功能实现
                    new ListItem("显示导航", NaviShowActivity.class),
                    new ListItem("导航界面绘制覆盖物", NaviOverlayActivity.class)
            },
            { // 地图相关问题解决
            },
            { // 导航相关问题解决
            }
    };

    public MyExpandableListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return mGroups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildren[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildren[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.group_index, null);
            holder = new GroupViewHolder();
            holder.mGroupName = (TextView) convertView.findViewById(R.id.groupName);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        String groupName = mGroups[groupPosition];
        holder.mGroupName.setText(groupName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_index, null);
            holder = new ChildViewHolder();
            holder.mChildName = (TextView) convertView.findViewById(R.id.childName);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        String childName = mChildren[groupPosition][childPosition].mText;
        holder.mChildName.setText(childName);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        ListItem item = mChildren[groupPosition][childPosition];
        Intent intent = new Intent(mContext, item.mActivityClass);
        mContext.startActivity(intent);
        return true;
    }

    class ListItem {
        private String mText;
        private Class<? extends Activity> mActivityClass;

        public ListItem(String text, Class<? extends Activity> activity) {
            mText = text;
            mActivityClass = activity;
        }
    }

    class GroupViewHolder {
        public TextView mGroupName;
    }

    class ChildViewHolder {
        public TextView mChildName;
    }
}