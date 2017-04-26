package net.tzsoft.wlw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import net.tzsoft.wlw.R;

import org.joda.time.DateTime;

/**
 * Created by hl on 2017/4/26.
 */

public class MainFragment extends BaseFragment {
    @ViewInject(R.id.date1_btn)
    Button date1Btn;
    @ViewInject(R.id.date2_btn)
    Button date2Btn;
    @ViewInject(R.id.date3_btn)
    Button date3Btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDateBtn();
        date3Btn.callOnClick();
    }
    private void initDateBtn(){
        DateTime now=DateTime.now();
        date3Btn.setText(now.toString("M.dd"));
        date2Btn.setText(now.minusDays(1).toString("M.dd"));
        date1Btn.setText(now.minusDays(2).toString("M.dd"));
    }
    @OnClick({R.id.date1_btn,R.id.date2_btn,R.id.date3_btn})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.date1_btn:
                date1Btn.setEnabled(false);date2Btn.setEnabled(true);date3Btn.setEnabled(true);
                break;
            case R.id.date2_btn:
                date2Btn.setEnabled(false);date1Btn.setEnabled(true);date3Btn.setEnabled(true);
                break;
            case R.id.date3_btn:
                date3Btn.setEnabled(false);date1Btn.setEnabled(true);date2Btn.setEnabled(true);
                break;
        }
    }
}
