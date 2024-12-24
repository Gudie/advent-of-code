fun main() {
    var dont = 0
    var dowel = 0
    var mul = 0

    fun part1(input: List<String>): Int {
        val boe = input.map { line ->
            val regex = Regex("""mul\((?<first>\d{1,3}),(?<second>\d{1,3})\)""")
            val regexMatches = regex.findAll(line) //.toList()
            regexMatches.fold(0) { acc, matchResult ->
                val first = matchResult.groups["first"]?.value?.toInt() ?: 0
                val second = matchResult.groups["second"]?.value?.toInt() ?: 0
                acc + first * second
            }
        }
        return boe.sum()
    }

    fun part2(input: List<String>): Int {
        val line = input.joinToString { it }
        val regex = Regex("""mul\((?<first>\d{1,3}),(?<second>\d{1,3})\)|(?<do>do\(\))|(?<dont>don't\(\))""")
        val regexMatches = regex.findAll(line).toList()
        val result = regexMatches.fold(Pair(0, true)) { acc, matchResult ->
            when {
                matchResult.groups["first"] != null -> {
                    mul++
                    val first = matchResult.groups["first"]?.value?.toInt() ?: 0
                    val second = matchResult.groups["second"]?.value?.toInt() ?: 0
                    Pair(acc.first + (if (acc.second) first * second else 0), acc.second)
                }

                matchResult.groups["do"] != null -> {
                    dowel++;Pair(acc.first, true)
                }

                matchResult.groups["dont"] != null -> {
                    dont++;Pair(acc.first, false)
                }

                else -> throw IllegalArgumentException() //Pair(acc.first, acc.second)
            }
        }
        return result.first
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    val testInput2 = readInput("Day03_test2")
    check(part1(testInput) == 161)
    check(part2(testInput2) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

//128670117
