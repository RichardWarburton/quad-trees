package com.insightfullogic.quad_trees

import scala.collection.mutable.MutableList

import QuadTree._

class QuadTree(boundary:Box) {

  private val points = MutableList[Point]()

  private var topLeft = unused
  private var topRight = unused
  private var bottomLeft = unused
  private var bottomRight = unused

  def insert(point:Point):Boolean = {
    if (!boundary.containsPoint(point))
      return false

    if (points.size < capacity) {
      points += point
      return true
    }

    if (isLeaf)
      split()

    return (topLeft.insert(point) || topRight.insert(point)
        || bottomLeft.insert(point) || bottomRight.insert(point))
  }

  def isLeaf: Boolean = {
    topLeft == unused
  }

  def split() {
    topLeft = new QuadTree(boundary.topLeftQuadrant())
    topRight = new QuadTree(boundary.topRightQuadrant())
    bottomLeft = new QuadTree(boundary.bottomLeftQuadrant())
    bottomRight = new QuadTree(boundary.bottomRightQuadrant())
  }

}

object QuadTree {

  private val capacity = 4

  private val unused:QuadTree = null

}
