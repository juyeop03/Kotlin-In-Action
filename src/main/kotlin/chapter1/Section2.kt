package chapter1

/*
// [리스트 2.1] 코틀린 'Hello, World!'
fun main(args: Array<String>) {
    println("Hello, world!")
}
 */

/*
// [리스트 2.2] 문자열 템플릿 사용
fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
    println(name)
}
 */

/*
// [리스트 2.3] 간단한 자바 클래스 Person
public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
 */

/*
// [리스트 2.4] 코틀린으로 변환한 Person 클래스
class Person(val name: String)
 */

/*
// [리스트 2.5] 클래스 안에서 변경 가능한 프로퍼티 선언하기
class Person(
    val name: String,
    val isMarried: Boolean
)
 */

/*
// [리스트 2.6] 자바에서 Person 클래스를 사용하는 방법
fun main() {
    Person person = new Person("Bob", true);
    System.out.println(person.getName());
    System.out.println(person.isMarried());
}
 */

/*
// [리스트 2.7] 코틀린에서 Person 클래스 사용하기
fun main() {
    val person = Person("Bob", true)
    println(person.name)
    println(person.isMarried)
}
 */

/*
// [리스트 2.8] 클래스와 함수 선언을 패키지에 넣기
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() = height == width
}

fun createRandomRectangle(): Rectangle {
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}
 */

/*
// [리스트 2.9] 다른 패키지에 있는 함수 임포트하기
fun main(args: Array<String>) {
    println(createRandomRectangle().isSquare)
}
 */

/*
// [리스트 2.10] 간단한 enum 클래스 정의하기
enum class Color {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    INDIGO,
    VIOLET
}
 */

/*
// [리스트 2.11] 프로퍼티와 메소드가 있는 enum 클래스 선언하기
enum class Color(
    val r: Int,
    val g: Int,
    val b: Int
) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b
}
 */

/*
// [리스트 2.12] when을 사용해 올바른 enum 값 찾기
// 79 페이지 "switch" 단어 오타
fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }
 */

/*
// [리스트 2.13] 한 when 분기 안에 여러 값 사용하기
fun getWarmth(color: Color) = when (color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "netural"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}
 */

/*
// [리스트 2.14] enum 상수 값을 임포트해서 enum 클래스 수식자 없이 enum 사용하기
fun getWarmth(color: Color) = when (color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "netural"
    BLUE, INDIGO, VIOLET -> "cold"
}
 */

/*
// [리스트 2.15] when의 분기 조건에 여러 다른 객체 사용하기
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }
 */

/*
// [리스트 2.16] 인자가 없는 when
fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
        (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
        (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
        else -> throw Exception("Dirty color")
    }
 */

/*
// [리스트 2.17] 식을 표현하는 클래스 계층
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
 */

/*
// [리스트 2.18] if 연쇄를 사용해 식을 계산하기
fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e as Num
        return n.value
    }
    if (e is Sum) {
        return eval(e.left) + eval(e.right)
    }
    throw IllegalArgumentException("Unknown expression")
}
 */

/*
// [리스트 2.19] 값을 만들어내는 if식
fun eval(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval(e.left) + eval(e.left)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }
 */

/*
// [리스트 2.20] if 중첩 대신 when 사용하기
fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }
 */

/*
// [리스트 2.21] 분기에 복잡한 동작이 들어가 있는 when 사용하기
fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main() {
    println(evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))))
}
 */

/*
// [리스트 2.22] when을 사용해 피즈버즈 게임 구현하기
fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun main() {
    for (i in 1..100) {
        println(fizzBuzz(i))
    }
}
 */

/*
// [리스트 2.23] 증가 값을 갖고 범위 이터레이션하기
fun main() {
    for (i in 100 downTo 1 step 2) {
        println(fizzBuzz(i))
    }
}
 */

/*
// [리스트 2.24] 맵을 초기화하고 이터레이션하기
fun main() {
    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}
 */

/*
// [리스트 2.25] in을 사용해 값이 범위에 속하는지 검사하기
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in 'a'..'z'

fun main() {
    println(isLetter('q'))
    println(isNotDigit('x'))
}
 */

/*
// [리스트 2.26] when에서 in 사용하기
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know..."
}

fun main() {
    println(recognize('8'))
}
 */

/*
// [리스트 2.27] 자바와 마찬가지로 try 사용하기
fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    }
    catch (e: NumberFormatException) {
        return null
    }
    finally {
        reader.close()
    }
}

fun main() {
    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))
}
 */

/*
// [리스트 2.28] try를 식으로 사용하기
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    }
    println(number)
}

fun main() {
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)
}
 */

/*
// [리스트 2.29] catch에서 값 반환하기
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}
 */
