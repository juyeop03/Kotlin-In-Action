package chapter1

/*
// [리스트 11.1] 람다를 인자로 받는 buildString() 정의하기
fun buildString(
    builderAction: (StringBuilder) -> Unit
): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}

fun main() {
    val s = buildString {
        it.append("Hello, ")
        it.append("World!")
    }
    println(s)
}
 */

/*
// [리스트 11.2] 수신 객체 지정 람다를 사용해 다시 정의한 buildString()
fun buildString(
    builderAction: StringBuilder.() -> Unit
): String {
    val sb = StringBuilder()
    sb.builderAction()
    return sb.toString()
}

fun main() {
    val s = buildString {
        this.append("Hello, ")
        append("World!")
    }
    println(s)
}
 */

/*
// [리스트 11.3] 수신 객체 지정 람다를 변수에 저장하기
val appendExcl: StringBuilder.() -> Unit = { this.append("!") }

fun main() {
    val stringBuilder = StringBuilder("Hi")
    stringBuilder.appendExcl()

    println(stringBuilder.toString())
    println(buildString(appendExcl))
}
 */

/*
// [리스트 11.4] 코틀린 HTML 빌더를 사용해 간단한 HTML 표 만들기
fun createSimpleTable() = createHTML()
    .table {
        tr {
            td { +"cell" }
        }
    }
 */

/*
// [리스트 11.5] HTML 빌더를 위한 태그 클래스 정의
open class Tag

class TABLE : Tag() {
    fun tr(init: TR.() -> Unit) {}
}

class TR : Tag() {
    fun td(init: TD.() -> Unit) {}
}

class TD : Tag()
 */

/*
// [리스트 11.6] HTML 빌더 호출의 수신 객체를 명시한 코드
fun createSimpleTable() = createHTML()
    .table {
        (this@table).tr {
            (this@tr).td {
                +"cell"
            }
        }
    }
 */

/*
// [리스트 11.7] HTML에서 문자열 만들기
fun createTable() =
    table {
        tr {
            td {

            }
        }
    }
 */

/*
// [리스트 11.8] 태그 빌더 함수 정의하기
fun tr(init: TR.() -> Unit) {
    val tr = TR()
    tr.init()
    children.add(tr)
}
 */

/*
// [리스트 11.9] 간단한 HTML 빌더의 전체 구현
open class Tag(private val name: String) {
    private val children = mutableListOf<Tag>()
    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }

    override fun toString(): String =
        "<$name>${children.joinToString("")}</$name>"
}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td")

fun createTable() =
    table {
        tr {
            td {

            }
        }
    }
 */

/*
// [리스트 11.10] HTML 빌더를 사용해 태그를 동적으로 생성하기
fun createAnotherTable() = table {
    for (i in 1..2) {
        tr {
            td {

            }
        }
    }
}
 */

/*
// [리스트 11.11] 부트스트랩 라이브러리를 사용해 드롭다운 메뉴를 HTML에 추가하기
<div class="dropdown">
    <button class="btn dropdown-toggle">
        Dropdown
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu">
        <li><a href="#">Action</a></li>
        <li><a href="#">Another action</a></li>
        <li role="separator" class="divider"></li>
        <li class="dropdown-header">Header</li>
        <li><a href="#">Separated link</a></li>
    </ul>
</div>
 */

/*
// [리스트 11.12] 코틀린 HTML 빌더를 사용해 드롭다운 메뉴 만들기
fun buildDropdown() = createHTML().div(classes = "dropdown") {
    button(classes = "btn dropdown-toggle") {
        +"Dropdown"
        span(classes = "caret")
    }
    ul(classes = "dropdown-menu") {
        li { a("#") { +"Action" } }
        li { a("#") { +"Another action" } }
        li { role = "separator"; classes = setOf("divider") }
        li { classes = setOf("dropdown-header"); +"Header" }
        li { a("#") { +"Separated link" } }
    }
}
 */

/*
// [리스트 11.13] 도우미 함수를 활용해 드롭다운 메뉴 만들기
fun dropdownExample() = createHTML().dropdown {
    dropdownButton { +"Dropdown" }
    dropdownMenu {
        item("#", "Action")
        item("#", "Another action")
        divider()
        dropdownHeader("Header")
        item("#", "Separated link")
    }
}
 */

