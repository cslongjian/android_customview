package com.rxjava.chenlongjian.rxjava_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by chenlongjian on 2016/7/6.
 */
public class ObserableMethodActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MainFragment(), this.toString())
                .commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        _removeWorkerFragments();
    }


    private void _removeWorkerFragments() {
//        Fragment frag = getSupportFragmentManager()//
//                .findFragmentByTag(RotationPersist1WorkerFragment.class.getName());
//
//        if (frag != null) {
//            getSupportFragmentManager().beginTransaction().remove(frag).commit();
//        }
//
//        frag = getSupportFragmentManager()//
//                .findFragmentByTag(RotationPersist2WorkerFragment.class.getName());
//
//        if (frag != null) {
//            getSupportFragmentManager().beginTransaction().remove(frag).commit();
//        }
    }
}
