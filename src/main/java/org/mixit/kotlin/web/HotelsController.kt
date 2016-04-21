package org.mixit.kotlin.web

import org.mixit.kotlin.domain.Hotel
import org.mixit.kotlin.service.HotelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class HotelsController {

    @Autowired
    lateinit var hotelService: HotelService

    data class HotelVo(
            val id:Long,
            val name: String,
            val address:String,
            val zip:String,
            val city:String,
            val country:String = "France"
    )


    fun Hotel.toVo() = HotelVo(
            id!!, name!!, address ?: "", zip ?: "", city?.name ?: "", city?.country ?: "")

    @RequestMapping("/api/hotels",method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun getHotels() = hotelService.hotels.map { it.toVo() }

    data class ChangeNameCmd (val name: String)

    @RequestMapping("/api/hotels/{id}", method = arrayOf(RequestMethod.PUT))
    @ResponseBody
    fun changeName(@PathVariable id:Long, @RequestBody cmd:ChangeNameCmd){
        println("Changing name of hotel $id :: $cmd")
        hotelService.changeName(id, cmd.name)
    }

}


