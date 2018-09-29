package com.aiden.kotlintest.utils

object MathUtils {

    fun getMinRotateArray(array: IntArray): Int {

        if (array.isEmpty()) {
            throw RuntimeException("input error!")
        }
        if (array.size == 1) {
            return array[0]
        }

        var left = 0
        var right = array.size - 1
        var mid = left
        var result = array[0]

        // 确保left下标对应的值在左边的递增数组，right下表对应的值在右边的递增数组
        while (array[left] >= array[right]) {
            if (right - left == 1) {
                return array[right]
            }

            mid = (left + right) / 2

            // 三个值相同的情况，需遍历数组取最小值
            if (array[left] == array[mid] && array[mid] == array[right]) {
                result = array[left]
                for (i in left + 1..right) {
                    if (array[i] < result) {
                        result = array[i]
                    }
                }
                return result
            }

            if (array[mid] >= array[left]) {
                left = mid
            } else {
                right = mid
            }
        }
        return result
    }

    fun pow(base: Double, exp: Int): Double {
        if (base == 0.0) {
            return 0.0
        }

        var exponent = exp
        var result = 1.0

        var isNegative = false

        if (exponent < 0) {
            isNegative = true
            exponent = - exponent
        }
        result = getResult(base, exponent)
        if (isNegative) {
            return 1 / result
        }
        return result
    }

    private fun getResult(base: Double, exp: Int): Double {
        if (exp == 0) {
            return 1.0
        }
        if (exp == 1) {
            return base
        }

        // 通过移位运算代替除以2
        var result = getResult(base, exp shr 1)
        result *= result

        // 用位与运算代替%2
        if ((exp and 0x1) == 1) {
            result *= base
        }
        return result
    }

    class JavaCode {
        fun toJSON(collection: Collection<Int>): String {
            val sb = StringBuilder()
            sb.append("[")
            val iterator = collection.iterator()
            while (iterator.hasNext()) {
                val element = iterator.next()
                sb.append(element)
                if (iterator.hasNext()) {
                    sb.append(", ")
                }
            }
            sb.append("]")
            return sb.toString()
        }
    }
}