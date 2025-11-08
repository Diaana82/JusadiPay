package com.mycompany.jusadipay;

/**
 *
 * @author patin
 */
public class JusadiPay {

    public static void main(String[] args) {
        //intanciamos con el contructor vacio
        User usuario1=new User();
        usuario1.setNombre("Sara");
        usuario1.setDocumento("1002723");
        usuario1.setTelefono("134255");
        usuario1.setCorreo("saritak.com");
        usuario1.setContraseña("123");
        usuario1.setEdad(18);
        //intanciar con el contructor con parametros
        User usuario2=new User("Diana", "1002799922", "3135374647", "dianitha20","diana123", 21);
        
   
        
    }
}
