package med.vol.api.controller;

import med.vol.api.domain.consulta.dto.DadosAgendamentoConsulta;
import med.vol.api.domain.consulta.dto.DadosDetalhamentoConsulta;
import med.vol.api.domain.consulta.service.ConsultaService;
import med.vol.api.domain.medico.enuns.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deveria devolver código 400 quando as informações estão inválidas")
    @WithMockUser //mocka um usuario para ignora o spring security
    void agendar_cenario1() throws Exception {
        //when ou act -> quando disparar essa ação
        var response = mvc.perform(post("/consultas"))
        .andReturn().getResponse();

        //then ou assert -> resultado esperado
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando as informações estão válidas")
    @WithMockUser //mocka um usuario para ignora o spring security
    void agendar_cenario2() throws Exception {
        //given -> dado
        var data = LocalDateTime.now().plusHours(12);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2L, 5L, data);

        //when ou act -> quando disparar essa ação
        when(consultaService.agendar(any())).thenReturn(dadosDetalhamento);
        var response = mvc.
                perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new DadosAgendamentoConsulta(2L,5L, data, especialidade)
                                ).getJson())
                ).
                andReturn().getResponse();

        //then ou assert -> resultado esperado
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamento).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}