package com.danielwestheide.slickeffecttypes.statuses

import com.danielwestheide.slickeffecttypes.db.DatabaseModule

class StatusModule(databaseModule: DatabaseModule) {
  import databaseModule._

  val statusRepository = new StatusRepository
  val statusReadService = new StatusReadService(slaveDatabase)
  val statusService = new StatusService(statusRepository, masterDatabase)
}
