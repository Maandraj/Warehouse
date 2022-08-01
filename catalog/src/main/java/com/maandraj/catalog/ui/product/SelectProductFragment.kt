package com.maandraj.catalog.ui.product

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maandraj.catalog.R
import com.maandraj.catalog.data.model.Product
import com.maandraj.catalog.databinding.FragmentSelectProductBinding
import com.maandraj.catalog.di.product.SelectProductComponent
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Lazy
import javax.inject.Inject

class SelectProductFragment : Fragment(R.layout.fragment_select_product) {

    private val selectProductArgs: SelectProductFragmentArgs by navArgs()

    @Inject
    lateinit var moshi: Moshi

    @Inject
    lateinit var factory: Lazy<SelectProductVM.Factory>

    private val viewModel: SelectProductComponent by viewModels {
        factory.get()
    }
    private val binding: FragmentSelectProductBinding by viewBinding(FragmentSelectProductBinding::bind)

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<SelectProductVMComponentViewModel>().selectProductComponent.inject(
            this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jsonAdapter: JsonAdapter<Product> = moshi.adapter(Product::class.java)
        val product = jsonAdapter.fromJson(selectProductArgs.product)
        val price: Float = product?.price ?: 0.0f
        val count: Float = product?.count ?: 0.0f
        val retailAmount = price * count
        with(binding) {
            tvCodeProduct.text = product?.code.toString()
            tvNameProduct.text = product?.name.toString()
            tvCountProduct.text = count.toString()
            tvPriceProduct.text = getString(R.string.item_product_price, product?.price.toString())
            tvRetailAmountProduct.text =
                getString(R.string.item_product_price, retailAmount.toString())
            if (product?.alcohol?.type != null) {
                tvAlcoholProduct.text = getString(R.string.product_alcohol_yes)
                tvAlcoholPercentProduct.text = getString(R.string.select_product_alcohol_percent,
                    product.alcohol.percent.toString())
                alcoholVisible(View.VISIBLE)
            } else {
                alcoholVisible(View.GONE)
                tvAlcoholProduct.text = getString(R.string.product_alcohol_no)
            }
        }
    }

    private fun alcoholVisible(visible: Int) {
        with(binding) {
            tvAlcoholPercentProduct.visibility = visible
            tvAlcoholPercentProductText.visibility = visible
        }
    }
}