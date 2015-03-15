package com.engine.dzapp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.engine.dzapp.DaoManager;
import com.engine.dzapp.R;
import com.engine.dzapp.actvity.AddEventActivity;
import com.engine.dzapp.dao.Event;

import java.util.Date;

/**
 * Created by cat1412266 on 15/3/15.
 */
public class AddEventFragment extends BaseFragment {
    @InjectView(R.id.eventName)
    EditText eventName;
    @InjectView(R.id.eventGroup)
    RadioGroup eventGroup;
    private Date date;
    public static final int EVENT = 1;
    public int type = -1;

    @Override
    protected int getViewId() {
        return R.layout.fragment_add_event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        date = new Date(getArguments().getLong(AddEventActivity.DATA));
        eventGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick(R.id.addEvent)
    public void add(View view) {
        if (validate()) {
            Event event = new Event();
            event.setEventName(eventName.getText().toString());
            event.setEventType(type);
            event.setStatus(0);
            event.setTime(date);
            DaoManager.getDaoSession().getEventDao().insert(event);
            Toast.makeText(getActivity(), "增加成功", Toast.LENGTH_SHORT).show();
            goBack();
        }
    }

    private boolean validate() {
        if (type == -1) {
            Toast.makeText(getActivity(), "请选择类型", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(eventName.getText().toString())) {
            Toast.makeText(getActivity(), "请输入事件名字", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.event) {
                type = EVENT;
            }
        }
    };
}
