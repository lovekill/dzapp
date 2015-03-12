package com.engine.dzapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.engine.dzapp.R;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by engine on 15/3/12.
 */
public class EventFragment extends BaseFragment implements CalendarPickerView.OnDateSelectedListener {
    @InjectView(R.id.calendar_view)
    CalendarPickerView calendarView;

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
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        Date firstDay = cal_1.getTime();

        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH,1);
        cale.set(Calendar.DAY_OF_MONTH,1);

        calendarView.init(firstDay, cale.getTime()).inMode(CalendarPickerView.SelectionMode.SINGLE);
        calendarView.setOnDateSelectedListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDateSelected(Date date) {

    }

    @Override
    public void onDateUnselected(Date date) {

    }
}
