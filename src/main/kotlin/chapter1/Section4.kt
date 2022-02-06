package chapter1

/*
// [리스트 4.1] 간단한 인터페이스 선언하기
interface Clickable {
    fun click()
}
 */

/*
// [리스트 4.2] 단순한 인터페이스 구현하기
class Button : Clickable {
    override fun click() = println("I was clicked")
}

fun main() {
    Button().click()
}
 */

/*
// [리스트 4.3] 인터페이스 안에 본문이 있는 메소드 정의하기
interface Clickable {
    fun click()
    fun showOff() = println("I'm a clickable!")
}
 */

/*
// [리스트 4.4] 동일한 메소드를 구현하는 다른 인터페이스 정의
interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}
 */

/*
// [리스트 4.5] 상속한 인터페이스의 메소드 구현 호출하기
// 146 페이지 "I'm focusable! 출력" 문장 오타
class Button : Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}
 */

/*
// [리스트 4.6] 열린 메소드를 포함하는 열린 클래스 정의하기
open class RichButton : Clickable {
    fun disable() {}
    open fun animate() {}
    override fun click() {}
}
 */

/*
// [리스트 4.7] 오버라이드 금지하기
open class RichButton : Clickable {
    final override fun click() {}
}
 */

/*
// [리스트 4.8] 추상 클래스 정의하기
abstract class Animated {
    abstract fun animate()
    open fun stopAnimating() {}
    fun animateTwice() {}
}
 */

/*
// [리스트 4.9] 직렬화할 수 있는 상태가 있는 뷰 선언
import java.io.Serializable

interface State : Serializable
interface View {
    fun getCurrentState(): State
    fun restoreState(state: State)
}
 */

/*
// [리스트 4.10] 자바에서 내부 클래스를 사용해 View 구현하기
public class Button implemntes View {

    @Override
    public State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(state: State) {}

    public class ButtonState implements State {}
}
 */

/*
// [리스트 4.11] 중첩 클래스를 사용해 코틀린에서 View 구현하기
class Button : View {

    override fun getCurrentState(): State = ButtonState()

    override fun restoreState(state: State) {}

    class ButtonState : State
}
 */

/*
// [리스트 4.12] 인터페이스 구현을 통해 식 표현하기
import java.lang.IllegalArgumentException

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }
 */

/*
// [리스트 4.13] sealed 클래스로 식 표현하기
sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
    }
 */

/*
// [리스트 4.14] 인터페이스의 프로퍼티 구현하기
interface User {
    val nickname: String
}

class PrivateUser(override val nickname: String) : User

class SubscribingUser(private val email: String) : User {
    override val nickname: String
        get() = email.substringBefore("@")
}

class FacebookUser(val accountId: Int) : User {
    override val nickname = getFacebookName(accountId)
}
 */

/*
// [리스트 4.15] 세터에서 뒷받침하는 필드 접근하기
class User(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(
                """
                Address was changed for $name:
                "$field" -> "$value".
                """.trimIndent()
            )
            field = value
        }
}
 */

/*
// [리스트 4.16] 비공개 세터가 있는 프로퍼티 선언하기
class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}
 */

/*
// [리스트 4.17] Client 클래스의 초기 정의
class Client(
    val name: String,
    val postalCode: Int
)
 */

/*
// [리스트 4.18] Client에 toString() 구현하기
class Client(
    private val name: String,
    private val postalCode: Int
) {
    override fun toString(): String =
        "Client(name=$name, postalCode=$postalCode"
}
 */

/*
// [리스트 4.19] Client에 equals() 구현하기
class Client(
    private val name: String,
    private val postalCode: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client)
            return false
        return name == other.name && postalCode == other.postalCode
    }

    override fun toString(): String =
        "Client(name=$name, postalCode=$postalCode"
}
 */

/*
// [리스트 4.20] Client에 hashCode 구현하기
class Client(
    private val name: String,
    private val postalCode: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client)
            return false
        return name == other.name && postalCode == other.postalCode
    }

    override fun toString(): String =
        "Client(name=$name, postalCode=$postalCode"

    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}
 */

/*
// [리스트 4.21] Client를 데이터 클래스로 선언하기
data class Client(
    val name: String,
    val postalCode: Int
)
 */

// [리스트 4.22] 클래스 위임 사용하기
/*
class CountingSet<T>(
    private val innerSet: MutableCollection<T> = HashSet()
) : MutableCollection<T> by innerSet {

    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}
 */

/*
// [리스트 4.24] 중첩 객체를 사용해 Comparator 구현하기
data class Person(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int =
            o1.name.compareTo(o2.name)
    }
}
 */

/*
// [리스트 4.25] 부 생성자가 여럿 있는 클래스 정의하기
class User {
    private val nickName: String

    constructor(email: String) {
        nickName = email.substringBefore("@")
    }

    constructor(facebookAccountId: Int) {
        nickName = getFacebookName(facebookAccountId)
    }
}
 */

/*
// [리스트 4.26] 부 생성자를 팩토리 메소드로 대신하기
class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) =
            User(email.substringBefore("@"))
        fun newFacebookUser(accountId: Int) =
            User(getFacebookName(accountId))
    }
}
 */

/*
// [리스트 4.27] 동반 객체에 이름 붙이기
class Person(val name: String) {
    companion object Loader {
        fun fromJson(jsonText: String): Person = Person("")
    }
}
 */

/*
// [리스트 4.28] 동반 객체에서 인터페이스 구현하기
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person(val name: String) {
    companion object : JSONFactory<Person> {
        override fun fromJSON(jsonText: String): Person = Person("")
    }
}
 */

/*
// [리스트 4.29] 동반 객체에 대한 확장 함수 정의하기
class Person(val firstName: String, val lastName: String) {
    companion object {}
}

fun Person.Companion.fromJSON(json: String): Person = Person("")
 */

/*
// [리스트 4.30] 무명 객체로 이벤트 리스너 구현하기
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

window.addMouseListener(
    object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            super.mouseClicked(e)
        }
        override fun mouseEntered(e: MouseEvent?) {
            super.mouseEntered(e)
        }
    }
)
 */

/*
// [리스트 4.31] 무명 객체 안에서 로컬 변수 사용하기
import java.awt.Window
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

fun countClicks(window: Window) {
    var clickCount = 0

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            clickCount++
        }
    })
}
 */
