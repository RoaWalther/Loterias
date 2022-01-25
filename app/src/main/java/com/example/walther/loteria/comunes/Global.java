package com.example.walther.gelsaaplication.comunes;

import java.net.Socket;

public class Global {


    public static final String CODIGO_COMPILATO  = "121";

    //
    //$991109,1111186383502383637732Y0704;571011015114493397|C|10.121.171.31|10013|1z


    public static final String    PREFIJO                 = "721";

    ///////
    public static final String    INITIAL_IP              = "10.121.171.31";
    public static final int       INITIAL_PORT            = 10013;
    public static final int       SOCKET_TIMEOUT          = 20000;
    public static final int       MAX_LEN_INPUTDATA       = 4096;
    public static final int       MAX_LEN_OUTPUTDATA      = 2048;

   ////
    public static final int       TRANSACTION_OK          = 1;
    public static final int       ERR_OPEN_SOCKET         = -1;
    public static final int       ERR_WRITE_SOCKET        = -2;
    public static final int       ERR_TIMEOUT_SOCKET      = -3;
    public static final int       ERR_READ_SOCKET         = -4;

    public static final byte      PIPE                    = '|';
    public static final byte      ARROBA                  = '@';
    public static final byte      COMA                    = ',';
    public static final byte      PUNTO_Y_COMA            = ';';

    public static final String    FIN_CADENA              = "z\n";
    public static final String    SPACES_SMALL            = "                    ";
    public static final String    SPACES_NORMAL           = "               ";
    public static final String    NULL_NUM                = " -- ";
    public static final String    NULL_VALOR              = "-----";

    // Identificadores de transaccion
    public static final String    TRX_PRELOGIN            = "0";
    public static final String    TRX_LOGIN               = "1";
    public static final String    TRX_LOTERIAS            = "2";
    public static final String    TRX_CONSULTAR_VENTAS    = "3";
    public static final String    TRX_VENTA_CHANCE        = "4";
    public static final String    TRX_CONF_ROLLO          = "6";
    public static final String    TRX_ANULAR_TK           = "7";
    public static final String    TRX_VER_RESULTADOS      = "11";
    public static final String    TRX_RECARGA_REALIZADA   = "14";
    public static final String    TRX_RECAUDOS            = "17";
    public static final String    TRX_LOT_EN_LINEA        = "19";
    public static final String    TRX_LOTENLINEA_APUESTA  = "20";
    public static final String    TRX_RECARGAS            = "21";
    public static final String    TRX_CERRAR_SESION       = "22";
    public static final String    TRX_PARAM_GANADIARIO    = "34";
    public static final String    TRX_GANADIARIO_APUESTA  = "35";
    public static final String    TRX_ANULAR_GANADIARIO   = "36";
    public static final String    TRX_RECAUDOS_PROCESO    = "37";
    public static final String    TRX_PARAM_SUPER_ASTRO   = "42";
    public static final String    TRX_ANULAR_ASTRO        = "45";
    public static final String    TRX_DEPORTIVAS          = "54";
    public static final String    TRX_MULTICHANCE         = "57";
    public static final String    TRX_MULTICHANCE_APUESTA = "58";
    public static final String    TRX_ANULAR_MULTICHANCE  = "59";
    public static final String    TRX_SUPERASTRO_APUESTA  = "60";
    public static final String    TRX_ASTRO_AUTO_APUESTA  = "61";
    public static final String    TRX_DOBLECHANCE         = "62";
    public static final String    TRX_ANULAR_DOBLECHANCE  = "64";
    public static final String    TRX_PING                = "99";

    public static final String    MSG_ERR_CONEXION        = "Error de Conexión: No se estableció comunicación con el servidor, revise la configuración de Datos Móviles o WIFI";

    public static byte[]         inputData                = new byte[MAX_LEN_INPUTDATA];
    public static byte[]         outputData               = new byte[MAX_LEN_OUTPUTDATA];
    public static String         strOutputData;
    public static String         TRX_CONSULTAR_PREMIO;
    public static String         inicio_colilla            = "p";
    public static String         inicio_cuadre             = "U";
    public static String         fin_asesor_cadena         = "Nz\n";
    public static String         TRX_CONSULTAR_CUADRE_ACTUAL;
    public static String         TRX_CONSULTAR_NUM_GANADORES;
    public static int            inputLen;
    public static int            outputLen;

    public static boolean         StatusExit              = true;
    public static int             sizeFont;
    public static boolean         negrillaFont;
    public static boolean         subrayadoFont;
    public static int             lengthLine;

    public static String             bufferchance;
    public static String primaryIP;
    public static int primaryPort;
    public static String serial;
    public static String IMEI;
    public static String MsgError;
    public static String usuario;
    public static String contraseña;

    public static String[] tokens = null;
    public static Socket tcpSocket;


    // Variables unpackConsultarPremio
    public static String loteriaSerie1;
    public static String loteriaSerie2;

    // Array Consulta Numeros Ganadores
    public static class ConsultarNumerosGanadores{

        public String resultado;

        public ConsultarNumerosGanadores(String resultado){

            this.resultado = resultado;
        }
    }
    public static ConsultarNumerosGanadores[] arrayConsultarNumerosGanadores = null;


// Array Consultar Premio
public static class ConsultarPremio {

    public String premio;

    public ConsultarPremio(String premio) {

        this.premio = premio;
    }

}
    public static Global.ConsultarPremio[] arrayConsultarPremio = null;

}