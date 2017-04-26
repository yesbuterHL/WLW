package net.tzsoft.wlw.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import net.tzsoft.wlw.R;
import net.tzsoft.wlw.fragment.MainFragment;
import net.tzsoft.wlw.fragment.StroeRoomFragment;
import net.tzsoft.wlw.utils.FragmentUtils;
import net.tzsoft.wlw.view.TabView;

public class MainActivity extends BaseActivity implements TabView.OnTabChangeListener {
    TabView tabview;
    Fragment currentFragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("首页");
        showBackwardView(0,false);
        tabview= (TabView) findViewById(R.id.tabview);
        tabview.setOnTabChangeListener(this);
        fragmentManager=getSupportFragmentManager();
        currentFragment=new MainFragment();
        FragmentUtils.replaceFragment(fragmentManager, R.id.layout_content,MainFragment.class, null, false);
        tabview.setCurrentTab(0);
    }

    @Override
    public void onTabChange(String tag) {
        if(tag==null||tag.isEmpty())return;
        switch (tag){
            case "1":replaceFragment(MainFragment.class);break;
            case "2":replaceFragment(StroeRoomFragment.class);break;
        }
    }
    private void replaceFragment(Class<? extends Fragment> newFragment) {
        currentFragment = FragmentUtils.switchFragment(fragmentManager,
                R.id.layout_content, currentFragment,
                newFragment, null, false);
    }
}
