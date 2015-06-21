package com.danielwestheide.slickeffecttypes.statuses

import java.sql.Timestamp
import java.time.LocalDateTime
import slick.driver.H2Driver.api._

trait CustomColumnTypes {

  implicit lazy val statusIdType =
    MappedColumnType.base[StatusId, String](_.value, StatusId.apply)
  implicit lazy val localDateTimeType =
    MappedColumnType.base[LocalDateTime, Timestamp](Timestamp.valueOf, _.toLocalDateTime)

}

object CustomColumnTypes extends CustomColumnTypes
