package models

import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class GallineroLock (private val maxHuevos:Int):MonitorProducerConsumer<Huevos>{

   val  gallinero :MutableList<Huevos> = mutableListOf()

    private val lock : ReentrantLock = ReentrantLock()
    private val gallineroEmptyCondicitions : Condition = lock.newCondition()
    private val gallineroFullCondicitions : Condition = lock.newCondition()

    private var huevosDisponible = false


  override  fun get():Huevos{
        lock.withLock {
            while (gallinero.size == 0) {
                try {
                    println("esperando a que haya huevos ")
                    gallineroEmptyCondicitions.await();
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            val huevo = gallinero.removeFirst() // Saco el primero
            println("\t*El gallinero tiene: " + size())//gallinero.size
            huevosDisponible = false

            gallineroFullCondicitions.signalAll()
            return huevo // Devolvemos el huevos
        }
        }

   override fun put(item:Huevos) {
        lock.withLock {
            while (size() == maxHuevos) {
                try {
                    gallineroFullCondicitions.await()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            gallinero.add(item)
            println("\t\tEl gallinero tiene: " + size())
            huevosDisponible = true
            // Ya hay cantidad a consumir, activamos
            gallineroEmptyCondicitions.signalAll();
        }
    }

    fun size(): Int {
        lock.withLock {

            return gallinero.size


        }




    }

    }





