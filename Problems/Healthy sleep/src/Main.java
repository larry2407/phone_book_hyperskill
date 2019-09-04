import java.util.*

fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        val a = scanner.nextInt()
        val b = scanner.nextInt()
        val h = scanner.nextInt()
        println(determineSleepHealthiness(a, b, h));
        }

        fun determineSleepHealthiness(a: Int, b: Int, h: Int): String {
        val output : String
        if(h>=a && h<b){
        output="Normal"
        }else if(h<a){
        output="Deficiency"
        }else{
        output="Excess"
        }
        return output
        }