package com.codedynamic.clinica.utilidades;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UtilidadHora {
    private static final String HORA_PATRON = "H:m:s";
    private static final DateTimeFormatter HORA_FORMATEADOR = DateTimeFormatter.ofPattern(HORA_PATRON);

    public static String formatoHora(LocalTime hora) {
        if (hora == null) {
            return null;
        }
        return HORA_FORMATEADOR.format(hora);
    }

    public static LocalTime analizarHora(String horaString) {
        try {
            return HORA_FORMATEADOR.parse(horaString, LocalTime::from);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static boolean horaValida(String horaString) {return UtilidadHora.analizarHora(horaString) != null;}
}
