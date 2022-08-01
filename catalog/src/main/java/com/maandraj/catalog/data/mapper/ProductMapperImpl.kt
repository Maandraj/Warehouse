package com.maandraj.catalog.data.mapper

import com.maandraj.catalog.data.mapper.enum.ATTR_ID
import com.maandraj.catalog.data.mapper.enum.AlcoholAds
import com.maandraj.catalog.data.mapper.enum.CODE_ID
import com.maandraj.catalog.data.mapper.list.ProductList
import com.maandraj.catalog.data.mapper.list.ResponseList
import com.maandraj.catalog.data.model.Product
import com.maandraj.core.mapper.BaseMapper
import javax.inject.Inject

/**
 * TODO Можно еще поиграться с SOLID'ностью и ООП
 */
class ProductMapperImpl @Inject constructor(
    private val alcoholMapper: AlcoholMapperImpl,
) : BaseMapper<List<String>, ProductList> {
    override fun map(res: List<String>): ProductList {
        val products = ProductList()
        val filterArray = arrayOf(AlcoholAds.PERCENT, AlcoholAds.TYPE)
        val regex = "<goods_attr\\sid=\"(\\d*)\"\\sattr_id=\"(\\d*)\">(.*)</goods_attr>".toRegex()
        res.forEach { line ->
            if (!line.startsWith(START_PREF)) {
                val items = line.split(";")
                val responseList = ResponseList()
                responseList.addAll(items)
                products.add(Product(code = responseList.getCode(),
                    barcode = responseList.getBarcode(),
                    name = responseList.getName(),
                    receiptText = responseList.getReceiptText(),
                    price = responseList.getPrice(),
                    count = responseList.getCount(),
                    null))
            }
        }
        products.forEach product@{ _product ->
            val arrayAttrs = arrayListOf<String>()
            run lit@{
                res.forEach res@{ line ->
                    if (line.startsWith(START_PREF)) {
                        val groups = regex.find(line)?.groups
                        val codeAttr = groups?.get(CODE_ID)?.value?.toLong() ?: -1L
                        if (_product.compareCode(codeAttr)) {
                            arrayAttrs.add(line)
                            return@res
                        }
                    }
                    if (arrayAttrs.isNotEmpty()) {
                        val alcohol = alcoholMapper.map(
                            res = arrayAttrs,
                            codeProduct = _product.code,
                            filterIds = filterArray,
                            regex = regex)
                        products.addAlcohol(_product, alcohol = alcohol)
                        return@lit
                    }
                }
            }
        }
        return products
    }
}

private const val START_PREF = "<goods_attr"