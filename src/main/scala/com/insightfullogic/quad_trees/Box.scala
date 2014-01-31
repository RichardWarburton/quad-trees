package com.insightfullogic.quad_trees


// AxisAlignedBounding
case class Box(center:Point, halfDimension:Point) {

  def containsPoint(point:Point) : Boolean = {
    containsPoint(point.x, point.y)
  }

  private def containsPoint(x: Double, y:Double) : Boolean = {
    x >= left && x <= right &&
    y >= bottom && y <= top
  }

  def intersects(other:Box) : Boolean = {
    lazy val bottomRight = containsPoint(other.bottom, other.right)
    lazy val bottomLeft = containsPoint(other.bottom, other.left)
    lazy val topRight = containsPoint(other.top, other.right)
    lazy val topLeft = containsPoint(other.top, other.left)
    topLeft || topRight || bottomLeft || bottomRight
  }

  lazy val quarterDim = halfDimension.half()

  def topLeftQuadrant()
    = Box(Point(center.x - quarterDim.x, center.y + quarterDim.y), quarterDim)

  def topRightQuadrant()
    = Box(Point(center.x + quarterDim.x, center.y + quarterDim.y), quarterDim)

  def bottomLeftQuadrant()
    = Box(Point(center.x - quarterDim.x, center.y - quarterDim.y), quarterDim)

  def bottomRightQuadrant()
    = Box(Point(center.x + quarterDim.x, center.y - quarterDim.y), quarterDim)

  private def top: Double = {
    center.y + halfDimension.y
  }

  private def bottom: Double = {
    center.y - halfDimension.y
  }

  private def right: Double = {
    center.x + halfDimension.x
  }

  private def left: Double = {
    center.x - halfDimension.x
  }

}
