package com.danielwestheide.slickeffecttypes

import com.danielwestheide.slickeffecttypes.db.DatabaseModule
import com.danielwestheide.slickeffecttypes.statuses._

import scala.concurrent.Await
import scala.concurrent.duration._

object App extends App with SchemaCreation {

  val databaseModule = new DatabaseModule
  val statusModule = new StatusModule(databaseModule)

  createSchema(databaseModule)

  val status = Await.result(
    statusModule.statusService.postStatus(
      "danielw",
      "Played around with Slick effect types today",
      "scala"),
    3.seconds)

  val update = statusModule.statusService.categorize(status.id, "phantom-types")

  Await.result(update, 1.second)

  Await.result(statusModule.statusReadService
    .statusesByAuthor("danielw", 0, 10), 100.milliseconds) foreach println

}