package org.example

import org.example.utils.splitPro
import java.lang.StringBuilder
import kotlin.math.roundToInt

object InputProcessor {
    fun proccess(input: String): String {
        val lines = input.splitPro("\r\n").apply { removeAt(0) }
        val tableSize = 2
        val roomMatrixes = lines.map {
            val (width, height, maxTables) = it.splitPro(" ").map { it.toInt() }
            var tableIndex = 1

            val room = Array(height) { CharArray(width) { '.' } }

            val verticalTablesInRow = (width / 2) + width.mod(2)
            val horizontalTablesInRow = width / tableSize

            var row = 0
            val even = width.mod(2) == 0

            if (even) {

            }

            while (row < height) {
                if (height - row >= tableSize) {
                    room.addVerticalTables(row, tableSize)
                    row += tableSize + 1
                } else if (height - row >= 1) {
                    room.addHorizontalRows(row, tableSize)
                    row += 2
                }
            }

            room.joinToString(separator= "\n") { it.joinToString(separator = "") }
        }
        return roomMatrixes.joinToString(separator = "\n\n")
    }
}

fun Array<CharArray>.addOptimalStructuresHorizontally(startIndex: Int, tableSize: Int) {
    val optimalStructureSize = tableSize * 3
    var optimalStructuresInWidth = (this[0].size / (optimalStructureSize + 1))
    if (this[0].size.mod(optimalStructureSize + 1) == 6)
        optimalStructuresInWidth+=1

    var row = startIndex
    var column = 0
    (0 until optimalStructuresInWidth).forEach {
        addVerticalTable(row, column)
        addVerticalTable(row, column + 2)
        addHorizontalTable(row, column + 4)
        addHorizontalTable(row + 2, column + 4)

        addVerticalTable(row + 4, column)
        addVerticalTable(row + 4, column + 2)
        addHorizontalTable(row + 3, column + 4)
        addHorizontalTable(row + 5, column + 4)
    }
}

fun Array<CharArray>.addVerticalTable(row: Int, column: Int) {
    this[row][column] = 'X'
    this[row+1][column] = 'X'
}

fun Array<CharArray>.addHorizontalTable(row: Int, column: Int) {
    this[row][column] = 'X'
    this[row+1][column] = 'X'
}
fun Array<CharArray>.addVerticalTables(startIndex: Int, tableSize: Int) {
    var row = startIndex
    (0 until tableSize).forEach {
        var column = 0
        (0 until this[row].size).forEach {
            this[row][column] = if (column.mod(2) == 0) 'X' else '.'
            column++
        }
        row++
    }
}

fun Array<CharArray>.addHorizontalRows(startIndex: Int, tableSize: Int) {
    var column = 0
    while (column <= this[startIndex].size - tableSize) {
        (0 until tableSize).forEach {
            this[startIndex][column] = 'X'
            column++
        }
        column++
    }
}

fun getRoomSize(x: String, y: String): Int {
    return x.toInt() * y.toInt()
}

data class Table(
    val id: Int,
    val originX: Int,
    val originY: Int,
    val endX: Int,
    val endY: Int
)

