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
        for (i in 0 until cant ) { //hace huevos entre 1 y la cant , que es en la fabrica el max huevos , y crea ese numero de huevos
            val huevo = Huevos(i+1, idGallina = id)
            println("Granja " + id + "-> Produzco huevo: " + i + ": " + huevo.id + " de " + huevo.yemas + " yemas")
            gallinero.put(huevo)
        }

    }
}