package com.kaplan.rssmvvm.news.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.*

@Root(strict = false, name = "rss")
data class Rss(@field:Element(name= "channel") var channel: Channel? = null)

@Root(strict = false, name = "channel")
data class Channel @JvmOverloads constructor(@field:ElementList(inline = true) var items: List<Item>? = null)

@Entity(tableName = "news")
@Root(name = "item", strict = false)
data class Item(@field:Element(name = "title", required = false) var title: String ="",
                @PrimaryKey @field:Element(name = "guid",required = false) var guid: Int =0,
                @field:Element(name = "pubDate", required = false) var pubDate: String= "",
                @Embedded @field:Element(name = "enclosure", required = false) var enclosure: Enclosure? = null,
                @field:Element(name = "description", required = false) var description: String= "",
                @field:Element(name = "link", required = false) var link: String= "",
                @field:Element(name = "category", required = false) var category: String= "")

data class Enclosure(@field:Attribute(name = "type") var type: String= "",
                     @field:Attribute(name = "length") var length: String= "",
                     @PrimaryKey @field:Attribute(name = "url") var url: String = "")

