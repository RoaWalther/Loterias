package com.example.walther.gelsaaplication.comunes;

import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Walther Roa
 */

public class Messages {
    /*******************************************************************************
     Metodo          : packMsgConsultarPremio
     Description     : Envia los parámetros de la colilla
     Return          : Nothing
     *****************************************************************************/

    public static void packMsgConsultarPremio (Context c){
       //Envia
        Global.strOutputData = Global.inicio_colilla + "," + Global.TRX_CONSULTAR_PREMIO  + Global.FIN_CADENA;

        Global.outputData = Global.strOutputData.getBytes();
        Global.outputLen = Global.outputData.length;
    }

    /*******************************************************************************
     Metodo          : unPackMsgConsultarPremio
     Description     : Recibe los parámetros del ConsultarPremio
     Input           : c = Contexto
     Return          : TRUE = Si desempaqueta correctamente, FALSE = Si ocurre un error
     mientras se desempaqueta
     ******************************************************************************/
    public static boolean unPackMsgConsultarPremio (Context c) {
        int numComas;

        Utils.replaceChar(Global.inputData, (byte) 0x0a, Global.COMA, Global.inputLen);
        Global.tokens = Utils.tokenizer(Global.inputData, 0, Global.inputLen, Global.COMA, 1);

        // Recibe
        // Recibe
        if (Global.tokens[0].equals("pz")) {
            //Utils.customAlertDialog("PREMIO NO EXISTE" + "\n" + "colillas" + "\n" + " NO PAGUE NINGUN PREMIO SI NO EXIXTE EN EL SISTEMA");
            return false;
           // System.out.println("");
        }else if (Global.tokens[0].equals("b")){

            numComas = Utils.contarCaracter(Global.inputData, 0, Global.inputLen, Global.COMA);// Cuenta la cantidad de tokens
            Global.tokens = Utils.tokenizer(Global.inputData, 0, Global.inputLen, Global.COMA, numComas);

            return true;
        }

        return true;
    }

    /*******************************************************************************
     Metodo          : packMsgAsesor
     Description     : Envia los parámetros de ConsultarCuadreActual
     Return          : Nothing
     *****************************************************************************/

    public static void packMsgConsultarCuadreActual (Context c){
        //Envia
        Global.strOutputData = Global.inicio_cuadre + ","+ Global.TRX_CONSULTAR_CUADRE_ACTUAL + "," + "N" + Global.FIN_CADENA;

        Global.outputData = Global.strOutputData.getBytes();
        Global.outputLen = Global.outputData.length;
    }



    /*******************************************************************************
     Metodo          : packMsgConsultarNumerosGanadores
     Description     : Envia los parámetros de ConsultarNumerosGanadores
     Return          : Nothing
     *****************************************************************************/
    public static void packMsgConsultarNumerosGanadores (Context c){
        //Envia
        Global.strOutputData =  "b"+ Global.TRX_CONSULTAR_NUM_GANADORES + Global.FIN_CADENA;

        Global.outputData = Global.strOutputData.getBytes();
        Global.outputLen = Global.outputData.length;
    }
    /*******************************************************************************
     Metodo          : unPackMsgconsulta
     Description     : Recibe los parámetros de ConsultarCuadreActual
     Input           : c = Contexto
     Return          : TRUE = Si desempaqueta correctamente, FALSE = Si ocurre un error
     mientras se desempaqueta
     ******************************************************************************/
    public static boolean unPackMsgNumerosGanadores(Context c) {

        int numComas;

        Utils.replaceChar(Global.inputData, (byte) 0x0a, Global.COMA, Global.inputLen);
        Global.tokens = Utils.tokenizer(Global.inputData, 0, Global.inputLen, Global.COMA, 1);

        // Recibe
        if (Global.tokens[0].equals("bb")) {
            //  Utils.customAlertDialog("Registro de fecha incorrecta o sin numeros ganadores");
            return false;

        } else if (Global.tokens[0].equals("bz")){
            // Utils.customAlertDialog("NO HAY NUMEROS GANADORES");
            return false;

        }else if (Global.tokens[0].equals("b")){

            numComas = Utils.contarCaracter(Global.inputData, 0, Global.inputLen, Global.COMA);// Cuenta la cantidad de tokens
            Global.tokens = Utils.tokenizer(Global.inputData, 0, Global.inputLen, Global.COMA, numComas);

            if(Global.tokens[1].length() <=2){
                //  Utils.customAlertDialog("Error en el mensaje recibido");
                return false;
            }

            Global.arrayConsultarNumerosGanadores = new Global.ConsultarNumerosGanadores[numComas - 1];

            for (int i = 0; i < numComas - 1; i++){

                if(i == numComas - 2) {
                    Global.arrayConsultarNumerosGanadores[i] = new Global.ConsultarNumerosGanadores(
                            Global.tokens[i + 1].substring(0, Global.tokens[i + 1].length() - 1) );
                } else {
                    Global.arrayConsultarNumerosGanadores[i] = new Global.ConsultarNumerosGanadores(
                            Global.tokens[i + 1] );
                }

                System.out.println("VALOR --------------" + Global.arrayConsultarNumerosGanadores[i].resultado);
            }

            return true;
        }

        return true;
    }
}