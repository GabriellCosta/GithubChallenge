package me.tigrao.service

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilderSpec : Spek({

    given("RetrofitBuilder") {
        val builder = RetrofitBuilder.build("http://github.com")

        it("should create a Retrofit instance to github") {
            val expected = equalTo("http://github.com/")
            assert.that(builder.baseUrl().toString(), expected)
        }

        it("should have GsonConverter") {
            val converterFactories = builder.converterFactories()
            val filterIsInstance = converterFactories.filterIsInstance<GsonConverterFactory>()
            listOfNotNull(filterIsInstance)
        }

    }

})