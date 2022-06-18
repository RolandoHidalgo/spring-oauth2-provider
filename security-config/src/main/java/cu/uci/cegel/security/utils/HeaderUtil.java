package cu.uci.cegel.security.utils;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */

@NoArgsConstructor
public final class HeaderUtil {

    private static String SUCCESS_HEADER = "X-sigipApp-success";
    private static String INFO_HEADER = "X-sigipApp-info";
    private static String ERROR_HEADER = "X-sigipApp-error";
    private static String BAD_REQUEST_HEADER = "X-sigipApp-badrequest";

    public static HttpHeaders entityActionAlert(String actionMessage) {
        return createHeaders(SUCCESS_HEADER, actionMessage);
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String identificador) {
        return createHeaders(SUCCESS_HEADER, "Se ha creado un " + entityName + " con identificador " + identificador + ".");
    }

    public static HttpHeaders createEntityCreationAlertCodigo(String entityName, String codigo) {
        return createHeaders(SUCCESS_HEADER, "Se ha creado una " + entityName + " con código " + codigo + ".");
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String identificador) {
        return createHeaders(SUCCESS_HEADER, "Se ha actualizado un " + entityName + " con identificador " + identificador + ".");
    }

    public static HttpHeaders createEntityUpdateAlertCodigo(String entityName, String codigo) {
        return createHeaders(SUCCESS_HEADER, "Se ha modificado la " + entityName + " de código " + codigo + ".");
    }

    public static HttpHeaders createEntityDeleteAlert(String entityName, String identificador) {
        return createHeaders(SUCCESS_HEADER, "Se ha eliminado un " + entityName + " con identificador " + identificador + ".");
    }

    public static HttpHeaders createEntityDeleteAlertCodigo(String entityName, String codigo) {
        return createHeaders(SUCCESS_HEADER, "Se ha eliminado una " + entityName + " con código " + codigo + ".");
    }

    public static HttpHeaders infoAlert(String mensaje) {
        return createHeaders(INFO_HEADER, mensaje);
    }

    public static HttpHeaders errorAlert(String mensaje) {
        return createHeaders(ERROR_HEADER, mensaje);
    }

    public static HttpHeaders badRequestAlert(String mensaje) {
        return createHeaders(BAD_REQUEST_HEADER, mensaje);
    }

    public static HttpHeaders internalRequestAlert(String mensaje) {
        return createHeaders(INFO_HEADER, mensaje);
    }

    private static HttpHeaders createHeaders(String header, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(header, message);
        return headers;
    }

}
