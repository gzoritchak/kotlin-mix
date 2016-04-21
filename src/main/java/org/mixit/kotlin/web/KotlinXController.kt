package org.mixit.kotlin.web

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.mixit.kotlin.db.City as TbCity
import org.mixit.kotlin.db.Hotel as TbHotel

@Controller
class  KotlinXController @Autowired constructor(val db: Database){

    data class Hotel(
            val id:Long,
            val name: String,
            val address:String,
            val zip:String,
            val city:String,
            val country:String)

    fun listHotels() = db.transaction {
        ((TbHotel innerJoin  TbCity)
                .selectAll()
                .map {
                    Hotel(  it[TbHotel.id],
                            it[TbHotel.name],
                            it[TbHotel.address],
                            it[TbHotel.zip],
                            it[TbCity.name],
                            it[TbCity.country])
                })

    }

    @RequestMapping("/kotlinx", produces = arrayOf(MediaType.TEXT_HTML_VALUE))
    @ResponseBody
    fun kotlinx() =  StringBuilder().appendHTML(false).html {
            header()
            body {
                navigation()
                headerwrap()
                section { id = "home" }
                hotels()
                section { id = "contact" }
            }
        }.toString()

    private fun BODY.hotels() {
        val hotels = listHotels()
        div("container"){
            div  ("row") {
                hotels.forEach {
                    hotel(it)
                }
            }
        }
    }

    private fun DIV.hotel(hotel: Hotel) {
        div ("hotel col-md-6") {
            div ("panel panel-default") {
                div ("panel-heading"){
                    h3("panel-title") { + hotel.name}
                }
                div ("panel-body") {
                    + hotel.address
                    br
                    + "${hotel.zip} ${hotel.city}"
                    br
                    + hotel.country
                }
            }
        }
    }

    private fun BODY.headerwrap() {
        div {
            id = "headerwrap"
            div ("container") {
                div ("row") {
                    h1 ("col-md-12"){ + "Kotlin Mix" }
                }
            }
        }
    }

    private fun BODY.navigation() {
        div ("navbar navbar-default navbar-fixed-top") {
            id = "navigation"

            div("container"){
                div( "navbar-header") {
                    a("#", classes = "navbar-brand") { b { + "MIX-IT" } }
                }
                div("navbar-collapse collapse"){
                    ul("nav navbar-nav") {
                        li { a ("/", classes= "smothscroll") { + "Thymeleaf"} }
                        li { a ("/angular", classes= "smothscroll") { + "Angular"} }
                        li ("active") { a ("/kotlinx", classes= " smothscroll") { + "Kotlinx"} }
                        li { a ("#contact", classes= "smothscroll") { + "Contact"} }
                    }
                }
            }
        }
    }

    private fun HTML.header() {
        head {
            title = "Kotlin Mix"
            listOf("/css/main.css",
                    "/webjars/bootstrap/3.3.6/css/bootstrap.min.css")
                    .forEach {
                        link {
                            media = "screen"
                            href = it
                            rel = "stylesheet"
                        }
                    }
        }
    }

}
