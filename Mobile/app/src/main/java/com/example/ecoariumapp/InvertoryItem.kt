package com.example.ecoariumapp

class InvertoryItem(var itemImage: String, var itemName: String, var itemMessage: String) {
    fun getImage(): String {
        return itemImage
    }

    fun getName(): String {
        return itemName
    }

    fun getMessage(): String {
        return itemMessage
    }
}
