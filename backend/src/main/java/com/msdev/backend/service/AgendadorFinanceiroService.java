package com.msdev.backend.service;

import com.msdev.backend.entity.ExtratoMensalEntity;
import com.msdev.backend.enums.StatusExtratoMensal;
import com.msdev.backend.repository.ExtratoMensalRepository;
import com.msdev.backend.security.jwt.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;

@Service
public class AgendadorFinanceiroService {
    private static final Logger logger = LoggerFactory.getLogger(AgendadorFinanceiroService.class);
    private final ExtratoMensalRepository extratoMensalRepositor;

    public AgendadorFinanceiroService(ExtratoMensalRepository extratoMensalRepositor) {
        this.extratoMensalRepositor = extratoMensalRepositor;
    }

    @Scheduled(cron = "0 1 0 1 * ?")
    @Transactional
    public void fecharExtratoDoMesAnterior(){
        YearMonth mesAnterior = YearMonth.now().minusMonths(1);

        logger.info("iniciando tarefa agendada: Fechando extratos para o mes {}", mesAnterior);

        List<ExtratoMensalEntity> extratosParaFechar =
                extratoMensalRepositor.findAllByMesReferenciaAndStatus(mesAnterior, StatusExtratoMensal.ABERTA);

        if(extratosParaFechar.isEmpty()){
            logger.info("Nenhum extrato em aberto encontrado para {}. Tarefa concluída", mesAnterior);
            return;
        }

        logger.info("Encontrados {} extratos para fechar.", extratosParaFechar.size());

        for(ExtratoMensalEntity extrato : extratosParaFechar){
            extrato.setStatus(StatusExtratoMensal.FECHADA);

            extratoMensalRepositor.save(extrato);
        }
        logger.info("Tarefa concluída: {} extratos do mês {} foram fechados com sucesso.", extratosParaFechar.size(), mesAnterior);
    }
}
