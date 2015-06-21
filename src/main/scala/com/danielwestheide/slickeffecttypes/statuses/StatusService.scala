package com.danielwestheide.slickeffecttypes.statuses

import com.danielwestheide.slickeffecttypes.db.{DB, Master}
import slick.dbio.DBIO
import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class StatusService(statusRepository: StatusRepository, db: DB[Master]) {

  def postStatus(author: String, text: String, category: String): Future[Status] = {
    val status = Status.create(author, text, category)
    for {
      _ <- db run statusRepository.save(status)
    } yield status
  }

  def categorize(statusId: StatusId, newCategory: String): Future[Either[String, Status]] = {
    val actions = for {
      statusOpt <- statusRepository.forId(statusId)
      result <- statusOpt match {
        case Some(status) =>
          val newStatus = status.copy(category = newCategory)
          statusRepository.save(newStatus).map(_ => Right(newStatus))
        case None => DBIO.successful(Left("unknown status"))
      }
    } yield result
    db.run(actions.transactionally)
  }

}
