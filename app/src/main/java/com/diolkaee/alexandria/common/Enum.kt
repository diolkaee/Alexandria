package com.diolkaee.alexandria.common

inline fun <reified T : Enum<T>> T.next(): T =
    if (this.ordinal >= enumValues<T>().size - 1)
        enumValues<T>()[0]
    else enumValues<T>()[this.ordinal + 1]

