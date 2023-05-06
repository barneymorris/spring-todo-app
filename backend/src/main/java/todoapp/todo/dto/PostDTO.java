package todoapp.todo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String title;
    private String text;
    private Integer userId;
}
