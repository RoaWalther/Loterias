/*
 * Utils.java
 *
 * Created on 21 de abril de 2008, 06:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.example.walther.gelsaaplication.comunes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import com.example.walther.gelsaaplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

/**
 *
 * Created by Walther Roa
 */
public class Utils {

    /** Creates a new instance of Utils */
    public Utils() {
    }

    /** Este metodo realiza un retardo en milisegundos                          
     * @
     */
    public static void delay(long milisecs){
        long t0 = System.currentTimeMillis() + milisecs;

        while(System.currentTimeMillis() < t0);

    }

    /** Este metodo convierte un array de Bytes a un objeto tipo String                          
     * @retorna la cadena tipo String
     */
    public static String uninterpret_ASCII(byte[] rawData, int offset, int length){
        char[] ret = new char[length];
        for (int i = 0; i < length; i++)
        {
            ret[i] = (char)rawData[offset + i];
        }
        return new String(ret);
    }

    /*******************************************************************************
     Function:   replaceChar
     Description: reemplazar un caracter por otro en un buffer
     Input:    *outputData = buffer a modificar
     antiguo = caracter que se reemplaza
     nuevo= caracter con el que se reemplaza
     len= longitud a recorrer en el buffer
     Return:   Nothing
     *******************************************************************************/
    public static void replaceChar(byte[] outputData, byte antiguo , byte nuevo, int len){
        int i;

        for(i=0; i<len; i++)
            if(outputData[i] == antiguo)
                outputData[i] = nuevo;
    }

    /** Este metodo separa un array de bytes en tokens dependiendo del caracter recibido  
     * @Retorna un array de cadenas con los tokens separados
     */
    public static String[] tokenizer(byte[] array, int offset, int length, byte separator, int numTokens){
        String[] tokens = new String[numTokens];
        int i, len_tok, j=0;

        while(j < numTokens){
            len_tok=0;
            for(i=offset; i<length; i++){
                if(array[i] == separator){

                    tokens[j]= uninterpret_ASCII(array, offset, len_tok);

                    offset += (len_tok + 1);
                    j++;
                    break;
                }
                len_tok++;
            }
        }

        return tokens;
    }

    /** Este metodo separa un array de bytes en tokens dependiendo del tama�o (LV)  
     * @Retorna un array de cadenas con los tokens separados
     */
    public static String[] tokenizer(byte[] array, int offset, int numTokens){
        String[] tokens = new String[numTokens];
        int i;

        for(i=0; i<numTokens; i++){
            tokens[i]= uninterpret_ASCII(array, offset + 1, array[offset]);
            offset += (array[offset] + 1);
        }

        return tokens;
    }

    /** Este metodo obtiene la fecha y hora actual
     *  en el formato YYYYMMDDhhmmss  
     * @Retorna una cadena con el formato de fecha y hora
     */
    public static String getDateTime(){
        String day, month, year, hour, minute, second;
        TimeZone tz = TimeZone.getTimeZone("GMT-5");
        Calendar actualDateTime = Calendar.getInstance(tz);

        year= String.valueOf(actualDateTime.get(actualDateTime.YEAR));
        month= String.valueOf(actualDateTime.get(actualDateTime.MONTH) + 1);
        day= String.valueOf(actualDateTime.get(actualDateTime.DAY_OF_MONTH));
        hour= String.valueOf(actualDateTime.get(actualDateTime.HOUR_OF_DAY));
        minute= String.valueOf(actualDateTime.get(actualDateTime.MINUTE));
        second= String.valueOf(actualDateTime.get(actualDateTime.SECOND));

        if((actualDateTime.get(actualDateTime.MONTH) + 1) < 10)
            month= "0" + month ;
        if(actualDateTime.get(actualDateTime.DAY_OF_MONTH) < 10)
            day= "0" + day ;
        if(actualDateTime.get(actualDateTime.HOUR_OF_DAY) < 10)
            hour= "0" + hour ;
        if(actualDateTime.get(actualDateTime.MINUTE) < 10)
            minute= "0" + minute ;
        if(actualDateTime.get(actualDateTime.SECOND) < 10)
            second= "0" + second ;

        //year = year.substring(2,4);

        String dateTime = year + month + day + hour + minute + second;
        return dateTime;
    }

