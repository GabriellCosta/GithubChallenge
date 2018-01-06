package me.tigrao.service

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class RetrofitBuilderSpec : Spek({

    given("RetrofitBuilder") {
        it("should create a Retrofit instance to github") {

            val builder = RetrofitBuilder.build("http://github.com")

            val expected = equalTo("http://github.com/")
            assert.that(builder.baseUrl().toString(), expected)
        }
    }

})