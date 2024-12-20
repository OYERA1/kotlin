package logic

typealias ReturnResponse<A> = Pair<String?, A?>

sealed class Status {
    data class Success<T : Any?>(val data: ReturnResponse<T?>) : Status()
    data class Error(val exception: Exception) : Status()
}