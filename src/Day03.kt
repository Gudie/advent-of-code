fun main() {
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
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
