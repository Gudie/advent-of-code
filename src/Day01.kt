import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val twoHistoriansLists = input.map {
            val line = it.split("\\s+".toRegex()).map { it.toInt() }
            Pair(line[0], line[1])
        }.unzip()
        val listOne = twoHistoriansLists.first.sorted()
        val listTwo = twoHistoriansLists.second.sorted()
        val distance = listOne.zip(listTwo).fold(0) { acc, pair ->
            acc + (pair.first - pair.second).absoluteValue
        }
        return distance
    }

    fun part2(input: List<String>): Int {
        val twoHistoriansLists = input.map {
            val line = it.split("\\s+".toRegex()).map { it.toInt() }
            Pair(line[0], line[1])
        }.unzip()
        val listOne = twoHistoriansLists.first
        val listTwo = twoHistoriansLists.second.groupBy { it }.map{it ->
            it.key to it.value.size
        }.toMap()
        val similarity = listOne.fold(0) { acc, i ->
            acc+i*listTwo.getOrDefault(i,0)
        }
        return similarity
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
