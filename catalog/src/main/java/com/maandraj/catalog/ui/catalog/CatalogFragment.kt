package com.maandraj.catalog.ui.catalog

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maandraj.catalog.R
import com.maandraj.catalog.data.model.Product
import com.maandraj.catalog.databinding.FragmentCatalogBinding
import com.maandraj.catalog.ui.catalog.adapter.CatalogAdapter
import com.maandraj.catalog.utils.snackbar
import com.maandraj.core.result.InternetError
import com.maandraj.core.result.ProductsGetError
import com.maandraj.core.result.ProductsNotFound
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Lazy
import javax.inject.Inject

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    @Inject
    lateinit var factory: Lazy<CatalogViewModel.Factory>

    @Inject
    lateinit var moshi: Moshi

    private val viewModel: CatalogViewModel by viewModels {
        factory.get()
    }
    private val binding: FragmentCatalogBinding by viewBinding(FragmentCatalogBinding::bind)

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CatalogComponentViewModel>().catalogComponent.inject(this)
        super.onAttach(context)
    }

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        CatalogAdapter() { product ->
            val jsonAdapter: JsonAdapter<Product> = moshi.adapter(Product::class.java)
            val jsonProduct = jsonAdapter.toJson(product)
            findNavController().navigate(CatalogFragmentDirections.actionProductsFragmentToSelectProductFragment(
                jsonProduct))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCatalog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvCatalog.adapter = adapter
            rvCatalog.layoutManager = LinearLayoutManager(requireContext())
            if (adapter.itemCount == 0)
            viewModel.getCatalog()
        }
        setupViewModel()
        setListeners()
    }

    private fun setListeners() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getCatalog()
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun setupViewModel() {
        viewModel.catalog.observe(viewLifecycleOwner) {
            adapter.submitList(it.products)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.includeProgress.root.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.snackbarError.observe(viewLifecycleOwner) { error ->
            when (error) {
                ProductsNotFound -> {
                    snackbar(binding.root, getString(R.string.error_products_not_found))
                }
                ProductsGetError->{
                    snackbar(binding.root, getString(R.string.error_get_products))
                }
                InternetError -> {
                    snackbar(binding.root, getString(R.string.error_internet_connection))
                }

            }
        }
    }
}