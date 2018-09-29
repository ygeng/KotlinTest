package com.aiden.kotlintest

import android.util.Log
import com.aiden.kotlintest.extension.formatHMS
import com.aiden.kotlintest.extension.formatYMD
import com.aiden.kotlintest.extension.formatYMDHMS
import com.aiden.kotlintest.utils.MathUtils
import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.HashMap

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

    @Test
    fun test2() {
        varargTest("111", "222", "333")
        varargTest("555", "666")
    }

    lateinit var stringArray: Array<String>

    fun varargTest(vararg stringParams: String) {
        stringArray = stringParams as Array<String>
        for (s in stringArray) {
            print(s)
        }
        println()
    }

    @Test
    fun test3() {
        val map = HashMap<String, String>()
        map.put("1", "111")
        map.put("1", "222")
        System.out.println(map.toString())
    }

    @Test
    fun test4() {
        val array1 = intArrayOf(1, 0, 1, 1, 1)
        System.out.println(MathUtils.getMinRotateArray(array1))

        val array2 = intArrayOf(3, 4, 5, 1, 1, 2)
        System.out.println(MathUtils.getMinRotateArray(array2))

        System.out.println(MathUtils.pow(-2.0, 0))
    }
}
