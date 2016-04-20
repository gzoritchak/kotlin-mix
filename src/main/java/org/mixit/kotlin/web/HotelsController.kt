package org.mixit.kotlin.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HotelsController {

    @RequestMapping("/api/hotels",method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun getHotels(){
        error("Not implemented")
    }

    @RequestMapping("/api/hotels/{id}", method = arrayOf(RequestMethod.PUT))
    @ResponseBody
    fun changeName(@PathVariable id:Long){
        error("Not implemented")
    }

}


