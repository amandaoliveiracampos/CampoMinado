package test.br.com.amanda.campominado.modelo;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.br.com.amanda.campominado.excecao.ExplosaoException;
import main.br.com.amanda.campominado.modelo.Campo;

public class CampoTest {

    public Campo campo;

    @BeforeEach
    public void iniciarCampos() {
        campo = new Campo(3, 3);
    }

    @Test
    public void testeVizinhoDistancia1() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    public void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    public void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    public void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    public void testeAbrirNaoMinadoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    public void testeNaoAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    public void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    public void testeAbrirMinadoNaoMarcado() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> campo.abrir());
    }

    @Test
    public void testeAbrirComVizinho1() {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    public void testeAbrirComVizinho2() {
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }
}
