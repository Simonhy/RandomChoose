package com.isoftstone.randomchoose.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.isoftstone.randomchoose.R;
import com.isoftstone.randomchoose.bean.Student;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yonghuangl on 2017/6/9.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Student> studentInfos;

    public GridViewAdapter(Context context, List<Student> studentInfos) {
        this.context = context;
        this.studentInfos = studentInfos;
    }

    @Override
    public int getCount() {
        return studentInfos.size() == 0 ? 0 : studentInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GrideViewHolder holder;
        if (null == view) {
            holder = new GrideViewHolder();
            view = View.inflate(context, R.layout.gridview_item, null);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.iv = (CircleImageView) view.findViewById(R.id.profile_image_press);
            view.setTag(holder);
        } else {
            holder = (GrideViewHolder) view.getTag();
        }
        Student student = studentInfos.get(i);
        if (student.isSelected()) {
            holder.iv.setVisibility(View.VISIBLE);
        } else {
            holder.iv.setVisibility(View.GONE);
        }
        holder.name.setText(student.getName());
        return view;
    }

    private boolean isChoose;
    private List<Integer> positions;

    public void updataChange(boolean isChoose, List<Integer> positions,List<Student> studentInfos) {
        this.isChoose = isChoose;
        this.positions = positions;
        this.studentInfos = studentInfos;
        notifyDataSetChanged();
    }

    class GrideViewHolder {
        TextView name;
        CircleImageView iv;
    }
}
