import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._

/**
 * Simple word count of a text file with Scala API
 */

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCount")
    val sc = new SparkContext(conf)

    val input= sc.textFile("/root/exampleData/pg4017.txt")   // read from a text file, make this a command line argument with compatible FS
    val word = input.flatMap(x => x.split(" "))  // improve the tokenizer
    val wordCount = word.map(x => (x,1)).reduceByKey(_ + _)
    wordCount.saveAsTextFile("/path/to/outputTextFile")
  }
}

