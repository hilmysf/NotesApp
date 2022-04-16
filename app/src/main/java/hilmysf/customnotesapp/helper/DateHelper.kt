package hilmysf.customnotesapp.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("hh:mm a")
        val date = Date()
        return dateFormat.format(date)
    }
}