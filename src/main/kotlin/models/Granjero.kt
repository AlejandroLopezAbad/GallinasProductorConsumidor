package models

class Granjero(
    private var gallinero:MonitorProducerConsumer<Huevos>,
    private val tam: Int

) :Thread(){
    override fun run(){
        println("EMPEZAMOS A CURRAR")
        Thread.sleep(1000L)
        val misHuevos= mutableListOf<Huevos>()

                for (i in 1 until 30) {
                    for (k in 0 until tam) {
                        val huevos = gallinero.get()
                        huevos.lote = i // LE asigno el lote
                        misHuevos.add(huevos)
                        var timeout = huevos.consumirHuevos()
                        Thread.sleep(timeout.toLong())
                        println("Granjero-> Paquete Lote: " + i + " empaqueto huevos: " + huevos.id + " " + huevos.yemas + "yemas de: " + huevos.idGallina)

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




