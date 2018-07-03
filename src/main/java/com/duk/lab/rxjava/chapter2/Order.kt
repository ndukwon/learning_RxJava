package com.duk.lab.rxjava.chapter2

class Order(id: String) {
    val id = id

    override fun toString(): String {
        return "Order ID: $id"
    }
}