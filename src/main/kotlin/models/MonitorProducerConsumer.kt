package models

interface MonitorProducerConsumer<T> {
    fun get(): T
    fun put(item: T)
}