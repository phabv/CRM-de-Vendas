package edu.br.crm.model;

public class Cliente {
    private Integer id;
    private String nome;
    private String documento;
    private String contato;
    
    public Cliente() {
        
    }
    
    public Cliente(Integer id, String nome, String documento, String contato) {
        setId(id);
        setNome(nome);
        setDocumento(documento);
        setContato(contato);
    }
    
    public Cliente(String nome, String documento, String contato) {
        this(0, nome, documento, contato);
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public void setContato(String contato) {
        this.contato = contato;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getDocumento(){
        return documento;
    }
    
    public String getContato(){
        return contato;
    }
}
