/*
 * Copyright (c) 2022 Robert Kovats
 */

import Colour.*

private const val RST = "\u001b[0m"

private infix fun String.coloured(colour: Colour): String = "${colour.code}$this$RST"

private enum class Colour(val code: String) {
    CYAN("\u001b[36m"),
    RED("\u001b[31m"),
    YELLOW("\u001b[33m")
}

fun main() {
    println("Guten Tag! Rechnen Sie das Göjz Funktion, indem Sie fügen die notwendige Daten unten hinzu.\n")
    try {
        print("Sie wissen, dass die Person mit der Ordnungsnummer ... "); val i = readInt(defaultValue = 1)
        print("hat die Summe von ... bezahlen "); val si = readInt()
        print("und Sie wünschen die Tabelle bis zu der Person mit der Ordnungsnummer ... herauszufinden "); val max = readInt(defaultValue = 10)

        println("\n\tDie Göjz Tabelle sieht so aus:\n")

        goejzDescending(i = i, si = si.toFloat(), doPrint = false)
        goejzAscending(i = i, si = si.toFloat(), iMax = max)

        println("\nNa ja, das war's!" coloured CYAN)
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
    val line = readlnOrNull() ?: throw EOFReachedExc()
    return try {
        line.toInt()
    } catch (e: Exception) {
        defaultValue?.also { println("\tOK, das wird $defaultValue" coloured YELLOW) }
            ?: throw InvalidFormatExc("\t'$line' ist keine gültige Nummer." coloured RED)
    }
}
