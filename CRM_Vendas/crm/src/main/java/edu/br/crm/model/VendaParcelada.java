package edu.br.crm.model;

public class VendaParcelada extends TipoVenda {
    public VendaParcelada() {
        
    }
    
    public VendaParcelada(Integer id, Integer idVenda,String nome, double qtd, double valorUnit) {
        super(id, idVenda,nome, qtd, valorUnit);
    }
    public VendaParcelada(Integer idVenda,String nome,double qtd,double valorUnit){
        super(idVenda,nome,qtd,valorUnit);
    }
    
    @Override
    public double calcularTotal(double qtd, double valorUnit) {
        return qtd*valorUnit+(valorUnit*0.05);
    }
}
