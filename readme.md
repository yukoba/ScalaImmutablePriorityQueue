# Scala immutable priority queue

This is an immutable priority queue for Scala.
This wraps FingerTree of Scalaz.

## Usage
```scala
import jp.yukoba.collection.immutable.PriorityQueue

case class Test(name: String, priority: Int) extends Ordered[Test]:
    override def compare(that: Test): Int = priority compare that.priority

val queue1 = PriorityQueue(Test("b", 2), Test("d", 4), Test("c", 3))
val queue2 = queue1.enqueue(Test("a", 1))

println(s"queue2 = $queue2")
println(s"queue2.head = ${queue2.head}")
println(s"queue2.tail = ${queue2.tail}")
println(s"queue2.dequeue = ${queue2.dequeue}")
println(s"queue2.dequeueOption = ${queue2.dequeueOption}")
println(s"queue2(3) = ${queue2(3)}")

```

### Output
```
queue2 = PriorityQueue(Test(d,4), Test(c,3), Test(b,2), Test(a,1))
queue2.head = Test(d,4)
queue2.tail = PriorityQueue(Test(c,3), Test(b,2), Test(a,1))
queue2.dequeue = (Test(d,4),PriorityQueue(Test(c,3), Test(b,2), Test(a,1)))
queue2.dequeueOption = Some((Test(d,4),PriorityQueue(Test(c,3), Test(b,2), Test(a,1))))
queue2(3) = Test(a,1)
```
