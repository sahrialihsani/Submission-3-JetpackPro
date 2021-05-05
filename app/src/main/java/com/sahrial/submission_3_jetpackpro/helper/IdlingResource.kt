package com.sahrial.submission_3_jetpackpro.helper


import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val RES = "GLOBAL"
    val idlingResource = CountingIdlingResource(RES)

    fun incr() {
        idlingResource.increment()
    }

    fun decr() {
        idlingResource.decrement()
    }
}
