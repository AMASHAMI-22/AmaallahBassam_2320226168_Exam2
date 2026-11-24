package com.example.mid_exam_amaallah_bassam;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mid_exam_amaallah_bassam.databinding.ActivityModeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    private ActivityModeBinding modeBinding;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modeBinding = ActivityModeBinding.inflate(getLayoutInflater());
        setContentView(modeBinding.getRoot());

        pagerAdapter = new PagerAdapter(this);
        modeBinding.ViewPager.setAdapter(pagerAdapter);

        // Set the text for each tab.
        new TabLayoutMediator(modeBinding.TabLayout, modeBinding.ViewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0: tab.setText(R.string.tab1);
                            break;
                        case 1: tab.setText(R.string.tab2);
                            break;
                        default: tab.setText(R.string.tab3);
                            break;
                    }
                })
                .attach();

        modeBinding.btnSetMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ModeActivity.this, v);
                popup.getMenuInflater().inflate(R.menu.mode_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.mode_light) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            return true;
                        } else if (id == R.id.mode_night) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            return true;
                        } else if (id == R.id.mode_system) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                            return true;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (modeBinding.ViewPager.getCurrentItem() != 0) {
            modeBinding.ViewPager.setCurrentItem(0);
        } else {
            super.onBackPressed();
        }
    }

    private class PagerAdapter extends FragmentStateAdapter {

        PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return new FirstFragment();
                case 1: return new SecondFragment();
                default: return new ThirdFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}