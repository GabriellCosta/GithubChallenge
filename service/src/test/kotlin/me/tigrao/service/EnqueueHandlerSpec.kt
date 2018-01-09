package me.tigrao.service

import android.arch.lifecycle.MutableLiveData
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.`when`
import retrofit2.Response

class EnqueueHandlerSpec : Spek({


    given("EnqueueHandler") {
        val mock = mock<MutableLiveData<String>>()
        val errorMock = mock<MutableLiveData<String>>()
        val enqueueHandler = EnqueueHandler(mock, errorMock)
        val mockResponse = mock<Response<String>>()

        on("handler a success enqueue") {
            `when`(mockResponse.isSuccessful).thenReturn(true)
            `when`(mockResponse.body()).thenReturn("teste")
            enqueueHandler.handler(mockResponse)

            it("should set his value on observer") {
                verify(mock).value = "teste"
            }
        }

        on("handler a fail enqueue") {
            `when`(mockResponse.isSuccessful).thenReturn(false)
            enqueueHandler.handler(mockResponse)

            it("should set the error on errorObserver") {
                verify(errorMock).value = EnqueueHandler.ERROR_MESSAGE
            }

        }

    }


})