package com.ryanl.stravasramapp.api.models

data class Segment(
    val id: Long,
    val name: String,
    val climb_category: Int,
    val climb_category_desc: String,
    val avg_grade: Float,
    val start_latlng: List<Float>,
    val end_latlng: List<Float>,
    val elev_difference: Float,
    val distance: Float,
    val points: String
)
