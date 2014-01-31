package com.insightfullogic.quad_trees


case class Point(x:Double, y:Double) {

  def half() = Point(x / 2, y / 2)

}
