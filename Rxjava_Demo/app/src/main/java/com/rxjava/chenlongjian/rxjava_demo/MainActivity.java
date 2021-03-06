package com.rxjava.chenlongjian.rxjava_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Button btn2;

    private LogAdapter _adapter;
    private List<String> _logs;
    private  ListView _logsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        _logsList = (ListView)findViewById(R.id.log_list);


        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ObservableMethodActivity.class);
                startActivity(intent);
            }
        });

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create method
                createObserver()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.w("Rxjava","onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("Rxjava","onError:" + e.getMessage());

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.w("Rxjava","onNext:" + integer);
                        _log("onNext"+integer);

                    }
                });

                //range method
                rangeObserver().subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        _log("rangeObserver -- onNext"+integer);
                    }
                });

                ////

            }
        });

        _setupLogger();
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
        return Observable.range(100,5);
    }




    private void _log(String logMsg) {

        if (_isCurrentlyOnMainThread()) {
            _logs.add(0, logMsg + " (main thread) ");
            _adapter.clear();
            _adapter.addAll(_logs);

            Log.w("Rxjava", "主线程上"+logMsg);

        } else {
            _logs.add(0, logMsg + " (NOT main thread) ");

            Log.w("Rxjava", "非主线程上"+logMsg);

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
        _adapter = new LogAdapter(this, new ArrayList<String>());
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
