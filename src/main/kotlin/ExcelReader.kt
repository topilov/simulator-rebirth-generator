package me.topilov

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.BufferedInputStream
import java.net.URL

/**
 * @created 16/11/2024 - 12:18 AM
 * @author asyncTopilov ~ loli <3
 **/
class ExcelReader(
    private val url: String,
) {

    fun readSheet(sheetIndex: Int): XSSFSheet {
        val connection = URL(url).openStream()
        val bufferedInputStream = BufferedInputStream(connection)
        val workbook = XSSFWorkbook(bufferedInputStream)
        val sheet = workbook.getSheetAt(sheetIndex)

        return sheet
    }
}