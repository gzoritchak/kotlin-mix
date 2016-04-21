package org.mixit.kotlin.db

import org.jetbrains.exposed.sql.Table

object City : Table() {
    val id          = long("id").autoIncrement().primaryKey()
    val name        = text("name")
    val state       = text("state")
    val country     = text("country")
    val map         = text("map")
}

object Review : Table(){
    val id          = long("id").autoIncrement().primaryKey()
    val checkInDate = date("check_in_date")
    val details     = text ("details")
    val idx         = integer ("idx")
    val rating      = integer ("rating")
    val title       = text ("title")
    val tripType    = text ("trip_type")
    val hotel       = reference ("hotel_id", Hotel.id)
}

object Hotel : Table(){
    val id          = long("id").autoIncrement().primaryKey()
    val city        = reference("city_id", City.id)
    val name        = text("name")
    val address     = text("address")
    val zip         = text("zip")

}
