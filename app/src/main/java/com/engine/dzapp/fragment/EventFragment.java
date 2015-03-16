package com.engine.dzapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.engine.dzapp.DaoManager;
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
    @InjectView(R.id.timeGroup)
    RadioGroup timeGroup;
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
        timeGroup.setOnCheckedChangeListener(onCheckedChangeListener);
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
        setDayEvent(new Date());
    }

    private void setDayEvent(Date dayEvent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayEvent);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = calendar.getTime();
        List<Event> list = mEventDao.queryBuilder().where(EventDao.Properties.Time.between(startDate, endDate)).list();
        EventAdapter adapter = new EventAdapter(getActivity(), list);
        listView.setAdapter(adapter);

    }

    private void setWeekEvent(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_WEEK,-calendar.get(Calendar.DAY_OF_WEEK));
        Date start = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK,7);
        Date end = calendar.getTime();
        String  format = "yyyy-MM-dd";
        List<Event> list = mEventDao.queryBuilder().where(EventDao.Properties.Time.between(start, end)).list();
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
        setDayEvent(date);
    }


    @Override
    public void onDateUnselected(Date date) {

    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener= new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==R.id.week){
                calendarView.setVisibility(View.GONE);
                setWeekEvent(new Date());
            }else if(checkedId == R.id.day){
                calendarView.setVisibility(View.GONE);
                setDayEvent(new Date());
            }else if(checkedId==R.id.month){
                calendarView.setVisibility(View.VISIBLE);
            }
        }
    };
}
