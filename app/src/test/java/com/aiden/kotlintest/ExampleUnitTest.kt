package com.aiden.kotlintest

import com.aiden.kotlintest.extension.formatHMS
import com.aiden.kotlintest.extension.formatYMD
import com.aiden.kotlintest.extension.formatYMDHMS
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        System.out.println(Date().formatYMDHMS())
        System.out.println(Date().formatYMD())
        System.out.println(Date().formatHMS())
    }

    @Test
    fun test1() {
        var result = "Hello".apply {
            println(this + " World")
            this + " World"
        }
        println(result)

        var result2 = "Hello".run {
            println(this + " World")
            this + " World"
        }
        println(result2)

    }
}
