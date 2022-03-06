package chapter1

/*
// [리스트 5.1] 무명 내부 클래스로 리스너 구현하기
fun main() {
    button.setOnClickListener(new OnClickListener()) {
        @Override
        public void onClick(View view) {}
    }
}
 */

/*
// [리스트 5.2] 람다로 리스너 구현하기
fun main() {
    button.setOnClickListener {}
}
 */

/*
// [리스트 5.3] 컬렉션을 직접 검색하기
class Person(
    val name: String,
    val age: Int
)

fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null

    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }

    println(theOldest)
}
 */

/*
// [리스트 5.4] 람다를 사용해 컬렉션 검색하기
fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxBy { it.age })
}
 */

/*
// [리스트 5.5] 멤버 참조를 사용해 컬렉션 검색하기
fun main() {
    people.maxBy(Person::age)
}
 */

/*
// [리스트 5.6] 이름 붙인 인자를 사용해 람다 넘기기
fun main() {
    val people = listOf(Person("이몽룡", 29), Person("성춘향", 31))
    val names = people.joinToString(
        separator = " ",
        transform = { p: Person -> p.name }
    )
    println(names)
}
 */

/*
// [리스트 5.7] 람다를 괄호 밖에 전달하기
fun main() {
    people.joinToString(" ") { p: Person -> p.name}
}
 */

/*
// [리스트 5.8] 람다 파라미터 타입 제거하기
fun main() {
    people.maxBy { p: Person -> p.age }
    people.maxBy { p -> p.age }
}
 */

/*
// [리스트 5.9] 디폴트 파라미터 이름 it 사용하기
fun main() {
    people.maxBy { it.age }
}
 */

/*
// [리스트 5.10] 함수 파라미터를 람다 안에서 사용하기
fun printMessageWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it")
    }
}
 */

/*
// [리스트 5.11] 람다 안에서 바깥 함수의 로컬 변수 변경하기
fun printProblemCounts(response: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0

    response.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }

    println("$clientErrors client errors, $serverErrors server errors")
}
 */

/*
// [리스트 5.12] 자연수의 시퀀스를 생성하고 사용하기
fun main() {
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())
}
 */

/*
// [리스트 5.13] 상위 디렉터리의 시퀀스 생성하고 사용하기
fun File.isInsideHiddenDirectory() =
    generateSequence(this) { it.parentFile }.any { it.isHidden }

fun main() {
    val file = File("/Users/svtk/.HiddenDir/a.txt")
    println(file.isHiddenDirectory())
}
 */

/*
// [리스트 5.14] SAM 생성자를 사용해 값 반환하기
fun createAllDoneRunnable(): Runnable =
    Runnable { println("All done!") }

fun main() {
    createAllDoneRunnable().run()
}
 */

/*
// [리스트 5.15] SAM 생성자를 사용해 listener 인스턴스 재사용하기
val listener = OnClickListener { view ->
    val text = when (view.id) {
        R.id.button1 -> "First button"
        R.id.button2 -> "Second button"
        else -> "Unknown button"
    }
    toast(text)
}
 */

/*
// [리스트 5.16] 알파벳 만들기
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }

    result.append("\nNow I know the alphabet!")
    return result.toString()
}
 */

/*
// [리스트 5.17] with를 사용해 알파벳 만들기
fun alphabet(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        append("\nNow I know the alphabet!")
        this.toString()
    }
}
 */

/*
// [리스트 5.18] with와 식을 본문으로 하는 함수를 활용해 알파벳 만들기
fun alphabet() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
    toString()
}
 */

/*
// [리스트 5.19] apply를 사용해 알파벳 만들기
fun alphabet() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()
 */

/*
// [리스트 5.20] apply를 TextView 초기화에 사용하기
fun createViewWithCustomAttributes(context: Context) =
    TextView(context).apply {
        text = "Sample Text"
        textSize = 20.0
    }
 */

/*
// [리스트 5.21] buildString으로 알파벳 만들기
fun alphabet() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}
 */
