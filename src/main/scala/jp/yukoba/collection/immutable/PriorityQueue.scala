package jp.yukoba.collection.immutable

import scala.collection.{AbstractSeq, GenTraversableOnce}
import scalaz.Scalaz.unfold
import scalaz.{FingerTree, Monoid, Reducer}

protected class PriorityQueue[A](val tree: FingerTree[A, A]) extends AbstractSeq[A] with Serializable {
  def enqueue(elem: A): PriorityQueue[A] = new PriorityQueue(tree :+ elem)
  def enqueue(elem: Iterable[A]): PriorityQueue[A] = new PriorityQueue(elem.foldLeft(tree)(_ :+ _))

  def :+(elem: A): PriorityQueue[A] = enqueue(elem)
  def +:(elem: A): PriorityQueue[A] = enqueue(elem)
  def ++(that: GenTraversableOnce[A]): PriorityQueue[A] = enqueue(that.toIterable.seq)
  def ++:(that: TraversableOnce[A]): PriorityQueue[A] = enqueue(that.toIterable)

  def deque: (A, PriorityQueue[A]) = (head, tail)
  def dequeOption: Option[(A, PriorityQueue[A])] = if (isEmpty) None else Some(deque)

  override def head: A = tree.split1(_ == tree.measure)._2
  def front: A = head

  override def tail: PriorityQueue[A] = {
    val t = tree.split1(_ == tree.measure)
    new PriorityQueue(t._1 <++> t._3)
  }

  override def isEmpty = tree.isEmpty

  override def toStream: Stream[A] = unfold(this)(t => if (t.isEmpty) None else Some(t.head, t.tail))

  override def iterator: Iterator[A] = toStream.iterator
  override def length: Int = tree.toStream.length
  override def apply(idx: Int): A = toStream(idx)
}

object PriorityQueue {
  /** null is the smallest */
  private class MaxMonoid[A](ord: Ordering[A]) extends Monoid[A] {
    override def zero: A = null.asInstanceOf[A]

    override def append(f1: A, f2: => A): A = {
      if (f1 == null) f2
      else if (f2 == null) f1
      else if (ord.lt(f1, f2)) f2
      else f1
    }
  }

  private def MaxReducer[A](ord: Ordering[A]): Reducer[A, A] =
    Reducer.identityReducer[A](new MaxMonoid(ord))

  def empty[A](implicit ord: Ordering[A]): PriorityQueue[A] =
    new PriorityQueue(FingerTree.empty(MaxReducer[A](ord)))

  def apply[A](elems: A*)(implicit ord: Ordering[A]): PriorityQueue[A] =
    elems.foldLeft(empty[A](ord))((pq, e) => pq.enqueue(e))
}
