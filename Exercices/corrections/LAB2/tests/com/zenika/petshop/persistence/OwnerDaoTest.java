package com.zenika.petshop.persistence;


import com.zenika.petshop.exceptions.WrongDataException;
import com.zenika.petshop.model.OwnerEntity;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.Assert.*;

public class OwnerDaoTest {

    @Test
    public void readAll_ok() {
        OwnerDao dao = new OwnerDao("tests/resources/owners-testdata-ok.csv");
        Map<Integer, OwnerEntity> data = dao.readAll();
        assertNotNull(data);
        assertEquals(data.size(), 5);
        OwnerEntity owner = data.get(1);
        assertNotNull(owner);
        assertEquals(owner.getId(), 1);
        assertEquals(owner.getName(), "Tintin");
    }

    @Test
    public void readAll_missing_file() {
        try {
            OwnerDao dao = new OwnerDao("tests/resources/owners-MISSINGFILE.csv");
        } catch (IllegalStateException e) {
            assertEquals(e.getCause().getClass(), FileNotFoundException.class);
            // This is good
        }
    }

    @Test
    public void readAll_wrong_separator() {
        test_should_fail_as_wrongdata("tests/resources/owners-testdata-ko-separator.csv");
    }

    @Test
    public void readAll_missingdata() {
        test_should_fail_as_wrongdata("tests/resources/owners-testdata-ko-missingdata.csv");
    }

    @Test
    public void readAll_wrong_format() {
        test_should_fail_as_wrongdata("tests/resources/owners-testdata-ko-format.csv");
    }

    private void test_should_fail_as_wrongdata(String s) {
        try {
            OwnerDao dao = new OwnerDao(s);
            dao.readAll();
            fail("Treatment should have failed with WrongDataException");
        } catch (WrongDataException e) {
            // This is good
        }
    }
}
