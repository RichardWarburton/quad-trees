package com.insightfullogic.quad_trees

import org.scalatest.prop.PropertyChecks

class QuadTreeSpec extends UnitTest with PropertyChecks {

  val boundary = Box(Point(50,50), Point(50,50))

  "A Quad Tree" should "reject out of boundary points" in {
    val outOfBoundaryPoint = Point(101, 101)
    assert(!quadTree.insert(outOfBoundaryPoint))
  }

  it should "insert nothing for an out of boundary point" in {
    val outOfBoundaryBox = Box(Point(151, 151),Point(30, 30))
    assert(quadTree.queryWithin(outOfBoundaryBox).isEmpty)
  }

  it should "query all points when the boundary is queried" in {
    val tree = quadTree
    val point: Point = Point(60, 60)
    assert(tree.insert(point))
    assert(tree.queryWithin(boundary).contains(point))
  }

  it should "insert and query more than the capacity of a tree node's elements" in {
    val tree = quadTree
    insert(tree, 60, 60)
    insert(tree, 59, 59)
    insert(tree, 58, 58)
    insert(tree, 57, 57)
    insert(tree, 56, 56)
    assert(tree.queryWithin(boundary).size == 5)
  }

  it should "only query stored nodes within the range" in {
    val tree = quadTree
    insert(tree, 60, 60)
    insert(tree, 59, 59)
    insert(tree, 58, 58)
    insert(tree, 57, 57)
    insert(tree, 56, 56)
    val range = Box(Point(60,60), Point(1.5,1.5))
    assert(tree.queryWithin(range).size == 2)
  }

  def insert(tree:QuadTree, x:Double, y:Double) : Point = {
    val point = Point(x, y)
    assert(tree.insert(point))
    return point
  }

  def quadTree: QuadTree = {
    new QuadTree(boundary)
  }
}
