import models.GallineroLock
import models.Granja
import models.Granjero
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object FabricaHuevos {
    val MAX_HUEVOS = 25
    val INTER_GRANJA = 1000
    val TAM_LOTE = 6
    val PRIO1 = 4
    val PRIO2 = 8

    fun runFabricaLock() {
        println("Huevos con Monitor Lock")
        val gallinero = GallineroLock(MAX_HUEVOS)
        val gr1 = Granja("Alexitto", gallinero, MAX_HUEVOS, INTER_GRANJA, PRIO1)
        val gr2 = Granja("GOKU", gallinero, MAX_HUEVOS, INTER_GRANJA, PRIO2)
        val em = Granjero(gallinero,TAM_LOTE)



        val pool = Executors.newFixedThreadPool(3)
        pool.execute(gr1)
        pool.execute(gr2)
        pool.execute(em)
        pool.shutdown()




        pool.awaitTermination(1L, TimeUnit.SECONDS)



        // Esperamos a que acaben


    }
}