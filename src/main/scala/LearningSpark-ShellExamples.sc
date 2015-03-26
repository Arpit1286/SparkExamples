import org.apache.spark.{SparkConf, SparkContext}

val conf = new SparkConf().setAppName("LS-ShellExamples")
val sc = new SparkContext(conf)
// creating RDD
val lines = sc.textFile("/<path to>/README.md")  // create a RDD of strings with text file

val pythonLines = lines.filter(_.contains("Python")) // transformation filtering lines containing python


// actions are when computations are called RDD. Spark computes RDD in lazy fashion, which is only computes them when an action is called
pythonLines.count()  // returns 3 in this case
pythonLines.first()  // ... String = high-level APIs in Scala, Java, and Python, and an optimized engine that