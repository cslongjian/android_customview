package com.rxjava.chenlongjian.rxjava_demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rxjava.chenlongjian.rxjava_demo.R;

/**
 * Created by chenlongjian on 2016/7/6.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    private Button mObservable;
    private Button mObservableTransfrombtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);

        initView(layout);

        return  layout;
    }


    void initView(View view)
    {
        mObservable = (Button) view.findViewById(R.id.btn_obserable);
        mObservable.setOnClickListener(this);

        mObservableTransfrombtn = (Button) view.findViewById(R.id.btn_obserable_change);
        mObservableTransfrombtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_obserable:
                clickedOn(new ObservableCreateFragment());
                break;
            case R.id.btn_obserable_change:
                clickedOn(new ObervableTransformingFragment());
            default:
                break;
        }
    }

    private void clickedOn(@NonNull Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }

}
