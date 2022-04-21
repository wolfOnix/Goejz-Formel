/*
 * Copyright (c) 2022 Robert Kovats
 */

fun main() {
    println("Guten Tag! Rechnen Sie das Göjz Funktion, indem Sie fügen die notwendige Daten unten hinzu.\n")
    try {
        print("i = "); val i = readInt(defaultValue = 1)
        print("s(i) = "); val si = readInt()
        print("iMax = "); val max = readInt(defaultValue = 10)

        println("\n\tDie Göjz Tabelle sieht so aus:\n")

        goejzDescending(i = i, si = si.toFloat(), doPrint = false)
        goejzAscending(i = i, si = si.toFloat(), iMax = max)
    } catch (e: Exception) {
        println(e.message)
    }
}

private fun goejzDescending(i: Int, si: Float, doPrint: Boolean = true): Float {
    val siMinusOne = if (i <= 1) si else goejzDescending(i - 1, i.toFloat() * si / (i - 1).toFloat())
    if (doPrint) printGoejz(i = i, si = si)
    return siMinusOne
}

private fun goejzAscending(i: Int, si: Float, iMax: Int): Float {
    printGoejz(i = i, si = si)
    return if (i >= iMax) si else goejzAscending(i + 1, i.toFloat() * si / (i + 1).toFloat(), iMax)
}

private fun printGoejz(i: Int, si: Float) {
    println("göjz($i) = $si${if (i != 1) " = ${i - 1} * ${si / (i - 1)}" else ""}")
}

private fun readInt(defaultValue: Int? = null): Int {
    val line = readLine() ?: throw EOFReachedExc()
    return try {
        line.toInt()
    } catch (e: Exception) {
        defaultValue ?: throw InvalidFormatExc("'$line' ist keine gültige Nummer.")
    }
}
