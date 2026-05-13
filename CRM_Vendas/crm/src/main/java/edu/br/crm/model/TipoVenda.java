package edu.br.crm.model;

public class TipoVenda {
    private Integer id;
    private Integer idVenda;
    private String nome;
    private Double qtd;
    private Double valorUnit;
    
    public TipoVenda() {
        
    }
    
    public TipoVenda(Integer id, Integer idVenda, String nome, Double qtd, Double valorUnit) {
        setId(id);
        setIdVenda(idVenda);
        setNome(nome);
        setQtd(qtd);
        setValorUnit(valorUnit);
    }
    
    public TipoVenda(Integer idVenda, String nome, Double qtd, Double valorUnit) {
        this(0, idVenda, nome, qtd, valorUnit);
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setIdVenda(Integer id_venda) {
        this.idVenda = id_venda;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }
    
    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }
    
    public Integer getId() {
        return id;
    }
    
    public Integer getIdVenda() {
        return idVenda;
    }
    
    public String getNome() {
        return nome;
    }
    
    public Double getQtd() {
        return qtd;
    }
    
    public Double getValorUnit() {
        return valorUnit;
    }
    
    public double calcularTotal(double qtd, double valorUnit) {
        return qtd*valorUnit;
    }
}