    /*******************************************************************************
     Function        : getDate
     Description     : Recibe la fecha en formato Dia/mes/año (20/01/18)
     Return          : Fecha
     ******************************************************************************/
    public static String getDate(){
        String day, month, year, hour, minute, second;
        TimeZone tz = TimeZone.getTimeZone("GMT-5");
        Calendar actualDateTime = Calendar.getInstance(tz);

        year= String.valueOf(actualDateTime.get(actualDateTime.YEAR));
        month= String.valueOf(actualDateTime.get(actualDateTime.MONTH) + 1);
        day= String.valueOf(actualDateTime.get(actualDateTime.DAY_OF_MONTH));
        hour= String.valueOf(actualDateTime.get(actualDateTime.HOUR_OF_DAY));
        minute= String.valueOf(actualDateTime.get(actualDateTime.MINUTE));
        second= String.valueOf(actualDateTime.get(actualDateTime.SECOND));

        if((actualDateTime.get(actualDateTime.MONTH) + 1) < 10)
            month= "0" + month ;
        if(actualDateTime.get(actualDateTime.DAY_OF_MONTH) < 10)
            day= "0" + day ;
        if(actualDateTime.get(actualDateTime.HOUR_OF_DAY) < 10)
            hour= "0" + hour ;
        if(actualDateTime.get(actualDateTime.MINUTE) < 10)
            minute= "0" + minute ;
        if(actualDateTime.get(actualDateTime.SECOND) < 10)
            second= "0" + second ;

        year = year.substring(2,4);

        //String dateTime = year + month + day + hour + minute + second;
        String dateTime = day + "/" + month + "/" + year;
        return dateTime;
    }

    /*******************************************************************************
     Function        : getTime
     Description     : Recibe la hora en formato HH:MM (Hora:Minuto)
     Return          : Hora
     ******************************************************************************/
    public static String getTime(){

        String time;
        String day, month, year, hour, minute, second, ampm;
        TimeZone tz = TimeZone.getTimeZone("GMT-5");
        Calendar actualDateTime = Calendar.getInstance(tz);

        hour= String.valueOf(actualDateTime.get(actualDateTime.HOUR_OF_DAY));
        minute= String.valueOf(actualDateTime.get(actualDateTime.MINUTE));


        if(actualDateTime.get(actualDateTime.HOUR_OF_DAY) < 10)
            hour= "0" + hour ;
        if(actualDateTime.get(actualDateTime.MINUTE) < 10)
            minute= "0" + minute ;

        time = hour + ":" + minute;

        return time;
    }

    /*******************************************************************************
     Function        : obtenerMesLetras
     Description     : Recibe el mes en entero y lo convierte a cadena
     Input           : mes = mes en entero a convertir
     Return          : Cadena con fecha
     ******************************************************************************/
    public static String obtenerMesLetras(int mes){

        switch (mes){
            case 1:
                return "Ene";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dic";
            default:
                return "";
        }

    }

    /** Obtiene un numero (int) aleatorio en un inervalo determinado
     *  int: 32 bits [ -2.147.483.648  a  2.147.483.647 ]
     * @desde = Limite inferior  
     * @hasta = Limite superior
     * @Retorna el numero aleatorio
     */
    public static int getRandomInt(int desde , int hasta){
        Random azar;
        azar = new Random(System.currentTimeMillis());
        return desde + Math.abs(azar.nextInt()) % (hasta - desde + 1);
    }


    /** Obtiene un numero (long) aleatorio en un inervalo determinado
     *  long: 64 bits [ -9.223.372.036.854.775.808  a  9.223.372.036.854.775.807 ]
     * @desde = Limite inferior  
     * @hasta = Limite superior
     * @Retorna el numero aleatorio
     */
    public static long getRandomLong(long desde , long hasta){
        Random azar;
        azar = new Random(System.currentTimeMillis());
        return desde + Math.abs(azar.nextLong()) % (hasta - desde + 1);
    }


   /**
    * Asigna al Buffer el caracter especificado en cada una de sus posiciones hasta completar size
    * @param Buffer: buffer de tipo byte que se va a modificar
    * @param caracter: caracter a agregar al buffer
    * @param size: Tama�o que se va copiar
    */
    public static void memSet(byte[] Buffer, byte caracter, int size ) {
        int i;

        for(i=0; i<size; i++){
            Buffer[i] = caracter;
        }
    }


