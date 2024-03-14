package com.example.ejercicio24breng.transacciones;

public class Transacciones {
    /*TABLA*/
    public static final String tabla_firmas = "tbl_firmas";


    /*CAMPOS*/
    public static final String id = "id";
    public static final String image = "image";
    public static final String descripcion = "descripcion";


    /* tablas - CREATE , DROP */
    public static final String CreateTableFirmas= "CREATE TABLE tbl_firmas" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "image BLOB, " +
            "descripcion TEXT)";

    public static final String DropTableFirmas= "DROP TABLE IF EXISTS tbl_firmas";


    /* Creacion del nombre de la base de datos */
    public static final String NameDataBase = "DBPWI";
}
