import jp.yukoba.collection.immutable.PriorityQueue
import org.scalatest.FunSuite

class PriorityQueueTest extends FunSuite {
  test("Int") {
    val q1 = PriorityQueue.empty[Int]
    val q2 = q1.enqueue(1)
    val q3 = q2.enqueue(3)
    val q4 = q3.enqueue(4)
    val q5 = q4.enqueue(2)

    assert(q5.toString() == "PriorityQueue(4, 3, 2, 1)")
    assert(q5.head == 4)
    assert(q5.tail == PriorityQueue(3, 2, 1))
    assert(q5.last == 1)
    assert(q5.tail.head == 3)
    assert(q5.indexOf(2) == 2)

    val (v1, q6) = q5.deque
    val (v2, _) = q6.deque
    assert(v1 == 4)
    assert(v2 == 3)
  }

  test("Object with Ordering") {
    case class Test(name: String, priority: Int)

    /** Reverse ordering */
    object TestMinOrdering extends Ordering[Test] {
      override def compare(x: Test, y: Test): Int = y.priority compare x.priority
    }

    val queue = PriorityQueue(Test("b", 2), Test("a", 1), Test("c", 3))(TestMinOrdering)
    println(s"queue = $queue")
  }

  test("Ordered object") {
    case class Test(name: String, priority: Int) extends Ordered[Test] {
      override def compare(that: Test): Int = priority compare that.priority
    }

    val queue1 = PriorityQueue(Test("b", 2), Test("d", 4), Test("c", 3))
    val queue2 = queue1.enqueue(Test("a", 1))

    println(s"queue2 = $queue2")
    println(s"queue2.head = ${queue2.head}")
    println(s"queue2.tail = ${queue2.tail}")
    println(s"queue2.deque = ${queue2.deque}")
    println(s"queue2.dequeOption = ${queue2.dequeOption}")
    println(s"queue2(3) = ${queue2(3)}")
  }
}
