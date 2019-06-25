package BasicScala.lectures.Basics

object DefaultArgs extends App {

  def trFact(n: Int, acc : Int = 1) : Int ={
    if(n <=1 ) acc
    else trFact(n-1,n*acc)
  }

  val fact10 = trFact(10,1)
  println(fact10)

  def savePicture(format: String = "jpg", width:Int=100, height: Int = 100) : Unit = println("Save Picture")

  savePicture(height = 600, width = 800, format = "bmp")

  savePicture(width = 200)
}
