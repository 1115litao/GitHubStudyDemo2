package com.kotilndemo

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

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
fun operatorTest(){
        val  test1  = testDate(1,2)
        val  test2  = testDate(2,3)
        println(test2+test1)

}
    data class testDate(val x :Int,val y :Int){
        //两个对象相乘
        operator fun times(testdate: testDate):testDate{
            return testDate(x*testdate.x,y*testdate.y)
        }
    }

    //两个对象相加
    operator fun testDate.plus(testdata: testDate) : testDate{
        return testDate(x+testdata.x,y+testdata.y)
    }
    //一个对象和一个Int类型数据相乘
    operator fun testDate.times(scale:Int) : testDate{
        return testDate((x*scale),(y*scale))
    }

//集合操作
    @Test
    fun collectopnTest(){

            var listTest = mutableListOf<Int>(1,2,3,4,5,6,7,8)
            var listTest2 = mutableListOf<Int>(1,2,3,4,5)
//                var str = "123".toList()
//                listTest.toSet()  //转化为一个只可读的集合
//                listTest.remove(1)
//                listTest.retainAll(listTest2)//两个集合的交集
//                listTest.elementAt(0)//下标为0
//                listTest.elementAtOrElse(10,{123}) //查找下标对应元素，如果越界会根据方法返回默认值。
//                listTest.elementAtOrNull(10)//如果越界返回null
//                listTest.indexOf(3)//获取元素下标  如果没有返回的是-1
//                listTest.last()//获取集合中最后一个元素
//                print(listTest.elementAtOrNull(10) )



    var list = listOf<String>("123","asd","yu").flatMap {
        it.toList()
    }

    }



    @Test
    fun flatMapDemoTest(){
//        var list = listOf<String>("asd","123","re","156")
//
//        print( list.flatMap { it.toList() })


       var list2 =  listOf(1, 23, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7)
        print(list2.flatMap { listOf(it >5) })
    }

}



