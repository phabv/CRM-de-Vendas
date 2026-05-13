package edu.br.crm.model;

public class Venda {
    private Integer id;
    private Integer id_cliente;
    private Integer id_vendedor;
    private String status;
    
    public Venda() {
    }
    
    public Venda(Integer id, Integer id_cliente, Integer id_vendedor, String status) {
        setId(id);
        setIdCliente(id_cliente);
        setIdVendedor(id_vendedor);
        setStatus(status);
    }
    
    public Venda(Integer id_cliente, Integer id_vendedor, String status) {
        this(0, id_cliente, id_vendedor, status);
    }
    
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setIdCliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    public void setIdVendedor(Integer id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getId() {
        return id;
    }
    
    public Integer getIdCliente() {
        return id_cliente;
    }
    
    public Integer getIdVendedor() {
        return id_vendedor;
    }
    
    public String getStatus() {
        return status;
    }
}
