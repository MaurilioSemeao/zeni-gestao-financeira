package com.msdev.backend.config;

import com.msdev.backend.entity.*;
import com.msdev.backend.enums.MeioPagamento;
import com.msdev.backend.enums.StatusExtratoMensal;
import com.msdev.backend.enums.TipoTransacao;
import com.msdev.backend.enums.TipoUsuario;
import com.msdev.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ExtratoMensalRepository extratoMensalRepository;
    private final TransacaoRepository transacaoRepository;
    private final CarteiraRepository carteiraRepository;
    private final CartaoRepository cartaoRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            ExtratoMensalRepository extratoMensalRepository,
            TransacaoRepository transacaoRepository,
            CarteiraRepository carteiraRepository,
            CartaoRepository cartaoRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.extratoMensalRepository = extratoMensalRepository;
        this.transacaoRepository = transacaoRepository;
        this.carteiraRepository = carteiraRepository;
        this.cartaoRepository = cartaoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private CategoriaEntity getCategoria(String nome, Long usuarioId) {
        return categoriaRepository.findAllByUsuarioId(usuarioId).stream()
                .filter(c -> c.getNome().equals(nome))
                .findFirst().orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + nome));
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Verifica se o usuário teste já existe
        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByEmail("teste@teste.com");
        if (usuarioExistente.isPresent()) {
            System.out.println("Usuário de teste já existe. Pulando a inserção automática (Seeder).");
            return;
        }

        System.out.println("Iniciando injeção de dados (DataSeeder)...");

        // 1. Criar Usuário
        UsuarioEntity usuario = new UsuarioEntity(
                "Usuário Teste",
                "teste@teste.com",
                passwordEncoder.encode("12345678"),
                TipoUsuario.PADRAO
        );
        usuario = usuarioRepository.save(usuario);

        // 2. Criar Carteira
        CarteiraEntity carteira = new CarteiraEntity();
        carteira.setName("Minha Carteira Principal");
        carteira.setUsuario(usuario);
        carteira.setSaldo(new BigDecimal("5000.00"));
        carteira = carteiraRepository.save(carteira);

        // 3. Criar Categorias
        List<String> nomesCategorias = Arrays.asList("Alimentação", "Transporte", "Lazer", "Moradia", "Saúde", "Salário");
        for (String nomeCat : nomesCategorias) {
            CategoriaEntity cat = new CategoriaEntity(nomeCat, true, usuario);
            categoriaRepository.save(cat);
        }

        // 3.5 Criar Cartões de Crédito
        CartaoEntity cartaoNubank = new CartaoEntity("Nubank Roxinho", "1234", usuario);
        CartaoEntity cartaoItau = new CartaoEntity("Itaú Personnalité", "5678", usuario);
        CartaoEntity cartaoXp = new CartaoEntity("XP Gold", "9012", usuario);
        
        cartaoNubank = cartaoRepository.save(cartaoNubank);
        cartaoItau = cartaoRepository.save(cartaoItau);
        cartaoXp = cartaoRepository.save(cartaoXp);

        final Long usuarioId = usuario.getId();

        CategoriaEntity catAlimentacao = getCategoria("Alimentação", usuarioId);
        CategoriaEntity catSalario = getCategoria("Salário", usuarioId);

        // 4. Criar Extratos e Transações (8 meses atrás até hoje)
        YearMonth mesAtual = YearMonth.now();
        java.util.Random random = new java.util.Random();
        
        for (int i = 8; i >= 0; i--) {
            YearMonth mes = mesAtual.minusMonths(i);
            
            ExtratoMensalEntity extrato = new ExtratoMensalEntity();
            extrato.setUsuario(usuario);
            extrato.setMesReferencia(mes);
            extrato.setStatus(i == 0 ? StatusExtratoMensal.ABERTA : StatusExtratoMensal.FECHADA);
            extrato.setSaldoExtrato(BigDecimal.ZERO);
            extrato = extratoMensalRepository.save(extrato);

            BigDecimal saldoMensal = BigDecimal.ZERO;

            // --- RECEITAS ---
            // 1. Salário do Mês (Dia 05)
            saldoMensal = saldoMensal.add(criarTransacao(extrato, carteira, usuario, "Salário do Mês", TipoTransacao.RECEITA, MeioPagamento.PIX, mes.atDay(5).atTime(8, 0), "5500.00", catSalario, null));

            // --- DESPESAS FIXAS (Moradia) ---
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Aluguel", TipoTransacao.DESPESA, MeioPagamento.PIX, mes.atDay(6).atTime(10, 0), "1500.00", getCategoria("Moradia", usuarioId), null));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Conta de Luz", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(10).atTime(14, 30), "180.00", getCategoria("Moradia", usuarioId), null));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Conta de Água", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(11).atTime(9, 15), "85.00", getCategoria("Moradia", usuarioId), null));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Internet (Fibra)", TipoTransacao.DESPESA, MeioPagamento.CREDITO, mes.atDay(15).atTime(18, 0), "120.00", getCategoria("Moradia", usuarioId), cartaoNubank));

            // --- TRANSPORTE ---
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Combustível (Posto Ipiranga)", TipoTransacao.DESPESA, MeioPagamento.CREDITO, mes.atDay(8).atTime(19, 45), "200.00", getCategoria("Transporte", usuarioId), cartaoItau));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Uber (Ida Trabalho)", TipoTransacao.DESPESA, MeioPagamento.PIX, mes.atDay(12).atTime(7, 30), "35.00", getCategoria("Transporte", usuarioId), null));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Uber (Volta Trabalho)", TipoTransacao.DESPESA, MeioPagamento.PIX, mes.atDay(12).atTime(18, 15), "42.00", getCategoria("Transporte", usuarioId), null));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Estacionamento Shopping", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(20).atTime(21, 0), "25.00", getCategoria("Transporte", usuarioId), null));

            // --- ALIMENTAÇÃO ---
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Supermercado (Compra do Mês)", TipoTransacao.DESPESA, MeioPagamento.CREDITO, mes.atDay(7).atTime(11, 20), "600.00", catAlimentacao, cartaoItau));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Padaria (Café da Manhã)", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(10).atTime(7, 45), "18.50", catAlimentacao, null));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Padaria (Café da Manhã)", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(18).atTime(8, 10), "22.00", catAlimentacao, null));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "iFood (Lanche da Tarde)", TipoTransacao.DESPESA, MeioPagamento.CREDITO, mes.atDay(14).atTime(16, 30), "45.00", catAlimentacao, cartaoNubank));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Almoço (Restaurante Self-Service)", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(15).atTime(12, 15), "35.00", catAlimentacao, null));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Almoço (Restaurante Self-Service)", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(22).atTime(12, 30), "40.00", catAlimentacao, null));

            // --- LAZER (Variável + Fixo) ---
            CategoriaEntity catLazer = getCategoria("Lazer", usuarioId);
            // Assinaturas fixas
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Assinatura Netflix", TipoTransacao.DESPESA, MeioPagamento.CREDITO, mes.atDay(3).atTime(10, 0), "45.00", catLazer, cartaoXp));
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Assinatura Spotify", TipoTransacao.DESPESA, MeioPagamento.CREDITO, mes.atDay(10).atTime(9, 0), "21.90", catLazer, cartaoXp));
            
            // Variável Lazer (Com variação de valor a cada mês)
            double variacaoHappyHour = 80.00 + (40.00 * random.nextDouble()); // Entre 80 e 120
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Happy Hour com Amigos", TipoTransacao.DESPESA, MeioPagamento.CREDITO, mes.atDay(14).atTime(22, 30), String.format(java.util.Locale.US, "%.2f", variacaoHappyHour), catLazer, cartaoItau));
            
            double variacaoCinema = 60.00 + (30.00 * random.nextDouble()); // Entre 60 e 90
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Cinema + Pipoca", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(25).atTime(19, 0), String.format(java.util.Locale.US, "%.2f", variacaoCinema), catLazer, null));
            
            double variacaoLivro = 40.00 + (30.00 * random.nextDouble()); // Entre 40 e 70
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Livro (Amazon)", TipoTransacao.DESPESA, MeioPagamento.CREDITO, mes.atDay(28).atTime(14, 20), String.format(java.util.Locale.US, "%.2f", variacaoLivro), catLazer, cartaoNubank));

            // --- SAÚDE ---
            saldoMensal = saldoMensal.subtract(criarTransacao(extrato, carteira, usuario, "Farmácia (Analgésicos/Cosméticos)", TipoTransacao.DESPESA, MeioPagamento.DEBITO, mes.atDay(17).atTime(15, 40), "65.00", getCategoria("Saúde", usuarioId), null));

            extrato.setSaldoExtrato(saldoMensal);
            extratoMensalRepository.save(extrato);
            
            // Atualiza saldo da carteira com o saldo daquele mês (no final do loop, refletirá o saldo de 8 meses simulados)
            carteira.setSaldo(carteira.getSaldo().add(saldoMensal));
            carteiraRepository.save(carteira);
        }

        System.out.println("DataSeeder concluído! Usuário, Categorias, Extratos, 3 Cartões de Crédito e 21 Transações por mês inseridos com sucesso (8 meses).");
    }

    private BigDecimal criarTransacao(ExtratoMensalEntity extrato, CarteiraEntity carteira, UsuarioEntity usuario, String descricao, TipoTransacao tipo, MeioPagamento meioPagamento, LocalDateTime data, String valorStr, CategoriaEntity categoria, CartaoEntity cartao) {
        BigDecimal valor = new BigDecimal(valorStr);
        TransacaoEntity t = new TransacaoEntity(descricao, tipo, meioPagamento, false, data, usuario);
        t.setValor(valor);
        t.setCategoria(categoria);
        t.setExtrato(extrato);
        t.setCarteira(carteira);
        t.setCartao(cartao);
        transacaoRepository.save(t);

        if (cartao != null) {
            cartao.setQuantidadeCompras(cartao.getQuantidadeCompras() + 1);
            cartao.setGastos(cartao.getGastos().add(valor));
            cartaoRepository.save(cartao);
        }

        return valor;
    }
}
