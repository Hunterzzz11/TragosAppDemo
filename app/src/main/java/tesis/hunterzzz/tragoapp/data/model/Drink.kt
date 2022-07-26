package tesis.hunterzzz.tragoapp.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName

@VersionedParcelize
data class Drink(
    @SerializedName("idDrink")
    val cocktailId: String = "",
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imagen)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    companion object CREATOR : Parcelable.Creator<Drink> {
        override fun createFromParcel(parcel: Parcel): Drink {
            return Drink(parcel)
        }

        override fun newArray(size: Int): Array<Drink?> {
            return arrayOfNulls(size)
        }
    }
}

data class DrinkList(
    @SerializedName("drinks")
    val drinkList: List<Drink>
)

@Entity(tableName = "tragosEntity")
data class DrinkEntity(
    @PrimaryKey
    val tragoId:String,
    @ColumnInfo(name = "trago_imagen") val imagen: String,
    @ColumnInfo(name = "trago_nombre") val nombre: String,
    @ColumnInfo(name = "trago_descripcion") val descripcion: String,
    @ColumnInfo(name = "trago_has_alcohol") val hasAlcohol: String

)

fun Drink.asFavoriteEntity(): DrinkEntity =
    DrinkEntity(this.cocktailId, this.imagen, this.nombre, this.descripcion, this.hasAlcohol)