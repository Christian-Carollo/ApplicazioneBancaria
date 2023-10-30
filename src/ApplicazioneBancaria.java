
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicazioneBancaria {

    private static List<ContoUtente> utenti = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Benvenuto nell'applicazione bancaria!");
        int scelta;

        do {
            System.out.println("Scegli un'opzione:");
            System.out.println("1. Crea un nuovo account");
            System.out.println("2. Effettua il login");
            System.out.println("3. Esci");
            scelta = input.nextInt();

            switch (scelta) {
                case 1:
                    creaAccount();
                    break;
                case 2:
                    effettuaLogin();
                    break;
                case 3:
                    System.out.println("Grazie per aver utilizzato l'applicazione bancaria!");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        } while (scelta != 3);

        input.close();
    }

    private static void creaAccount() {
        System.out.print("Inserisci il tuo nome: ");
        String nome = input.next();
        System.out.print("Inserisci la tua email: ");
        String email = input.next();
        System.out.print("Inserisci una password: ");
        String password = input.next();

        ContoUtente nuovoUtente = new ContoUtente(nome, email, password);
        utenti.add(nuovoUtente);

        System.out.println("Account creato con successo!");
    }

    private static void effettuaLogin() {
        System.out.print("Inserisci l'email: ");
        String email = input.next();
        System.out.print("Inserisci la password: ");
        String password = input.next();

        ContoUtente utente = cercaUtente(email);

        if (utente != null && utente.getPassword().equals(password)) {
            gestisciAccount(utente);
        } else {
            System.out.println("Login fallito. Email o password errate.");
        }
    }

    private static ContoUtente cercaUtente(String email) {
        for (ContoUtente utente : utenti) {
            if (utente.getEmail().equals(email)) {
                return utente;
            }
        }
        return null;
    }

    private static void gestisciAccount(ContoUtente utente) {
        System.out.println("Benvenuto, " + utente.getNome() + "!");
        int scelta;

        do {
            System.out.println("Scegli un'opzione:");
            System.out.println("1. Visualizza saldo");
            System.out.println("2. Effettua un deposito");
            System.out.println("3. Effettua un prelievo");
            System.out.println("4. Visualizza storico transazioni");
            System.out.println("5. Effettua il logout");
            scelta = input.nextInt();

            switch (scelta) {
                case 1:
                    utente.visualizzaSaldo();
                    break;
                case 2:
                    utente.effettuaDeposito();
                    break;
                case 3:
                    utente.effettuaPrelievo();
                    break;
                case 4:
                    utente.visualizzaStoricoTransazioni();
                    break;
                case 5:
                    System.out.println("Hai effettuato il logout.");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        } while (scelta != 5);
    }
}

class ContoUtente {
    private String nome;
    private String email;
    private String password;
    private double saldo;
    private List<Transazione> transazioni = new ArrayList<>();

    public ContoUtente(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.saldo = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void visualizzaSaldo() {
        System.out.println("Il tuo saldo attuale è: $" + saldo);
    }

    public void effettuaDeposito() {
        System.out.print("Inserisci l'importo da depositare: $");
        Scanner input = new Scanner(System.in);
        double deposito = input.nextDouble();
        if (deposito > 0) {
            saldo += deposito;
            transazioni.add(new Transazione("Deposito", deposito));
            System.out.println("Hai depositato $" + deposito);
        } else {
            System.out.println("L'importo del deposito deve essere positivo.");
        }
    }

    public void effettuaPrelievo() {
        System.out.print("Inserisci l'importo da prelevare: $");
        Scanner input = new Scanner(System.in);
        double prelievo = input.nextDouble();
        if (saldo >= prelievo && prelievo > 0) {
            saldo -= prelievo;
            transazioni.add(new Transazione("Prelievo", prelievo));
            System.out.println("Hai prelevato $" + prelievo);
        } else {
            System.out.println("Fondi insufficienti o importo non valido per il prelievo.");
        }
    }

    public void visualizzaStoricoTransazioni() {
        System.out.println("Storico delle transazioni:");
        for (Transazione transazione : transazioni) {
            System.out.println(transazione);
        }
    }
}

class Transazione {
    private String tipo;
    private double importo;

    public Transazione(String tipo, double importo) {
        this.tipo = tipo;
        this.importo = importo;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Importo: $" + importo;
    }
}
