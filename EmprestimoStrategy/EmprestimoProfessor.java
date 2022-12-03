package EmprestimoStrategy;

import Emprestimo.Emprestimo;
import Livro.Livro;
import Livro.Exemplar;
import LivroState.Emprestado;
import Usuario.AlunoGraduacao;
import Usuario.Usuario;

import java.time.LocalDate;

public class EmprestimoProfessor implements EmprestimoStrategy {

    @Override
    public void emprestar(Usuario usuario, Livro livro) {
        String mensagem = null;

        // TODO Auto-generated method stub
//        Boolean regra1 = livro.getExemplares().size() > 0;
        Boolean regra1 = livro.isAvailableExemplar(livro.getCodigoIdentificacao());
        if (!regra1) {
            mensagem = "Insucesso! Não há disponibilidade de exemplades desse livro!";
        }

        Boolean regra2 = !usuario.getDevedor();
        if (!regra2) {
            mensagem = "Insucesso! Usuário é devedor!";
        }

        Boolean regra3 = !(usuario.getEmprestimos().size() > AlunoGraduacao.limiteEmprestimoAberto);
        if (!regra3) {
            mensagem = "Insucesso! Usuário ultrapassou o limite de empréstimos!";
        }


//        Boolean regra4 = ((livro.getReservas().size() < livro.getExemplares().size() ? true
//                : livro.EncontrarReservaPorIdUsuario(usuario.getCodigoIdentificacao()) != null));
        Boolean regra4 = ((livro.getReservas().size() < livro.countAvailableExemplar(livro.getCodigoIdentificacao()) ? true
                : livro.EncontrarReservaPorIdUsuario(usuario.getCodigoIdentificacao()) != null));
        if (!regra4) {
            mensagem = "Insucesso! Quantidade de reservas maior do que a quantidade de exmplares disponíveis!";
        }

        Boolean regra5 = usuario.EncontrarEmprestimoPorIdLivro(livro.getCodigoIdentificacao()) == null;
        if (!regra5) {
            mensagem = "Insucesso! Qaaaaaaaaaaaa!";
        }

        if (regra1 && regra2 && regra3 && regra4 && regra5) {

            Exemplar selectExemplar = livro.getAvaiableExemplarByLivroId(livro.getCodigoIdentificacao());

            LocalDate dataDevolver = LocalDate.now().plusDays(usuario.getTempoDeEmprestimoDias());;
            Emprestimo novoEmprestimo = new Emprestimo(usuario, livro, dataDevolver);

            selectExemplar.setEstado(new Emprestado(selectExemplar));
            selectExemplar.setUltimoEmprestimo(novoEmprestimo);

            usuario.getEmprestimos().add(novoEmprestimo);
            mensagem = "Sucesso!";
        }

        System.out.println("Usuário: " + usuario.getNome() +
                "\nTítulo: " + livro.getTitulo() +
                "\nMensagem: " + mensagem);

    }

}
