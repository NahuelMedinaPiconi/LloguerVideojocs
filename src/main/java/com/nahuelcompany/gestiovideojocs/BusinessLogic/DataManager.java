/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nahuelcompany.gestiovideojocs.BusinessLogic;

import com.nahuelcompany.gestiovideojocs.Exceptions.BusinessException;
import com.nahuelcompany.gestiovideojocs.Missatges;
import com.nahuelcompany.gestiovideojocs.dto.Client;
import com.nahuelcompany.gestiovideojocs.dto.Game;
import com.nahuelcompany.gestiovideojocs.dto.Platform;
import com.nahuelcompany.gestiovideojocs.dto.Rental;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DataManager {

    public static final int RENTALDAYS = 7;

    private static final List<Client> clients = new ArrayList<>();
    private static final List<Game> games = new ArrayList<>();
    private static final List<Rental> rentals = new ArrayList<>();

    private static int nextClientId = 1;
    private static int nextGameId = 1;
    private static int nextRentalId = 1;

    public DataManager() {
        loadInitialData();
    }

    public static List<Client> getClients() {
        return clients;
    }

    public static List<Game> getGames() {
        return games;
    }

    public static List<Rental> getRentals() {
        return rentals;
    }
    
    //Gestió clients

    public static Client addClient(String name, String nationalId, String phone, LocalDate registrationDate)
        throws BusinessException {
    if (name == null || name.isBlank()
            || nationalId == null || nationalId.isBlank()
            || phone == null || phone.isBlank()) {
        throw new BusinessException(Missatges.get("error.client.incomplet"));
    }
    for (Client c : clients) {
        if (c.getNationalId().equalsIgnoreCase(nationalId.trim())) {
            throw new BusinessException(Missatges.get("error.client.duplicate"));
        }
    }
    LocalDate date = (registrationDate != null) ? registrationDate : LocalDate.now();
    Client client = new Client(nextClientId++, name.trim(), nationalId.trim().toUpperCase(),
            phone.trim(), date);
    clients.add(client);
    return client;
}

    public static void removeClient(Client client) throws BusinessException {
        if (client == null) {
            throw new BusinessException(Missatges.get("error.client.incomplet"));
        }
        if (hasActiveRentals(client)) {
            throw new BusinessException(Missatges.get("error.client.activeRentals"));
        }
        clients.remove(client);
    }

    public static boolean hasActiveRentals(Client client) {
        for (Rental r : rentals) {
            if (!r.isReturned() && r.getClient().equals(client)) {
                return true;
            }
        }
        return false;
    }

    // Gestió de jocs

    public static Game addGame(String title, String genre, Map<Platform, Integer> stock)
            throws BusinessException {
        if (title == null || title.isBlank()) {
            throw new BusinessException(Missatges.get("error.client.incomplet"));
        }
        if (stock == null || stock.isEmpty()) {
            throw new BusinessException(Missatges.get("error.rental.noStock"));
        }
        for (int units : stock.values()) {
            if (units < 0) {
                throw new BusinessException(Missatges.get("error.rental.invalidDates"));
            }
        }
        Game game = new Game(nextGameId++, title.trim(), genre, stock);
        games.add(game);
        return game;
    }

    public static void removeGame(Game game) throws BusinessException {
        if (game == null) {
            throw new BusinessException(Missatges.get("error.client.incomplet"));
        }
        if (hasActiveRentals(game)) {
            throw new BusinessException(Missatges.get("error.game.activeRentals"));
        }
        games.remove(game);
    }

    public static boolean hasActiveRentals(Game game) {
        for (Rental r : rentals) {
            if (!r.isReturned() && r.getGame().equals(game)) {
                return true;
            }
        }
        return false;
    }

    /** Gestió de plataformes disponibles segons el joc
     * @param game
     * @return  */
    public List<Platform> getAvailablePlatforms(Game game) {
        List<Platform> available = new ArrayList<>();
        if (game != null) {
            for (Map.Entry<Platform, Integer> e : game.getStock().entrySet()) {
                if (e.getValue() > 0) {
                    available.add(e.getKey());
                }
            }
        }
        return available;
    }

    public static int getAvailableUnits(Game game, Platform platform) {
        return game.getStock().getOrDefault(platform, 0);
    }

    // -Gestió lloguers

    public static Rental rentGame(Client client, Game game, Platform platform,
                            LocalDate start, LocalDate due) throws BusinessException {
        if (client == null || game == null || platform == null || start == null || due == null) {
            throw new BusinessException(Missatges.get("error.client.incomplet"));
        }
        if (due.isBefore(start)) {
            throw new BusinessException(Missatges.get("error.rental.invalidDates"));
        }
        if (getAvailableUnits(game, platform) <= 0) {
            throw new BusinessException(Missatges.get("error.rental.noStock"));
        }
        if (hasActiveRental(client, game)) {
            throw new BusinessException(Missatges.get("error.rental.duplicate"));
        }
        return createRental(client, game, platform, start, due);
    }

    public static void returnRental(Rental rental) throws BusinessException {
        if (rental == null) {
            throw new BusinessException(Missatges.get("error.client.incomplet"));
        }
        if (rental.isReturned()) {
            throw new BusinessException(Missatges.get("error.rental.invalidDates"));
        }
        rental.setReturned(true);
        rental.setReturnDate(LocalDate.now());
        Map<Platform, Integer> stock = rental.getGame().getStock();
        stock.put(rental.getPlatform(), stock.getOrDefault(rental.getPlatform(), 0) + 1);
    }

    // Comprovació de si el lloguer ha caducat.
    public static boolean isOverdue(Rental rental) {
        return !rental.isReturned() && LocalDate.now().isAfter(rental.getDueDate());
    }

    private static boolean hasActiveRental(Client client, Game game) {
        for (Rental r : rentals) {
            if (!r.isReturned() && r.getClient().equals(client) && r.getGame().equals(game)) {
                return true;
            }
        }
        return false;
    }

    // Crea un lloguer i baixa una unitat de les plataformes.
    private static Rental createRental(Client client, Game game, Platform platform, LocalDate start, LocalDate due) {
        game.getStock().put(platform, game.getStock().get(platform) - 1);
        Rental rental = new Rental(nextRentalId++, client, game, platform, start, due);
        rentals.add(rental);
        return rental;
    }

    // Carregar dades inicials

    private static void loadInitialData() {
        try {
            Client marc = addClient("Marc Rosselló", "39375629E", "696477333", LocalDate.now());
            Client laura = addClient("Laura Vidal", "49336654A", "736633533", LocalDate.now());

            Map<Platform, Integer> stock1 = new EnumMap<>(Platform.class);
            stock1.put(Platform.PS5, 2);
            stock1.put(Platform.SWITCH_2, 1);
            Game zelda = addGame("The Legend of Zelda", "Aventura", stock1);

            Map<Platform, Integer> stock2 = new EnumMap<>(Platform.class);
            stock2.put(Platform.XBOX, 1);
            stock2.put(Platform.PC, 2);
            Game halo = addGame("Halo Infinite", "Acció", stock2);

            LocalDate today = LocalDate.now();
            
            createRental(marc, zelda, Platform.SWITCH_2, today, today.plusDays(RENTALDAYS));
            
            createRental(laura, halo, Platform.XBOX, today.minusDays(14), today.minusDays(7));
        } catch (BusinessException e) {
            throw new IllegalStateException("Error carregant les dades inicials", e);
        }
    }
}