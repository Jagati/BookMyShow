package com.lldproject.bookmyshow;

import com.lldproject.bookmyshow.model.BaseModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication {

    public static void main(String[] args) {
        BaseModel baseModel = new BaseModel();
        baseModel.setId(2L);
        SpringApplication.run(BookMyShowApplication.class, args);

    }

}
