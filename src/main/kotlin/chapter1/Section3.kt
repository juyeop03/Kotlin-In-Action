package chapter1

/*
// [리스트 3.1] joinToString() 함수의 초기 구현
fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {

    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    println(joinToString(list, "; ", "(", ")"))
}
 */

/*
// [리스트 3.2] 디폴트 파라미터 값을 사용해 jsonToString() 정의하기
fun <T> jsonToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) : String {

    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    println(jsonToString(list, ", ", "", ""))
    println(jsonToString(list, ))
    println(jsonToString(list, "; "))
    println(jsonToString(list, postfix = ";", prefix = "#"))
}
 */

/*
// [리스트 3.4] joinToString()를 확장으로 정의하기
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {

    val result = StringBuilder(prefix)

    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    println(list.joinToString(separator = "; ", prefix = "(", postfix = ")"))
    println(list.joinToString())
}
 */

/*
// [리스트 3.5] 멤버 함수 오버라이드하기
open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

fun main() {
    val view: View = Button()
    view.click()
}
 */

/*
// [리스트 3.6] 확장 함수는 오버라이드 할 수 없다.
fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")

fun main() {
    val view: View = Button()
    view.showOff()
}
 */

/*
// [리스트 3.7] 확장 프로퍼티 선언하기
val String.lastChar: Char
    get() = get(length - 1)

fun main() {
    println("Kotlin".lastChar)
}
 */

/*
// [리스트 3.8] 변경 가능한 프로퍼티 선언하기
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }

fun main() {
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}
 */

/*
// [리스트 3.9] String 확장 함수를 사용해 경로 파싱하기
fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun main() {
    parsePath("/Users/yole/kotlin-book/chapter.adoc")
}
 */

/*
// [리스트 3.10] 경로 파싱에 정규식 사용하기
fun parsePath(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}
 */

/*
// [리스트 3.11] 코드 중복을 보여주는 예제
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Name"
        )
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Address"
        )
    }
}
 */

/*
// [리스트 3.12] 로컬 함수를 사용해 코드 중복 줄이기
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    fun validate(
        user: User,
        value: String,
        fileName: String
    ) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fileName"
            )
        }
    }

    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
}
 */

/*
// [리스트 3.13] 로컬 함수에서 바깥 함수의 파라미터 접근하기
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fieldName"
            )
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
}
 */

/*
// [리스트 3.14] 검증 로직을 확장 함수로 추출하기
class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user $id: empty $fieldName"
            )
        }
    }

    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser(user: User) {
    user.validateBeforeSave()
}
 */
