package models

class Granjero(
    private var gallinero: MonitorProducerConsumer<Huevos>,
    private val numLotes: Int,
    private val numHuevosPorLote: Int


) : Thread() {
    override fun run() {
        println("EMPEZAMOS A CURRAR")

        val misHuevos = mutableListOf<Huevos>()

        for (i in 0 until numLotes) {//Con este for vamos rellenamos un lote , y llegamos al numLotes que es el Maximo de Lotes que quiero hacer

            for (k in 0 until numHuevosPorLote) { //CON ESTE FOR RELLENAMOS EL LOTE YA QUE VAMOS DE 0 al 12 (12 ES EL MAXIMO NUMERO EN UN LOTE) Y LOS VA AÑADIENDO

                val huevo = gallinero.get() //AQUI SE ESTA CONSUMIENDO
                huevo.lote = i // LE asigno el lote
                misHuevos.add(huevo)
                var timeout = huevo.consumirHuevos()

                Thread.sleep(timeout.toLong())
                println("Granjero-> Paquete Lote: " + i + " empaqueto huevos: " + huevo.id + " " + huevo.yemas + "yemas de: " + huevo.idGallina)

            }
            imprimirLote(misHuevos)
            misHuevos.clear()
        }


    }

    private fun imprimirLote(misHuevos: MutableList<Huevos>) {
        println("\t->Imprimiendo Lote")
        misHuevos.forEach {
            println("\t->$it")
        }
    }


}




