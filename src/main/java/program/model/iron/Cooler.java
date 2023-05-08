package program.model.iron;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cooler {
    private int id;
    private String manufacturer;
    private String model;
    private int TDP;
}
