package com.shaklee.resources.hp.content;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContentUnitTest.MockConfig.class)
@ActiveProfiles(profiles = "local")
public class ScoreUnitTest {

}
