package com.Project.project.model;
import java.time.LocalDateTime;

public class Servicios {

    private Proveedor proveedor;
    private double monto;
    private String numReferencia;
    private String tipoServicio;
    private LocalDateTime fechaPago;
    private String estado;
    private Cuenta cuentaOrigen;
    private Transaccion transaccion;

    public Servicios() {
    }

    public Servicios(Cuenta cuentaOrigen, String estado, LocalDateTime fechaPago, double monto, String numReferencia, Proveedor proveedor, String tipoServicio, Transaccion transaccion) {
        this.cuentaOrigen = cuentaOrigen;
        this.estado = estado;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.numReferencia = numReferencia;
        this.proveedor = proveedor;
        this.tipoServicio = tipoServicio;
        this.transaccion = transaccion;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public boolean recargarCel(String numero, double monto) {

        if (cuentaOrigen == null) return false;
        if (numero == null || numero.isBlank()) return false;
        if (monto <= 0) return false;
        if (cuentaOrigen.getSaldo() < monto) return false;

        // Se descuenta el monto
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);

        // Se registran datos del servicio
        this.proveedor = null;
        this.monto = monto;
        this.numReferencia = numero;
        this.tipoServicio = "RECARGA_CELULAR";
        this.fechaPago = LocalDateTime.now();
        this.estado = "COMPLETADO";

        this.transaccion = new Transaccion(
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

    public boolean pagarFactura(Proveedor prov, String referencias, double monto) {
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

        this.transaccion = new Transaccion(
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
        String provNombre = (proveedor != null) ? proveedor.getNombreproveedor(): "N/A";
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
