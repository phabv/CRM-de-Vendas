package edu.br.crm.service;

import edu.br.crm.dao.VendaDAO;
import edu.br.crm.model.Venda;
import edu.br.crm.model.Cliente;
import edu.br.crm.model.TipoVenda;
import edu.br.crm.model.Vendedor;
import java.sql.SQLException;
import java.util.List;

public class VendaService {

    private final VendaDAO dao = new VendaDAO();
    
    public int criarVenda(Venda v) throws SQLException {
        validarVenda(v);
        return dao.inserirVenda(v);
    }

    public int criarCliente(Cliente c) throws SQLException {
        validarCliente(c);
        return dao.inserirCliente(c);
    }
    
    public int criarVendedor(Vendedor c) throws SQLException {
        validarVendedor(c);
        return dao.inserirVendedor(c);
    }
    
    public int criarTipoVenda(TipoVenda c) throws SQLException {
        validarTipoVenda(c);
        return dao.inserirTipoVenda(c);
    }

    public boolean atualizarCliente(Cliente c) throws SQLException {
        if (c.getId() == -1) {
            throw new IllegalArgumentException("Contato sem ID não pode ser atualizado. ");
        }
        validarCliente(c);
        return dao.atualizarCliente(c);
    }
    
    public boolean atualizarVendedor(Vendedor c) throws SQLException {
        if (c.getId() == -1) {
            throw new IllegalArgumentException("Contato sem ID não pode ser atualizado. ");
        }
        validarVendedor(c);
        return dao.atualizarVendedor(c);
    }

    public boolean removerCliente(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return dao.removerClientePorId(id);
    }
    
    public boolean removerVendedor(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return dao.removerVendedorPorId(id);
    }

    public Cliente buscarCliente(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return dao.buscarClientePorId(id);
    }
    
    public Vendedor buscarVendedor(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return dao.buscarVendedorPorId(id);
    }
    
    public List<Venda> listarVenda() throws SQLException {
        return dao.listarVendas();
    }
    
    public List<TipoVenda> listarTipoVenda() throws SQLException {
        return dao.listarTipoVenda();
    }

    public List<Cliente> listarCliente() throws SQLException {
        return dao.listarCliente();
    }
    
    public List<Vendedor> listarVendedor() throws SQLException {
        return dao.listarVendedor();
    }

    public List<Cliente> buscarClientePorNome(String trecho) throws SQLException {
        if (trecho == null) {
            trecho = "";
        }
        return dao.buscarClientePorNome(trecho.trim());
    }
    
    public List<Vendedor> buscarVendedorPorNome(String trecho) throws SQLException {
        if (trecho == null) {
            trecho = "";
        }
        return dao.buscarVendedorPorNome(trecho.trim());
    }
    
    public double exibirFaturamento() throws SQLException {
        return dao.exibirFaturamento();
    }

    private void validarCliente(Cliente c) {
        if (c.getNome() == null || c.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (c.getDocumento() == null || c.getDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("Documento é obrigatório.");
        }
        if (c.getContato() == null || c.getContato().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
    }
    
    private void validarVenda(Venda c) {
        if (c.getIdCliente() == null) {
            throw new IllegalArgumentException("Documento é obrigatório.");
        }
        if (c.getIdVendedor() == null) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
        if (c.getStatus() == null || c.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
    }
    
    private void validarTipoVenda(TipoVenda c) {
        if (c.getId() == null) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (c.getQtd() == null) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
        if (c.getValorUnit() == null) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
    }
    
    private void validarVendedor(Vendedor c) {
        if (c.getNome() == null || c.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
    }

}
