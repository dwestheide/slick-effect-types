package com.danielwestheide.slickeffecttypes.statuses

import com.danielwestheide.slickeffecttypes.statuses.CustomColumnTypes._
import com.danielwestheide.slickeffecttypes.statuses.StatusTable._
import slick.driver.H2Driver.api._

class StatusRepository {

  def save(status: Status) = statuses.insertOrUpdate(status)
  def forId(statusId: StatusId) = statuses.filter(_.id === statusId).result.headOption

}
