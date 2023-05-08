package program.model.iron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RAM {
    private int id;
    private String manufacturer;
    private String model;
    private String type;
    private int memory;
    private int MHzFrequency;
}
