package com.danielwestheide.slickeffecttypes.statuses

import java.time.LocalDateTime
import slick.driver.H2Driver.api._
import CustomColumnTypes._

class StatusTable(tag: Tag) extends Table[Status](tag, "statuses") {

  def id = column[StatusId]("id")
  def createdAt = column[LocalDateTime]("created_at")
  def author = column[String]("author")
  def text = column[String]("text")
  def category = column[String]("category")

  def * = (id, createdAt, author, text, category) <>
    (Function.tupled(Status.apply _), Status.unapply)

}

object StatusTable {

  lazy val statuses = TableQuery[StatusTable]

}
