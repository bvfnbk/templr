package com.github.bvfnbk.templr.api.model

/**
 * @author bvfnbk
 */
sealed class Model<T> {
    abstract fun toRawModel(): T
}

data class ListModel(val content: List<Model<*>>) : Model<List<*>>(), List<Model<*>> by content {
    override fun toRawModel(): List<*> = map { it.toRawModel() }
}

data class ObjectModel(val content: Map<String, Model<*>>) : Model<Map<String,*>>(), Map<String, Model<*>> by content {
    override fun toRawModel(): Map<String, *> = content.entries.map { (key, value) -> Pair(key, value.toRawModel()) }.toMap()
}

data class StringModel(private val content: String) : Model<String>() {
    override fun toRawModel(): String = content
}

data class NumberModel(private val content: Number) : Model<Number>() {
    override fun toRawModel(): Number = content
}

data class BooleanModel(private val content: Boolean) : Model<Boolean>() {
    override fun toRawModel(): Boolean = content
}
