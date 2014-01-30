package com.insightfullogic.quad_trees

import collection.mutable.Stack
import org.scalatest._
import org.scalatest.prop.PropertyChecks
import org.scalatest.prop.TableDrivenPropertyChecks._

//
//
class BoxSpec extends UnitTest with PropertyChecks {
  val coords = Table(
    ("x",   "y", "is contained"),
    (0.0,   1.0,  true),
    (-1.0,  -1.0, false),
    (-1.0,  0.0,  true),
    (0.0,   -1.0, false),
    (1.5,   1.5,  false),
    (1.5,   0.0,  false),
    (0.0,   1.5,  true),
    (0.5,   0.0,  true),
    (0.5,   0.5,  true),
    (0.5,   1.0,  true),
    (0.0,   0.5,  true),
    (1.0,   0.5,  true)
  )

  "A Box" should "contain points inside and not those outside" in {
    forAll (coords) { (x: Double, y: Double, isContained:Boolean) =>
      whenever (true) {
        val box = Box(Point(0,1), Point(1,1))
        val point = Point(x,y)
        assert(box.containsPoint(point) == isContained)
      }
    }
  }

}

