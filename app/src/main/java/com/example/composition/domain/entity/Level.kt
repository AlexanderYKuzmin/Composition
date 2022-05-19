package com.example.composition.domain.entity

import android.os.Parcel
import android.os.Parcelable

enum class Level(val id: Int) : Parcelable {

    TEST(1), EASY(2), NORMAL(3), HARD(4);

    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Level> {
        override fun createFromParcel(parcel: Parcel): Level {
            return values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<Level?> {
            return arrayOfNulls(size)
        }
    }
}