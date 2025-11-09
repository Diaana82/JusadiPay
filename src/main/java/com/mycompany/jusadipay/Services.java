package com.mycompany.jusadipay;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Services {
    private supplier proveedor;
    private double monto;
    private String numReferencia;
    private String tipoServicio;
    private LocalDateTime fechaPago;
    private String estado;
    private Account cuentaOrigen;
    private Transaction transaccion;
    private static final List<supplier> PROVEEDORES = new ArrayList<>();

    public Services() {
    }

    public Services(supplier proveedor, double monto, String numReferencia, String tipoServicio, LocalDateTime fechaPago, String estado, Account cuentaOrigen, Transaction transaccion) {
        this.proveedor = proveedor;
        this.monto = monto;
        this.numReferencia = numReferencia;
        this.tipoServicio = tipoServicio;
        this.fechaPago = fechaPago;
        this.estado = estado;
        this.cuentaOrigen = cuentaOrigen;
        this.transaccion = transaccion;
    }

    
    
    public supplier getProveedor() {
        return proveedor;
    }

    public void setProveedor(supplier proveedor) {
        this.proveedor = proveedor;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Account getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Account cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Transaction getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaction transaccion) {
        this.transaccion = transaccion;
    }
    
    public static List<supplier> obtenerProveedor() {
    return Collections.unmodifiableList(PROVEEDORES);
    }
    
    
    public boolean recargarCel(String numero, double monto) {
    if (cuentaOrigen == null) return false;
    if (numero == null || numero.isBlank()) return false;
    if (monto <= 0) return false;
    if (cuentaOrigen.getSaldo() < monto) return false;

  
    cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);


    this.proveedor = null; 
    this.monto = monto;
    this.numReferencia = numero;
    this.tipoServicio = "RECARGA_CELULAR";
    this.fechaPago = LocalDateTime.now();
    this.estado = "COMPLETADA";

    this.transaccion = new Transaction(
        "TX-" + System.nanoTime(),
        this.fechaPago,
        monto,
        this.tipoServicio,
        this.estado,
        this.cuentaOrigen,
        null,
        null
    );
    return true;
}
    
    public boolean pagarFactura(supplier prov, String referencias, double monto) {
    if (cuentaOrigen == null) return false;
    if (prov == null) return false;
    if (referencias == null || referencias.isBlank()) return false;
    if (monto <= 0) return false;
    if (cuentaOrigen.getSaldo() < monto) return false;


    cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);


    this.proveedor = prov;
    this.monto = monto;
    this.numReferencia = referencias;
    this.tipoServicio = "PAGO_FACTURA";
    this.fechaPago = LocalDateTime.now();
    this.estado = "COMPLETADA";

    this.transaccion = new Transaction(
        "TX-" + System.nanoTime(),
        this.fechaPago,
        monto,
        this.tipoServicio,
        this.estado,
        this.cuentaOrigen,
        null,
        null
    );
    return true;
}
    
public String generarComprobante() {
    String provNombre = (proveedor != null) ? proveedor.getNombreProveedor() : "N/A";
    return new StringBuilder()
        .append("=== COMPROBANTE SERVICIO ===\n")
        .append("Proveedor: ").append(provNombre).append('\n')
        .append("Tipo: ").append(tipoServicio).append('\n')
        .append("Referencia: ").append(numReferencia).append('\n')
        .append("Monto: ").append(monto).append('\n')
        .append("Fecha: ").append(fechaPago).append('\n')
        .append("Estado: ").append(estado).append('\n')
        .append("Cuenta Origen: ").append(cuentaOrigen != null ? cuentaOrigen.getNumCuenta() : "N/A").append('\n')
        .append("Transacción: ").append(transaccion != null ? transaccion.getIdTransaccion() : "N/A")
        .toString();
}
    
}
