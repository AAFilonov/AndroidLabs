package com.example.lr_9.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.lr_9.R;
import com.example.lr_9.db.model.Group;

import java.util.List;

public class ExpandableItemListAdapter extends BaseExpandableListAdapter {
    private final List<Group> groups;

    private final Context mContext;

    public ExpandableItemListAdapter(List<Group> groups, Context context) {
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
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView textViewName = convertView.findViewById(R.id.name);
        TextView textViewDescription = convertView.findViewById(R.id.description);
        TextView textViewCost = convertView.findViewById(R.id.cost);
        TextView textDevelopmentDate = convertView.findViewById(R.id.developmentDate);
        TextView textVersion = convertView.findViewById(R.id.version);
        TextView textId = convertView.findViewById(R.id.textItemId);
        textViewName.setText("name: " + groups.get(groupPosition).getItems().get(childPosition).getName());
        textViewDescription.setText("description: " + groups.get(groupPosition).getItems().get(childPosition).getDescription());
        textViewCost.setText("cost: " + groups.get(groupPosition).getItems().get(childPosition).getCost());
        textDevelopmentDate.setText("development date: " + groups.get(groupPosition).getItems().get(childPosition).getDevelopmentDate());
        textVersion.setText("version: " + groups.get(groupPosition).getItems().get(childPosition).getVersion());
        textId.setText(groups.get(groupPosition).getItems().get(childPosition).getId().toString());
        textId.setVisibility(View.INVISIBLE);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}