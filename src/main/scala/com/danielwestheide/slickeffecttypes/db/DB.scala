package com.danielwestheide.slickeffecttypes.db

import slick.dbio.{NoStream, DBIOAction}
import slick.driver.H2Driver.api._

import scala.concurrent.Future

class DB[R <: Role](databaseConfiguration: DatabaseConfiguration[R]) {

  private val underlyingDatabase = databaseConfiguration.createDatabase()

  def run[A, E <: Effect](a: DBIOAction[A, NoStream, E])(implicit p: R HasPrivilege E): Future[A] = underlyingDatabase.run(a)

}
