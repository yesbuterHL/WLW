package net.tzsoft.wlw.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.tzsoft.wlw.R;
import net.tzsoft.wlw.fragment.MainFragment;
import net.tzsoft.wlw.fragment.StatiFragment;
import net.tzsoft.wlw.fragment.StroeRoomFragment;
import net.tzsoft.wlw.fragment.WorkFragment;
import net.tzsoft.wlw.utils.FragmentUtils;
import net.tzsoft.wlw.utils.LogToFile;
import net.tzsoft.wlw.view.TabView;

public class MainActivity extends FragmentActivity implements TabView.OnTabChangeListener {
    TabView tabview;
    Fragment currentFragment;
    FragmentManager fragmentManager;
    TextView titleTv;
    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogToFile.init(getApplicationContext());
        LogToFile.i("MainActivity","onCreate");
        setContentView(R.layout.activity_main);
        titleTv= (TextView) findViewById(R.id.text_title);
        backBtn= (Button) findViewById(R.id.button_backward);
        titleTv.setText("首页");
        backBtn.setVisibility(View.GONE);
        tabview= (TabView) findViewById(R.id.tabview);
        tabview.setOnTabChangeListener(this);
        fragmentManager=getSupportFragmentManager();
        currentFragment=new MainFragment();
        replaceFragment(MainFragment.class);
        LogToFile.i("MainActivity","replaceFragment");
        tabview.setCurrentTab(0);
    }

    @Override
    public void onTabChange(String tag) {
        if(tag==null||tag.equals(""))return;
        switch (tag){
            case "1":replaceFragment(MainFragment.class);break;
            case "2":replaceFragment(StroeRoomFragment.class);break;
            case "3":replaceFragment(WorkFragment.class);break;
            case "4":replaceFragment(StatiFragment.class);break;
        }
    }
    private void replaceFragment(Class<? extends Fragment> newFragment) {
        currentFragment = FragmentUtils.switchFragment(fragmentManager,
                R.id.layout_content, currentFragment,
                newFragment, null, false);
    }
}
