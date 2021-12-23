package com.example.lr_9.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.lr_9.R;
import com.example.lr_9.db.model.Subgroup;

import java.util.List;

public class SubgroupsListAdapter extends BaseExpandableListAdapter {
    private final List<Subgroup> subgroups;

    private final Context mContext;

    public SubgroupsListAdapter(List<Subgroup> subgroups, Context context) {
        this.subgroups = subgroups;
        this.mContext = context;


    }

    @Override
    public int getGroupCount() {
        return subgroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return subgroups.get(groupPosition).getItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return subgroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return subgroups.get(groupPosition).getItems().get(childPosition);
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
            convertView = inflater.inflate(R.layout.list_subgroup, null);
        }


        TextView textGroup = convertView.findViewById(R.id.textSubgroupName);
        TextView textGroupId = convertView.findViewById(R.id.textSubgroupId);
        textGroup.setText("     "+subgroups.get(groupPosition).getName());
        textGroupId.setText(subgroups.get(groupPosition).getId().toString());
        textGroupId.setVisibility(View.INVISIBLE);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_item, null);
        }

        TextView textViewName = convertView.findViewById(R.id.name);
        TextView textViewDescription = convertView.findViewById(R.id.description);
        TextView textViewCost = convertView.findViewById(R.id.cost);
        TextView textDevelopmentDate = convertView.findViewById(R.id.developmentDate);
        TextView textVersion = convertView.findViewById(R.id.version);
        TextView textId = convertView.findViewById(R.id.textItemId);
        textViewName.setText("name: " + subgroups.get(groupPosition).getItems().get(childPosition).getName());
        textViewDescription.setText("description: " + subgroups.get(groupPosition).getItems().get(childPosition).getDescription());
        textViewCost.setText("cost: " + subgroups.get(groupPosition).getItems().get(childPosition).getCost());
        textDevelopmentDate.setText("development date: " + subgroups.get(groupPosition).getItems().get(childPosition).getDevelopmentDate());
        textVersion.setText("version: " + subgroups.get(groupPosition).getItems().get(childPosition).getVersion());
        textId.setText(subgroups.get(groupPosition).getItems().get(childPosition).getId().toString());
        textId.setVisibility(View.INVISIBLE);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}