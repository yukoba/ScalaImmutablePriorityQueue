package jp.yukoba.collection.immutable

import scalaz.Maybe.Just
import scalaz.{FingerTree, Reducer, Semigroup}

import scala.collection.AbstractSeq

class PriorityQueue[A] protected (val tree: FingerTree[A, A]) extends AbstractSeq[A] with Serializable:
    def enqueue(elem: A): PriorityQueue[A] = new PriorityQueue(tree :+ elem)
    def enqueue(elem: IterableOnce[A]): PriorityQueue[A] = new PriorityQueue(elem.iterator.foldLeft(tree)(_ :+ _))

    def :+(elem: A): PriorityQueue[A] = enqueue(elem)
    def +:(elem: A): PriorityQueue[A] = enqueue(elem)
    def ++(that: IterableOnce[A]): PriorityQueue[A] = enqueue(that)
    def ++:(that: IterableOnce[A]): PriorityQueue[A] = enqueue(that)

    def dequeue: (A, PriorityQueue[A]) = (head, tail)
    def dequeueOption: Option[(A, PriorityQueue[A])] = if isEmpty then None else Some(dequeue)

    override def head: A = tree.split1(n => tree.measure == Just(n))._2
    def front: A = head

    override def tail: PriorityQueue[A] =
        val t = tree.split1(n => tree.measure == Just(n))
        new PriorityQueue(t._1 <++> t._3)

    override def isEmpty: Boolean = tree.isEmpty

    override def iterator: Iterator[A] = Iterator.unfold(this)(t => if t.isEmpty then None else Some(t.head, t.tail))
    override def length: Int = tree.iterator.length
    override def apply(idx: Int): A = iterator.drop(idx).next()

    override protected def className: String = "PriorityQueue"

object PriorityQueue:
    private def MaxReducer[A](ord: Ordering[A]): Reducer[A, A] =
        Reducer.identityReducer[A](using
            new Semigroup[A]:
                override def append(f1: A, f2: => A): A = ord.max(f1, f2),
        )

    def empty[A](implicit ord: Ordering[A]): PriorityQueue[A] =
        new PriorityQueue(FingerTree.empty(using MaxReducer[A](ord)))

    def apply[A](elems: A*)(implicit ord: Ordering[A]): PriorityQueue[A] = empty[A](using ord).enqueue(elems)
