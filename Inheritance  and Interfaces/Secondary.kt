class Secondary(name: String, val skill: String) : BaseClass(name) {
    override fun display() {
        println("This is the Secondary class. Name: $name, Skill: $skill")
    }
}
