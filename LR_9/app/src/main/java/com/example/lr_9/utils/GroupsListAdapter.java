package com.example.lr_9.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.lr_9.R;
import com.example.lr_9.db.model.Group;

import java.util.List;

public class GroupsListAdapter extends BaseExpandableListAdapter {
    private final List<Group> groups;

    private final Context mContext;

    public GroupsListAdapter(List<Group> groups, Context context) {
        this.groups = groups;
        this.mContext = context;


    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }


        TextView textGroup = convertView.findViewById(R.id.textGroup);
        TextView textGroupId = convertView.findViewById(R.id.textGroupId);
        textGroup.setText(groups.get(groupPosition).getName());
        textGroupId.setText(groups.get(groupPosition).getId().toString());
        textGroupId.setVisibility(View.INVISIBLE);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_subgroup, null);
        }
        ExpandableListView listView = convertView.findViewById(R.id.exListViewSubgroup);
        SubgroupsListAdapter adapter = new SubgroupsListAdapter(groups.get(groupPosition).getItems(), mContext);
        listView.setOnChildClickListener(this::handleClick);

        listView.setAdapter(adapter);


        return convertView;
}

    private boolean handleClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        ExpandableListView elv = (ExpandableListView) view;
        int hh = elv.getChildCount();
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}