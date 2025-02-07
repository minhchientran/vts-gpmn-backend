package viettel.gpmn.platform.core.data.response;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Response {
    private int code;
    private String message;
    private Object payload;
    public Response(int code, String message, Object object) {
        if (object != null) {
            Map<String, Object> payload = new HashMap<>();
            if (object instanceof List<?>) {
                payload.put("totalRecords", ((List<?>) object).size());
                payload.put("content", object);
                this.payload = payload;
            }
            else if (object instanceof Page<?>) {
                payload.put("totalRecords", ((Page<?>) object).getTotalElements());
                payload.put("totalPage", ((Page<?>) object).getTotalPages());
                payload.put("page", ((Page<?>) object).getPageable().getPageNumber());
                payload.put("size", ((Page<?>) object).getPageable().getPageSize());
                payload.put("content", ((Page<?>) object).getContent());
                this.payload = payload;
            }
            else {
                this.payload = object;
            }
        }
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(Object object) {
        this(200, "success", object);
    }

    public static Response ok() {
        return new Response(null);
    }
    public static Response ok(Object object) {
        return new Response(object);
    }
}
