package program.model.iron;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CPU {
    private int id;
    private String manufacturer;
    private String model;
    private String socket;
    private int cores;
    private int threads;
    private float frequency;
    private String memoryType;
    private int TDP;
}
