package com.engine.dzapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
<<<<<<< HEAD
=======
import com.engine.dzapp.DaoManager;
>>>>>>> 3e19fb1da616098afd874e119ed98becb65e447c
import com.engine.dzapp.R;
import com.engine.dzapp.actvity.AddEventActivity;
import com.engine.dzapp.adapter.EventAdapter;
import com.engine.dzapp.dao.Event;
import com.engine.dzapp.dao.EventDao;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by engine on 15/3/12.
 */
public class EventFragment extends BaseFragment implements CalendarPickerView.OnDateSelectedListener {
    @InjectView(R.id.calendar_view)
    CalendarPickerView calendarView;
    @InjectView(R.id.add)
    TextView add;
    @InjectView(R.id.listView)
    ListView listView;
    private Date selectDate = new Date();
    private EventDao mEventDao;

    @Override
    protected int getViewId() {
        return R.layout.fragment_event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        Date firstDay = cal_1.getTime();

        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        calendarView.init(firstDay, cale.getTime()).inMode(CalendarPickerView.SelectionMode.SINGLE);
        calendarView.setOnDateSelectedListener(this);
        mEventDao = DaoManager.getDaoSession().getEventDao();
        setDayEvent();
    }

    private void setDayEvent() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        Date endDate = calendar.getTime();
        List<Event> list = mEventDao.queryBuilder().where(EventDao.Properties.Time.between(startDate,endDate)).list();
        EventAdapter adapter = new EventAdapter(getActivity(), list);
        listView.setAdapter(adapter);

    }

    @OnClick(R.id.add)
    public void addEvent(View view) {
        Intent intent = new Intent(getActivity(), AddEventActivity.class);
        intent.putExtra(AddEventActivity.DATA, selectDate.getTime());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDateSelected(Date date) {
        this.selectDate = date;
    }


    @Override
    public void onDateUnselected(Date date) {

    }
}
