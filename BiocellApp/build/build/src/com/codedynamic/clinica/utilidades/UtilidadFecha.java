package com.codedynamic.clinica.utilidades;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilidadFecha {
	private static final String FECHA_PATRON = "dd-MM-yyyy";
    private static final DateTimeFormatter FECHA_FORMATEADOR = DateTimeFormatter.ofPattern(FECHA_PATRON);

    public static String formato(LocalDate fecha) {
        if (fecha == null) {
            return  null;
        }
        return FECHA_FORMATEADOR.format(fecha);
    }

    public static LocalDate analizar(String fechaString) {
        try {
            return FECHA_FORMATEADOR.parse(fechaString, LocalDate::from);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static boolean fechaValida(String fechaString) {
        return UtilidadFecha.analizar(fechaString) != null;
    }
}
