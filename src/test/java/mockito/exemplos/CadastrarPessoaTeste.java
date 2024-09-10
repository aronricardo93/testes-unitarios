package mockito.exemplos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTeste {

    @Mock
    private ApiDosCorreios apiDosCorreios;

    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void validarDadosDeCadastro(){
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("BA","Pojuca", "Rua do bobo", "APTO", "Lambda");
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("47489000")).thenReturn(dadosLocalizacao);
        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Aron", "123456789", LocalDate.of(1993,03,03), "47489000");

        assertEquals("Aron", pessoa.getNome());
        assertEquals("123456789", pessoa.getDocumento());
        assertEquals(LocalDate.of(1993,03,03), pessoa.getNascimento());
        assertEquals("BA", pessoa.getEndereco().getUf());
        assertEquals("Pojuca", pessoa.getEndereco().getCidade());
        assertEquals("Rua do bobo", pessoa.getEndereco().getLogradouro());
        assertEquals("APTO", pessoa.getEndereco().getComplemento());
        assertEquals("Lambda", pessoa.getEndereco().getBairro());
    }
}
