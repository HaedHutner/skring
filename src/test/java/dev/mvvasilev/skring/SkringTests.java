package dev.mvvasilev.skring;

import dev.mvvasilev.skring.autoconfig.SkringAutoConfiguration;
import dev.mvvasilev.skring.context.V8Context;
import dev.mvvasilev.skring.util.V8StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkringAutoConfiguration.class)
public class SkringTests {

    @Autowired
    private V8Context context;

    @PostConstruct
    public void init() throws IOException {
        context.runResource("scripts/EmptyTestScript.js");
    }

    @Test(expected = NullPointerException.class)
    public void testContextRunResource_withNullOrEmptyResource() throws IOException {
        context.runResource("");
    }

    @Test
    public void testContextInvoke() {
        Integer result = context.invoke(Integer.class, "getAnInteger");

        Assert.assertEquals(800, (int) result);
    }

    @Test
    public void testContextInvokeWithParametersAndResult() {
        Boolean result = context.invoke(Boolean.class, "validate", 400, 300);

        Assert.assertTrue(result);
    }

    @Test
    public void testContextInvokeWithParametersAndResult_withString() {
        Boolean result = context.invoke(Boolean.class, "validateString", "someString");

        Assert.assertTrue(result);
    }

}
