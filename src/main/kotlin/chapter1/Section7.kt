package chapter1

/*
// [리스트 7.1] plus 연산자 구현하기
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
}
 */

/*
// [리스트 7.2] 연산자를 확장 함수로 정의하기
operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}
 */

/*
// [리스트 7.3] 두 피연산자의 타입이 다른 연산자 정의하기
operator fun Point.times(scale: Double): Point {
    return Point((x * scale).toInt(), (y * scale).toInt())
}
 */

/*
// [리스트 7.4] 결과 타입이 피연산자 타입과 다른 연산자 정의하기
operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}
 */

/*
// [리스트 7.5] 단항 연산자 정의하기
operator fun Point.unaryMinus(): Point {
    return Point(-x, -y)
}
 */

/*
// [리스트 7.6] 증가 연산자 정의하기
operator fun BigDecimal.inc() = this + BigDecimal.ONE
 */

/*
// [리스트 7.7] equals 메소드 구현하기
class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Point) return false
        return other.x == x && other.y == y
    }
}
 */

/*
// [리스트 7.8] compareTo 메소드 구현하기
class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }
}
 */

/*
// [리스트 7.9] get 관례 구현하기
operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}
 */

/*
// [리스트 7.10] 관례를 따르는 set 구현하기
data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}
 */

/*
// [리스트 7.11] in 관례 구현하기
data class Rectangle(val upperLeft: Point, val lowRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x until lowRight.x &&
            p.y in upperLeft.y until lowRight.y
}
 */

/*
// [리스트 7.12] 날짜의 범위 다루기
val now = LocalDate.now()
val vacation = now..now.plusDays(10)

fun main() {
    println(now.plusWeeks(1) in vacation)
}
 */

/*
// [리스트 7.13] 날짜 범위에 대한 이터레이터 구현하기
operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object : Iterator<LocalDate> {
        var current = start

        override fun hasNext(): Boolean {
            return current <= endInclusive
        }

        override fun next(): LocalDate = current.apply {
            current = plusDays(1)
        }
    }
 */

/*
// [리스트 7.14] 구조 분해 선언을 사용해 여러 값 반환하기
data class NameComponents(val name: String, val extension: String)

fun splitFilename(fullName: String): NameComponents {
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1])
}

fun main() {
    val (name, ext) = splitFilename("example.kt")
    println(name)
    println(ext)
}
 */

/*
// [리스트 7.15] 컬렉션에 대해 구조 분해 선언 사용하기
data class NameComponents(val name: String, val extension: String)

fun splitFilename(fullName: String): NameComponents {
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}
 */

/*
// [리스트 7.16] 구조 분해 선언을 사용해 맵 이터레이션하기
fun printEntries(map: Map<String, String>) {
    for ((key, value) in map) {
        println("$key -> $value")
    }
}
 */

/*
// [리스트 7.17] 지연 초기화를 뒷받침하는 프로퍼티를 통해 구현하기
class Person(val name: String) {
    private var _emails: List<Email>? = null
    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails(this)
            }
            return _emails!!
        }
}
 */

/*
// [리스트 7.18] 지연 초기화를 위임 프로퍼티를 통해 구현하기
class Person(val name: String) {
    val emails by lazy { loadEmails(this) }
}
 */

/*
// [리스트 7.19] PropertyChangeSupport를 사용하기 위한 도우미 클래스
open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun remomvePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}
 */

/*
// [리스트 7.20] 프로퍼티 변경 통지를 직접 구현하기
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }

    var salary: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}

fun main() {
    val p = Person("Dmitry", 34, 2000)
    p.addPropertyChangeListener(
        PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed" +
                    "from ${event.oldValue} to ${event.newValue}")
        }
    )
}
 */

/*
// [리스트 7.21] 도우미 클래스를 통해 프로퍼티 변경 통지 구현하기
class ObservableProperty(
    val propName: String,
    var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {

    fun getValue(): Int = propValue
    fun setValue(newValue: Int) {
        val oldVaue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldVaue, newValue)
    }
}

class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) { _age.setValue(value) }

    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) { _salary.setValue(value) }
}
 */

/*
// [리스트 7.22] ObservableProperty를 프로퍼티 위임에 사용할 수 있게 바꾼 모습
class ObservableProperty(var propValue: Int, val changeSupport: PropertyChangeSupport) {
    operator fun getValue(p: Person, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: Person, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}
 */

/*
// [리스트 7.23] 위임 프로퍼티를 통해 프로퍼티 변경 통지 받기
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}
 */

/*
// [리스트 7.24] Delegates.observable을 사용해 프로퍼티 변경 통지 구현하기
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}
 */

/*
// [리스트 7.25] 값을 맵에 저장하는 프로퍼티 정의하기
class Person {
    // 추가 정보
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    // 필수 정보
    val name: String
        get() = _attributes["name"]!!
}
 */

/*
// [리스트 7.26] 값을 맵에 저장하는 위임 프로퍼티 사용하기
class Person {
    private val _attributes = hashMapOf<String, String>()
    fun setAttributes(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String by _attributes
}
 */

/*
// [리스트 7.27] 위임 프로퍼티를 사용해 데이터베이스 칼럼 접근하기
object Users: IdTable() {
    val name = varchar("name", length = 50).index()
    val age = integer("age")
}

class User(id: EntityID) : Entity(id) {
    var name: String by Users.name
    var age: Int by Users.age
}
 */
