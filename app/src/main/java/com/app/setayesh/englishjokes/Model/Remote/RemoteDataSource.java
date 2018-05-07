package com.app.setayesh.englishjokes.Model.Remote;

import android.util.Log;

import com.app.setayesh.englishjokes.Model.AppDataContract;
import com.app.setayesh.englishjokes.Model.pojo.Joke;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements AppDataContract {

    private ApiService apiService;

    @Inject
    public RemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Flowable<Joke> getAllJokes() {
        return apiService.getJokesFromServer()
                .subscribeOn(Schedulers.io())
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("1397", throwable.getMessage());
                    }
                });
    }

    @Override
    public void insertAllJokes(Joke joke) {

    }

    @Override
    public void deleteAllJokes() {

    }





    /*
    @Override
    public void findItems(final setOnArrayListener listener) {
       Call<Joke> call =apiService.getJokesFromServer();
       call.enqueue(new Callback<Joke>() {
           @Override
           public void onResponse(Call<Joke> call, Response<Joke> response) {
               if (response.isSuccessful()) {
                   Joke list =response.body();
                   listener.addArrayListener(list);
                   listener.addBooleanListener(true);
                   Log.i("625", list.getValue().size()+"  remote size");
               } else {
                   listener.addBooleanListener(false);
                   Log.i("625", "error");
               }
           }

           @Override
           public void onFailure(Call<Joke> call, Throwable t) {
               listener.addBooleanListener(false);
               Log.i("625", "error remote: "+t.toString());
           }
       });
    }
     */
}