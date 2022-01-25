package com.example.walther.gelsaaplication.comunes;


import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;


/**
 * Created by Walther Roa
 */

public class TCP {

    /*******************************************************************************
     Function        : transaction
     Description     : Realiza la transacción
     Input           : len = Longitud
     Return          : TRUE = Retorna transacción ok, FALSE = Catch Exception
     ******************************************************************************/
    public static int transaction(int len){

        String received = "";
        PrintStream output;
        InputStream input;

        //Se conecta al servidor
        //Socket socket = new Socket();
        //Global.socket.connect(new InetSocketAddress(Global.primaryIP, Global.primaryPort), Global.SOCKET_TIMEOUT);
        System.out.println("PRINT 1 ----------------------------------------------");
        if ( tcpOpenSocket() == false){
            return Global.ERR_OPEN_SOCKET;
        }
        System.out.println("PRINT 2 ----------------------------------------------");

        System.out.println("--------------------------------------------------------------------");
        System.out.println("Enviando ...");
        System.out.println(" URL: " + Global.INITIAL_IP + ":" + Global.INITIAL_PORT);
        Utils.dumpMemory(Global.outputData, len);
        System.out.println("--------------------------------------------------------------------");

        System.out.println("PRINT 3 ----------------------------------------------");
        try {
            output = new PrintStream(Global.tcpSocket.getOutputStream());

            //envia peticion
            output.write(Global.outputData, 0, len);
        } catch (IOException e) {
            // Cierra el socket si hay falla en el envio
            disconnect();
            //Log.e("E/TCP Client 2 :", "" + ex.getMessage());
            System.out.println("Error Enviando - getOutputStream"+ e.getMessage() );
            return Global.ERR_WRITE_SOCKET;
        }

        System.out.println("PRINT 4 ----------------------------------------------");

        try {
            Global.tcpSocket.setSoTimeout(Global.SOCKET_TIMEOUT);
        } catch (SocketException e) {
            System.out.println("Error setSoTimeout"+ e.getMessage() );
            return Global.ERR_TIMEOUT_SOCKET;
        }
        System.out.println("PRINT 5 ----------------------------------------------");

        try {
            input = Global.tcpSocket.getInputStream();
            //recibe respuesta
            Global.inputLen = input.read(Global.inputData);
        } catch (IOException e) {
            // Cierra el socket si hay falla en el recibido
            disconnect();
            //Log.e("E/TCP Client 2 :", "" + ex.getMessage());
            System.out.println("Error getInputStream  "+ e.getMessage() );
            return Global.ERR_READ_SOCKET;
        }
        System.out.println("PRINT 6 ----------------------------------------------");

        if (Global.inputLen > 0) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Recibido: ");
            Utils.dumpMemory(Global.inputData, Global.inputLen);
            System.out.println("--------------------------------------------------------------------");

            Utils.replaceSpecialChars();            // Reemplaza los caracteres especiales

        }
        System.out.println("PRINT 7 ----------------------------------------------");

        System.out.println("PRINT 8 ----------------------------------------------");

        return Global.TRANSACTION_OK;

    }


    /*******************************************************************************
     Function        : checkConex
     Description     : Revisa la conexión de la transacción
     Return          : TRUE = Retorna transacción ok, FALSE = Retorna false
     ******************************************************************************/
    public static boolean checkConex (){

        System.out.println("PRINT checkConex 1 ----------------------------------------------");
        if ( transaction(0) == Global.TRANSACTION_OK){
            System.out.println("PRINT checkConex 2 ----------------------------------------------");
            return true;
        }
        else{
            System.out.println("PRINT checkConex 3 ----------------------------------------------");
            return false;
        }

    }

    /*******************************************************************************
     Function        : disconnect
     Description     : Desconecta el socket
     Return          : TRUE = Retorna socket cerrado, FALSE = Catch Exception
     ******************************************************************************/
    public static boolean disconnect(){

        System.out.println("PRINT disconnect 1 ----------------------------------------------");
        try{

            Global.tcpSocket.close();
            return true;

        } catch (IOException ex) {
            System.out.println("Error, cerrando socket ---" + ex.getMessage() );
            Log.e("Error, cerrando socket:", "" + ex.getMessage());
            return false;
        }
    }

    /*******************************************************************************
     Function        : tcpOpenSocket
     Description     : Crea y conecta un nuevo socket
     Return          : TRUE = Retorna socker abeirto, FALSE = Catch Exception
     ******************************************************************************/
    public static boolean tcpOpenSocket(){


        if( Global.tcpSocket == null || Global.tcpSocket.isClosed() ){

            System.out.println(" ********************************************* Creando nuevo socket");
            Global.tcpSocket = new Socket();
        }

        try{

            System.out.println(" ********************************************* Antes de conectar");

            if ( Global.tcpSocket.isConnected() == false ) {

                System.out.println(" ********************************************* Conectando socket");
                Global.tcpSocket.connect(new InetSocketAddress(Global.INITIAL_IP, Global.INITIAL_PORT), Global.SOCKET_TIMEOUT);
            }

        } catch (IOException ex) {
            System.out.println("Error, tcpOpenSocket ---" + ex.getMessage() );
            System.out.println("********************************************* Error, tcpOpenSocket" + "" + ex.getMessage());
            disconnect();
            return false;
        }

        return true;
    }
}
