import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val boe = input.map { line ->
            line.split("\\s+".toRegex()).map { it.toInt() }
        }.map { report ->
            val trend = report[0] - report[1]
            report.drop(1).fold(Acc(true, report[0])) { acc, i ->
                val diffSafe = (acc.previous - i).absoluteValue in 1..3
                val trendSafe = (trend > 0 && acc.previous > i) || (trend < 0 && acc.previous < i)
                Acc(acc.acc && diffSafe && trendSafe, i)
            }
        }.count { it.acc }

        return boe
    }

    fun part2(input: List<String>): Int {
        val boe = input.map { line ->
            line.split("\\s+".toRegex()).map { it.toInt() }
        }.map { report ->
            val trend = report[0] - report[1]
            report.drop(1).fold(AccDamper(true, report[0], true)) { acc, i ->
                val diffSafe = (acc.previous - i).absoluteValue in 1..3
                val trendSafe = (trend > 0 && acc.previous > i) || (trend < 0 && acc.previous < i)
                if (diffSafe && trendSafe) {
                    AccDamper(acc.acc, i, acc.damper)
                } else if (acc.damper) {
                    AccDamper(acc.acc, acc.previous, false)
                } else {
                    AccDamper(false, i, false)
                }
            }
        }
        val boe3 = input.map { line ->
            line.split("\\s+".toRegex()).map { it.toInt() }
        }.map { report1 ->
            val report = report1.reversed()
            val trend = report[0] - report[1]
            report.drop(1).fold(AccDamper(true, report[0], true)) { acc, i ->
                val diffSafe = (acc.previous - i).absoluteValue in 1..3
                val trendSafe = (trend > 0 && acc.previous > i) || (trend < 0 && acc.previous < i)
                if (diffSafe && trendSafe) {
                    AccDamper(acc.acc, i, acc.damper)
                } else if (acc.damper) {
                    AccDamper(acc.acc, acc.previous, false)
                } else {
                    AccDamper(false, i, false)
                }
            }
        }
        val boe4 = boe.zip(boe3).map { it.first.acc || it.second.acc }

        val boe2 = boe4.count { it }

        return boe2

    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
//    check(part1(testInput) == 2)
//    check(part2(testInput) == 5)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
//    part1(input).println()
    part2(input).println()
}

data class Acc(val acc: Boolean, val previous: Int)
data class AccDamper(val acc: Boolean, val previous: Int, val damper: Boolean)
