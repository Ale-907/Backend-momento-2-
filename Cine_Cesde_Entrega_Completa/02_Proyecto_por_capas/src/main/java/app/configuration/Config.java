package app.configuration;

import app.repository.*;
import app.service.*;
import app.service.inputports.*;
import app.service.outputports.*;
import app.service.helpers.DatosRelacionados;
import app.domain.enums.ClasificacionEnum;
import app.view.*;
import app.ui.UserInterface;

public class Config {
    public static UserInterface createUserInterface() {
        ClienteRepositoryPort clienteRepository = new ClienteRepositoryImp();
        PeliculaRepositoryPort peliculaRepository = new PeliculaRepositoryImp();
        ComboComidaRepositoryPort comboComidaRepository = new ComboComidaRepositoryImp();
        CineRepositoryPort cineRepository = new CineRepositoryImp();
        Sala2DRepositoryPort sala2DRepository = new Sala2DRepositoryImp();
        Sala3DRepositoryPort sala3DRepository = new Sala3DRepositoryImp();
        TaquilleroRepositoryPort taquilleroRepository = new TaquilleroRepositoryImp();
        ConfiteroRepositoryPort confiteroRepository = new ConfiteroRepositoryImp();
        TicketRepositoryPort ticketRepository = new TicketRepositoryImp();
        DatosRelacionados datos = new DatosRelacionados(clienteRepository, peliculaRepository,
                comboComidaRepository, cineRepository, sala2DRepository, sala3DRepository, ticketRepository);
        AsientoRepositoryPort asientoRepository = new AsientoRepositoryImp(datos);
        ClienteServiceInterface clienteService = new ClienteServiceImp(clienteRepository, datos);
        PeliculaServiceInterface peliculaService = new PeliculaServiceImp(peliculaRepository, datos);
        ComboComidaServiceInterface comboComidaService = new ComboComidaServiceImp(comboComidaRepository, datos);
        CineServiceInterface cineService = new CineServiceImp(cineRepository, datos);
        Sala2DServiceInterface sala2DService = new Sala2DServiceImp(sala2DRepository, datos);
        Sala3DServiceInterface sala3DService = new Sala3DServiceImp(sala3DRepository, datos);
        AsientoServiceInterface asientoService = new AsientoServiceImp(asientoRepository, datos);
        TaquilleroServiceInterface taquilleroService = new TaquilleroServiceImp(taquilleroRepository);
        ConfiteroServiceInterface confiteroService = new ConfiteroServiceImp(confiteroRepository);
        TicketServiceInterface ticketService = new TicketServiceImp(ticketRepository, datos);

        // Los datos iniciales pasan por los mismos servicios que usa el menu.
        clienteService.createCliente("101", "Laura Restrepo");
        peliculaService.createPelicula(1, "La aventura del cine", 110, ClasificacionEnum.TODO_PUBLICO);
        comboComidaService.createComboComida(1, "Combo Pareja", 28000);
        cineService.createCine(1, "Cine Cesde");
        cineService.agregarPelicula(1, 1);
        sala2DService.createSala2D(1, 12, true);
        sala3DService.createSala3D(2, 8, "Basicas");
        taquilleroService.createTaquillero("T01", "Carlos Perez", 1300000, 100);
        confiteroService.createConfitero("C01", "Maria Gomez", 1300000, 35);
        ClienteView clienteView = new ClienteView(clienteService);
        PeliculaView peliculaView = new PeliculaView(peliculaService);
        ComboComidaView comboComidaView = new ComboComidaView(comboComidaService);
        CineView cineView = new CineView(cineService);
        Sala2DView sala2DView = new Sala2DView(sala2DService);
        Sala3DView sala3DView = new Sala3DView(sala3DService);
        AsientoView asientoView = new AsientoView(asientoService);
        TaquilleroView taquilleroView = new TaquilleroView(taquilleroService);
        ConfiteroView confiteroView = new ConfiteroView(confiteroService);
        TicketView ticketView = new TicketView(ticketService);
        return new UserInterface(clienteView, peliculaView, comboComidaView, cineView, sala2DView, sala3DView, asientoView, taquilleroView, confiteroView, ticketView);
    }
}
