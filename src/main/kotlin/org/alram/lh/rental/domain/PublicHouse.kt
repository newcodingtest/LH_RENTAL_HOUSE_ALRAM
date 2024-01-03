package org.alram.lh.rental.domain

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class PublicHouse(
    private val city: String,
    private val title: String,
    private val staDT: String,
    private val endDT: String,
    private var url: String
                      )