package com.danielwestheide.slickeffecttypes

import com.danielwestheide.slickeffecttypes.db.DatabaseModule
import com.danielwestheide.slickeffecttypes.statuses.StatusTable
import slick.dbio.DBIO
import slick.driver.H2Driver.api._

import scala.concurrent.Await
import scala.concurrent.duration._

trait SchemaCreation {
  def createSchema(databaseModule: DatabaseModule) = {
    Await.result(databaseModule.masterDatabase.run(
      DBIO.seq(StatusTable.statuses.schema.drop, StatusTable.statuses.schema.create)
    ), 2.seconds)
  }
}
