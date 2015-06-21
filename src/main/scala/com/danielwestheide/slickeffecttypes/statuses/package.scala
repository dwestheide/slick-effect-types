package com.danielwestheide.slickeffecttypes

import java.time.{LocalDateTime, Clock, OffsetDateTime}
import java.util.UUID

package object statuses {

  case class Status(
    id: StatusId,
    createdAt: LocalDateTime,
    author: String,
    text: String,
    category: String
  )

  case class StatusId(value: String) extends AnyVal

  object StatusId {
    def nextId(): StatusId = StatusId(UUID.randomUUID().toString)
  }

  object Status {
    def create(author: String, text: String, category: String): Status = {
      Status(StatusId.nextId(), LocalDateTime.now(Clock.systemUTC()), author, text, category)
    }
  }




}
