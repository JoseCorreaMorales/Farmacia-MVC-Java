package farmacia.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fechas {
    //funcion para convertir LocalDate a String
    public static String Conversion(LocalDate fecha){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return formato.format(fecha);
        }catch (Exception e){
            return "";
        }
    }
    //funcion para convertir String a LocalDate
    public static LocalDate Conversion(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return formato.parse(fecha, LocalDate::from);
    }
}
