package models

class Granja(
    private var id: String,
    private var gallinero: MonitorProducerConsumer<Huevos>,
    private var cant: Int,
    private var ms: Int,
    private var prioridad: Int
) : Thread() {

    override fun run() {
        priority = prioridad
        for (i in 1 until cant + 1) {
            val huevo = Huevos(i, idGallina = id)
            println("Granja " + id + "-> Produzco huevo: " + i + ": " + huevo.id + " de " + huevo.yemas + " yemas")
            gallinero.put(huevo)
        }
        try {
            sleep(ms.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}