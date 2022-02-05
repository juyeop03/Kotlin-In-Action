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
