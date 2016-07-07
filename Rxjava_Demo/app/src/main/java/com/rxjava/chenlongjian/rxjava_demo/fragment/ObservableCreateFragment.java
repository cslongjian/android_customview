package com.rxjava.chenlongjian.rxjava_demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.rxjava.chenlongjian.rxjava_demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongjian on 2016/7/7.
 */
public class ObservableCreateFragment extends Fragment implements View.OnClickListener {

    private Button createbtn;
    private Button mRangebtn;


    private LogAdapter _adapter;
    private List<String> _logs;
    private ListView _logsList;

    private Subscription mSubscription; //用于解绑

    private Subscriber subscriber = new Subscriber() {
        @Override
        public void onCompleted() {
            _log("触发结束条件,或者自动调用");
        }

        @Override
        public void onError(Throwable e) {
            _log(e.toString());
        }

        @Override
        public void onNext(Object o) {
            _log(o.toString());

        }
    };

    private Subscriber mRangeSubscriber = new Subscriber() {
        @Override
        public void onCompleted() {
            _log("触发结束条件,或者自动调用");
        }

        @Override
        public void onError(Throwable e) {
            _log(e.toString());
        }

        @Override
        public void onNext(Object o) {
            _log("range--:"+o.toString());

        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_observablecreate, container, false);

        initView(layout);

        return  layout;
    }

    void initView(View view)
    {
        createbtn = (Button)view.findViewById(R.id.create);
        createbtn.setOnClickListener(this);

        mRangebtn = (Button)view.findViewById(R.id.range);
        mRangebtn.setOnClickListener(this);

        _logsList = (ListView)view.findViewById(R.id.log_list);
        _setupLogger();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.create:
                    mSubscription = createObserver()
                            .subscribeOn(Schedulers.io()) //设置跑在IO线程，属于后台操作
                            .observeOn(AndroidSchedulers.mainThread()) //设置跑在主线程，用于刷新UI
                            .subscribe(subscriber);
                //不设置的情况下，都跑在主线程中。
//                createObserver().subscribe(subscriber);
                break;
            case R.id.range:
                    mSubscription =  rangeObserver().subscribe(mRangeSubscriber);
                break;
            default:
                break;
        }
    }


    private Observable<Integer> createObserver() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 5; i++) {
                        int temp = new Random().nextInt(10);
                        if (temp > 8) {
                            //if value>8, we make an error
                            subscriber.onError(new Throwable("value >8"));
                            break;
                        } else {
                            subscriber.onNext(temp);
                            _log("call"+temp);
                        }
                        // on error,complete the job
                        if (i == 4) {
                            subscriber.onCompleted();
                        }
                    }
                }
            }
        });
    }

    private Observable<Integer> rangeObserver()
    {
        return Observable.range(1,5);
    }


    private Observable<Integer> deferObserver()
    {
        return Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                //延时出来了，内部要返回一个Observable  
               return  Observable.create(new Observable.OnSubscribe<Integer>() {
                   @Override
                   public void call(Subscriber<? super Integer> subscriber) {
                       subscriber.onNext(123);
                       subscriber.onCompleted();
                   }
               });
            }
        });
    }


    ///log日志 部分
    private void _log(String logMsg) {

        if (_isCurrentlyOnMainThread()) {
            _logs.add(0, logMsg + " (main thread) ");
            _adapter.clear();
            _adapter.addAll(_logs);
        } else {
            _logs.add(0, logMsg + " (NOT main thread) ");

            // You can only do below stuff on main thread.
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    _adapter.clear();
                    _adapter.addAll(_logs);
                }
            });
        }
    }

    private void _setupLogger() {
        _logs = new ArrayList<String>();
        _adapter = new LogAdapter(getContext(), new ArrayList<String>());
        _logsList.setAdapter(_adapter);
    }

    private boolean _isCurrentlyOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }


    private class LogAdapter
            extends ArrayAdapter<String> {

        public LogAdapter(Context context, List<String> logs) {
            super(context, R.layout.item_log, R.id.item_log, logs);
        }
    }


}
