package com.innovez.utils.eav.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for {@link JpaBackedMetadataService}
 *
 * @author zakyalvan
 */
@ContextConfiguration(classes = TestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaBackedMetadataServiceTest {
    @Autowired
    private MetadataService metadataService;

    @Test
    public void testCreateEntityMetadataNormally() {

    }

    @Test
    public void testInquiryDefinedMetadata() {

    }
}
