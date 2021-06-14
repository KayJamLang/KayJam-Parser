package kayjam.parser

fun<T> Iterable<T?>.lastNotNull(): T? {
    var v: T? = null
    for (value in this)
        if(value!=null) v = value

    return v
}