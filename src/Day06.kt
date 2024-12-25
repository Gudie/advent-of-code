fun main() {
    fun turnRight(direction: Pair<Int, Int>): Pair<Int, Int> {
        return when(direction) {
            Pair(1, 0) -> Pair(0, -1)
            Pair(0, 1) -> Pair(1, 0)
            Pair(-1, 0) -> Pair(0, 1)
            Pair(0, -1) -> Pair(-1, 0)
            else -> throw (IllegalArgumentException())
        }
    }

    fun part1(input: List<String>): Int {
        val lab = input.map { it.toMutableList() }
        var guard = Pair(-1, -1)
        var direction = Pair(-1, 0)
        lab.mapIndexed { x, row ->
            val y = row.indexOf('^')
            guard = if (y != -1) Pair(x, y) else guard
        }
        println("${guard}_$direction")

        lab[guard.first][guard.second] = 'X'
        try {
            while (true) {
                val wannaGo = Pair(guard.first + direction.first, guard.second + direction.second)
                if (lab[wannaGo.first][wannaGo.second] == '#') {
                    direction = turnRight(direction)
                } else {
                    lab[wannaGo.first][wannaGo.second] = 'X'
                    guard = wannaGo
                }
            }
        } catch (e: Exception) {
            lab.map{it.println()}
            e.println()
        }

        return lab.flatten().count { it == 'X' }.also{println(it)}
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
    check(part1(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
