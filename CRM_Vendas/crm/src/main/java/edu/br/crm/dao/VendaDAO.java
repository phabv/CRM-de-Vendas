package edu.br.crm.dao;

import edu.br.crm.model.Venda;
import edu.br.crm.model.Cliente;
import edu.br.crm.model.TipoVenda;
import edu.br.crm.model.Vendedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public int inserirVenda(Venda v) throws SQLException {
        String sql = "INSERT INTO vendas (id_cliente, id_vendedor, status) VALUES(?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, v.getIdCliente());
            ps.setInt(2, v.getIdVendedor());
            ps.setString(3, v.getStatus());
            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new SQLException("Inserção falhou: nenhuma linha afetada.");
            }
// Recupera o ID gerado automaticamente (AUTO_INCREMENT)
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            throw new SQLException(
                    "InserÃ§Ã£o falhou: nÃ£o foi possÃ­vel obter o ID gerado.");
        }
    }
    
    public int inserirCliente(Cliente c) throws SQLException {
        String sql = "INSERT INTO clientes (nome_cliente, documento, contato) VALUES(?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getDocumento());
            ps.setString(3, c.getContato());
            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new SQLException("InserÃ§Ã£o falhou: nenhuma linha afetada.");
            }
// Recupera o ID gerado automaticamente (AUTO_INCREMENT)
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            throw new SQLException(
                    "InserÃ§Ã£o falhou: nÃ£o foi possÃ­vel obter o ID gerado.");
        }
    }
    
    public int inserirTipoVenda(TipoVenda v) throws SQLException {
        String sql = "INSERT INTO itens_venda (id_venda, descricao, qtd, valor_unit) VALUES(?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, v.getIdVenda());
            ps.setString(2, v.getNome());
            ps.setDouble(3, v.getQtd());
            ps.setDouble(4, v.getValorUnit());
            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new SQLException("Inserção falhou: nenhuma linha afetada.");
            }
// Recupera o ID gerado automaticamente (AUTO_INCREMENT)
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            throw new SQLException(
                    "InserÃ§Ã£o falhou: nÃ£o foi possÃ­vel obter o ID gerado.");
        }
    }

    public boolean atualizarCliente(Cliente c) throws SQLException {
        String sql = "UPDATE clientes SET nome_cliente=?, documento=?, contato=? WHERE id_cliente = ?";
try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getDocumento());
            ps.setString(3, c.getContato());
            ps.setInt(4, c.getId());
            return ps.executeUpdate() > 0;
        }
    }
    
    public int inserirVendedor(Vendedor c) throws SQLException {
        String sql = "INSERT INTO vendedores (nome_vendedor, percentual_comissao) VALUES(?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNome());
            ps.setDouble(2, c.getComm());
            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new SQLException("Inserção falhou: nenhuma linha afetada.");
            }
// Recupera o ID gerado automaticamente (AUTO_INCREMENT)
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            throw new SQLException(
                    "Inserção falhou: não foi possÃ­vel obter o ID gerado.");
        }
    }
    
    public boolean atualizarVendedor(Vendedor c) throws SQLException {
        String sql = "UPDATE vendedores SET nome_vendedor=?, percentual_comissao=? WHERE id_vendedor = ?";
try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setDouble(2, c.getComm());
            ps.setInt(3, c.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean removerClientePorId(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id_cliente=?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
    
    public boolean removerVendedorPorId(int id) throws SQLException {
        String sql = "DELETE FROM vendedores WHERE id_vendedor=?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public Cliente buscarClientePorId(int id) throws SQLException {
        String sql = "SELECT id_cliente, nome_cliente, documento, contato FROM clientes WHERE id_cliente = ?";
try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCliente(rs);
                }
                return null;
            }
        }
    }
    
    public Vendedor buscarVendedorPorId(int id) throws SQLException {
        String sql = "SELECT id_vendedor, nome_vendedor, percentual_comissao FROM vendedores WHERE id_vendedor = ?";
try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapVendedor(rs);
                }
                return null;
            }
        }
    }
    
    public List<TipoVenda> listarTipoVenda() throws SQLException {
        String sql = "SELECT id_item_venda, id_venda, descricao, qtd, valor_unit FROM itens_venda ORDER BY id_item_venda";
        List<TipoVenda> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapTipoVenda(rs));
            }
        }
        return lista;
    }
    
    public List<Venda> listarVendas() throws SQLException {
        String sql = "SELECT id_venda, id_cliente, id_vendedor, status, data FROM vendas ORDER BY id_venda";
        List<Venda> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapVenda(rs));
            }
        }
        return lista;
    }

    public List<Cliente> listarCliente() throws SQLException {
        String sql = "SELECT id_cliente, nome_cliente, documento, contato FROM clientes ORDER BY id_cliente";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapCliente(rs));
            }
        }
        return lista;
    }
    
    public List<Vendedor> listarVendedor() throws SQLException {
        String sql = "SELECT id_vendedor, nome_vendedor, percentual_comissao FROM vendedores ORDER BY id_vendedor";
        List<Vendedor> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapVendedor(rs));
            }
        }
        return lista;
    }

    public List<Cliente> buscarClientePorNome(String trecho) throws SQLException {
        String sql = "SELECT id_cliente, nome_cliente, documento, contato FROM clientes WHERE nome_cliente LIKE ? ORDER  BY nome_cliente";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + trecho + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapCliente(rs));
                }
            }
        }
        return lista;
    }
    
    public List<Vendedor> buscarVendedorPorNome(String trecho) throws SQLException {
        String sql = "SELECT id_vendedor, nome_vendedor, percentual_comissao FROM vendedores WHERE nome_vendedor LIKE ? ORDER  BY nome_vendedor";
        List<Vendedor> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + trecho + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapVendedor(rs));
                }
            }
        }
        return lista;
    }
    
    private TipoVenda mapTipoVenda(ResultSet rs) throws SQLException {
        return new TipoVenda(
                rs.getInt("id_item_venda"),
                rs.getInt("id_venda"),
                rs.getString("descricao"),
                rs.getDouble("qtd"),
                rs.getDouble("valor_unit")
        );
    }
    
    private Venda mapVenda(ResultSet rs) throws SQLException {
        return new Venda(
                rs.getInt("id_venda"),
                rs.getInt("id_cliente"),
                rs.getInt("id_vendedor"),
                rs.getString("status")
        );
    }

    private Cliente mapCliente(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id_cliente"),
                rs.getString("nome_cliente"),
                rs.getString("Documento"),
                rs.getString("Contato")
        );
    }
    
    private Vendedor mapVendedor(ResultSet rs) throws SQLException {
        return new Vendedor(
                rs.getInt("ID_vendedor"),
                rs.getString("Nome_vendedor"),
                rs.getDouble("percentual_comissao")
        );
    }
    public static double exibirFaturamento() throws SQLException {
        String sql = "SELECT SUM(qtd*valor_unit) FROM itens_venda";
        try (Connection conn = ConnectionFactory.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getDouble(1);
            }
        }
        throw new SQLException("Erro ao gerar faturamento!!!");
        }
    }
