// Examples illustrated in Learning Spark book, all of these are run in spark's scala shell in standalone mode

import org.apache.spark.{SparkConf, SparkContext}

val conf = new SparkConf().setAppName("LS-ShellExamples")
val sc = new SparkContext(conf)
// creating RDD
val lines = sc.textFile("/<path to>/README.md")  // create a RDD of strings with text file

val pythonLines = lines.filter(_.contains("Python")) // transformation filtering lines containing python


// actions are when computations are called RDD. Spark computes RDD in lazy fashion, which is only computes them when an action is called
pythonLines.count()  // returns 3 in this case
pythonLines.first()  // ... String = high-level APIs in Scala, Java, and Python, and an optimized engine that

// create an RDD from an in memory collection
val pandas = sc.parallelize(List("pandas", "i like pandas"))

// creating an RDD and operating on it

val inputRDD = sc.textFile("/root/exampleData/log.txt")  // apache webserver log file,
val errorsRDD = inputRDD.filter(_.contains("404"))   // transformation will return pointer to new RDD
val successRDD = inputRDD.filter(_.contains("200"))  // inputRDD can be reused again

val errOrSuccRDD = errorsRDD.union(successRDD)

val newRDD = inputRDD.filter(line => line.contains("404") || line.contains("200")) // filtering without calling union

println("Input had " + errorsRDD.count() + " concerning lines")  // calling count action here
println("here are 10 examples")
errorsRDD.take(10).foreach(println)   // calling take action, this returns number of elements from RDD


val input = sc.parallelize(List(1,2,3,4))  // creating a new RDD
val result = input.map(x => x*x)  // using map transformation and applying a function to each element in the RDD

// flatMap
val newLine = sc.parallelize(List("hello world", "hi"))
val words = lines.flatMap(line => line.split(" "))  // calling split on each line, will return RDD which will contain ("hello", "world", "hi")

// aggregate example
val Input = sc.parallelize(List(1,2,3,4,5))

val sumCount = Input.aggregate((0,0))(
  (acc,value)=>(acc._1 + value, acc._2 + 1),  // (sum the values from the RDD, add the positions of added values)
  (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2)) // (combine the partitionsfor first place, combine the paritions for second place)
val avg = sumCount._1 / sumCount._2.toDouble

// ############### //
//     Chapter4    //
// ############### //

val newStringRDD = sc.parallelize(List("Biggus Dickus", "Ostentatious Basterd", "Any sumBitch"))
val keyValueRDD = newStringRDD.map(x => (x(0), x))  // creates a key value RDD with first word of the string as key and string a value

// the are many inbuilt functions which operate on key value RDDs
// example word count in spark
val wordCountInput = sc.textFile("/path/to/textFile")
val word = wordCountInput.flatMap(x => x.split(" "))
val wordCount = words.map(x => (x, 1).reduceByKey((x, y) => x + y)



