package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.json.simple.JSONValue.toJSONString;

public class Log {

    private BufferedWriter bufferedWriter;
    private static final String nombre= "logger.json";

    public Log(boolean reset) throws IOException {
        this.open(!reset);
    }

    private void open(boolean append) throws IOException {
        this.bufferedWriter= new BufferedWriter(new FileWriter(this.nombre,append));
    }

    public void addline(String linea) throws IOException {
        SimpleDateFormat fecha= new SimpleDateFormat("DD/MM/YYYY HH/MM/SS");
        //EN FECHA QUEDA GUARDADO LA FECHA DE INSERCIÓN EN EL LOG
        String formatoFecha= fecha.format(new Date());
        this.bufferedWriter.write(toJSONString("El cambio fue realizado: "+formatoFecha+" "+linea+"\n"));
        //en "linea" se supone que estan todos los datos que solicita el enunciado
        this.open(true);
        this.close();
    }

    public void resetLog() throws IOException {
        this.open(false);
        this.close();
    }

    public void close() throws IOException {
        this.bufferedWriter.close();
    }
}