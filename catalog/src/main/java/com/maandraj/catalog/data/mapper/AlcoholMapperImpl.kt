package com.maandraj.catalog.data.mapper

import com.maandraj.catalog.data.mapper.enum.*
import com.maandraj.catalog.data.model.Alcohol
import javax.inject.Inject

class AlcoholMapperImpl @Inject constructor() : AlcoholMapper<ArrayList<String>, Alcohol?> {
    override fun map(
        res: ArrayList<String>,
        filterIds: Array<AlcoholAds>,
        codeProduct: Long,
        regex: Regex,
    ): Alcohol? {
        try {
            var alcohol = Alcohol(null, 0.0f)
            res.forEach { attr ->
                val groups = regex.find(attr)?.groups
                val code = groups?.get(CODE_ID)?.value?.toLong() ?: return null
                if (code != codeProduct) return null
                val attrId = groups[ATTR_ID]?.value?.toInt()
                val isFindId = filterIds.any { filter -> filter.id == attrId }
                if (isFindId) {
                    when (attrId) {
                        AlcoholAds.TYPE.id -> {
                            val type = groups[TYPE_ID]?.value?.toInt()
                            alcohol = alcohol.copy(type = type)
                        }
                        AlcoholAds.PERCENT.id -> {
                            val percent = groups[TYPE_ID]?.value?.toFloat()
                            alcohol = alcohol.copy(percent = percent ?: 0.0f)
                        }
                    }
                }
            }
            if (alcohol.type == null) return null
            return alcohol
        } catch (ex: Exception) {
            return null
        }
    }
}