/*
// [리스트 11.14] item 함수를 사용해 드롭다운 메뉴 만들기
ul {
    classes = setOf("dropdown-menu")
    item("#", "Action")
    item("#", "Another action")
    li { role = "separator"; classes = setOf("divider") }
    li { classes = setOf("dropdown-header"); +"Header" }
    item("#", "Separated link")
}
 */

/*
// [리스트 11.15] 드롭다운 메뉴를 만드는 최상위 함수
fun StringBuilder.dropdown(
    block: DIV.() -> Unit
): String = div("dropdown", block)
 */

/*
// [리스트 11.16] 클래스 안에서 invoke 메소드 정의하기
class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name!")
    }
}

fun main() {
    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter("Dmitry")
}
 */

/*
// [리스트 11.17] 함수 타입을 확장하면서 invoke()를 오버라이딩하기
data class Issue(
    val id: String,
    val project: String,
    val type: String,
    val priority: String,
    val description: String
)

class ImportantIssuePredicate(val project: String) : (Issue) -> Boolean {
    override fun invoke(issue: Issue): Boolean {
        return issue.project == project && issue.isImportant()
    }

    private fun Issue.isImportant(): Boolean {
        return type == "Bug" && (priority == "Major" || priority == "Ciriticlal")
    }
}
 */

/*
// [리스트 11.18] 유연한 DSL 문법을 제공하기 위해 invoke 사용하기
class DependencyHandler {
    fun compile(coordinate: String) {
        println("Added dependency on $coordinate")
    }

    operator fun invoke(body: DependencyHandler.() -> Unit) {
        body()
    }
}
 */

/*
// [리스트 11.19] 코틀린테스트 DSL 단언문 표현하기
s shoud startWith("kot")
 */

/*
// [리스트 11.20] should 함수 구현
infix fun <T> T.shoud(matcher: Matcher<T>) = matcher.test(this)
 */

/*
// [리스트 11.21] 코틀린테스트 DSL에 사용하기 위한 Matcher 선언
interface Matcher<T> {
    fun test(value: T)
}

class startWith(val prefix: String) : chapter1.Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix))
            throw AssertionError("String $value does not start with $prefix")
    }
}
 */

/*
// [리스트 11.22] 코틀린테스트 DSL에서 메소드 호출 연쇄시키기
"kotlin" should start with "kot"
 */

/*
// [리스트 11.23] 중위 호출 연쇄를 지원하기 위한 API 정의
object start

infix fun String.should(x: start): StartWrapper = StartWrapper(this)

class StartWrapper(val value: String) {
    infix fun with(prefix: String) =
        if (!value.startsWith(prefix))
            throw AssertionError("String does not start with $prefix: $value")
        else
            Unit
}
 */

/*
// [리스트 11.24] 날짜 조작 DSL 정의
val Int.days: Period
    get() = Period.ofDays(this)

val Period.ago: LocalDate
    get() = LocalDate.now() - this

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this
 */

/*
// [리스트 11.25] 익스포즈드에서 테이블 선언하기
object Country : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 50)
}
 */

/*
// [리스트 11.26] 익스포즈드에서 두 테이블 조인(join)하기
val result = (Country join Customer)
    .select { Country.name eq "USA" }

fun main() {
    result.forEach { println(it[Customer.name]) }
}
 */

/*
// [리스트 11.27] 안코를 사용해 안드로이드 경고 창 표시하기
fun Activity.showAreYouSureAlert(process: () -> Unit) {
    alert(title = "Are you sure?",
         message = "Are you really sure?") {
        positiveButton("Yes") { process() }
        negativeButton("No") { cancel() }
    }
}
 */

/*
// [리스트 11.28] alert API의 선언
fun Context.alert(
    message: String,
    title: String,
    init: AlertDialogBuilder.() -> Unit
)

class AlertDialogBuilder {
    fun positiveButton(text: String, callback: DialogInterface.() -> Unit)
    fun negativeButton(text: String, callback: DialogInterface.() -> Unit)
}
 */

/*
// [리스트 11.29] 안코를 사용해 간단한 액티비티 정의하기
verticalLayout {
    val email = editText {
        hint = "Email"
    }

    val password = editText {
        hint = "Password"
        transformationMethod =
            PasswordTransformationMethod.getInstance()
    }

    button("Log In") {
        onClick {
            login(email.text, password.text)
        }
    }
}
 */
