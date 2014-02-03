package com.insightfullogic.quad_trees

import collection.mutable.Stack
import org.scalatest._
import org.scalatest.prop.PropertyChecks
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.Tables.Table

class BoxSpec extends UnitTest with PropertyChecks {

  val box = Box(Point(0,1), Point(1,1))

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

  "A Box" should "contain matches inside and not those outside" in {
    forAll (coords) { (x: Double, y: Double, isContained:Boolean) =>
        val point = Point(x,y)
        assert(box.containsPoint(point) == isContained)
    }
  }

  val otherBoxes = Table(
    ("center x",   "center y", "half dimension x", "half dimension y", "intersects"),
    (0.0,          1.0,        1.0,                1.0,                true),
    (1.0,          0.0,        1.0,                1.0,                true),
    (-1.0,         0.0,        1.0,                1.0,                true),
    (1.0,          2.0,        1.0,                1.0,                true),
    (2.0,          2.0,        0.5,                0.5,                false),
    (2.0,          -2.0,       0.5,                0.5,                false),
    (-2.0,         2.0,        0.5,                0.5,                false),
    (-2.0,         -2.0,       0.5,                0.5,                false)
  )

  it should "intersect boxes that overlap with it" in {
    forAll(otherBoxes) { (centreX:Double, centreY:Double, halfDimX:Double, halfDimY:Double, intersects:Boolean) =>
      val otherBox = Box(Point(centreX, centreY), Point(halfDimX, halfDimY))
      assert(box.intersects(otherBox) == intersects)
      assert(otherBox.intersects(box) == intersects)
    }
  }

}

