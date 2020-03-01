package com.zenika.petshop.persistence;


import com.zenika.petshop.exceptions.WrongDataException;
import com.zenika.petshop.model.OwnerEntity;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OwnerDaoTest {

    public static final String ROOT_DIR = System.getProperty("user.dir") + "/src/test/resources/";

    @Test
    public void readAll_ok() {
        OwnerDao dao = new OwnerDao(ROOT_DIR + "owners-testdata-ok.csv");
        Map<Integer, OwnerEntity> data = dao.readAll();
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
            new OwnerDao(ROOT_DIR + "owners-MISSINGFILE.csv").readAll();
        } catch (IllegalStateException e) {
            assertThat(e).hasCauseInstanceOf(FileNotFoundException.class);
        }
    }

    @Test(expected = WrongDataException.class)
    public void readAll_wrong_separator() {
        new OwnerDao(ROOT_DIR + "owners-testdata-ko-separator.csv").readAll();
    }

    @Test(expected = WrongDataException.class)
    public void readAll_missingdata() {
        new OwnerDao(ROOT_DIR + "owners-testdata-ko-missingdata.csv").readAll();
    }

    @Test(expected = WrongDataException.class)
    public void readAll_wrong_format() {
        new OwnerDao(ROOT_DIR + "owners-testdata-ko-format.csv").readAll();
    }
}
