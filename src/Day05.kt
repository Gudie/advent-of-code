fun main() {
    fun part1(input: List<String>): Int {
        val rules = input.takeWhile { it.contains("|") }.map { it.split("|") }.map { Pair(it[0].toInt(), it[1].toInt()) }.toHashSet()
        val pages = input.takeLastWhile { !it.contains("|") }.drop(1).map { it.split(",").map { it.toInt() } }

        var count = 0
        for (line in pages.indices) {
            var safe = true
            for (i in 0..<pages[line].size - 1) {
                for (j in i..<pages[line].size) {
                    safe = safe && !rules.contains(Pair(pages[line][j], pages[line][i]))
                }
            }
            count += if (safe) pages[line][pages[line].size / 2] else 0
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val rules = input.takeWhile { it.contains("|") }.map { it.split("|") }.map { Pair(it[0].toInt(), it[1].toInt()) }.toHashSet()
        val pages = input.takeLastWhile { !it.contains("|") }.drop(1).map { it.split(",").map { it.toInt() } }.map { it.toIntArray() }

        val incorrect = mutableListOf<IntArray>()
        for (line in pages.indices) {
            var safe = true
            for (i in 0..<pages[line].size - 1) {
                for (j in i..<pages[line].size) {
                    safe = safe && !rules.contains(Pair(pages[line][j], pages[line][i]))
                }
            }
            if (!safe) {
                incorrect.add(pages[line])
            }
        }

        for (line in 0..<incorrect.size) {
            for (i in 0..<incorrect[line].size - 1) {
                for (j in i..<incorrect[line].size) {
                    if (rules.contains(Pair(incorrect[line][j], incorrect[line][i]))) {
                        val swap = incorrect[line][i]
                        incorrect[line][i] = incorrect[line][j]
                        incorrect[line][j] = swap
                    }
                }
            }
        }

        return incorrect.sumOf { it -> it[it.size/2] }
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
