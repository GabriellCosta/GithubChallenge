package me.tigrao.service

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
        val success = mock<Function1<String, Unit>>()
        val error = mock<Function1<String, Unit>>()
        val enqueueHandler = EnqueueHandler(success, error)
        val mockResponse = mock<Response<String>>()

        on("handler a success enqueue") {
            `when`(mockResponse.isSuccessful).thenReturn(true)
            `when`(mockResponse.body()).thenReturn("teste")
            enqueueHandler.handler(mockResponse)

            it("should set his value on observer") {
                verify(success)("teste")
            }
        }

        on("handler a fail enqueue") {
            `when`(mockResponse.isSuccessful).thenReturn(false)
            enqueueHandler.handler(mockResponse)

            it("should set the error on errorObserver") {
                verify(error)(EnqueueHandler.ERROR_MESSAGE)
            }

        }

    }


})