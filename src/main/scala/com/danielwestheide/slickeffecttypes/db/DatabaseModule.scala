package com.danielwestheide.slickeffecttypes.db

import slick.driver.H2Driver.backend.DatabaseDef
import slick.driver.H2Driver.api._

class DatabaseModule {
  val masterDatabase: DatabaseDef = Database.forConfig("databases.master")
  val slaveDatabase: DatabaseDef = Database.forConfig("databases.slave")
}
