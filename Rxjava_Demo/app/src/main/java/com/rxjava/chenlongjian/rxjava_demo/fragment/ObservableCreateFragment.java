package com.rxjava.chenlongjian.rxjava_demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.rxjava.chenlongjian.rxjava_demo.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by chenlongjian on 2016/7/7.
 */
public class ObservableCreateFragment extends Fragment implements View.OnClickListener {

    private Button mClearbtn;
    private Button createbtn;
    private Button mRangebtn;
    private Button mDeferbtn;
    private Button mJustbtn;
    private Button mFrombtn;
    private Button mIntervalbtn;
    private Button mRepeatbtn;
    private Button mTimebtn;


    private LogAdapter _adapter;
    private List<String> _logs;
    private ListView _logsList;

    private Subscription mSubscription; //用于解绑


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_observablecreate, container, false);
        initView(layout);
        return  layout;
    }

    void initView(View view)
    {

        mClearbtn = (Button)view.findViewById(R.id.clearlog);
        mClearbtn.setOnClickListener(this);

        createbtn = (Button)view.findViewById(R.id.create);
        createbtn.setOnClickListener(this);

        mRangebtn = (Button)view.findViewById(R.id.range);
        mRangebtn.setOnClickListener(this);

        mDeferbtn = (Button)view.findViewById(R.id.defer);
        mDeferbtn.setOnClickListener(this);

        mJustbtn = (Button)view.findViewById(R.id.just);
        mJustbtn.setOnClickListener(this);

        mFrombtn = (Button)view.findViewById(R.id.from);
        mFrombtn.setOnClickListener(this);

        mIntervalbtn = (Button)view.findViewById(R.id.interval);
        mIntervalbtn.setOnClickListener(this);

        mRepeatbtn = (Button)view.findViewById(R.id.repeat);
        mRepeatbtn.setOnClickListener(this);

        mTimebtn = (Button)view.findViewById(R.id.time);
        mTimebtn.setOnClickListener(this);

        _logsList = (ListView)view.findViewById(R.id.log_list);
        _setupLogger();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.clearlog:
                _clearLogger();
                break;

            case R.id.create:
                mSubscription = createObservable()
                            .subscribeOn(Schedulers.io()) //设置被观察者启动订阅跑在IO线程，属于后台操作
                            .observeOn(AndroidSchedulers.mainThread()) //设置观察者跑在主线程，回调用于刷新UI
                            .subscribe(getObserver());
                //不设置的情况下，都跑在主线程中。
//                createObserver().subscribe(subscriber);
                break;

            case R.id.range:
                mSubscription =  rangeObservable().subscribe(getObserver());
                break;

            case R.id.defer:
                deferObservable().subscribe(getObserver());
                break;

            case R.id.just:
                justObservable().subscribe(getObserver());
                break;

            case R.id.from:
                fromObservable().subscribe(getObserver());
                break;

            case R.id.interval:
                intervalObservable().subscribe(getObserver());
                break;

            case R.id.repeat:
                repeatObservable().subscribe(getObserver());
                break;

            case R.id.time:
                timeObservable().subscribe(getObserver());
                break;

            default:
                break;
        }
    }


//    标准的create创建方式。内部要创建一个订阅者，用于通知
    private Observable<Integer> createObservable() {
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

//    传递5个数，从1开始的整数
    private Observable<Integer> rangeObservable()
    {
        return Observable.range(1,5);
    }

    //当订阅时候才调用创建新的被观察对象
    private Observable<Integer> deferObservable()
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

    //just 发送一个对象，过去。
    private Observable<Integer> justObservable()
    {
        return Observable.just(123123123);
    }

    //from 将数组或者集合中的对象，一个一个的发送出去。
    private Observable<Integer> fromObservable()
    {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0 ; i<5; i++)
        {
            arrayList.add(i*2+5);
        }
        return Observable.from(arrayList);
    }

    //设置的参数的。每个多少单位时间，发出一个对象，从0开始，（如下设置是，每5S。发出一个。从0开始。）
    private Observable<Long> intervalObservable()
    {
        return  Observable.interval(5, TimeUnit.SECONDS);
    }

    //重复发送
    private Observable<Integer> repeatObservable()
    {
        return  Observable.just(1).repeat(5);
    }


    //指定多少时间单位后发送一个0。。
    private Observable<Long> timeObservable()
    {
        return Observable.timer(5, TimeUnit.SECONDS);
    }

   private Observer<Object> getObserver()
   {
       return new Observer<Object>() {
           @Override
           public void onCompleted() {
               _log("----触发完成条件----");
           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onNext(Object o) {
               _log("onNext----:"+o.toString());
           }
       };
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

    private void _clearLogger()
    {
        if (_logs.size()>0) {
            _logs.clear();
            _adapter.clear();
            _adapter.addAll(_logs);
        }
    }

    private boolean _isCurrentlyOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private class LogAdapter extends ArrayAdapter<String> {

        public LogAdapter(Context context, List<String> logs) {
            super(context, R.layout.item_log, R.id.item_log, logs);
        }
    }


}
