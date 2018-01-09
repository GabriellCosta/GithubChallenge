package me.tigrao.service

import android.arch.lifecycle.Observer
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import retrofit2.mock.MockRetrofit

class ServiceRepositoryImplSpec: Spek({

    given("a ServiceRespository implementation") {
        val repositorieDTO = mock<RepositorieDTO>()
        val observer = mock<Observer<RepositorieDTO>>()
        val build = RetrofitBuilder.build("http://api.github.com")
        val build1 = MockRetrofit.Builder(build)
                .build()
        val create = build1.create(ServiceEndpoint::class.java)
        create.returningResponse(Gson().toJson(repositorieDTO))
        val newInstance = ServiceRepositoryImpl(build.create(ServiceEndpoint::class.java))
        newInstance.valueObserver.observeForever(observer)

        on("repositorie fetch") {
            newInstance.fetchRepositories("java", "stars", 1)


            it("should have 30 items") {
                verify(observer).onChanged(repositorieDTO)
            }



        }

    }




})