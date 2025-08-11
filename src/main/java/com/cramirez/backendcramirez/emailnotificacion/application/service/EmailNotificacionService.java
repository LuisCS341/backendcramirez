package com.cramirez.backendcramirez.emailnotificacion.application.service;

import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.domain.entity.ClienteConyuge;
import com.cramirez.backendcramirez.cliente.dto.ClienteConyugeDTO;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteConyugeRepository;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository;
import com.cramirez.backendcramirez.copropietario.domain.entity.Copropietario;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioDTO;
import com.cramirez.backendcramirez.copropietario.infrastructure.repository.CopropietarioRepository;
import com.cramirez.backendcramirez.documento.infrastructure.repository.IdentificacionRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DepartamentoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DistritoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.ProvinciaRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
import com.cramirez.backendcramirez.lote.domain.entity.*;
import com.cramirez.backendcramirez.lote.dto.*;
import com.cramirez.backendcramirez.lote.infrastructure.repository.*;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.EstadoCivilRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.NacionalidadRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.TipoContratoRepository;
import com.cramirez.backendcramirez.proyecto.infrastructure.repository.ProyectoRepository;
import com.cramirez.backendcramirez.proyecto.infrastructure.repository.TipoProyectoRepository;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import javax.swing.text.Segment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmailNotificacionService {

    private final JavaMailSender mailSender;
    private final ClienteRepository clienteRepository;
    private final IdentificacionRepository identificacionRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;
    private final LoteRepository loteRepository;
    private final LinderoRepository linderoRepository;
    private final MatrizRepository matrizRepository;
    private final CuotaRepository cuotaRepository;
    private final CuotaExtraordinariaRepository cuotaExtraordinariaRepository;
    private final UbicacionRepository ubicacionRepository;
    private final NacionalidadRepository nacionalidadRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final TipoProyectoRepository tipoProyectoRepository;
    private final TipoContratoRepository tipoContratoRepository;
    private final ClienteConyugeRepository clienteConyugeRepository;
    private final CopropietarioRepository copropietarioRepository;

    public EmailNotificacionService(JavaMailSender mailSender, ClienteRepository clienteRepository, IdentificacionRepository identificacionRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository, LoteRepository loteRepository, LinderoRepository linderoRepository, MatrizRepository matrizRepository, CuotaRepository cuotaRepository, CuotaExtraordinariaRepository cuotaExtraordinariaRepository, UbicacionRepository ubicacionRepository, NacionalidadRepository nacionalidadRepository, EstadoCivilRepository estadoCivilRepository, TipoProyectoRepository tipoProyectoRepository, TipoContratoRepository tipoContratoRepository, ClienteConyugeRepository clienteConyugeRepository, CopropietarioRepository copropietarioRepository) {
        this.mailSender = mailSender;
        this.clienteRepository = clienteRepository;
        this.identificacionRepository = identificacionRepository;
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
        this.loteRepository = loteRepository;
        this.linderoRepository = linderoRepository;
        this.matrizRepository = matrizRepository;
        this.cuotaRepository = cuotaRepository;
        this.cuotaExtraordinariaRepository = cuotaExtraordinariaRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.nacionalidadRepository = nacionalidadRepository;
        this.estadoCivilRepository = estadoCivilRepository;
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.tipoContratoRepository = tipoContratoRepository;
        this.clienteConyugeRepository = clienteConyugeRepository;
        this.copropietarioRepository = copropietarioRepository;
    }

    public void sendEmail(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + idCliente));
        LoteDTO loteDTO = getLotePrincipal(idCliente);
        LinderoDTO linderoDTO = loteDTO.getLindero();
        CuotaDTO cuotaDTO = loteDTO.getCuota();
        MatrizDTO matrizDTO = loteDTO.getMatriz();
        CuotaExtraordinariaDTO cuotaExtraordinariaDTO = loteDTO.getCuotaextraordinaria();
        ClienteConyugeDTO clienteConyugeDTO = getClienteConyugePrincipal(idCliente);
        CopropietarioDTO copropietarioDTO = getCopropietarioPrincipal(idCliente);


        String to = cliente.getCorreoElectronico();
        String nombre = cliente.getNombresApellidos();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // <- multipart = true


            String subject = "VALIDACION DE DATOS_CONTRATO DEFINITIVO - GRUPO AYBAR";

            String emailContent = String.format("""
        <p>Estimado(a) %s,</p>
        
        <p>Reciba un cordial saludo de parte de <strong>Vivienda Para Todos</strong>.</p>
        
        <p>Por medio del presente se le adjunta a este correo, el proyecto de contrato final de transferencia de posesión, documento que formaliza su contrato preparatorio y confirma la entrega definitiva del lote adquirido con nosotros.</p>
        
        <p><strong>Se requiere:</strong> Revise el documento detenidamente para garantizar que toda la información sea correcta.</p>
        
        <ul>
            <li>Proyecto:</li>
            <li>Mz – Lt:</li>
            <li>Nombre, DNI, estado civil y dirección del Cliente:</li>
            <li>Nombre, DNI, estado civil y dirección del Copropietario (de ser el caso):</li>
            <li>Nombre, DNI y ¿matrimonio por bienes separados? y dirección del Cónyuge (de ser el caso):</li>
            <li>Su predio lo tiene pagado al 100%% o tiene algún pendiente de pago:</li>
            <li>Cuenta con constancia de No adeudo:</li>
            <li>Cuenta con Certificado de Lote:</li>
            <li>Cuenta con planos de su lote:</li>
            <li>Cuenta con sus medios de pago:</li>
            <li>Cuenta con algún acto adicional como Adenda, acta o acuerdo adicional con la empresa:</li>
            <li>Requiere copia legalizada de su contrato:</li>
            <li>Requiere el servicio de Habilitación Urbana (solo en los casos de Huaral):</li>
            <li>Nos podría adjuntar la información solicitada (en PDF, imagen o escaneado):</li>
            <li>Requerimientos adicionales en los que le podemos ayudar:</li>
        </ul>
        
        <p>Sin otro particular, y esperando que tenga un excelente día, nos despedimos cordialmente.</p>
        
        <br>
        <p>Atentamente,<br>
        Vivienda Para Todos<br>
        Aybar Corp</p>
        """, nombre);

        helper.setTo(to);

        /*
        helper.setCc(new String[]{
                "cramirezsac2024@gmail.com",
                "VIVIENDAPARATODOS@aybarsac.com"
        });

         */

        helper.setSubject(subject);
        helper.setText(emailContent, true); // Contenido HTML
        helper.setFrom("tu_email@gmail.com", "Vivienda Para Todos");

        String docIdTxt       = obtenerTexto(identificacionRepository.findById(cliente.getIdIdentificacion()), "DocumentoIdentificacion");
        String deptoTxt       = obtenerTexto(departamentoRepository.findById(cliente.getIdDepartamento()), "NombreDepartamento");
        String provinciaTxt   = obtenerTexto(provinciaRepository.findById(cliente.getIdProvincia()), "NombreProvincia");
        String distritoTxt    = obtenerTexto(distritoRepository.findById(cliente.getIdDistrito()), "NombreDistrito");
        String estadoCivilTxt = obtenerTexto(estadoCivilRepository.findById(cliente.getIdEstadoCivil()), "EstadoCivil");
        String nacionalidadTxt  = obtenerTexto(nacionalidadRepository.findById(cliente.getIdNacionalidad()), "NombreNacionalidad");
        Map<String, String> vars = new HashMap<>();
        String fechaFormatoLegal = buildFechaFormatoLegal();
        vars.put("fechaFormatoLegal", fechaFormatoLegal);
        //Cliente
        vars.put("nombresApellidos", nullSafe(cliente.getNombresApellidos()));
        vars.put("numeroIdentificacion",   nullSafe(cliente.getNumeroIdentificacion()));
        vars.put("celularCliente",         nullSafe(cliente.getCelularCliente()));
        vars.put("direccion",              nullSafe(cliente.getDireccion()));
        vars.put("correoElectronico",      nullSafe(cliente.getCorreoElectronico()));
        vars.put("operario",               nullSafe(cliente.getOperario()));
        vars.put("documentoIdentificacion",nullSafe(docIdTxt));
        vars.put("ocupacion",              nullSafe(cliente.getOcupacion()));
        vars.put("descripcionEstadoCivil", nullSafe(cliente.getDescripcionEstadoCivil()));
        vars.put("departamento",           nullSafe(deptoTxt));
        vars.put("provincia",              nullSafe(provinciaTxt));
        vars.put("distrito",               nullSafe(distritoTxt));
        vars.put("nacionalidad",           nullSafe(nacionalidadTxt));
        vars.put("estadoCivil",           nullSafe(estadoCivilTxt));

        //Lote
        String tipoContratoTxt = (obtenerTexto(tipoContratoRepository.findById(loteDTO.getIdTipoContrato()), "TipoContrato"));
        String tipoProyectoTxt = (obtenerTexto(tipoProyectoRepository.findById(loteDTO.getIdTipoProyecto()), "TipoProyecto"));
        if (loteDTO != null) {
            BigDecimal ali = toBigDecimal(loteDTO.getAlicuota());
            vars.put("empresaVende", nullSafe(loteDTO.getEmpresaVende()));
            vars.put("tipoProyecto", nullSafe(tipoProyectoTxt));
            vars.put("contrato", nullSafe(tipoContratoTxt));
            vars.put("idLote", padLeftZeros(loteDTO.getIdLote(), 5));

            String codigoFmt = formatearCodigoLote(loteDTO.getCodigoLoteCliente(), 5);
            vars.put("codigoLoteCliente", codigoFmt);

            vars.put("manzana",                         nullSafe(loteDTO.getManzana()));
            vars.put("numeroLote",                      nullSafe(loteDTO.getNumeroLote()));
            vars.put("areaLote",                        formatoCompleto(loteDTO.getAreaLote()));
            vars.put("areaLoteLetras",                  nullSafe(loteDTO.getAreaLoteLetras()));
            vars.put("costoLote",                       formatoCompleto(loteDTO.getCostoLote()));
            vars.put("montoLetras",                 nullSafe(loteDTO.getCostoLoteLetras()));
            vars.put("costoLoteLetras",                 nullSafe(loteDTO.getCostoLoteLetras()));
            vars.put("empresa",                         nullSafe(loteDTO.getEmpresa()));
            vars.put("rucVendedor",                     nullSafe(loteDTO.getRucVendedor()));
            vars.put("direccionVendedor",               nullSafe(loteDTO.getDireccionVendedor()));
            vars.put("representanteLegal",              nullSafe(loteDTO.getRepresentanteLegalVendedor()));
            vars.put("dniVendedor",                     nullSafe(loteDTO.getDniVendedor()));
            vars.put("numeroPartidaPoderVendedor",      nullSafe(loteDTO.getNumeroPartidaPoderVendedor()));
            vars.put("moneda",                          nullSafe(loteDTO.getMoneda()));
            vars.put("numCuenta",                       nullSafe(loteDTO.getNumCuenta()));
            vars.put("cci",                             nullSafe(loteDTO.getCci()));
            vars.put("fechaSale", formatearFechaLargaConOriginal(loteDTO.getFechaSale()));
            vars.put("fechaFirmaContratoDefinitivo",    nullSafe(loteDTO.getFechaFirmaContratoDefinitivo()));
            vars.put("ubicacionLote",                   nullSafe(loteDTO.getUbicacionLote()));
            vars.put("fechaInicioContrato",             nullSafe(loteDTO.getFechaInicioContrato()));
            vars.put("fechaCancelacionContrato",        nullSafe(loteDTO.getFechaCancelacionContrato()));
            vars.put("precioMetroCuadrado",             formatoCompleto(loteDTO.getPrecioMetroCuadrado()));
            vars.put("precioMetroCuadradoLetras",       nullSafe(loteDTO.getPrecioMetroCuadradoLetras()));
            vars.put("tipoRepresentante",               nullSafe(loteDTO.getTipoRepresentante()));
            vars.put("mantenimientoMensual",            formatoCompleto(loteDTO.getMantenimientoMensual()));
            vars.put("mantenimientoMensualLetras",      nullSafe(loteDTO.getMantenimientoMensualLetras()));
            vars.put("fechaEntrega",                    nullSafe(loteDTO.getFechaEntrega()));
            vars.put("alicuota", formatoAlicuota(ali, 4, false));
            vars.put("alicuotaLetras",                  nullSafe(loteDTO.getAlicuotaLetras()));
        }

        //Lindero
        if (linderoDTO != null) {
            vars.put("porElFrente", formatoCompleto(linderoDTO.getPorElFrente()));
            vars.put("porLaDerecha", formatoCompleto(linderoDTO.getPorLaDerecha()));
            vars.put("porLaIzquierda", formatoCompleto(linderoDTO.getPorLaIzquierda()));
            vars.put("porElFondo", formatoCompleto(linderoDTO.getPorElFondo()));
        }

        //Cuota
        if (cuotaDTO != null) {
            vars.put("saldoLote", formatoCompleto(cuotaDTO.getSaldoLote()));
            vars.put("letrasPendientePago",               nullSafe(cuotaDTO.getLetrasPendientePago()));
            vars.put("cuentaRecaudadora",                 nullSafe(cuotaDTO.getCuentaRecaudadora()));
            vars.put("cuotaInicialIncluyeSeparacion",     formatoCompleto(cuotaDTO.getCuotaInicialIncluyeSeparacion()));
            vars.put("cuotaInicialIncluyeSeparacionLetras", nullSafe(cuotaDTO.getCuotaInicialIncluyeSeparacionLetras()));
            vars.put("montoCuotas",                       nullSafe(cuotaDTO.getMontoCuotas()));
            vars.put("montoCuotaLetras",                  nullSafe(cuotaDTO.getMontoCuotaLetras()));
            vars.put("fechaPago",                         nullSafe(cuotaDTO.getFechaPago()));
            vars.put("cuotaInicialBanco",                 nullSafe(cuotaDTO.getCuotaInicialBanco()));
            vars.put("saldoLoteLetras",                   nullSafe(cuotaDTO.getSaldoLoteLetras()));
            vars.put("cantidadCuotas",                    nullSafe(cuotaDTO.getCantidadCuotas()));
            vars.put("cantidadCuotaLetras",               nullSafe(cuotaDTO.getCantidadCuotaLetras()));
            vars.put("cantidadCuotaCuentaRecaudadora",    nullSafe(cuotaDTO.getCantidadCuotaCuentaRecaudadora()));
            vars.put("cantidadCuotaBanco",                nullSafe(cuotaDTO.getCantidadCuotaBanco()));
            vars.put("cuotaPendientePago",                nullSafe(cuotaDTO.getCuotaPendientePago()));
            vars.put("mediosPago",                        nullSafe(cuotaDTO.getMediosPago()));
            vars.put("estadoCuenta",                      nullSafe(cuotaDTO.getEstadoCuenta()));
            vars.put("montoDeudaLetra",                   nullSafe(cuotaDTO.getMontoDeudaLetra()));

        }


            String departamentoMatrizTxt = (obtenerTexto(departamentoRepository.findById(matrizDTO.getIdDepartamentoMatriz()), "NombreDepartamento"));
            String provinciaMatrizTxt = (obtenerTexto(provinciaRepository.findById(matrizDTO.getIdProvinciaMatriz()), "NombreProvincia"));
            String distritoMatrizTxt = (obtenerTexto(distritoRepository.findById(matrizDTO.getIdDistritoMatriz()), "NombreDistrito"));
            String ubicacionTxt = (obtenerTexto(ubicacionRepository.findById(matrizDTO.getIdUbicacion()), "Ubicacion"));
        //Matriz
        if (matrizDTO != null) {
            vars.put("areaMatrizHasMatriz",   nullSafe(matrizDTO.getAreaMatrizHasMatriz()));
            vars.put("registroMatriz",        nullSafe(matrizDTO.getRegistroMatriz()));
            vars.put("partidaMatriz",         nullSafe(matrizDTO.getPartidaMatriz()));
            vars.put("unidadCatastralMatriz", nullSafe(matrizDTO.getUnidadCatastralMatriz()));
            vars.put("urbanizacionMatriz",    nullSafe(matrizDTO.getUrbanizacionMatriz()));
            vars.put("compraventaMatriz",     nullSafe(matrizDTO.getCompraventaMatriz()));
            vars.put("situacionLegalMatriz",  nullSafe(matrizDTO.getSituacionLegalMatriz()));
            vars.put("txtdepartamentomatriz",  nullSafe(departamentoMatrizTxt));
            vars.put("txtdistritomatriz",  nullSafe(distritoMatrizTxt));
            vars.put("txtprovinciamatriz",  nullSafe(provinciaMatrizTxt));
            vars.put("txtubicacionmatriz",  nullSafe(ubicacionTxt));

        }
        if (cuotaExtraordinariaDTO != null) {
            vars.put("cantidadCuotaExtraordinaria",         formatoEntero(cuotaExtraordinariaDTO.getCantidadCuotaExtraordinaria()));
            vars.put("cantidadCuotaExtraordinariaLetras",   nullSafe(cuotaExtraordinariaDTO.getCantidadCuotaExtraordinariaLetras()));
            vars.put("montoCuotaExtraordinaria",            formatoCompleto(cuotaExtraordinariaDTO.getMontoCuotaExtraordinaria())); // o nullSafe(...)
            vars.put("montoCuotaExtraordinariaLetras",      nullSafe(cuotaExtraordinariaDTO.getMontoCuotaExtraordinariaLetras()));
        }


            String IdentificacionTxt =(obtenerTexto(identificacionRepository.findById(clienteConyugeDTO.getIdIdentificacionConyuge()), "DocumentoIdentificacion"));
            String DepartamentoConyugeTxt =(obtenerTexto(departamentoRepository.findById(clienteConyugeDTO.getIdDepartamentoConyuge()), "NombreDepartamento"));
            String ProvinciaConyugeTxt =(obtenerTexto(provinciaRepository.findById(clienteConyugeDTO.getIdProvinciaConyuge()), "NombreProvincia"));
            String DistritoConyugeTxt =(obtenerTexto(distritoRepository.findById(clienteConyugeDTO.getIdDistritoConyuge()), "NombreDistrito"));

        if (clienteConyugeDTO != null) {
            vars.put("nombresApellidosConyuge",        nullSafe(clienteConyugeDTO.getNombresApellidosConyuge()));
            vars.put("direccionConyuge",               nullSafe(clienteConyugeDTO.getDireccionConyuge()));
            vars.put("numeroIdentificacionConyuge",    nullSafe(clienteConyugeDTO.getNumeroIdentificacionConyuge()));
            vars.put("operarioConyuge",                nullSafe(clienteConyugeDTO.getOperarioConyuge()));
            vars.put("prefijoPaisConyuge",             nullSafe(clienteConyugeDTO.getPrefijoPaisConyuge()));
            vars.put("documentoIdentificacionConyuge", nullSafe(IdentificacionTxt));
            vars.put("ocupacionConyuge",               nullSafe(clienteConyugeDTO.getOcupacionConyuge()));
            vars.put("nacionalidadConyuge",            nullSafe(clienteConyugeDTO.getNacionalidadConyuge()));
            vars.put("residenciaConyuge",              nullSafe(clienteConyugeDTO.getResidenciaConyuge()));
            vars.put("departamentoConyuge",            nullSafe(DepartamentoConyugeTxt));
            vars.put("provinciaConyuge",               nullSafe(ProvinciaConyugeTxt));
            vars.put("distritoConyuge",                nullSafe(DistritoConyugeTxt));
        }


            String DocumentoCopropietarioTxt = (obtenerTexto(identificacionRepository.findById(copropietarioDTO.getIdIdentificacionCopropietarios()), "DocumentoIdentificacion"));
            String DepartamentoCopropietarioTxt=(obtenerTexto(departamentoRepository.findById(copropietarioDTO.getIdDepartamentoCopropietarios()), "NombreDepartamento"));
            String ProvinciaCopropietarioTxt=(obtenerTexto(provinciaRepository.findById(copropietarioDTO.getIdProvinciaCopropietarios()), "NombreProvincia"));
            String DistritoCopropietarioTxt=(obtenerTexto(distritoRepository.findById(copropietarioDTO.getIdDistritoCopropietarios()), "NombreDistrito"));
            String EstadoCivilCopropietarioTxt=(obtenerTexto(estadoCivilRepository.findById(copropietarioDTO.getIdEstadoCivilCopropietarios()), "EstadoCivil"));

            if (copropietarioDTO != null) {
            vars.put("nombresApellidosCopropietarios",       nullSafe(copropietarioDTO.getNombresApellidosCopropietarios()));
            vars.put("direccionCopropietarios",              nullSafe(copropietarioDTO.getDireccionCopropietarios()));
            vars.put("numeroIdentificacionCopropietarios",   nullSafe(copropietarioDTO.getNumeroIdentificacionCopropietarios()));
            vars.put("documentoIdentificacionCopropietarios",nullSafe(DocumentoCopropietarioTxt));
            vars.put("estadoCivilCopropietarios",            nullSafe(EstadoCivilCopropietarioTxt));
            vars.put("ocupacionCopropietarios",              nullSafe(copropietarioDTO.getOcupacionCopropietarios()));
            vars.put("nacionalidadCopropietarios",           nullSafe(copropietarioDTO.getNacionalidadCopropietarios()));
            vars.put("departamentoCopropietarios",           nullSafe(DepartamentoCopropietarioTxt));
            vars.put("provinciaCopropietarios",              nullSafe(ProvinciaCopropietarioTxt));
            vars.put("distritoCopropietarios",               nullSafe(DistritoCopropietarioTxt));
        }

        List<CopropietarioDTO> copropietarios =
                copropietarioRepository.findByIdClienteCopropietarios(idCliente)
                        .stream()
                        .map(this::convertirACopropietarioDTO)
                        .collect(Collectors.toList());

        int tipoContrato = loteDTO.getIdTipoContrato();

        boolean tieneCuotaExtra = cuotaExtraordinariaDTO != null
                && cuotaExtraordinariaDTO.getCantidadCuotaExtraordinaria() != null;

        boolean tieneConyuge = tipoContrato != 3
                && clienteConyugeDTO != null
                && clienteConyugeDTO.getIdClienteConyuge() > 0; // o la regla que uses

        boolean tieneCopropietarioLista = tipoContrato != 3
                && copropietarios != null
                && copropietarios.stream().anyMatch(c -> c.getIdCopropietario() > 0);


        String plantillaPath = elegirPlantilla(
                loteDTO.getIdTipoContrato(),
                tieneConyuge,
                tieneCopropietarioLista,
                tieneCuotaExtra
        );

        byte[] contratoGenerado = generarDocxDesdePlantilla(plantillaPath, vars);

        String nombreAdjunto = buildSafeFilename(cliente.getNombresApellidos(), ".docx");
        helper.addAttachment(nombreAdjunto, new ByteArrayResource(contratoGenerado));

        mailSender.send(message);

    } catch (MessagingException | UnsupportedEncodingException e) {
        throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
    }
}

    private CopropietarioDTO getCopropietarioPrincipal(Integer idCliente) {
        List<Copropietario> lista = Optional
                .ofNullable(copropietarioRepository.findByIdClienteCopropietarios(idCliente))
                .orElse(Collections.emptyList());

        return lista.stream()
                .filter(Objects::nonNull)
                .min(Comparator.comparing(Copropietario::getIdCopropietario))
                .map(this::convertirACopropietarioDTO)
                .orElse(null);
    }


    private String elegirPlantilla(int idTipoContrato,
                                   boolean tieneConyuge,
                                   boolean tieneCopropietarioLista,
                                   boolean tieneCuotaExtra) {

        final String baseDir = "plantillas/";

        switch (idTipoContrato) {
            case 1: // T1
            case 2: // T2
                String t = (idTipoContrato == 1) ? "T1" : "T2";
                String sufijo = "";

                if (tieneConyuge && tieneCopropietarioLista && !tieneCuotaExtra) {
                    sufijo = "contodo_sincuotaextraordinaria";
                } else if (tieneConyuge && tieneCopropietarioLista) {
                    sufijo = "contodo";
                } else if (tieneConyuge && !tieneCopropietarioLista && !tieneCuotaExtra) {
                    sufijo = "sincopropietario_sincuotaextraordinaria";
                } else if (tieneConyuge && !tieneCopropietarioLista) {
                    sufijo = "sincopropietario";
                } else if (!tieneConyuge && tieneCopropietarioLista && !tieneCuotaExtra) {
                    sufijo = "sinconyuge_sincuotaextraordinaria";
                } else if (!tieneConyuge && tieneCopropietarioLista) {
                    sufijo = "sinconyuge";
                } else if (!tieneConyuge && !tieneCopropietarioLista && !tieneCuotaExtra) {
                    sufijo = "sincuotaextraordinaria";
                }

                String candidato = baseDir + "Bplantilla_" + t + (sufijo.isEmpty() ? "" : "_" + sufijo) + ".docx";
                if (new ClassPathResource(candidato).exists()) return candidato;

                return baseDir + "Bplantilla_" + t + ".docx";

            case 3: // T3 (solo cambia por cuota extraordinaria)
                String archivoT3 = "Bplantilla_T3" + (tieneCuotaExtra ? "" : "_sincuotaextraordinaria") + ".docx";
                String candidatoT3 = baseDir + archivoT3;
                if (new ClassPathResource(candidatoT3).exists()) return candidatoT3;

                return baseDir + "Bplantilla_T3.docx";

            default:
                // Fallback seguro
                return baseDir + "Bplantilla_T1.docx";
        }
    }

    private ClienteConyugeDTO getClienteConyugePrincipal(Integer idCliente) {
        return clienteConyugeRepository
        .findByIdCliente(idCliente)
        .map(clienteConyuge -> {

            ClienteConyugeDTO dto = convertirAClienteConyugeDTO(clienteConyuge);
            dto.setIdClienteConyuge(clienteConyuge.getIdClienteConyuge());
            dto.setIdCliente(clienteConyuge.getIdCliente());
            dto.setIdNacionalidadConyuge(clienteConyuge.getIdNacionalidadConyuge());
            dto.setOcupacionConyuge(clienteConyuge.getOcupacionConyuge());
            dto.setIdIdentificacionConyuge(clienteConyuge.getIdIdentificacionConyuge());
            dto.setIdResidenciaConyuge(clienteConyuge.getIdResidenciaConyuge());
            dto.setIdOperarioConyuge(clienteConyuge.getIdOperarioConyuge());
            dto.setIdDepartamentoConyuge(clienteConyuge.getIdDepartamentoConyuge());
            dto.setIdProvinciaConyuge(clienteConyuge.getIdProvinciaConyuge());
            dto.setIdDistritoConyuge(clienteConyuge.getIdDistritoConyuge());
            dto.setNombresApellidosConyuge(clienteConyuge.getNombresApellidosConyuge());
            dto.setDireccionConyuge(clienteConyuge.getDireccionConyuge());
            dto.setNumeroIdentificacionConyuge(clienteConyuge.getNumeroIdentificacionConyuge());

            return dto;
        })
        .orElse(null);
    }

    private LoteDTO getLotePrincipal(int idCliente) {
        return loteRepository.findFirstByIdClienteLoteOrderByIdLoteAsc(idCliente)
                .map(this::convertToLoteDTO)
                .orElse(null);
    }

    private CopropietarioDTO convertirACopropietarioDTO(Copropietario copropietario) {
        CopropietarioDTO dto = new CopropietarioDTO();
        dto.setIdCopropietario(copropietario.getIdCopropietario());
        dto.setIdClienteCopropietarios(copropietario.getIdClienteCopropietarios());
        dto.setIdResidenciaCopropietarios(copropietario.getIdResidenciaCopropietarios());
        dto.setIdOperarioCopropietarios(copropietario.getIdOperarioCopropietarios());
        dto.setOcupacionCopropietarios(copropietario.getOcupacionCopropietarios());
        dto.setIdDepartamentoCopropietarios(copropietario.getIdDepartamentoCopropietarios());
        dto.setIdProvinciaCopropietarios(copropietario.getIdProvinciaCopropietarios());
        dto.setIdDistritoCopropietarios(copropietario.getIdDistritoCopropietarios());
        dto.setIdNacionalidadCopropietarios(copropietario.getIdNacionalidadCopropietarios());
        dto.setIdEstadoCivilCopropietarios(copropietario.getIdEstadoCivilCopropietarios());
        dto.setIdIdentificacionCopropietarios(copropietario.getIdIdentificacionCopropietarios());
        dto.setNombresApellidosCopropietarios(copropietario.getNombresApellidosCopropietarios());
        dto.setDireccionCopropietarios(copropietario.getDireccionCopropietarios());
        dto.setNumeroIdentificacionCopropietarios(copropietario.getNumeroIdentificacionCopropietarios());
        dto.setDescripcionEstadoCivilCopropietarios(copropietario.getDescripcionEstadoCivilCopropietarios());
        return dto;
    }
        private ClienteConyugeDTO convertirAClienteConyugeDTO(ClienteConyuge clienteConyuge) {
        ClienteConyugeDTO dto = new ClienteConyugeDTO();
        dto.setIdClienteConyuge(clienteConyuge.getIdClienteConyuge());
        dto.setIdCliente(clienteConyuge.getIdCliente());
        dto.setIdNacionalidadConyuge(clienteConyuge.getIdNacionalidadConyuge());
        dto.setOcupacionConyuge(clienteConyuge.getOcupacionConyuge());
        dto.setIdIdentificacionConyuge(clienteConyuge.getIdIdentificacionConyuge());
        dto.setIdResidenciaConyuge(clienteConyuge.getIdResidenciaConyuge());
        dto.setIdOperarioConyuge(clienteConyuge.getIdOperarioConyuge());
        dto.setIdDepartamentoConyuge(clienteConyuge.getIdDepartamentoConyuge());
        dto.setIdProvinciaConyuge(clienteConyuge.getIdProvinciaConyuge());
        dto.setIdDistritoConyuge(clienteConyuge.getIdDistritoConyuge());
        dto.setNombresApellidosConyuge(clienteConyuge.getNombresApellidosConyuge());
        dto.setDireccionConyuge(clienteConyuge.getDireccionConyuge());
        dto.setNumeroIdentificacionConyuge(clienteConyuge.getNumeroIdentificacionConyuge());

        return dto;
    }


    private LoteDTO convertToLoteDTO(Lote lote) {
        LoteDTO dto = new LoteDTO();
        dto.setIdLote(lote.getIdLote());
        dto.setIdOperario(lote.getIdOperario());
        dto.setIdClienteLote(lote.getIdClienteLote());
        dto.setIdClienteClone(lote.getIdClienteClone());
        dto.setCodigoLoteCliente(lote.getCodigoLoteCliente());
        dto.setIdTipoProyecto(lote.getIdTipoProyecto());
        dto.setIdTipoContrato(lote.getIdTipoContrato());
        dto.setIdUbicacion(lote.getIdUbicacion());
        dto.setManzana(lote.getManzana());
        dto.setNumeroLote(lote.getNumeroLote());
        dto.setAreaLote(lote.getAreaLote());
        dto.setAreaLoteLetras(lote.getAreaLoteLetras());
        dto.setCostoLote(lote.getCostoLote());
        dto.setCostoLoteLetras(lote.getCostoLoteLetras());
        dto.setEmpresa(lote.getEmpresa());
        dto.setEmpresaVende(lote.getEmpresaVende());
        dto.setRucVendedor(lote.getRucVendedor());
        dto.setDireccionVendedor(lote.getDireccionVendedor());
        dto.setRepresentanteLegalVendedor(lote.getRepresentanteLegalVendedor());
        dto.setDniVendedor(lote.getDniVendedor());
        dto.setNumeroPartidaPoderVendedor(lote.getNumeroPartidaPoderVendedor());
        dto.setMoneda(lote.getMoneda());
        dto.setNumCuenta(lote.getNumCuenta());
        dto.setCci(lote.getCci());
        dto.setFechaSale(lote.getFechaSale());
        dto.setFechaFirmaContratoDefinitivo(lote.getFechaFirmaContratoDefinitivo());
        dto.setUbicacionLote(lote.getUbicacionLote());
        dto.setFechaInicioContrato(lote.getFechaInicioContrato());
        dto.setFechaCancelacionContrato(lote.getFechaCancelacionContrato());
        dto.setPrecioMetroCuadrado(lote.getPrecioMetroCuadrado());
        dto.setPrecioMetroCuadradoLetras(lote.getPrecioMetroCuadradoLetras());
        dto.setTipoRepresentante(lote.getTipoRepresentante());
        dto.setMantenimientoMensual(lote.getMantenimientoMensual());
        dto.setMantenimientoMensualLetras(lote.getMantenimientoMensualLetras());
        dto.setFechaEntrega(lote.getFechaEntrega());
        dto.setAlicuota(lote.getAlicuota());
        dto.setAlicuotaLetras(lote.getAlicuotaLetras());


        linderoRepository.findByIdLote(lote.getIdLote())
                .map(this::convertirALinderoDTO)
                .ifPresent(dto::setLindero);

        matrizRepository.findByIdLote(lote.getIdLote())
                .map(this::convertirAMatrizDTO)
                .ifPresent(dto::setMatriz);

        cuotaExtraordinariaRepository.findByIdLote(lote.getIdLote())
                .map(this::convertirACuotaExtraordinariaDTO)
                .ifPresent(dto::setCuotaextraordinaria);


        cuotaRepository.findByIdLote(lote.getIdLote())
                .map(this::convertirACuotaDTO)
                .ifPresent(dto::setCuota);

        return dto;
    }

    private LinderoDTO convertirALinderoDTO(Lindero lindero) {
        LinderoDTO dto = new LinderoDTO();
        dto.setIdLindero(lindero.getIdLindero());
        dto.setIdLote(lindero.getIdLote());
        dto.setPorLaDerecha(lindero.getPorLaDerecha());
        dto.setPorLaIzquierda(lindero.getPorLaIzquierda());
        dto.setPorElFrente(lindero.getPorElFrente());
        dto.setPorElFondo(lindero.getPorElFondo());
        dto.setDescripcionPorLaDerecha(lindero.getDescripcionPorLaDerecha());
        dto.setDescripcionPorLaIzquierda(lindero.getDescripcionPorLaIzquierda());
        dto.setDescripcionPorElFrente(lindero.getDescripcionPorElFrente());
        dto.setDescripcionPorElFondo(lindero.getDescripcionPorElFondo());

        return dto;
    }

    private MatrizDTO convertirAMatrizDTO(Matriz matriz) {
        MatrizDTO dto = new MatrizDTO();
        dto.setIdMatriz(matriz.getIdMatriz());
        dto.setIdLote(matriz.getIdLote());
        dto.setIdDepartamentoMatriz(matriz.getIdDepartamentoMatriz());
        dto.setIdProvinciaMatriz(matriz.getIdProvinciaMatriz());
        dto.setIdDistritoMatriz(matriz.getIdDistritoMatriz());
        dto.setIdUbicacion(matriz.getIdUbicacion());
        dto.setAreaMatrizHasMatriz(matriz.getAreaMatrizHasMatriz());
        dto.setRegistroMatriz(matriz.getRegistroMatriz());
        dto.setPartidaMatriz(matriz.getPartidaMatriz());
        dto.setUnidadCatastralMatriz(matriz.getUnidadCatastralMatriz());
        dto.setUrbanizacionMatriz(matriz.getUrbanizacionMatriz());
        dto.setCompraventaMatriz(matriz.getCompraventaMatriz());
        dto.setSituacionLegalMatriz(matriz.getSituacionLegalMatriz());

        dto.setTxtdepartamentomatriz(obtenerTexto(departamentoRepository.findById(matriz.getIdDepartamentoMatriz()), "NombreDepartamento"));
        dto.setTxtprovinciamatriz(obtenerTexto(provinciaRepository.findById(matriz.getIdProvinciaMatriz()), "NombreProvincia"));
        dto.setTxtdistritomatriz(obtenerTexto(distritoRepository.findById(matriz.getIdDistritoMatriz()), "NombreDistrito"));
        dto.setTxtubicacionmatriz(obtenerTexto(ubicacionRepository.findById(matriz.getIdUbicacion()), "Ubicacion"));
        return dto;
    }

    private CuotaExtraordinariaDTO convertirACuotaExtraordinariaDTO(CuotaExtraordinaria cuotaextraordinaria) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(cuotaextraordinaria.getIdCuotaExtraordinaria());
        dto.setIdLote(cuotaextraordinaria.getIdLote());
        dto.setCantidadCuotaExtraordinaria(cuotaextraordinaria.getCantidadCuotaExtraordinaria());
        dto.setCantidadCuotaExtraordinariaLetras(cuotaextraordinaria.getCantidadCuotaExtraordinariaLetras());
        dto.setMontoCuotaExtraordinaria(cuotaextraordinaria.getMontoCuotaExtraordinaria());
        dto.setMontoCuotaExtraordinariaLetras(cuotaextraordinaria.getMontoCuotaExtraordinariaLetras());
        return dto;
    }

    public CuotaDTO convertirACuotaDTO(Cuota cuota) {
        CuotaDTO dto = new CuotaDTO();
        dto.setIdLote(cuota.getIdLote());
        dto.setIdCuota(cuota.getIdCuota());
        dto.setLetrasPendientePago(cuota.getLetrasPendientePago());
        dto.setCuentaRecaudadora(cuota.getCuentaRecaudadora());
        dto.setCuotaInicialIncluyeSeparacion(cuota.getCuotaInicialIncluyeSeparacion());
        dto.setCuotaInicialIncluyeSeparacionLetras(cuota.getCuotaInicialIncluyeSeparacionLetras());
        dto.setMontoCuotas(cuota.getMontoCuotas());
        dto.setMontoCuotaLetras(cuota.getMontoCuotaLetras());
        dto.setFechaPago(cuota.getFechaPago());
        dto.setCuotaInicialBanco(cuota.getCuotaInicialBanco());
        dto.setCantidadCuotas(cuota.getCantidadCuotas());
        dto.setCantidadCuotaLetras(cuota.getCantidadCuotaLetras());
        dto.setCantidadCuotaCuentaRecaudadora(cuota.getCantidadCuotaCuentaRecaudadora());
        dto.setCantidadCuotaBanco(cuota.getCantidadCuotaBanco());
        dto.setCuotaPendientePago(cuota.getCuotaPendientePago());
        dto.setSaldoLote(cuota.getSaldoLote());
        dto.setSaldoLoteLetras(cuota.getSaldoLoteLetras());
        dto.setMediosPago(cuota.getMediosPago());
        return dto;
    }


    private String formatoAlicuota(Number n, int decimales, boolean multiplicarPorCien) {
        if (n == null) return "";
        BigDecimal bd = new BigDecimal(n.toString());
        if (multiplicarPorCien) {
            bd = bd.multiply(new BigDecimal("100")); // solo si en BD guardas fracción (0.0008)
        }
        // SIN redondeo: trunca
        bd = bd.setScale(decimales, RoundingMode.DOWN);

        DecimalFormatSymbols s = new DecimalFormatSymbols(Locale.US);
        String pattern = "0." + "0".repeat(decimales); // ej: "0.0000"
        return new DecimalFormat(pattern, s).format(bd);
    }

    private String buildFechaFormatoLegal() {
        // Usa zona de Lima para evitar desfases si el server está en otra TZ
        LocalDate hoy = LocalDate.now(ZoneId.of("America/Lima"));

        int dia = hoy.getDayOfMonth();
        int anio = hoy.getYear();
        String dia2 = String.format("%02d", dia);
        String mesLargo = hoy.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "PE")).toUpperCase();

        String diaTexto = numeroATextoEs(dia);   // en MAYÚSCULAS
        String anioTexto = numeroATextoEs(anio); // en MAYÚSCULAS

        return String.format(
                "LIMA, A LOS %s (%s) DÍAS DEL MES DE %s DEL AÑO %d (%s).",
                dia2, diaTexto, mesLargo, anio, anioTexto
        );
    }

    private String formatoEntero(Number n) {
        if (n == null) return "";
        DecimalFormat df = new DecimalFormat("0");
        df.setGroupingUsed(false);
        return df.format(n);
    }

    private String formatearFechaLargaConOriginal(String fecha) {
        if (fecha == null || fecha.isBlank()) return "-";
        try {
            // Espera "dd/MM/yyyy" (también funciona con "d/M/yyyy")
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("d/M/uuuu");
            LocalDate ld = LocalDate.parse(fecha.trim(), parser);

            // "10 de agosto de 2025"
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' uuuu", new Locale("es", "ES"));
            String larga = ld.format(fmt);

            String out = larga + " (" + fecha + ")";
            return out.toUpperCase(new Locale("es", "ES"));
        } catch (DateTimeParseException e) {

            return fecha;
        }
    }

    private String padLeftZeros(Number n, int width) {
        if (n == null) return "";
        return String.format("%0" + width + "d", n.longValue());
    }


    private String formatearCodigoLote(String codigo, int ancho) {
        if (codigo == null || codigo.isBlank()) return "";
        String[] parts = codigo.trim().split("-", 2);
        String left = parts[0].trim();

        // si es numérico, puedes usar String.format; si no, hacemos padding manual
        if (left.matches("\\d+")) {
            left = String.format("%0" + ancho + "d", Long.parseLong(left));
        } else {
            while (left.length() < ancho) left = "0" + left;
        }

        return (parts.length > 1) ? left + "-" + parts[1] : left;
    }



    private BigDecimal toBigDecimal(Number n) {
        if (n == null) return null;
        return (n instanceof BigDecimal) ? (BigDecimal) n : new BigDecimal(n.toString());
    }

    private String formatoCompleto(Number n) {
        if (n == null) return "";
        BigDecimal bd = (n instanceof BigDecimal) ? (BigDecimal) n : new BigDecimal(n.toString());
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(bd);
    }

    private static String nullSafe(Object o) {
        return o == null ? "" : String.valueOf(o);
    }

    private String obtenerTexto(Optional<?> opt, String property) {
        if (opt.isEmpty() || property == null || property.isBlank()) return "";
        Object entity = opt.get();
        try {
            String getter = "get" + property.substring(0,1).toUpperCase() + property.substring(1);
            java.lang.reflect.Method m = entity.getClass().getMethod(getter);
            Object val = m.invoke(entity);
            return val == null ? "" : String.valueOf(val);
        } catch (Exception e) {
            return "";
        }
    }

    //** Carga plantilla .docx y reemplaza {clave} o {{clave}} en cuerpo, headers y footers. */
    private byte[] generarDocxDesdePlantilla(String classpathTemplate, Map<String, String> vars) {
        try (InputStream in = new ClassPathResource(classpathTemplate).getInputStream();
             XWPFDocument doc = new XWPFDocument(in);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            // Cuerpo
            replaceInParagraphsBold(doc.getParagraphs(), vars);
            replaceInTablesBold(doc.getTables(), vars);


            // Headers
            for (XWPFHeader h : doc.getHeaderList()) {
                replaceInParagraphs(h.getParagraphs(), vars);
                replaceInTables(h.getTables(), vars);
            }

            // Footers
            for (XWPFFooter f : doc.getFooterList()) {
                replaceInParagraphs(f.getParagraphs(), vars);
                replaceInTables(f.getTables(), vars);
            }

            doc.write(baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo generar el DOCX: " + e.getMessage(), e);
        }
    }

    private void replaceInParagraphsBold(List<XWPFParagraph> paragraphs, Map<String, String> vars) {
        if (paragraphs == null || paragraphs.isEmpty()) return;
        for (XWPFParagraph p : paragraphs) replaceRunsInParagraphBold(p, vars);
    }

    private void replaceInTablesBold(List<XWPFTable> tables, Map<String, String> vars) {
        if (tables == null || tables.isEmpty()) return;
        for (XWPFTable t : tables) {
            for (XWPFTableRow row : t.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    replaceInParagraphsBold(cell.getParagraphs(), vars);
                    replaceInTablesBold(cell.getTables(), vars); // por si hay tablas anidadas
                }
            }
        }
    }

    private void replaceRunsInParagraphBold(XWPFParagraph p, Map<String, String> vars) {
        if (p == null || vars == null || vars.isEmpty()) return;

        String full = paragraphText(p);
        if (full.isEmpty()) return;

        Pattern pattern = buildPlaceholderPattern(vars.keySet());
        if (pattern == null) return;

        Matcher m = pattern.matcher(full);
        List<Segment> segs = new ArrayList<>();
        int last = 0;

        while (m.find()) {
            if (m.start() > last) segs.add(new Segment(full.substring(last, m.start()), false));
            String key = m.group("key");
            String val = vars.getOrDefault(key, "");
            segs.add(new Segment(val, true)); // valores en NEGRITA
            last = m.end();
        }
        if (last < full.length()) segs.add(new Segment(full.substring(last), false));

        for (int i = p.getRuns().size() - 1; i >= 0; i--) p.removeRun(i);
        for (Segment s : segs) {
            if (s.text.isEmpty()) continue;
            XWPFRun r = p.createRun();
            r.setText(s.text);
            r.setBold(s.bold);
        }
    }
    private Pattern buildPlaceholderPattern(Set<String> keys) {
        if (keys == null || keys.isEmpty()) return null;
        String alternation = keys.stream().map(Pattern::quote).collect(Collectors.joining("|"));
        // Matchea {clave} o {{clave}} y captura la clave en el grupo "key"
        String regex = "\\{\\{?(?<key>(" + alternation + "))\\}\\}?";
        return Pattern.compile(regex);
    }

    private static class Segment {
        final String text; final boolean bold;
        Segment(String t, boolean b) { this.text = t; this.bold = b; }
    }

    private String paragraphText(XWPFParagraph p) {
        StringBuilder sb = new StringBuilder();
        for (XWPFRun r : p.getRuns()) {
            String t = r.getText(0);
            if (t != null) sb.append(t);
        }
        return sb.toString();
    }


    private void replaceInTables(List<XWPFTable> tables, Map<String, String> vars) {
        for (XWPFTable t : tables) {
            for (XWPFTableRow r : t.getRows()) {
                for (XWPFTableCell c : r.getTableCells()) {
                    replaceInParagraphs(c.getParagraphs(), vars);
                    // tablas anidadas dentro de una celda
                    replaceInTables(c.getTables(), vars);
                }
            }
        }
    }

    private void replaceInParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> vars) {
        for (XWPFParagraph p : paragraphs) {
            List<XWPFRun> runs = p.getRuns();
            if (runs == null || runs.isEmpty()) continue;

            // Unir texto de todos los runs del párrafo
            StringBuilder sb = new StringBuilder();
            for (XWPFRun run : runs) sb.append(run.toString());
            String text = sb.toString();

            // Reemplazos {clave} y {{clave}}
            for (Map.Entry<String, String> e : vars.entrySet()) {
                String k = e.getKey();
                String v = e.getValue() == null ? "" : e.getValue();
                text = text.replace("{" + k + "}", v).replace("{{" + k + "}}", v);
            }

            // Dejar el párrafo en un solo run con el texto ya reemplazado
            for (int i = runs.size() - 1; i >= 1; i--) p.removeRun(i);
            XWPFRun r0 = runs.get(0);
            r0.setText("", 0);       // limpia contenido previo
            r0.setText(text, 0);
        }
    }


    private String buildSafeFilename(String base, String ext) {
        String normalized = Normalizer.normalize(base == null ? "" : base, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); // Quita acentos
        String noIllegal = normalized.replaceAll("[\\\\/:*?\"<>|]", ""); // Quita caracteres inválidos
        String cleaned = noIllegal.trim().replaceAll("\\s+", " ");      // Colapsa espacios
        cleaned = cleaned.replace(' ', '_');                            // Espacios a guiones bajos
        if (cleaned.isEmpty()) cleaned = "Documento";
        if (cleaned.length() > 100) cleaned = cleaned.substring(0, 100); // Limita longitud
        return cleaned + ext;
    }

    private String numeroATextoEs(int n) {
        if (n < 0 || n > 9999) return String.valueOf(n).toUpperCase();

        final String[] UNIDADES = {
                "CERO","UNO","DOS","TRES","CUATRO","CINCO","SEIS","SIETE","OCHO","NUEVE",
                "DIEZ","ONCE","DOCE","TRECE","CATORCE","QUINCE","DIECISÉIS","DIECISIETE","DIECIOCHO","DIECINUEVE"
        };
        final String[] DECENAS = {
                "", "", "VEINTE","TREINTA","CUARENTA","CINCUENTA","SESENTA","SETENTA","OCHENTA","NOVENTA"
        };
        final String[] CENTENAS = {
                "", "CIENTO","DOSCIENTOS","TRESCIENTOS","CUATROCIENTOS","QUINIENTOS",
                "SEISCIENTOS","SETECIENTOS","OCHOCIENTOS","NOVECIENTOS"
        };

        if (n < 20) return UNIDADES[n];

        if (n < 30) {
            if (n == 20) return "VEINTE";
            // 21..29: "VEINTIUNO", "VEINTIDÓS", ...
            int u = n - 20;
            String[] veinti = {"","UNO","DOS","TRES","CUATRO","CINCO","SEIS","SIETE","OCHO","NUEVE"};
            String t = "VEINTI" + veinti[u];
            // acentos 22, 23, 26
            if (n == 22) t = "VEINTIDÓS";
            if (n == 23) t = "VEINTITRÉS";
            if (n == 26) t = "VEINTISÉIS";
            return t;
        }

        if (n < 100) {
            int d = n / 10, u = n % 10;
            return u == 0 ? DECENAS[d] : DECENAS[d] + " Y " + UNIDADES[u];
        }

        if (n == 100) return "CIEN";

        if (n < 1000) {
            int c = n / 100, r = n % 100;
            return r == 0 ? CENTENAS[c] : CENTENAS[c] + " " + numeroATextoEs(r);
        }

        // 1000..9999
        int miles = n / 1000;
        int resto = n % 1000;
        String pref = (miles == 1) ? "MIL" : numeroATextoEs(miles) + " MIL";
        return resto == 0 ? pref : pref + " " + numeroATextoEs(resto);
    }
}
