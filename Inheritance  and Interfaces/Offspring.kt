class Offspring(name: String, val age: Int) : BaseClass(name) {
    override fun display() {
        println("This is the Offspring class. Name: $name, Age: $age")
    }
}
