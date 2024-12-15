package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.app.dto.MostrarRegistroDto;

import java.util.List;

@Service
public class MostrarRegistroService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MostrarRegistroDto obtenerRegistroPorSerie(String serie) {
        String sql = """
            SELECT 
                R.SERIERegistro AS serieRegistro, 
                R.IDComputadora AS idComputadora, 
                R.IDTecnico AS idTecnico, 
                E.Descripcion AS descripcionEstado, 
                R.Adelanto, 
                R.TOTAL AS total, 
                R.FechaEstimadaDeEntrega AS fechaEstimadaDeEntrega, 
                R.FechaRealDeEntrega AS fechaRealDeEntrega, 
                R.ProblemaReportado AS problemaReportado
            FROM REGISTRO AS R
            INNER JOIN ESTADO AS E
            ON R.IDEstado = E.IDEstado
            WHERE R.SERIERegistro = ?
        """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(MostrarRegistroDto.class), serie);
    }

    public List<MostrarRegistroDto> obtenerRegistrosPorDni(String dni) {
        String sql = """
            SELECT 
                R.SERIERegistro AS serieRegistro, 
                R.IDComputadora AS idComputadora, 
                R.IDTecnico AS idTecnico, 
                E.Descripcion AS descripcionEstado, 
                R.Adelanto, 
                R.TOTAL AS total, 
                R.FechaEstimadaDeEntrega AS fechaEstimadaDeEntrega, 
                R.FechaRealDeEntrega AS fechaRealDeEntrega, 
                R.ProblemaReportado AS problemaReportado
            FROM REGISTRO AS R
            INNER JOIN ESTADO AS E
            ON R.IDEstado = E.IDEstado
            INNER JOIN CLIENTE AS C
            ON C.IDCliente = R.IDCliente
            WHERE C.ClienteDni = ?
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarRegistroDto.class), dni);
    }

    public List<MostrarRegistroDto> obtenerTodosLosRegistros() {
        String sql = """
            SELECT 
                R.SERIERegistro AS serieRegistro, 
                R.IDComputadora AS idComputadora, 
                R.IDTecnico AS idTecnico, 
                E.Descripcion AS descripcionEstado, 
                R.Adelanto, 
                R.TOTAL AS total, 
                R.FechaEstimadaDeEntrega AS fechaEstimadaDeEntrega, 
                R.FechaRealDeEntrega AS fechaRealDeEntrega, 
                R.ProblemaReportado AS problemaReportado
            FROM REGISTRO AS R
            INNER JOIN ESTADO AS E
            ON R.IDEstado = E.IDEstado
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarRegistroDto.class));
    }
}



