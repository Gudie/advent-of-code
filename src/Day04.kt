fun main() {
    fun part1(input: List<String>): Int {
        val arr = input.map { it.toList() }
        return arr.foldIndexed(0) { x, acc, row ->
            row.foldIndexed(acc) { y, rowAcc, cell ->

                rowAcc + if (cell != 'X') 0 else {
                    var count = 0
                    for (dx in -1..1) {
                        for (dy in -1..1) {
                            var valid = true
                            for ((index, symbol) in arrayOf('M', 'A', 'S').withIndex()) {
                                valid = try {
                                    valid && (arr[x + dx * (index + 1)][y + dy * (index + 1)] == symbol)
                                } catch (e: Exception) {
                                    false
                                }
                            }
                            count += if (valid) 1 else 0
                        }
                    }
                    count
                }
            }
        }
    }

    fun part2(input: List<String>): Int {
        val arr = input.map { it.toList() }
        val result = arr.foldIndexed(0) { x, acc, row ->
            row.foldIndexed(acc) { y, rowAcc, cell ->

                rowAcc + if (cell != 'A') 0 else {
                    var count = 0
                    try {
                        if (
                            (arr[x-1][y-1]=='M' && arr[x+1][y+1]=='S' ||
                            arr[x+1][y+1]=='M' && arr[x-1][y-1]=='S' )
                            &&
                            (arr[x-1][y+1]=='M' && arr[x+1][y-1]=='S' ||
                            arr[x+1][y-1]=='M' && arr[x-1][y+1]=='S')
                        ) {
                            count++
                        }
                    } catch (e: Exception) {
                    }
                    count
                }
            }
        }
        return result
    }

// Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

// Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    val testInput2 = readInput("Day04_test2")
    check(part1(testInput) == 4)
    check(part1(testInput2) == 18)
    check(part2(testInput2) == 9)

// Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
