package Usuario;

import ReservarStrategy.ReservaProfessor;

public class Professor extends Usuario {

    public static int tempoDeEmprestimoDias = 7;
    private int qtdNotificacoes;

    public Professor(String codigoIdentificacao, String nome) {
        super(codigoIdentificacao, nome);
        this.qtdNotificacoes = 0;
        this.reservaStrategy = new ReservaProfessor();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        this.setQtdNotificacoes(this.qtdNotificacoes += 1);

    }

    public int getQtdNotificacoes() {
        return qtdNotificacoes;
    }

    public void setQtdNotificacoes(int qtdNotificacoes) {
        this.qtdNotificacoes = qtdNotificacoes;
    }

}