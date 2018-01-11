package me.tigrao.service

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import retrofit2.Response

class EnqueueHandlerSpec : Spek({


    given("EnqueueHandler") {
        val success = mock<Function1<String, Unit>>()
        val error = mock<Function1<String, Unit>>()
        val enqueueHandler = EnqueueHandler(success, error)

        on("handler a success enqueue") {
            val mockResponse = mock<Response<String>> {
                on { isSuccessful } doReturn true
                on { body() } doReturn "teste"
            }
            enqueueHandler.handler(mockResponse)

            it("should set his value on sucesso function") {
                verify(success)("teste")
            }
        }

        on("handler a fail enqueue") {
            val mockResponse = mock<Response<String>> {
                on { isSuccessful } doReturn false
            }
            enqueueHandler.handler(mockResponse)

            it("should set the error on error function") {
                verify(error)(EnqueueHandler.ERROR_MESSAGE)
            }

        }

    }


})