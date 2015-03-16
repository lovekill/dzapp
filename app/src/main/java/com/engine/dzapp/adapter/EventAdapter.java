package com.engine.dzapp.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.engine.dzapp.R;
import com.engine.dzapp.dao.Event;
import com.engine.dzapp.fragment.AddEventFragment;

import java.util.List;

/**
 * Created by cat1412266 on 15/3/15.
 */
public class EventAdapter extends DZAdapter<Event> {
    public EventAdapter(Context mContext, List<Event> mList) {
        super(mContext, mList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_event, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.eventName.setText(event.getEventName());
        holder.eventTime.setText(DateFormat.format("MM/dd/yyyy h:mmaa", event.getTime()));
        if (event.getEventType() == AddEventFragment.EVENT) {
            holder.type.setText("[事件/待办]");
        }
        if(event.getStatus()==0){
           holder.status.setText("未开始");
        }
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_event.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static


    class ViewHolder {
        @InjectView(R.id.type)
        TextView type;
        @InjectView(R.id.eventName)
        TextView eventName;
        @InjectView(R.id.status)
        TextView status;
        @InjectView(R.id.eventTime)
        TextView eventTime;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
