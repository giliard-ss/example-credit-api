package com.example.credit.repository;

import com.example.credit.entity.Credito;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class CreditoRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private CreditoRepository creditoRepository;


    @Test
    void shouldReturnEntitiesWhenNumeroNfseExists() {
        Credito credito = this.createCredito();

        List<Credito> creditos = this.creditoRepository.findAllByNumeroNfse(credito.getNumeroNfse());

        Assertions.assertFalse(creditos.isEmpty());
        Assertions.assertEquals(1, creditos.size());
        Assertions.assertEquals(credito.getNumeroNfse(), creditos.get(0).getNumeroNfse());
    }

    @Test
    void shouldReturnEmptyListWhenNumeroNfseDoesNotExist() {
        List<Credito> creditos = this.creditoRepository.findAllByNumeroNfse("7891011");
        Assertions.assertTrue(creditos.isEmpty());
    }

    @Test
    void shouldReturnEntityWhenNumeroCreditoExists() {
        Credito credito = this.createCredito();

        Optional<Credito> result = this.creditoRepository.findByNumeroCredito(credito.getNumeroCredito());

        assertThat(result).isPresent();
        assertThat(result.get().getNumeroCredito()).isEqualTo(credito.getNumeroCredito());
    }

    @Test
    void shouldReturnEmptyWhenNumeroCreditoDoesNotExists() {
        Optional<Credito> credito = this.creditoRepository.findByNumeroCredito("123456");
        assertThat(credito).isNotPresent();
    }


    private Credito createCredito() {
       Credito credito = new Credito();
       credito.setNumeroCredito("123456");
       credito.setNumeroNfse("7891011");
       credito.setDataConstituicao(LocalDate.of(2024, 2, 25));
       credito.setValorIssqn(new BigDecimal("1500.75"));
       credito.setTipoCredito("ISSQN");
       credito.setSimplesNacional(true);
       credito.setAliquota(new BigDecimal("5.0"));
       credito.setValorFaturado(new BigDecimal("30000.00"));
       credito.setValorDeducao(new BigDecimal("5000.00"));
       credito.setBaseCalculo(new BigDecimal("25000.00"));
       em.persist(credito);
       return credito;
    }
}
