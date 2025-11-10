package com.mycompany.jusadipay.polimorfismo;

import com.mycompany.jusadipay.Account;
import com.mycompany.jusadipay.Transaction;

public interface OperacionCuenta {
     Transaction ejecutar(Account cuenta);
}
