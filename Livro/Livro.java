package Livro;

import java.util.ArrayList;

import NotificarObserver.Observer;
import NotificarObserver.Subject;
import Reserva.Reserva;
import Usuario.Usuario;

public class Livro implements Subject {

    public String codigoIdentificacao;
    public String titulo;
    String editora;
    String autores;
    String edicao;
    int anoDaPublicação;

    private ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();

    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public Livro(String codigoIdentificacao, String titulo, String editora, String autores, String edicao,
            int anoDaPublicação) {
        this.observers = new ArrayList<Observer>();

        this.codigoIdentificacao = codigoIdentificacao;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoDaPublicação = anoDaPublicação;
    }

    public String getCodigoIdentificacao() {
        return codigoIdentificacao;
    }

    public void setCodigoIdentificacao(String codigoIdentificacao) {
        this.codigoIdentificacao = codigoIdentificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutores() {
        return autores;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public int getAnoDaPublicação() {
        return anoDaPublicação;
    }

    public void setAnoDaPublicação(int anoDaPublicação) {
        this.anoDaPublicação = anoDaPublicação;
    }

    public ArrayList<Exemplar> getExemplares() {
        return this.exemplares;
    }

    public void setExemplares(ArrayList<Exemplar> exemplares) {
        this.exemplares = exemplares;
    }

    public void addReserva(Usuario usuario, String data) {
        this.reservas.add(new Reserva(this, usuario));
        if (this.reservas.size() > 2) {
            this.notifyObservers();
        }
    }

    private final ArrayList<Observer> observers;

    @Override
    public void registerObserver(Observer observer) {
        // TODO Auto-generated method stub
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        for (Observer observer: observers) {
            observer.update();
        }
    }

    public Reserva EncontrarReservaPorIdUsuario(String id) {
        for (Reserva reserva : this.reservas) {
            if (reserva.getUsuario().getCodigoIdentificacao().equals(id)) {
                return reserva;
            }
        }
        return null;
    }

    public void adicionarExemplar(Exemplar exemplar) {
        this.exemplares.add(exemplar);
    }

    public boolean isAvailableExemplar(String id) {
        for (Exemplar exemplar: exemplares) {
            if ((exemplar.getLivro().getCodigoIdentificacao().equals(id)) && (exemplar.getEstado().status)) {
                return true;
            }
        }
        return false;
    }

    public int countAvailableExemplar(String id) {
        int soma = 0;
        for (Exemplar exemplar: exemplares) {
            if ((exemplar.getLivro().getCodigoIdentificacao().equals(id)) && (exemplar.getEstado().status)) {
                soma += 1;
            }
        }
        return soma;
    }

    public Exemplar getAvaiableExemplarByLivroId(String id) {
        for (Exemplar exemplar: exemplares) {
            if ((exemplar.getLivro().getCodigoIdentificacao().equals(id)) && (exemplar.getEstado().status)) {
                return exemplar;
            }
        }
        return null;
    }

    public Exemplar getExemplarByIdUsuarioIdLivro(String uid, String lid) {
        for (Exemplar exemplar: exemplares) {
            if (exemplar.getUltimoEmprestimo().getLivro().getCodigoIdentificacao().equals(lid)
                    && exemplar.getUltimoEmprestimo().getUsuario().getCodigoIdentificacao().equals(uid)) {
                return exemplar;
            }
        }
        return null;
    }

    public void removeReservaByIdUsuario(String id) {
        for (Reserva reserva: reservas) {
            if (reserva.getUsuario().getCodigoIdentificacao().equals(id)) {
                reservas.remove(reserva);
                break;
            }
        }
    }
}
