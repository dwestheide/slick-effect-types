package com.danielwestheide.slickeffecttypes.statuses

import slick.driver.H2Driver.api._
import slick.driver.H2Driver.backend.DatabaseDef
import CustomColumnTypes._
import StatusTable._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class StatusRepository(database: DatabaseDef) {

  def save(status: Status): Future[Unit] = {
    for {
      _ <- database.run(statuses += status)
    } yield ()
  }

  def forId(statusId: StatusId): Future[Option[Status]] = {
    database.run {
      statuses.filter(_.id === statusId).result.headOption
    }
  }

}
