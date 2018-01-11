package me.tigrao.service

import retrofit2.Response

class EnqueueHandler<T>(private val success: (T) -> Unit,
                        private val error: (String) -> Unit) {

    companion object {
        const val ERROR_MESSAGE = "Fail to load list"
    }

    fun handler(response: Response<T>): Unit  =
            if (response.isSuccessful) success(response.body()!!)
            else error(ERROR_MESSAGE)

}

