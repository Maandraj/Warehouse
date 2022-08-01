package com.maandraj.catalog.ui.catalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maandraj.catalog.R
import com.maandraj.catalog.data.model.Product
import com.maandraj.catalog.databinding.ItemProductBinding


class CatalogAdapter(private val onClickItem: (product: Product) -> Unit) :
    ListAdapter<Product, CatalogAdapter.CatalogViewHolder>(CatalogDiffUtil) {
    inner class CatalogViewHolder(private val item: ItemProductBinding) :
        RecyclerView.ViewHolder(item.root) {
        init {
            item.root.setOnClickListener {
                val product = this@CatalogAdapter.getItem(layoutPosition)
                onClickItem.invoke(product)
            }
        }

        fun bind(product: Product) {
            with(item) {
                tvNameProduct.text = product.name
                tvCountProduct.text = product.count.toString()
                tvCodeProduct.text =
                    itemView.context.getString(R.string.item_product_code, product.code.toString())
                tvPriceProduct.text = itemView.context.getString(R.string.item_product_price,
                    product.price.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder =
        CatalogViewHolder(
            item = ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}