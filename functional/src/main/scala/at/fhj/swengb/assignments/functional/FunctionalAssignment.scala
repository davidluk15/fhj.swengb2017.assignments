package at.fhj.swengb.assignments.functional

/**
  * In this assignment you have the chance to demonstrate basic understanding of
  * functions like map/filter/foldleft a.s.o.
  **/
object FunctionalAssignment {

  /**
    * A function which returns its parameters in a changed order. Look at the type signature.
    */
  def flip[A, B](t: (A, B)): (B, A) = t.swap

  /**
    * given a Seq[A] and a function f : A => B, return a Seq[B]
    */
  def unknown[A, B](as: Seq[A], fn: A => B): Seq[B] = for (x <- as) yield fn(x)

  /**
    * Returns the absolute value of the parameter i.
    *
    * @param i a value, either with a positive or a negative sign.
    * @return
    */
  def abs(i: Int): Int = if (i >= 0) i else -1 * i


  // Describe with your own words what this function does.
  // in the comment below, add a description what this function does - in your own words - and give
  // the parameters more descriptive names.
  //
  // Afterwards, compare your new naming scheme with the original one.
  // What did you gain with your new names? What did you loose?
  //
  /**
    *foldleft function that takes two arguments the accumulator and the current item in the list.
    *The function goes from left to right trough the list for example it can sum up a list like above.
    * There has to be a starting accumulator then the first element is added to the accmulator (acc + first element)
    * The result is the new accumulator and this goes on until the list is finished, the final result is the last accumulator.
    * Many different operations can be executed with foldLeft for example +,- * operations.
    *
    *
    *
    * @param list = List of elements
    * @param acc = This is the accumulator for the fold operation and also the starting value.
    * @param function = Type of function that gets applied to the elements in the list and the accmulator.
    * @tparam value = Value of the list element.
    * @tparam accValue = Value of the accumulator, here the interim result is stored and also the final value is the accumulator
    *                 if there are no more elements in the list.
    * @return = Function return type of accValue
    */
  def op[value, accValue](list: Seq[value], acc: accValue)(function: (accValue, value) => accValue): accValue = list.foldLeft(acc)(function)

  /**
    * implement the summation of the given numbers parameter.
    * Use the function 'op' defined above.
    *
    * @param numbers = Numbers where the foldLeft function gets applied
    * @return
    */
  def sum(numbers: Seq[Int]): Int = op(numbers,0) (_+_)


  /**
    * calculate the factorial number of the given parameter.
    *
    * for example, if we pass '5' as parameter, the function should do the following:
    *
    * 5! = 5 * 4 * 3 * 2 * 1
    *
    * @param i parameter for which the factorial must be calculated
    * @return i!
    */
  def fact(i: Int): Int = if (i==0) 1 else i * fact(i-1)

  /**
    * compute the n'th fibonacci number
    *
    * hint: use a internal loop function which should be written in a way that it is tail
    * recursive.
    *
    * https://en.wikipedia.org/wiki/Fibonacci_number
    */
  def fib(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib( n-1 ) + fib( n-2 )
  }

  /**
    * Implement a isSorted which checks whether an Array[A] is sorted according to a
    * given comparison function.
    *
    * Implementation hint: you always have to compare two consecutive elements of the array.
    * Elements which are equal are considered to be ordered.
    */
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
        def sort(i: Int): Boolean = {
            if (i + 1 > as.length-1) true
            else if (!gt(as(i), as(i + 1))) false
            else sort(i + 1)
          }
        sort(0)
      }
  /**
    * Takes both lists and combines them, element per element.
    *
    * If one sequence is shorter than the other one, the function stops at the last element
    * of the shorter sequence.
    */
  def genPairs[A, B](as: Seq[A], bs: Seq[B]): Seq[(A, B)] = as zip bs


  // a simple definition of a linked list, we define our own list data structure
  sealed trait MyList[+A]

  case object MyNil extends MyList[Nothing]

  case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

  // the companion object contains handy methods for our data structure.
  // it also provides a convenience constructor in order to instantiate a MyList without hassle
  object MyList {

    def sum(list: MyList[Int]): Int = list match {
        case MyNil => 0
        case Cons(h,t) => h + sum(t)

    }

    def product(list: MyList[Int]): Int = list match{
        case MyNil => 0
        case Cons(h,t) => if (h==0) product(t) else h*product(t)


    }

    def apply[A](as: A*): MyList[A] = {
      if (as.isEmpty) MyNil
      else Cons(as.head, apply(as.tail: _*))
    }

  }

}

