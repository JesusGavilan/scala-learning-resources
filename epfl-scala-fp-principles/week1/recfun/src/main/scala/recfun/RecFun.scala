package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c == 0 || r == c) 1
    else pascal(c-1,r-1)+ pascal(c, r-1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def iter(chars: List[Char], numOpens: Int): Boolean = {
      if (chars.isEmpty) {
        numOpens == 0
      }
      else {
        val h = chars.head
        val t = chars.tail
        val n =
          if (h == '(') numOpens + 1
          else if (h == ')') numOpens - 1
          else numOpens
        if (n >= 0) iter(chars.tail, n)
        else false
      }
    }

    iter(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
      def iter(m: Int, c: List[Int]): Int = {
        if (c.isEmpty) 0
        else if (m - c.head == 0) 1
        else if (m - c.head < 0) 0
        else countChange(m - c.head, c) + countChange(m, c.tail)
      }
      iter(money, coins.sorted)

  }
