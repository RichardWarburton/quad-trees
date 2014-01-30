package com.insightfullogic.quad_trees


// AxisAlignedBounding
case class Box(center:Point, halfDimension:Point) {

  def containsPoint(point:Point) : Boolean = {
    point.x >= center.x - halfDimension.x &&
    point.x <= center.x + halfDimension.x &&
    point.y >= center.y - halfDimension.y &&
    point.y <= center.y + halfDimension.y
  }


}
