package me.tigrao.service

import android.arch.lifecycle.MutableLiveData
import retrofit2.Response


class EnqueueHandler<T>(private val valueObserver: MutableLiveData<T>,
                        private val errorObserver: MutableLiveData<String>) {

    fun handler(response: Response<T>) =
            if (response.isSuccessful) valueObserver.value = response.body()
            else errorObserver.value = "Sem valor"

}

