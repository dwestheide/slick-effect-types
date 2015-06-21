package com.danielwestheide.slickeffecttypes

import slick.dbio.Effect
import slick.dbio.Effect.{Transactional, Schema, Write, Read}

import scala.annotation.implicitNotFound

package object db {

  trait Role
  trait Master extends Role
  trait Slave extends Role

  @implicitNotFound("'${R}' database is not privileged to perform effect '${E}'.")
  trait HasPrivilege[R <: Role, E <: Effect]

  implicit val slaveCanRead: Slave HasPrivilege Read = null
  implicit val slaveCanDoExpensiveReads: Slave HasPrivilege ExpensiveRead = null

  type ReadWriteTransaction = Read with Write with Transactional
  trait ExpensiveRead extends Read

  implicit val masterCanRead: Master HasPrivilege Read = null
  implicit val masterCanWrite: Master HasPrivilege Write = null
  implicit val masterCanUpdateSchema: Master HasPrivilege Schema = null
  implicit val masterCanPerformTransactions: Master HasPrivilege ReadWriteTransaction = null

}
