package com.danielwestheide.slickeffecttypes.statuses

import com.danielwestheide.slickeffecttypes.statuses.CustomColumnTypes._
import com.danielwestheide.slickeffecttypes.statuses.StatusTable._
import slick.driver.H2Driver.api._
import slick.driver.H2Driver.backend.DatabaseDef

import scala.concurrent.Future

class StatusReadService(database: DatabaseDef) {

  def statusesByAuthor(author: String, offset: Int, limit: Int): Future[Seq[Status]] = {
    database.run {
      statuses
        .filter(_.author === author)
        .sortBy(_.createdAt.desc)
        .drop(offset)
        .take(limit)
        .result
    }
  }

}
