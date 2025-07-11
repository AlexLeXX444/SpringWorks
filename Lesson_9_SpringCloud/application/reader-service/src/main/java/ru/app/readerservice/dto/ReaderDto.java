package ru.app.readerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.app.readerservice.entity.Reader;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDto {

    private String name;

    public Reader toEntity() {
        Reader reader = new Reader();
        reader.setName(this.name);
        return reader;
    }
}
