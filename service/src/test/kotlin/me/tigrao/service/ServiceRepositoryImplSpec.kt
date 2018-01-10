package me.tigrao.service

import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito

class ServiceRepositoryImplSpec: Spek({

    given("a ServiceRespository implementation") {
        val repositorieDTO = readFile("repositories_page_1.json", RepositorieDTO::class.java)
        val observer = mock<Observer<RepositorieDTO>>()
        val serviceWrapper = mock<ServiceWrapper>()
        val newInstance = ServiceRepositoryImpl(serviceWrapper)
        val mock = mock<RepositoryRequest>()
        Mockito.`when`(serviceWrapper.search(mock)).then {
            observer.onChanged(repositorieDTO)
        }

        on("repositorie fetch") {
            newInstance.fetchRepositories(mock)
                    .observeForever(observer)

            it("should observe the correct value") {
                verify(observer).onChanged(repositorieDTO)
            }

        }

    }




})