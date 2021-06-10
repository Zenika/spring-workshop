package com.zenika.petshop.persistence;


import com.zenika.petshop.exceptions.WrongDataException;
import com.zenika.petshop.model.OwnerEntity;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OwnerDaoTest {

    public static final String ROOT_DIR = System.getProperty("user.dir") + "/src/test/resources/";

    @Test
    public void readAll_ok() {
        Map<Integer, OwnerEntity> data = runDao("owners-testdata-ok.csv");
        assertThat(data).isNotNull();
        assertThat(data).hasSize(5);
        OwnerEntity owner = data.get(1);
        assertThat(owner).isNotNull();
        assertThat(owner.getId()).isEqualTo(1);
        assertThat(owner.getName()).isEqualTo("Tintin");
    }

    @Test
    public void readAll_missing_file() {
        try {
            runDao("owners-MISSINGFILE.csv");
        } catch (IllegalStateException e) {
            assertThat(e).hasCauseInstanceOf(FileNotFoundException.class);
        }
    }

    @Test(expected = WrongDataException.class)
    public void readAll_wrong_separator() {
        runDao("owners-testdata-ko-separator.csv");
    }

    @Test(expected = WrongDataException.class)
    public void readAll_missingdata() {
        runDao("owners-testdata-ko-missingdata.csv");
    }

    @Test(expected = WrongDataException.class)
    public void readAll_wrong_format() {
        runDao("owners-testdata-ko-format.csv");
    }

    private Map<Integer, OwnerEntity> runDao(String file) {
        OwnerDao dao = new OwnerDao();
        dao.setData(new FileSystemResource(ROOT_DIR + file));
        return dao.readAll();
    }

}