    /**
     * Muestra la memoria de un boffer en hexa y ascii
     * @param Buffer: buffer que se va a mostrar
     * @param tam: Tama�o que se va a mostrar     
     */
    public static void dumpMemory(byte[] Buffer, int tam)
    {
        int i;

        byte[] BufferDisplay = new byte[tam];

        for(i=0; i<tam; i++){
            if( Buffer[i] >= 32 && Buffer[i] <= 126 )
                BufferDisplay[i] = Buffer[i];
            else
                BufferDisplay[i] = '.';
        }

        System.out.println("\n\n\n");

        for(i=0; i<tam; i++){

            //System.out.print( ISOUtil.unPadLeft( (ISOUtil.padleft(Integer.toHexString(Buffer[i]), 2, '0').toUpperCase() ) , 'F' ) + " " );

            System.out.print(ISOUtil.hexString(Buffer[i]) + " ");

            if( (i+1) % 16 == 0){
               System.out.print( "   "  + uninterpret_ASCII(BufferDisplay, i - 15, 16) );
               System.out.println();
            }
            else if(i+1 == tam){

               System.out.print( ISOUtil.padleft( "   ", 3*(16-tam%16), ' ' ) );
               System.out.print( "   " + uninterpret_ASCII(BufferDisplay, i - (tam%16) + 1, tam%16) );

               System.out.println();
            }

        }

        System.out.println();

        //System.out.println(ISOUtil.hexString(Buffer, 0, tam));        

    }

    /*******************************************************************************
     Function        : strLen
     Description     : Recibe un array de bytes y muestra la cantidad
     Input           : Buffer = Tamaño del byte
     Return          : Cantidad
     ******************************************************************************/
    public static int strLen(byte[] Buffer) {
        int i;

        for(i=0; i<Buffer.length; i++){
            if(Buffer[i] == 0x00)
                break;
        }

        return i;
    }


    /*******************************************************************************
     Function        : formatMiles
     Description     : Recibe una cadena y la convierte en formato miles
     Input           : cadena = Cadena a convertir
     Return          : cadena formateada
     ******************************************************************************/
    public static String formatMiles(String cadena){
        int i, k = 0;
        int j = 0;
        int tam;

        tam = cadena.length();

        byte[] cadena_orig = cadena.getBytes();
        byte[] cad_destino = new byte[50];

        i=tam/3;															// Calc�lo la cantidad de puntos de mil que se van a agregar
        if(i*3==tam) i--;
	k=0;

	for(j=tam-1; j>=0; j--){
            cad_destino[j+i]=cadena_orig[j];
            k++;
            if( (k/3)*3==k ){
                i--;

                if( (j+i) > 0 )
                    cad_destino[j+i]='.';
            }
	}

        return uninterpret_ASCII(cad_destino, 0, strLen(cad_destino) ) ;
    }

    /**
     * 弹出撤销框
     */
    private static int toByte(char c) {
        byte b = (byte)"0123456789ABCDEF".indexOf(c);
        return b;
    }
    public static byte[] hexStringToByteArray(String hex) {
        int len = hex.length() / 2;
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();

        for(int i = 0; i < len; ++i) {
            int pos = i * 2;
            result[i] = (byte)(toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }

        return result;
    }

    /*******************************************************************************
     Function        : extraerString
     Description     : Extrae digitos de una cadena
     Input           : s = Cadena a extraer
     Return          : cadena final
     ******************************************************************************/
    public static String extraerString(String s){
        String strfinal="";

        for(int i =0; i< s.length();i++) {
            strfinal +="3";
            strfinal += s.substring(i,i+1);

        }
        return strfinal;
    }

    /*******************************************************************************
     Function        : writeLOG
     Description     :
     Input           : data =
                       tipo =
     Return          : 0
     ******************************************************************************/
    public  static int writeLOG(String data, String tipo) {

        File extStore = Environment.getExternalStorageDirectory();
        String fileName = tipo+".txt";
        String path = extStore.getAbsolutePath() + "/" + fileName;
        try {
            File myFile = new File(path);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);
            myOutWriter.close();
            fOut.close();

            //Toast.makeText(getApplicationContext(), fileName + " saved", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("Ficheros Reverso w", "Error al escribir fichero a memoria interna");
        }
        return 0;
    }

    /*******************************************************************************
     Function        : getImei
     Description     : Obtiene el IMEI de un dispositivo en Android
     Input           : c = Contexto
     Return          : IMEI
     ******************************************************************************/
    @SuppressLint("MissingPermission")
    public static String getImei(Context c) {
        TelephonyManager telephonyManager = (TelephonyManager) c
                .getSystemService(Context.TELEPHONY_SERVICE);

        return telephonyManager.getDeviceId();
    }

