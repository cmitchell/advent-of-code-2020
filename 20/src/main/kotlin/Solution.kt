import java.io.File

var topsToBottoms = mutableListOf<Pair<Int, String>>()
var leftToRights = mutableListOf<Pair<Int, String>>()

fun main(args : Array<String>) {
  val input = File(args.first()).readLines()
  println("Solution 1: ${solution1(input)}")
  println("Solution 2: ${solution2(input)}")
}

private fun solution1(input :List<String>) :Int {
  val tiles = mutableListOf<List<String>>()
  var tops = mutableMapOf<Int, Pair<String, String>>()
  var bottoms = mutableMapOf<Int, Pair<String, String>>()
  var lefts = mutableMapOf<Int, Pair<String, String>>()
  var rights = mutableMapOf<Int, Pair<String, String>>()

  var n = 11
  while (n != input.size + 12) {
    tiles.add(input.subList(n-11, n))
    n += 12
  }

  for (tile in tiles) {
    var tileID = tile.first().substring( tile.first().indexOf(" ") + 1, tile.first().indexOf(":") ).toInt()
    var top = tile.get(1)
    var bottom = tile.last()
    var left = StringBuilder()
    var right = StringBuilder()
    for (i in 1..tile.size - 1) {
      left.append(tile.get(i).first())
      right.append(tile.get(i).last())
    }
    tops.put(tileID, Pair(top, top.reversed()))
    bottoms.put(tileID, Pair(bottom, bottom.reversed()))
    lefts.put(tileID, Pair(left.toString(), left.toString().reversed()))
    rights.put(tileID, Pair(right.toString(), right.toString().reversed()))
  }

  // println(tiles.size)
  var commonTopsBottoms = findCommon(tops, bottoms, listOf<String>())
  println( commonTopsBottoms)
  // orderTiles(commonTopsBottoms, true)
  // println(topsToBottoms)
  // commonTopsBottoms = findCommon(tops, bottoms, listOf<String>(), true)
  // println( commonTopsBottoms)
  // orderTiles(commonTopsBottoms, true)
  // println(topsToBottoms)

  return 0
}

private fun solution2(input :List<String>) :Int {
  return 0
}

private fun orderTiles(toOrder :Map<Pair<Int, Int>, Pair<Boolean, Boolean>>, tToB :Boolean) {
  for ((tOrL, orientation1) in toOrder) {
    for ((bOrR, orientation2) in toOrder) {
      if (tOrL.first == bOrR.second) {
        if (tToB) {
          topsToBottoms.add(bOrR.first, )
          topsToBottoms.add(bOrR.second)
          topsToBottoms.add(tOrL.first)
          topsToBottoms.add(tOrL.second)
        } else {

        }

      }
    }
  }
}

private fun findCommon(sideA :Map<Int, Pair<String, String>>, sideB :Map<Int, Pair<String, String>>,
   ignoreList :List<String>) :Map<Pair<Int, Int>, Pair<Boolean, Boolean>> {
  var common = mutableMapOf<Pair<Int, Int>, Pair<Boolean, Boolean>>()
  var side1 = sideA.toMutableMap()
  var side2 = sideB.toMutableMap()

    for ((tile1, s1) in side1) {
      for ((tile2, s2) in side2) {
        if (s1.first.equals(s2.first)) {
          common.put(Pair(tile1, tile2), Pair(false, false))
        }
        
        if (s1.second.equals(s2.first)) {
          common.put(Pair(tile1, tile2), Pair(true, false))
        }

        if (s1.first.equals(s2.second)) {
          common.put(Pair(tile1, tile2), Pair(false, true))
        }

        // Both reversed will be he same as both not reversed...
        // if (s1.second.equals(s2.second)) {
        //   common.put(Pair(tile1, tile2), Pair(true, true))
        // }
      }
    }

  return common
}