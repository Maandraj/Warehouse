package com.maandraj.catalog.ui.catalog.adapter

import androidx.recyclerview.widget.DiffUtil
import com.maandraj.catalog.data.model.Product

object CatalogDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}