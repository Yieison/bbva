package co.empresa.bbva.modelo;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;

    
    public Usuario(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
