package com.sahrial.submission_3_jetpackpro

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

object PagingTest {
    fun <T> listPage(list: List<T>): PagedList<T> {
        val data = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(data[ArgumentMatchers.anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(data.size).thenReturn(list.size)

        return data
    }
}