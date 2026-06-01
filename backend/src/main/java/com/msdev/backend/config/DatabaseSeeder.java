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
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            ExtratoMensalRepository extratoMensalRepository,
            TransacaoRepository transacaoRepository,
            CarteiraRepository carteiraRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.extratoMensalRepository = extratoMensalRepository;
        this.transacaoRepository = transacaoRepository;
        this.carteiraRepository = carteiraRepository;
        this.passwordEncoder = passwordEncoder;
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

        final Long usuarioId = usuario.getId();

        CategoriaEntity catAlimentacao = categoriaRepository.findAll().stream()
                .filter(c -> c.getNome().equals("Alimentação") && c.getUsuario().getId().equals(usuarioId))
                .findFirst().orElseThrow();
                
        CategoriaEntity catSalario = categoriaRepository.findAll().stream()
                .filter(c -> c.getNome().equals("Salário") && c.getUsuario().getId().equals(usuarioId))
                .findFirst().orElseThrow();

        // 4. Criar Extratos e Transações (7 meses atrás até hoje)
        YearMonth mesAtual = YearMonth.now();
        
        for (int i = 7; i >= 0; i--) {
            YearMonth mes = mesAtual.minusMonths(i);
            
            ExtratoMensalEntity extrato = new ExtratoMensalEntity();
            extrato.setUsuario(usuario);
            extrato.setMesReferencia(mes);
            // Se for o mês 0 (atual), deixa ABERTA. Os meses passados ficam FECHADA.
            extrato.setStatus(i == 0 ? StatusExtratoMensal.ABERTA : StatusExtratoMensal.FECHADA);
            extrato.setSaldoExtrato(new BigDecimal("1000.00"));
            extrato = extratoMensalRepository.save(extrato);

            // Criar algumas transações para este extrato (no dia 15 daquele mês)
            LocalDateTime dataTransacao = mes.atDay(15).atTime(12, 0);

            // Transação de Receita
            TransacaoEntity receita = new TransacaoEntity(
                    "Salário do Mês",
                    TipoTransacao.RECEITA,
                    MeioPagamento.PIX,
                    false,
                    dataTransacao,
                    usuario
            );
            receita.setValor(new BigDecimal("5000.00"));
            receita.setCategoria(catSalario);
            receita.setExtrato(extrato);
            receita.setCarteira(carteira);
            transacaoRepository.save(receita);

            // Transação de Despesa
            TransacaoEntity despesa = new TransacaoEntity(
                    "Supermercado Mensal",
                    TipoTransacao.DESPESA,
                    MeioPagamento.DEBITO,
                    false,
                    dataTransacao.plusDays(2), // Dia 17
                    usuario
            );
            despesa.setValor(new BigDecimal("850.50"));
            despesa.setCategoria(catAlimentacao);
            despesa.setExtrato(extrato);
            despesa.setCarteira(carteira);
            transacaoRepository.save(despesa);
        }

        System.out.println("DataSeeder concluído! Usuário, Categorias, Extratos e Transações inseridos com sucesso.");
    }
}
