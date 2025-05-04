package com.example.housecall_takehomeassissment.data.mapper

import com.example.housecall_takehomeassissment.data.localstorage.entity.DrugEntity
import com.example.housecall_takehomeassissment.data.models.ConceptProperty

fun ConceptProperty.toEntity(userId: String) = rxcui?.let {
    DrugEntity(
        rxcui = it,
        userId = userId,
        name = name,
        synonym = synonym,
        tty = tty,
        language = language,
        suppress = suppress,
        umlscui = umlscui
    )
}

fun DrugEntity.toConceptProperty(): ConceptProperty {
    return ConceptProperty(
        rxcui = rxcui,
        name = name,
        synonym = synonym,
        tty = tty,
        language = language,
        suppress = suppress,
        umlscui = umlscui
    )
}