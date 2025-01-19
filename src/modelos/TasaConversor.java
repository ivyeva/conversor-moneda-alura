package conversor.modelos;
import com.google.gson.annotations.SerializedName;

public record TasaConversor(
        @SerializedName("base_code") String monedaOrigen,
        @SerializedName("target_code") String monedaDestino,
        @SerializedName("conversion_rate") Double tasaCambio
) {
    @Override
    public String toString() {
        return "TasaConversor{" +
                "monedaOrigen=" + monedaOrigen +
                ", monedaDestino=" + monedaDestino +
                ", tasaCambio=" + tasaCambio + " " + monedaDestino + "/" + monedaOrigen +
                '}';
    }

    public String tasaCambioToString() {
        return tasaCambio + " " + monedaDestino + "/" + monedaOrigen;
    }
}
