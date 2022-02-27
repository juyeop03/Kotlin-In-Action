package chapter1

/*
// [리스트 6.1] if 검사를 통해 null 값 다루기
fun strLenSafe(s: String?): Int =
    if (s != null) s.length else 0
 */

/*
// [리스트 6.2] 널이 될 수 있는 프로퍼티를 다루기 위해 안전한 호출 사용하기
class Empolyee(val name: String, val manager: Empolyee?)
fun managerName(empolyee: Empolyee): String? = empolyee.manager?.name
 */

/*
// [리스트 6.3] 안전한 호출 연쇄시키기
class Address(
    val streetAddress: String,
    val zipCode: Int,
    val city: String,
    val country: String
)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
    val country = company?.address?.country
    return if (country != null) country else "Unknown"
}
 */

/*
// [리스트 6.4] 엘비스 연산자를 활용해 널 값 다루기
fun strLenSafe(s: String?): Int = s?.length ?: 0
 */

/*
// [리스트 6.5] throw와 엘비스 연산자 함께 사용하기
fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No address")
    with (address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}
 */

/*
// [리스트 6.6] 안전한 연산자를 사용해 equals 구현하기
class Person(val firstName: String, val lastName: String?) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person ?: return false
        return otherPerson.firstName == firstName &&
            otherPerson.lastName == lastName
    }

    override fun hashCode(): Int =
        firstName.hashCode() * 37 + lastName.hashCode()
}
 */

/*
// [리스트 6.7] 널 아님 단언 사용하기
fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}
 */

/*
// [리스트 6.8] 스윙 액션에서 널 아님 단언 사용하기
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.JList

class CopyRowAction(val list: JList<String>) : AbstractAction() {

    override fun isEnabled(): Boolean =
        list.selectedValue != null

    override fun actionPerformed(e: ActionEvent?) {
        val value = list.selectedValue!!
    }
}
 */

/*
// [리스트 6.9] let을 사용해 null이 아닌 인자로 함수 호출하기
fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun main() {
    var email: String? = "yole@example.com"
    email?.let { sendEmailTo(it) }

    email = null
    email?.let { sendEmailTo(it) }
}
 */

/*
// [리스트 6.10] 널 아님 단언을 사용해 널이 될 수 있는 프로퍼티 접근하기
class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    private var myService: MyService? = null

    @Before fun setUp() {
        myService = MyService()
    }

    @Test fun testAction() {
        Assert.assertEquals("foo", myService!!.performAction())
    }
}
 */

/*
// [리스트 6.11] 나중에 초기화하는 프로퍼티 사용하기
class MyTest {
    private lateinit var myService: MyService

    @Before fun setUp() {
        myService = MyService()
    }

    @Test fun testAction() {
        Assert.assertEquals("foo", myService.performAction())
    }
}
 */

/*
// [리스트 6.12] null이 될 수 있는 수신 객체에 대해 확장 함수 호출하기
fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) {
        println("Please fill in the required fields")
    }
}
 */

/*
// [리스트 6.13] 널이 될 수 있는 타입 파라미터 다루기
fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}
 */

/*
// [리스트 6.14] 타입 파라미터에 대해 널이 될 수 없는 상한을 사용하기
fun <T : Any> printHashCode(t: T) {
    println(t.hashCode())
}
 */

/*
// [리스트 6.15] 널 가능성 애노테이션이 없는 자바 클래스
public class Person {
    private final String name;

    public Person(String name) {
        this.name = name
    }

    public String getName() {
        return name;
    }
}
 */

/*
// [리스트 6.16] 널 검사 없이 자바 클래스 접근하기
fun yellAt(person: Person) {
    println(person.name.toUpperCase() + "!!!")
}
 */

/*
// [리스트 6.17] 널 검사를 통해 자바 클래스 접근하기
fun yellAtSafe(person: Person) {
    println((person.name ?: "Anyone").toUpperCase() + "!!!")
}
 */

/*
// [리스트 6.18] String 파라미터가 있는 자바 인터페이스
interface StringProcessor {
    void process(String value);
}
 */

/*
// [리스트 6.19] 자바 인터페이스를 여러 다른 널 가능성으로 구현하기]
class StringPrinter : StringProcessor {
    override fun process(value: String) {
        println(value)
    }
}

class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        if (value != null) {
            println(value)
        }
    }
}
 */

/*
// [리스트 6.20] 널이 될 수 있는 원시 타입
data class Person(val name: String, val age: Int? = null) {
    fun isOlderThan(other: Person): Boolean? {
        if (age == null || other.age == null) {
            return null
        }
        return age > other.age
    }
}
 */

/*
// [리스트 6.21] 널이 될 수 있는 값으로 이뤄진 컬렉션 만들기
import java.io.BufferedReader

fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()) {
        try {
            val number = line.toInt()
            result.add(number)
        } catch (e: NumberFormatException) {
            result.add(null)
        }
    }
    return result
}
 */

/*
// [리스트 6.22] 널이 될 수 있는 값으로 이뤄진 컬렉션 다루기
fun addValidNumbers(numbers: List<Int?>) {
    var sumOfValidNumbers = 0
    var invalidNumbers = 0

    for (number in numbers) {
        if (number != null) {
            sumOfValidNumbers += number
        } else {
            invalidNumbers++
        }
    }

    println("Sum of valid numbers: $sumOfValidNumbers")
    println("Invalid numbers: $invalidNumbers")
}
 */

/*
// [리스트 6.23] filterNotNull를 널이 될 수 있는 값으로 이뤄진 컬렉션에 대해 사용하기
fun addValidNumbers(numbers: List<Int?>) {
    val validNumbers = numbers.filterNotNull()
    println("Sum of valid numbers: ${validNumbers.sum()}")
}
 */

/*
// [리스트 6.24] 읽기 전용과 변경 가능한 컬렉션 인터페이스
fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (item in source) {
        target.add(item)
    }
}
 */

/*
// [리스트 6.25] 컬렉션 파라미터가 있는 자바 인터페이스
interface FileContentProcessor {
    void processContents(
        File path,
        byte[] binaryContents,
        List<String> textContents
    );
}
 */

/*
// [리스트 6.26] FileContentProcess를 코틀린으로 구현한 모습
class FileIndexer : FileContentProcessor {
    override fun processContents(
        path: File,
        binaryContents: ByteArray?,
        textContents: List<String>?
    )
}
 */

/*
// [리스트 6.27] 컬렉션 파라미터가 있는 다른 자바 인터페이스
interface DataParse<T> {
    void parseData(
        String input,
        List<T> output,
        List<String> errors
    )
}
 */

/*
// [리스트 6.28] DataParse의 코틀린 구현
class PersonParser : DataParser<Person> {
    override fun parseData(
        input: String,
        output: MutableList<Person>,
        errors: MutableList<String>
    )
}
 */

/*
// [리스트 6.29] 배열 사용하기
fun main(args: Array<String>) {
    for (i in args.indices) {
        println("Argument $i is: ${args[i]}")
    }
}
 */

/*
// [리스트 6.30] 알파벳으로 이뤄진 배열 만들기
val letters = Array<String>(26) { i -> ('a' + i).toString() }
 */

/*
// [리스트 6.31] 컬렉션을 vararg 메소드에게 넘기기
val strings = listOf("a", "b", "c")

fun main() {
    println("%s/%s/%s".format(*strings.toTypedArray()))
}
 */

/*
// [리스트 6.32] 배열에 forEachIndexed 사용하기
fun main(args: Array<String>) {
    args.forEachIndexed { index, s ->
        println("Argument $index is: $s")
    }
}
 */
