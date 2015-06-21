package com.danielwestheide.slickeffecttypes.statuses

import com.danielwestheide.slickeffecttypes.db.DatabaseModule

class StatusModule(databaseModule: DatabaseModule) {
  import databaseModule._

  val statusRepository = new StatusRepository(masterDatabase)
  val statusReadService = new StatusReadService(slaveDatabase)
}
