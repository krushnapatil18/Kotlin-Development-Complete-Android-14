fun main() {
    val child1 = Offspring("Alice", 20)
    val child2 = Secondary("Bob", "Painting")
    val child3 = Tertiary("Charlie", 5)

    child1.display()
    child2.display()
    child3.display()

    // Using interfaces
    val multiTalentedPerson = object : BaseClass("David"), Singer, Archery {
        override fun display() {
            println("This is a multi-talented person: $name")
        }

        override fun sing() {
            println("$name is singing!")
        }

        override fun shootArrow() {
            println("$name is shooting an arrow!")
        }
    }

    multiTalentedPerson.display()
    multiTalentedPerson.sing()
    multiTalentedPerson.shootArrow()
}
