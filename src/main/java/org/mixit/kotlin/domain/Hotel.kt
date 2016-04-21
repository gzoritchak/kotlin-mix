package org.mixit.kotlin.domain

import javax.persistence.*

@Entity
open class Hotel  {

    @Id
    @GeneratedValue
    var id: Long? = null

    @ManyToOne(optional = false)
    var city: City?         = null
    var name: String?       = null
    var address: String?    = null
    var zip: String?        = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    open var reviews: MutableSet<Review> = hashSetOf()

}

