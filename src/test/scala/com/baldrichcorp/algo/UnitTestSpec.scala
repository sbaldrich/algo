package com.baldrichcorp.algo

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

/**
  * Base class for Scala tests.
  *
  * @author Santiago Baldrich.
  */
@RunWith(classOf[JUnitRunner])
abstract class UnitTestSpec extends FlatSpec with Matchers{

}
