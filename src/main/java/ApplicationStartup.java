import entity.Client;
import entity.Planet;
import entity.Ticket;
import util.DatabaseMigration;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

import service.CrudService;
import service.impl.ClientCrudServiceImpl;
import service.impl.PlanetCrudServiceImpl;
import service.impl.TicketCrudServiceImpl;

public class ApplicationStartup {

    public static void main(String[] args) {
        loadHibernateProperties();
        migrateDatabase();
        runCrudService();
    }

    private static void loadHibernateProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/hibernate.properties"));
            properties.forEach((key, value) -> System.setProperty((String) key, (String) value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void migrateDatabase() {
        DatabaseMigration.main(null);
    }

    private static void runCrudService() {
        CrudService<Client, Long> clientService = new ClientCrudServiceImpl();
        CrudService<Planet, String> planetService = new PlanetCrudServiceImpl();
        CrudService<Ticket, Long> ticketService = new TicketCrudServiceImpl();

        Client client = new Client();
        client.setName("Jane Do");
        Client createdClient = clientService.create(client);
        System.out.println("Created client with ID: " + createdClient.getId());


        Planet planet = new Planet();
        planet.setId("EARTH");
        planet.setName("Earth");
        Planet createdPlanet = planetService.create(planet);
        System.out.println("Created planet with ID: " + createdPlanet.getId());


        Client clientId = clientService.getById(2L);
        Planet fromPlanet = planetService.getById("MARS");
        Planet toPlanet = planetService.getById("VEN");

        Ticket ticket = new Ticket();
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setClient(clientId);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);

        Ticket createdTicket = ticketService.create(ticket);
        System.out.println("Created ticket with ID: " + createdTicket.getId());


        clientService.delete(client.getId());
        System.out.println("Client deleted successfully.");

        planetService.delete(planet.getId());
        System.out.println("Planet deleted successfully.");

        Long ticketIdToDelete = 1L;
        ticketService.delete(ticketIdToDelete);
        System.out.println("Ticket deleted successfully.");

    }
}


