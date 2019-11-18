import scala.util.Random

object Main {

  def main(args: Array[String]): Unit = {
    println(s"${keyDecrypt("PSYCHOKING")("VHTUBXXNG")}")
    println(s"${rotateCipher("HelloJoey")}")
  }

  val rand = new Random()
  val letters: String = ('A' to 'Z').mkString
  val nums: List[Int] = (1 to 26).toList
  val intMap: Map[Int, Char] = nums.zip(letters).toMap
  val charMap: Map[Char, Int] = intMap.map(_.swap)

  def correct(n : Int): Int = n match {
    case n if n > 0 => ((n - 1) % 26) + 1
    case n => 26 - (n.abs % 26)
  }

  def toChar(n: Int): Char = intMap(correct(n))

  def fromChar(n: Char): Int = charMap(n)

  def intShift(ss: Seq[Int], ls: String): String = ss.zip(ls).map { case (a, b) => toChar(a + fromChar(b)) }.mkString

  def shift(n: Int, s: String): String = intShift(List.fill(s.length)(n), s)

  def keyEncrypt(xs: String)(str: String): String = intShift(xs.map(fromChar), str)

  def keyDecrypt(xs: String)(str: String): String = intShift(xs.map(fromChar).map(- _), str)

  def dim(xs: String): Int = Math.sqrt(xs.length).ceil.toInt

  def garbage(n: Int): String = (1 to n).map(_ => toChar(rand.between(1, 27))).mkString

  def pad(xs: String): String = xs ++ garbage(dim(xs) * dim(xs) - xs.length)

  def table(s: String): List[String] = pad(s).grouped(dim(s)).toList

  def squareCipher(f: List[String] => List[String])(s: String): String = f(table(s)).mkString

  def transposeCipher(s: String): String = squareCipher(_.transpose.map(_.mkString))(s)

  def rotateCipher(s: String): String = squareCipher(_.transpose.map(_.mkString).map(_.reverse))(s)

}
