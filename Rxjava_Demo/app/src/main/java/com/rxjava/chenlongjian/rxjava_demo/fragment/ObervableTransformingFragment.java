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
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by chenlongjian on 2016/7/8.
 */
public class ObervableTransformingFragment extends Fragment implements View.OnClickListener{

    private Button mClearbtn;

    private Button mBufferbtn;
    private Button mFlatmapbtn;




    private LogAdapter _adapter;
    private List<String> _logs;
    private ListView _logsList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_observabletransforming, container, false);

        initView(layout);

        _logsList = (ListView)layout.findViewById(R.id.log_list);
        _setupLogger();

        return  layout;
    }

    void initView(View view)
    {
        mClearbtn = (Button)view.findViewById(R.id.clearlog);
        mClearbtn.setOnClickListener(this);

        mBufferbtn = (Button)view.findViewById(R.id.buffer);
        mBufferbtn.setOnClickListener(this);

        mFlatmapbtn = (Button)view.findViewById(R.id.flatmap);
        mFlatmapbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clearlog:
                _clearLogger();
                break;

            case R.id.buffer:
                bufferObservable().subscribe(getObserver());
                bufferTimeObservable().subscribe(getObserver());
                break;

            case R.id.flatmap:
                flatMapObservable().subscribe(getObserver());
                flatMapIterableObservable().subscribe(getObserver());
                mapObservable().subscribe(getObserver());


            default:
                break;
        }
    }

//    用object接受，可以处理所有的类型
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

//    buffer第一个参数是，组装几个数据，第二个参数是跳过几个参数，
// 比如（1,1）组装一个数据，跳过一个数据。结果是每次输出一个。1,2,3,4,5,6,7,8,9,
//    （1,2） 1,3,5,7,9
//    （1,3） 1,4,7
//    （2,1） 12,23,34,45,56,67,78,89,9；

    private Observable<List<Integer>> bufferObservable()
    {
        return Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).buffer(2, 1);
    }

    //buffer另外一种用法，根据时间间隔控制 3秒钟缓存，发射一次
    private Observable<List<Long>> bufferTimeObservable() {
        return Observable.interval(1, TimeUnit.SECONDS).buffer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }


    //传入INTEGER类型，变换位String类似输出Observable 与MAP转换不同，map仅仅是进行参数的操作，不进行Observable的输出。对比在下面。
    private Observable<String> flatMapObservable() {
        return Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just("flatmap转换类型添加内容输出"+integer);
            }
        });
    }

    private Observable<String> flatMapIterableObservable()
    {
        return Observable.just(1,2,3).flatMapIterable(new Func1<Integer, Iterable<String>>() {
            @Override
            public Iterable<String> call(Integer integer) {
                ArrayList<String> s = new ArrayList<>();
                for (int i = 0; i < integer; i++) {
                    s.add("修改数据"+integer);
                }
                return s;
            }
        });
    }


    private Observable<String> mapObservable()
    {
        return Observable.just(1,2,3,4,5,6,7,8,9).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "map仅仅添加String--："+integer;
            }
        });
    }

    private GroupedObservable groupObservable()
    {
        return  GroupedObservable.create(1, new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {

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
