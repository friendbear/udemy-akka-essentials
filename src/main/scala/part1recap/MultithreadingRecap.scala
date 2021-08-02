package part1recap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object MultithreadingRecap extends App {

  val aThread = new Thread(() => println("I'm running in parallel"))
  aThread.start()
  aThread.join()

  val threadHello = new Thread(() => (1 to 1000).foreach(_ => println("hello")))
  val threadGoodbye = new Thread(() => (1 to 1000).foreach(_ => println("goodbye")))
  threadHello.start()
  threadGoodbye.start()

  class BankAccount(@volatile private var amount: Int) {
    override def toString: String = "" + amount

    def withdraw(money: Int) = this.amount -= money

    def safeWithdraw(money: Int) = this.synchronized {
      this.amount -= money
    }
  }

  /*
    BA (10000)

    T1 -> withdraw 1000
    T2 -> withdraw 2000

    T1 -> this.amount = this.amount - ....
    T2 -> this.amount = this.amount - 2000 = 8000
   */
  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    42
  }

  future.onComplete {
    case Success(42) => println("I found the meaning of life")
    case Failure(_) => println("something happen with the meaning of life!")
  }

  val aProcessedFuture = future.map{_ + 1} // Future with 43
  val aFlatFuture = future.flatMap { value =>
    Future(value + 2)
  } // Future with 44

  val filterdFuture = future.filter(_ % 2 == 0)

  val aNonsenseFuture = for {
    meaningOfLife <- future
    filteredMeaning <- filterdFuture
  } yield meaningOfLife + filteredMeaning

}
