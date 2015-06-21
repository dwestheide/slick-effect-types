package com.danielwestheide.slickeffecttypes.db

class DatabaseModule {
  val masterDatabase: DB[Master] = new DB(DatabaseConfiguration.Master)
  val slaveDatabase: DB[Slave] = new DB[Slave](DatabaseConfiguration.Slave)
}
