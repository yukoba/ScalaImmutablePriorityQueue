import jp.yukoba.collection.immutable.PriorityQueue
import org.scalatest.FunSuite

class PriorityQueueTest extends FunSuite {
  test("Int") {
    val q1: PriorityQueue[Int] = PriorityQueue.empty
    val q2 = q1.enqueue(1)
    val q3 = q2.enqueue(3)
    val q4 = 4 +: q3
    val q5 = (List(5) ++: (q4 :+ 2)) ++ Vector(6)

    assert(q5.toString() == "PriorityQueue(6, 5, 4, 3, 2, 1)")
    assert(q5.head == 6)
    assert(q5.tail == PriorityQueue(5, 4, 3, 2, 1))
    assert(q5.last == 1)
    assert(q5.tail.head == 5)
    assert(q5.indexOf(2) == 4)
    assert(q5.foldLeft(0)(_ + _) == 21)
    assert(q5.headOption == Some(6))
    assert(q5.maxOption == Some(6))
    assert(q5.minOption == Some(1))
    assert(q5.isTraversableAgain)
    assert(q5.toList == List(6, 5, 4, 3, 2, 1))
    assert(q5.toVector == Vector(6, 5, 4, 3, 2, 1))

    val (v1, q6) = q5.dequeue
    val (v2, _) = q6.dequeue
    assert(v1 == 6)
    assert(v2 == 5)
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
    println(s"queue2.dequeue = ${queue2.dequeue}")
    println(s"queue2.dequeueOption = ${queue2.dequeueOption}")
    println(s"queue2(3) = ${queue2(3)}")
  }
}
