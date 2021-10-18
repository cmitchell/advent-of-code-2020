import java.io.File
import java.util.LinkedList
import com.ginsberg.cirkle.MutableCircularList

fun main(args : Array<String>) {
    val input = File(args.first()).readLines()
    println("Solution 1: ${solution1(input)}")
    println("Solution 2: ${solution2(input)}")
  }

  private fun solution1(input :List<String>) :Int {
    var circle = LinkedList<Int>(input.first().toList().map{ Integer.parseInt(it.toString()) }.toMutableList())

    var currentCupIndex = 0
    for (i in 0 until 10) {
      println("CurrentCupIndex   " + currentCupIndex)
      println("Circle:  " + circle)

      // MutableCirclarList's subList is busted... 
      // val removedCups = circle.subList(currentCupIndex + 1, currentCupIndex + 4)

      var removedCups = mutableListOf<Int>()
      var destinationCup = circle.get(currentCupIndex) - 1
      var removedIndexes = mutableListOf<Int>()

      if (currentCupIndex + 3 >= circle.size) {
        var diff = currentCupIndex + 1
        var tempIndexes = mutableListOf<Int>()
        while (diff < circle.size) {
          removedCups.add(circle.get(diff))
          tempIndexes.add(diff)
          diff++
        }
        val remainder = 3 - ((circle.size - 1) - currentCupIndex)
        var remainderCount = 0
        while (remainderCount < remainder) {
          removedCups.add(circle.get(remainderCount))
          tempIndexes.add(remainderCount)
          remainderCount++
        }

        while (!tempIndexes.isEmpty()) {
          circle.removeAt(tempIndexes.max()!!)
          removedIndexes.add(tempIndexes.max()!!)
          tempIndexes.remove(tempIndexes.max()!!)
        }

      } else {
        removedCups.add(circle.get(currentCupIndex + 1))
        removedCups.add(circle.get(currentCupIndex + 2))
        removedCups.add(circle.get(currentCupIndex + 3))
        circle.removeAt(currentCupIndex + 3)
        circle.removeAt(currentCupIndex + 2)
        circle.removeAt(currentCupIndex + 1)  
        removedIndexes.add(currentCupIndex + 3)
        removedIndexes.add(currentCupIndex + 2)
        removedIndexes.add(currentCupIndex + 1)
      }
      println("Picked up cups:  " + removedCups)

      while (!circle.contains(destinationCup)) {
        destinationCup--
        if (destinationCup < circle.min()!!) {
          destinationCup = circle.max()!!
        }
      }

      var destinationCupIndex = circle.indexOf(destinationCup)
      println("Destination:  " + destinationCup)
      // println("dIndex   " + destinationCupIndex)
      // println("currIndex   " + currentCupIndex)
      // println("&&&&&&&&&    " + removedIndexes)
      var allRemovedBeforeCurrIndex = true
      for (a in removedIndexes) {
        if (a > currentCupIndex) {
          allRemovedBeforeCurrIndex = false
          break
        }
      }

      if (destinationCupIndex < currentCupIndex) {
        var tempCount = destinationCupIndex
        var firstReverseIndex = destinationCupIndex - 3
        if (firstReverseIndex >= 0) {
          circle.addAll(destinationCupIndex + 1, removedCups)
          var shiftCount = 0
          while ( (destinationCupIndex -1) -3 != shiftCount ) {
            var shift = circle.get(0)
            circle.removeAt(0)
            circle.add(shift)
            shiftCount--
          }
        } else {
          if (!allRemovedBeforeCurrIndex) {

          while (tempCount > -1) {
            val e = removedCups.last()
            removedCups.add(0, circle.get(tempCount))
            circle.set(tempCount, e)
            removedCups.remove(e)
            tempCount--
          }

          if (firstReverseIndex == -3) {
            circle.addAll(circle.size, removedCups )
          } else if (firstReverseIndex == -2) {
            circle.add(1, removedCups.last())
            removedCups.remove(removedCups.last())
            circle.add(0, removedCups.last())
            removedCups.remove(removedCups.last())
            circle.addAll(circle.size, removedCups )
          } else if (firstReverseIndex == -1) {
            circle.add(0, removedCups.last())
            removedCups.remove(removedCups.last())
            circle.addAll(circle.size, removedCups )
          }
        }  else {
          circle.addAll(circle.indexOf(destinationCup) + 1, removedCups)
        }
      }
      } else {
        circle.addAll(circle.indexOf(destinationCup) + 1, removedCups)
      }
     
      currentCupIndex++
      if (currentCupIndex == circle.size) {
        currentCupIndex = 0
      }

    }
    println("Circle:  " + circle)

    return 0
  }

  private fun solution2(input :List<String>) :Int {
    return 0
  }