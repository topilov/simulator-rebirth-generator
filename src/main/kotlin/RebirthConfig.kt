package me.topilov

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @created 16/11/2024 - 1:04 AM
 * @author asyncTopilov ~ loli <3
 **/
data class RebirthConfig(
    @JsonProperty("excel_table_url") val excelTableUrl: String,
    @JsonProperty("sheet") val sheet: Int,
    @JsonProperty("range") val range: RebirthRangeConfigEntry,
    @JsonProperty("required_level") val requiredLevel: Int,
    @JsonProperty("boosters") val boosters: Map<String, Double>
)

data class RebirthRangeConfigEntry(
    @JsonProperty("from") val from: Int,
    @JsonProperty("to") val to: Int,
)
