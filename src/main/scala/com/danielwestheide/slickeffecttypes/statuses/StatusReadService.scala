package com.danielwestheide.slickeffecttypes.statuses

import com.danielwestheide.slickeffecttypes.db.{DB, ExpensiveRead, Slave}
import com.danielwestheide.slickeffecttypes.statuses.CustomColumnTypes._
import com.danielwestheide.slickeffecttypes.statuses.StatusTable._
import slick.driver.H2Driver.api._
import slick.jdbc.GetResult
import slick.profile.SqlStreamingAction

import scala.concurrent.Future

class StatusReadService(database: DB[Slave]) {

  implicit val getStatusId = GetResult(r => StatusId(r.nextString()))
  implicit val getLocalDateTime = GetResult(r => r.nextTimestamp().toLocalDateTime)
  implicit val getStatusResult = GetResult(r => Status(r.<<, r.<<, r.<<, r.<<, r.<<))

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

  def statusesByCategory(category: String, offset: Int, limit: Int): Future[Seq[Status]] = {
   val action: SqlStreamingAction[Seq[Status], Status, ExpensiveRead] =
     sql"""select id, created_at, author, text, category from statuses
         where category = $category
         order by created_at desc limit $limit offset $offset""".as[Status]
    database.run(action)
  }

}
