package edu.br.crm.model;

public class Vendedor {
    private Integer id;
    private String nome;
    private double comm; 
    
    public Vendedor() {
        
    }
    
    public Vendedor(Integer id, String nome, double comm) {
        setId(id);
        setNome(nome);
        setComm(comm);
    }
    
    public Vendedor(String nome, double comm) {
        this(0, nome, comm);
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setComm(double comm) {
        this.comm = comm;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public double getComm() {
        return comm;
    }
}
