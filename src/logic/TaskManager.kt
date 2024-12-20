package logic

import kotlin.Exception

fun handleStatus(status: Status) {
    when (status) {
        is Status.Success<*> -> {
            val (first, second) = status.data
            if (first != null) {
                println(first)
            }
            if (second != null) {
                println(second)
            }
        }

        is Status.Error -> "Houve um erro! ${status.exception.message}"
    }

}


class TaskManager(private val taskList: MutableList<Task> = mutableListOf()) {
    fun addTask(): Status {
        val name = generateSequence {
            print("Tarefa: ")
            readlnOrNull()
        }.first { it.isNotBlank() }

        print("Descrição: (opcional)")
        val description = readlnOrNull()

        taskList.add(Task(name, description, id = id))
        return Status.Success(Pair("Produto adicionado!", null))
    }

    fun listTasks(): Status {
        val qtyOfCompleted = taskList.filter { it.isComplete }.size
        return Status.Success(Pair("[$qtyOfCompleted/${taskList.size}]", taskList))
    }

    fun searchTask(): Status {
        val id = generateSequence {
            print("Id: ")
            readlnOrNull()
        }.first { it.isNotBlank() }


        taskList.find { it.id == id }?.let {
            return Status.Success(Pair("Task encontrada!", it))
        }
        return Status.Error(Exception("Task não encontrada!"))
    }

    fun updateStatus(): Status {
        val id = generateSequence {
            print("Id: ")
            readlnOrNull()
        }.first { it.isNotBlank() }

        taskList.find { it.id == id }?.let {
            it.isComplete = !it.isComplete
            return Status.Success(Pair("Task atualizada!", null))
        }

        return Status.Error(Exception("Task não encontrada para ser atualizada!"))
    }

    fun deleteTask(): Status {
        taskList.find { it.id == id }?.let {
            taskList.remove(it)
            return Status.Success(Pair("Task deletada!", null))
        }
        return Status.Error(Exception("Task não encontrada para ser deletada!"))
    }

    fun filterTask(): Status {
        val id = generateSequence {
            println("Filtrar tasks por: [1/0] ")
            readlnOrNull()?.toIntOrNull()
        }.first { it == 1 || it == 0 }

        taskList.filter { it.isComplete == (id == 1) }.let {
            return Status.Success(Pair(null, it))
        }
    }

    companion object {
        val id = "${System.currentTimeMillis()}"
    }
}


fun TaskManager.result(resolve: () -> Status) {
    handleStatus(resolve())
}
