package sortExceptions

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val inputStackTrace: String = args[0]
    val rsi: String = args[1]
    val rse: String = args[2]

    val isInteresting = sortExceptions(inputStackTrace, rsi, rse)

    if (isInteresting) {
        throw InterestingInputException()

        // DONE: Investigate in more detail why System.exit(1) / exitProcess(1) does not work
    }
}

class InterestingInputException : Exception()

// DONE: Check if this thing can be an empty string ("")
private const val EMPTY_RS_TOKEN = ""

private fun sortExceptions(stackTrace: String, rsi: String, rse: String): Boolean {
    val regexToInclude = Regex(rsi)
    val regexToExclude = Regex(rse)

    return when {
        rsi != EMPTY_RS_TOKEN -> regexToInclude in stackTrace
        rse != EMPTY_RS_TOKEN -> regexToExclude !in stackTrace
        else -> true
    }
}