    /*******************************************************************************
     Function        : getSerialMaquina
     Description     : Obtiene el serial del dispositivo
     Input           : c = Contexto
     Return          : serial de la Maquina
     ******************************************************************************/
    public static String getSerialMaquina(Context context) {
        String serialMaquina = "";
        try {
            Class c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            Log.i("sunmi", "the sn:" + (String) get.invoke(c, "ro.serialno"));
            serialMaquina = (String) get.invoke(c,"ro.serialno");
            //Log.i("sunmi", "First four characters:" + (String) get.invoke(c, "ro.serialno").substring(0, 4));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serialMaquina;
    }

    /*******************************************************************************
     Function        : getSerialLaperla
     Description     : Obtiene el serial de la perla
     Input           : c = Contexto
     Return          : serialPerla
     ******************************************************************************/
    public static String getSerialLaperla(Context c){

        //String Imei = getImei(c);
        //String Imei = Global.IMEI;
        String serialMaquina = getSerialMaquina(c);
        String serial = Global.PREFIJO + serialMaquina.substring(serialMaquina.length()-8, serialMaquina.length() );

        return "72131008606"; // SERIAL PARA PRUEBAS EN OTROS DISPOSITIVOS
        //return serial;
    }

    /*******************************************************************************
     Function        : contarCaracter
     Description     : Cuenta caracteres
     Input           : array = Array a dividir
                       offset = inicio
                       length = longitud
                       separator = separador
     Return          : numero de caracteres
     ******************************************************************************/
    public static int contarCaracter (byte[] array, int offset, int length, byte separator) {

        int i;
        int numCaracteres = 0;

        for (i = offset; i < length; i++) {
            if (array[i] == separator) {

                numCaracteres++;
            }
        }
        return numCaracteres;
    }

    // Metodo para convertir una hora en formato 12 a entero

    /*******************************************************************************
     Function        : timeStrToInt_12h
     Description     : Convierte una hora en formato 12 a entero
     Input           : horaLlegada = Hora a convertir
     Return          : hora convertida
     ******************************************************************************/
    public static int timeStrToInt_12h (String horaLlegada){
        String meridiano;
        String horas24 = "";

        meridiano = horaLlegada.substring(6,8);

        horaLlegada = horaLlegada.substring(0,5);
        horaLlegada = horaLlegada.replace(":","");

        if (meridiano.equals("PM") ){
            horas24 = String.valueOf( Integer.parseInt( horaLlegada.substring(0,2) ) + 12 );
            horaLlegada = horaLlegada.substring( 2, horaLlegada.length() );
        }

        horaLlegada = horas24 + horaLlegada;

        return Integer.parseInt(horaLlegada);
    }

    /*******************************************************************************
     Function        : timeStrToInt_24h
     Description     : Convierte una hora en formato 24 a entero
     Input           : horaLlegada = Hora a convertir
     Return          : hora convertida
     ******************************************************************************/
    public static int timeStrToInt_24h (String horaActual){

        horaActual = horaActual.substring(0,5);
        horaActual = horaActual.replace(":","");

        return Integer.parseInt(horaActual);
    }

    /*******************************************************************************
     Function        : getDecimals
     Description     : Recibe un numero decimal y retorna una cadena con los digitos decimales deseados
     Input           : numero = numero a convertir
                       cantDecimales = Cantidad de decimales
     Return          : cadena con los decimales seleccionados
     ******************************************************************************/
    public static String getDecimals(String numero, int cantDecimales){
        String parteDecimal = "", parteDecimalRet = "";
        int numPuntos = 0, numTokens;

        numero = numero + ".";

        numPuntos = contarCaracter(numero.getBytes(),0,numero.getBytes().length,(byte) '.');

        numTokens = numPuntos;

        Global.tokens = tokenizer(numero.getBytes(), 0, numero.getBytes().length, (byte) '.', numTokens);

        if (numTokens > 1)
            parteDecimal = Global.tokens[1];

        //parteDecimal = ISOUtil.padleft(parteDecimal, cantDecimales, '0');
        if (parteDecimal.length() < cantDecimales)
            parteDecimal = ISOUtil.padRight(parteDecimal, cantDecimales, '0');

        parteDecimalRet = parteDecimal.substring(0,cantDecimales);

        return parteDecimalRet;
    }

    /*******************************************************************************
     Function        : pintarLineaDialogo
     Description     : Pinta la linea de un AlertDialog de color verde
     Input           : c = Contexto
                       dialog = Dialogo a pintar
     Return          : cadena con los decimales seleccionados
     ******************************************************************************/
    public static void pintarLineaDialogo(Context c, Dialog dialog) {

        int titleDividerId = c.getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = dialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundColor(c.getResources().getColor(R.color.colorPrimary));
    }

    /*******************************************************************************
     Function        : colorizeDatePicker
     Description     : Pinta la linea de un DatePicker del color del colorPrimary
     Input           : datePicker = DatePicker a pintar
     Return          : Nothing
     ******************************************************************************/
    public static void colorizeDatePicker(DatePicker datePicker) {
        Resources system = Resources.getSystem();
        int dayId = system.getIdentifier("day", "id", "android");
        int monthId = system.getIdentifier("month", "id", "android");
        int yearId = system.getIdentifier("year", "id", "android");

        NumberPicker dayPicker = (NumberPicker) datePicker.findViewById(dayId);
        NumberPicker monthPicker = (NumberPicker) datePicker.findViewById(monthId);
        NumberPicker yearPicker = (NumberPicker) datePicker.findViewById(yearId);

        setDividerColor(dayPicker);
        setDividerColor(monthPicker);
        setDividerColor(yearPicker);
    }

    /*******************************************************************************
     Function        : setDividerColor
     Description     : pintador de la función colorizeDatePicker
     Input           : picker = NumberPicker a pintar
     Return          : Nothing
     ******************************************************************************/
    private static void setDividerColor(NumberPicker picker) {
        if (picker == null)
            return;

        final int count = picker.getChildCount();
        for (int i = 0; i < count; i++) {
            try {
                Field dividerField = picker.getClass().getDeclaredField("mSelectionDivider");
                dividerField.setAccessible(true);
                ColorDrawable colorDrawable = new ColorDrawable(picker.getResources().getColor(R.color.colorPrimary));
                dividerField.set(picker, colorDrawable);
                picker.invalidate();
            } catch (Exception e) {
                Log.w("setDividerColor", e);
            }
        }
    }

    /*******************************************************************************
     Function        : format40CharsPerLine
     Description     : Reemplaza por un salto de linea al llegar a 40
     Input           : str = cadena de llegada
     Return          : Cadena a imprimir
     ******************************************************************************/
    public static String format40CharsPerLine (String str){
        String strRet = "";
        int i = 0;

        for (i=0; i < str.length() - 40; i += 40)
            strRet = strRet + str.substring(i, i + 40) + "\n";

        strRet = strRet + str.substring(i, str.length() );

        return strRet;
    }


    /*******************************************************************************
     Function        : replaceSpecialChars
     Description     : Reemplaza los caracteres especiales
     Input           :
     Return          :
     ******************************************************************************/
    public static void replaceSpecialChars (){

        replaceChar(Global.inputData, (byte) 0xF1, (byte) 'n', Global.inputData.length); // Cambio la ñ por n
        replaceChar(Global.inputData, (byte) 0xD1, (byte) 'N', Global.inputData.length); // Cambio la Ñ mayusculas por la N
        replaceChar(Global.inputData, (byte) 0xC1, (byte) 'A', Global.inputData.length); // Cambio la A tildada por A
        replaceChar(Global.inputData, (byte) 0xC9, (byte) 'E', Global.inputData.length); // Cambio la E tildada por E
        replaceChar(Global.inputData, (byte) 0xCD, (byte) 'I', Global.inputData.length); // Cambio la I tildada por I
        replaceChar(Global.inputData, (byte) 0xD3, (byte) '0', Global.inputData.length); // Cambio la 0 tildada por 0
        replaceChar(Global.inputData, (byte) 0xDA, (byte) 'U', Global.inputData.length); // Cambio la U tildada por U
        replaceChar(Global.inputData, (byte) 0xE1, (byte) 'a', Global.inputData.length); // Cambio la a tildada por a
        replaceChar(Global.inputData, (byte) 0xE9, (byte) 'e', Global.inputData.length); // Cambio la e tildada por e
        replaceChar(Global.inputData, (byte) 0xED, (byte) 'i', Global.inputData.length); // Cambio la i tildada por i
        replaceChar(Global.inputData, (byte) 0xF3, (byte) 'o', Global.inputData.length); // Cambio la o tildada por o
        replaceChar(Global.inputData, (byte) 0xFA, (byte) 'u', Global.inputData.length); // Cambio la u tildada por u
        replaceChar(Global.inputData, (byte) 0xBF, (byte) ' ', Global.inputData.length); // Cambio la ¿ tildada por " "
        replaceChar(Global.inputData, (byte) 0xA1, (byte) ' ', Global.inputData.length); // Cambio la ¡ tildada por " "
    }
}
