package org.mixit.kotlin.web

import com.github.javafaker.Faker
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class HotelsController {


    data class Hotel(
            val id:Long,
            val name: String,
            val address:String,
            val zip:String,
            val city:String,
            val country:String = "France"
    )

    val faker = Faker()


    @RequestMapping("/api/hotels",method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun getHotels() =
            (1..33).map {
                Hotel(it.toLong(),
                        "Hotel ${faker.address().cityName()}",
                        "${faker.address().streetAddress()}",
                        "${faker.address().zipCode()}",
                        "${faker.address().cityName()}")
            }


    data class ChangeNameCmd (val name: String)

    @RequestMapping("/api/hotels/{id}", method = arrayOf(RequestMethod.PUT))
    @ResponseBody
    fun changeName(@PathVariable id:Long, @RequestBody cmd:ChangeNameCmd){
        println("Changing name of hotel $id :: $cmd")
    }

}


