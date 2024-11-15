package me.topilov

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.poi.xssf.usermodel.XSSFSheet
import java.io.File

/**
 * @created 16/11/2024 - 12:10 AM
 * @author asyncTopilov ~ loli <3
 **/
fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Specify path to config")
        return
    }

    val configFilePath = args[0]
    val config = loadConfig(configFilePath)

    val sheet = ExcelReader(config.excelTableUrl).readSheet(config.sheet)

    val rebirths = getRebirthsMap(sheet, config.range.from, config.range.to)

    rebirths.forEach { (rebirth, price) ->
        println("""
"$rebirth": {
    "price": $price,
    "level": ${config.requiredLevel},
    "boosters": {
${formatBoosters(config.boosters)}
    }
},
        """.trimIndent())
    }
}

private fun getRebirthsMap(sheet: XSSFSheet, from: Int, to: Int): Map<Int, String> {
    val rebirths = hashMapOf<Int, String>()

    for (rowIndex in from..to) {
        val row = sheet.getRow(rowIndex)
        val rebirth = row.getCell(0).numericCellValue.toInt()
        val price = row.getCell(1).numericCellValue.toBigDecimal().toPlainString()

        rebirths[rebirth] = price
    }

    return rebirths
}

private fun formatBoosters(boosters: Map<String, Double>): String {
    val size = boosters.size
    val builder = StringBuilder()

    boosters.entries.forEachIndexed { index, (booster, boost) ->
        builder.append("        \"$booster\": $boost")
        if (index + 1 != size) builder.appendLine(",")
    }

    return builder.toString()
}

private fun loadConfig(filePath: String): RebirthConfig {
    val objectMapper = ObjectMapper()
    return objectMapper.readValue(File(filePath))
}