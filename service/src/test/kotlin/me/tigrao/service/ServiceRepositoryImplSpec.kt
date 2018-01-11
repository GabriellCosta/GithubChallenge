package me.tigrao.service

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class ServiceRepositoryImplSpec : Spek({

    given("a ServiceRespository implementation") {
        val serviceWrapper = mock<ServiceWrapper>()
        val newInstance = ServiceRepositoryImpl(serviceWrapper)

        on("repository fetch") {
            val repositoryRequest = mock<RepositoryRequest>()
            val enqueueHandler = mock<EnqueueHandler<RepositorieDTO>>()
            newInstance.fetchRepositories(repositoryRequest, enqueueHandler)

            it("should call correct service wrapper method") {
                verify(serviceWrapper).search(repositoryRequest, enqueueHandler)
            }

        }

        on("pull request fetch") {
            val pullRequestRequest = mock<PullRequestRequest>()
            val enqueueHandler = mock<EnqueueHandler<List<PullRequestVO>>>()
            newInstance.fetchPullRequest(pullRequestRequest, enqueueHandler)

            it("should call correct service wrapper method") {
                verify(serviceWrapper).pullRequests(pullRequestRequest, enqueueHandler)
            }

        }

    }

})