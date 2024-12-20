import logic.*

fun initialMenu() {
    println("-".repeat(20))
    println("1 - Adicionar nova tarefa à lista.")
    println("2 - Listar todas as tarefas.")
    println("3 - Buscar tarefa por ID.")
    println("4 - Atualizar o status de uma tarefa pelo ID.")
    println("5 - Filtrar uma tarefa pelos status.")
    println("6 - Excluir uma tarefa pelo ID.")
    println("0 - Sair.")
    println("-".repeat(10))
}


fun main() {
    val manager = TaskManager()
    var response: Int;
    do {
        initialMenu()
        response = readlnOrNull()?.toIntOrNull() ?: continue
        when (response) {
            0 -> break
            1 -> manager.result { manager.addTask() }
            2 -> manager.result { manager.listTasks() }
            3 -> manager.result { manager.searchTask() }
            4 -> manager.result { manager.updateStatus() }
            5 -> manager.result { manager.filterTask() }
            6 -> manager.result { manager.deleteTask() }
            else -> {
                println("Comando não encontrado...")
                continue
            }
        }
    } while (true)


}