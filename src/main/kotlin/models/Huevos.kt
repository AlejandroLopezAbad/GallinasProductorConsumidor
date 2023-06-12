package models

data class Huevos(
    val id :Int,
    val yemas: Int = (1..3).random(),
    val idGallina:String
) {

    var lote:Int=0

    fun consumirHuevos():Int{
        return yemas

    }
    override fun toString(): String {
        return "Huevos(id=$id, yemas=$yemas, idGallina='$idGallina', lote=$lote)"
    }

}