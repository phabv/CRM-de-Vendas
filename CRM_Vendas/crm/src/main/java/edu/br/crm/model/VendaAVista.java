package edu.br.crm.model;

public class VendaAVista extends TipoVenda {
    public VendaAVista() {
        
    }
    
    public VendaAVista(Integer id, Integer idVenda,String nome, double qtd, double valorUnit) {
        super(id, idVenda,nome, qtd, valorUnit);
    }
    public VendaAVista(Integer idVenda,String nome,double qtd,double valorUnit){
        super(idVenda,nome,qtd,valorUnit);
    }
    
    @Override
    public double calcularTotal(double qtd, double valorUnit) {
        return qtd*valorUnit-(valorUnit*0.05);
    }
}
