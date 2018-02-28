package activity.allen.com.utilbox;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.ViewInject;

import Fragments.Menu1Fragment;
import Fragments.Menu2Fragment;
import Fragments.Menu3Fragment;
import application.allen.com.utilbox.mApplication;

public class TabActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private LayoutInflater mInflateer;
    String [] ViewName={"目录1","目录2","目录3"};
    int [] ViewID={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    Fragment [] MenuFraments ={Menu1Fragment.newInstance("",""), Menu2Fragment.newInstance("",""), Menu3Fragment.newInstance("","")};

    private mApplication app = mApplication.getInstance();


    @ViewInject(id=R.id.toolbar)
    Toolbar toolbar;

    @ViewInject(id=R.id.tab_layout)
    TabLayout tablayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        mInflateer = LayoutInflater.from(this);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tablayout.setSelectedTabIndicatorColor(R.color.colorPrimary);
        tablayout.setupWithViewPager(mViewPager);
        for(int i=0;i<mSectionsPagerAdapter.getCount();i++)
        {
            tablayout.getTabAt(i).setCustomView(setupTabView(i));
        }

    }



    public View setupTabView(int i)
    {
        View view = mInflateer.inflate(R.layout.tabindicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon_indicator);
        TextView textView = (TextView) view.findViewById(R.id.txt_indicator);
        TextView msgCount = (TextView) view.findViewById(R.id.message_count);
        imageView.setBackgroundResource(ViewID[i]);
        textView.setText(ViewName[i]);
        return view;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment2 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment2() {
        }
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label2);
            textView.setText("hahahahahahaha");
            return rootView;
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
/*            Fragment fragment = null;

            switch(position)
            {
                case 0:
                    fragment =  PlaceholderFragment.newInstance(position + 1);
                    break;
                case 1:
                    fragment =  PlaceholderFragment2.newInstance(position + 1);
                    break;
                case 2:
                    fragment =  PlaceholderFragment.newInstance(position + 1);
                    break;
            }*/
            return MenuFraments[position];
        }

        @Override
        public int getCount() {
            return ViewName.length;
        }
    }
}
