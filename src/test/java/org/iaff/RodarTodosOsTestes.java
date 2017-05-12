package org.iaff;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CadastroCidadesTest.class,
    CadastroUsuariosTest.class,
    CadastroPessoasTest.class,
    CadastroPacientesTest.class
})
public class RodarTodosOsTestes {
}
