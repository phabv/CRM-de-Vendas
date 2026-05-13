package edu.br.crm.controller;

import edu.br.crm.model.Cliente;
import edu.br.crm.model.TipoVenda;
import edu.br.crm.model.Vendedor;
import edu.br.crm.model.Venda;
import edu.br.crm.model.VendaAVista;
import edu.br.crm.model.VendaParcelada;
import edu.br.crm.service.VendaService;
import java.sql.SQLException;
import java.util.List;

public class VendaController {

    private final VendaService service = new VendaService();
    
    public int criarVenda(Integer id_cliente, Integer id_vendedor, String status) throws
            SQLException {
        Venda v = new Venda(id_cliente, id_vendedor, status);
        return service.criarVenda(v);
    }
    
    public int criarVendaAVista(Integer idVenda,String nome, double qtd, double valorUnit) throws
            SQLException {
        TipoVenda v = new VendaAVista(idVenda, nome, qtd, valorUnit);
        return service.criarTipoVenda(v);
    }
    
    public int criarVendaParcelada(Integer idVenda,String nome, double qtd, double valorUnit) throws
            SQLException {
        TipoVenda v = new VendaParcelada(idVenda, nome, qtd, valorUnit);
        return service.criarTipoVenda(v);
    }

    public int criarCliente(String nome, String documento, String contato) throws
            SQLException {
        Cliente c = new Cliente(nome, documento, contato);
        return service.criarCliente(c);
    }
    
    public int criarVendedor(String nome, double comm) throws
            SQLException {
        Vendedor c = new Vendedor(nome, comm);
        return service.criarVendedor(c);
    }

    public boolean atualizarCliente(int id, String nome, String documento, String contato) throws SQLException {
        Cliente c = new Cliente(id, nome, documento, contato);
        return service.atualizarCliente(c);
    }
    
    public boolean atualizarVendedor(int id, String nome, double comm) throws SQLException {
        Vendedor c = new Vendedor(id, nome, comm);
        return service.atualizarVendedor(c);
    }

    public boolean removerCliente(int id) throws SQLException {
        return service.removerCliente(id);
    }
    
    public boolean removerVendedor(int id) throws SQLException {
        return service.removerVendedor(id);
    }

    public Cliente buscarCliente(int id) throws SQLException {
        return service.buscarCliente(id);
    }
    
    public Vendedor buscarVendedor(int id) throws SQLException {
        return service.buscarVendedor(id);
    }
    
    public List<Venda> listarVenda() throws SQLException {
        return service.listarVenda();
    }
    
    public List<TipoVenda> listarTipoVenda() throws SQLException {
        return service.listarTipoVenda();
    }
    
    public List<Cliente> listarCliente() throws SQLException {
        return service.listarCliente();
    }

    public List<Vendedor> listarVendedor() throws SQLException {
        return service.listarVendedor();
    }

    public List<Cliente> buscarClientePorNome(String trecho) throws SQLException {
        return service.buscarClientePorNome(trecho);
    }
    
    public List<Vendedor> buscarVendedorPorNome(String trecho) throws SQLException {
        return service.buscarVendedorPorNome(trecho);
    }
    
    public double exibirFaturamento() throws SQLException {
        return service.exibirFaturamento();
    }
}
