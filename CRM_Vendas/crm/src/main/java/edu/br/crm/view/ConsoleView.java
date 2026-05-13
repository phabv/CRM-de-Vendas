package edu.br.crm.view;

import java.util.Scanner;
import java.util.List;
import java.sql.SQLException;

import edu.br.crm.model.Cliente;
import edu.br.crm.model.Vendedor;
import edu.br.crm.controller.VendaController;
import edu.br.crm.model.TipoVenda;
import edu.br.crm.model.Venda;
import edu.br.crm.model.VendaAVista;
import edu.br.crm.model.VendaParcelada;

public class ConsoleView {

    private final VendaAVista vista = new VendaAVista();
    private final VendaParcelada parcelada = new VendaParcelada();
    private final VendaController controller = new VendaController();
    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {
        int op;

        do {
            mostrarMenu();
            op = lerInt("Escolha: ");

            try {
                switch (op) {
                    case 1 -> menuVendas();
                    case 2 -> menuCliente();
                    case 3 -> menuVendedor();
                    case 0 -> System.out.println("Encerrando. . .");
                    default -> System.out.println("Opç?o inválida.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Erro de validaç?o: " + e.getMessage());

            } catch (SQLException e) {
                System.out.println("Erro no banco: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }

        } while (op != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n=== CRUD Clientes ===");
        System.out.println("1) Menu de vendas");
        System.out.println("2) Menu de cliente");
        System.out.println("3) Menu de vendedor");
        System.out.println("0) Sair");
    }
    
    private void menuCliente() throws SQLException {
        int op;
        do {
            System.out.println("1) Criar cliente");
            System.out.println("2) Listar cliente ");
            System.out.println("3) Atualizar cliente");
            System.out.println("4) Buscar cliente por nome");
            System.out.println("5) Buscar cliente por ID");
            System.out.println("6) Remover cliente");
            System.out.println("0) Voltar");
            op = lerInt("Escolha: ");
            try {
                switch (op) {
                    case 1 -> criarCliente();
                    case 2 -> listarCliente();
                    case 3 -> atualizarCliente();
                    case 4 -> buscarClientePorNome();
                    case 5 -> buscarClientePorId();
                    case 6 -> removerCliente();
                    case 0 -> System.out.println("Voltando. . .");
                    default -> System.out.println("Opção inválida.");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Erro de validação: " + e.getMessage());

            } catch (SQLException e) {
                System.out.println("Erro no banco: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (op!=0);
    }
    
    private void menuVendedor() throws SQLException {
        int op;
        do {
            System.out.println("1) Criar vendedor");
            System.out.println("2) Listar vendedor");
            System.out.println("3) Atualizar vendedor");
            System.out.println("4) Buscar vendedor por nome");
            System.out.println("5) Buscar vendedor por ID");
            System.out.println("6) Remover vendedor");
            System.out.println("0) Voltar");
            op = lerInt("Escolha: ");
            try {
                switch (op) {
                    case 1 -> criarVendedor();
                    case 2 -> listarVendedor();
                    case 3 -> atualizarVendedor();
                    case 4 -> buscarVendedorPorNome();
                    case 5 -> buscarVendedorPorId();
                    case 6 -> removerVendedor();
                    case 0 -> System.out.println("Voltando. . .");
                    default -> System.out.println("Opção inválida.");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Erro de validação: " + e.getMessage());

            } catch (SQLException e) {
                System.out.println("Erro no banco: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (op!=0);
    }
    
    public void menuVendas() {
        int op;
        do {
            System.out.println("1) Criar ID de venda");
            System.out.println("2) Venda à vista");
            System.out.println("3) Venda parcelada");
            System.out.println("4) Listar status de vendas");
            System.out.println("5) Listar itens de venda");
            System.out.println("6) Exibir faturamento");
            System.out.println("0) Voltar");
            op = lerInt("Escolha: ");
            try {
                switch (op) {
                    case 1 -> criarVenda();
                    case 2 -> criarVendaAVista();
                    case 3 -> criarVendaParcelada();
                    case 4 -> listarVendas();
                    case 5 -> listarItemVenda();
                    case 6 -> exibirFaturamento();
                    case 0 -> System.out.println("Voltando. . .");
                    default -> System.out.println("Opção inválida.");
                } 
            } catch (IllegalArgumentException e) {
                System.out.println("Erro de validaç?o: " + e.getMessage());

            } catch (SQLException e) {
                System.out.println("Erro no banco: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (op!=0);
    }
    
    private void criarVenda() throws SQLException {
        Integer id_cliente = lerInt("Digite o ID do cliente: ");
        Integer id_vendedor = lerInt("Digite o ID do vendedor: ");
        String status = lerLinha("Digite o status da venda (A/R): ");
        int id = controller.criarVenda(id_cliente, id_vendedor, status);
        System.out.println("Venda criada com o ID: " + id);    
    }
    
    private void criarVendaAVista() throws SQLException {
        int id_venda = lerInt("Digite o ID da venda: ");
        int qtd_item = lerInt("Digite a quantidade de itens: ");
        double total = 0;
        
        for (int i=0;i<qtd_item; i++) {
            String nome = lerLinha("Digite o nome do item: ");
            double qtd = lerDouble("Quantidade: ");
            double val = lerDouble("Valor unitário: ");
            total += vista.calcularTotal(qtd, val);
            int id = controller.criarVendaAVista(id_venda, nome, qtd, val);
            System.out.println("Item de venda criado com o ID: " + id);
        }
        
        System.out.println("Total: " + total);
    }
    
    private void criarVendaParcelada() throws SQLException {
        int id_venda = lerInt("Digite o ID da venda: ");
        int qtd_item = lerInt("Digite a quantidade de itens: ");
        double total = 0;
        
        for (int i=0;i<qtd_item; i++) {
            String nome = lerLinha("Digite o nome do item: ");
            double qtd = lerDouble("Quantidade: ");
            double val = lerDouble("Valor unitário: ");
            total += parcelada.calcularTotal(qtd, val);
            int id = controller.criarVendaAVista(id_venda, nome, qtd, val);
            System.out.println("Item de venda criado com o ID: " + id);
        }
        
        System.out.println("Total: " + total);
    }

    private void criarCliente() throws SQLException {
        String nome = lerLinha("Nome: ");
        String documento = lerLinha("Documento (RG ou CPF): ");
        String contato = lerLinha("Contato (Email ou telefone): ");

        int id = controller.criarCliente(nome, documento, contato);
        System.out.println("Cliente criado com ID: " + id);
    }
    
    
    private void criarVendedor() throws SQLException {
        String nome = lerLinha("Nome: ");
        double comm = lerDouble("Percentual de comissão: ");

        int id = controller.criarVendedor(nome, comm);
        System.out.println("Vendedor criado com ID: " + id);
    }
    
    private void listarVendas() throws SQLException {
        
        List<Venda> lista = controller.listarVenda();

        if (lista.isEmpty()) {
            System.out.println();
            System.out.println("Nenhuma venda cadastrada.");
            System.out.println();
            return;
        }

        for (Venda c : lista) {
            System.out.println();
            System.out.println("ID da venda: " + c.getId());
            System.out.println("ID do cliente: " + c.getIdCliente());
            System.out.println("ID do vendedor: " + c.getIdVendedor());
            System.out.println("Status da venda (A - Aprovado / R - Reprovada): " + c.getStatus());
            System.out.println();
        }
    }

    private void listarItemVenda() throws SQLException {
        
        List<TipoVenda> lista = controller.listarTipoVenda();

        if (lista.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum item cadastrado.");
            System.out.println();
            return;
        }

        for (TipoVenda c : lista) {
            System.out.println();
            System.out.println("ID do item: " + c.getId());
            System.out.println("ID da venda: " + c.getIdVenda());
            System.out.println("Nome: " + c.getNome());
            System.out.println("Quantidade: " + c.getQtd());
            System.out.println("Valor Unitário: " + c.getValorUnit());
            System.out.println();
        }
    }
    
    private void listarCliente() throws SQLException {
        
        List<Cliente> lista = controller.listarCliente();

        if (lista.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum cliente cadastrado.");
            System.out.println();
            return;
        }

        for (Cliente v : lista) {
            System.out.println();
            System.out.println("ID: " + v.getId());
            System.out.println("Nome: " + v.getNome());
            System.out.println("Documento: " + v.getDocumento());
            System.out.println("Contato: " + v.getContato());
            System.out.println();
        }
    }
    
    private void listarVendedor() throws SQLException {
        
        List<Vendedor> lista = controller.listarVendedor();

        if (lista.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum vendedor cadastrado.");
            System.out.println();
            return;
        }

        for (Vendedor v : lista) {
            System.out.println();
            System.out.println("ID: " + v.getId());
            System.out.println("Nome: " + v.getNome());
            System.out.println("Percentual de comissão: " + v.getComm());
            System.out.println();
        }
    }

    private void buscarClientePorId() throws SQLException {
        int id = lerInt("ID: ");

        Cliente c = controller.buscarCliente(id);

        if (c == null) {
            System.out.println();
            System.out.println("Não encontrado.");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());
            System.out.println("Documento (RG ou CPF): " + c.getDocumento());
            System.out.println("Contato: " + c.getContato());
            System.out.println();

        }
    }
    
    private void buscarVendedorPorId() throws SQLException {
        int id = lerInt("ID: ");

        Vendedor v = controller.buscarVendedor(id);

        if (v == null) {
            System.out.println();
            System.out.println("Não encontrado.");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("ID: " + v.getId());
            System.out.println("Nome: " + v.getNome());
            System.out.println("Percentual de comissão: " + v.getComm());
            System.out.println();
        }
    }

    private void buscarClientePorNome() throws SQLException {
        String trecho = lerLinha("Nome: ");

        List<Cliente> lista = controller.buscarClientePorNome(trecho);

        if (lista.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum encontrado.");
            System.out.println();
            return;
        }

        for (Cliente c : lista) {
            System.out.println();
            System.out.println("ID: " + c.getId());
            System.out.println("Nome: " + c.getNome());
            System.out.println("Documento (RG ou CPF): " + c.getDocumento());
            System.out.println("Contato: " + c.getContato());
            System.out.println();
        }
    }
    
    private void buscarVendedorPorNome() throws SQLException {
        String trecho = lerLinha("Nome: ");

        List<Vendedor> lista = controller.buscarVendedorPorNome(trecho);

        if (lista.isEmpty()) {
            System.out.println();
            System.out.println("Nenhum encontrado.");
            System.out.println();
            return;
        }

        for (Vendedor v : lista) {
            System.out.println();
            System.out.println("ID: " + v.getId());
            System.out.println("Nome: " + v.getNome());
            System.out.println("Percentual de comissão: " + v.getComm());
            System.out.println();
        }
    }

    private void atualizarCliente() throws SQLException {
        int id = lerInt("ID: ");
        String nome = lerLinha("Novo nome: ");
        String documento = lerLinha("Novo documento (RG ou CPF): ");
        String contato = lerLinha("Novo contato: ");

        boolean ok = controller.atualizarCliente(id, nome, documento, contato);

        System.out.println(ok ? "Atualizado com sucesso." : "ID não encontrado.");
    }
    
    private void atualizarVendedor() throws SQLException {
        int id = lerInt("ID: ");
        String nome = lerLinha("Novo nome: ");
        double comm = lerDouble("Nova comissão: ");
        
        boolean ok = controller.atualizarVendedor(id, nome, comm);

        System.out.println(ok ? "Atualizado com sucesso." : "ID não encontrado.");
    }

    private void removerCliente() throws SQLException {
        int id = lerInt("ID: ");

        boolean ok = controller.removerCliente(id);

        System.out.println(ok ? "Removido com sucesso." : "ID não encontrado.");
    }
    
    private void removerVendedor() throws SQLException {
        int id = lerInt("ID: ");

        boolean ok = controller.removerVendedor(id);

        System.out.println(ok ? "Removido com sucesso." : "ID não encontrado.");
    }
    
    private void exibirFaturamento() throws SQLException {
        System.out.println("Faturamento: " + controller.exibirFaturamento());
    }

    private int lerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());

            } catch (Exception e) {
                System.out.println("Digite número válido.");
            }
        }
    }
    
    private double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine());

            } catch (Exception e) {
                System.out.println("Digite número válido.");
            }
        }
    }

    private String lerLinha(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }
}