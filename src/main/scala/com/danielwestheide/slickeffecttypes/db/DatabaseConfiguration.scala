package com.danielwestheide.slickeffecttypes.db

import slick.driver.H2Driver.backend.DatabaseDef
import slick.driver.H2Driver.api._

sealed trait DatabaseConfiguration[R <: Role] {
  def createDatabase(): DatabaseDef
}

object DatabaseConfiguration {
  object Master extends DatabaseConfiguration[Master] {
    def createDatabase() = Database.forConfig("databases.master")
  }
  object Slave extends DatabaseConfiguration[Slave] {
    def createDatabase() = Database.forConfig("databases.slave")
  }
}
