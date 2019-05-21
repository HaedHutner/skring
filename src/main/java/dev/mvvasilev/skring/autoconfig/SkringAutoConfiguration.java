package dev.mvvasilev.skring.autoconfig;

import dev.mvvasilev.skring.context.V8Context;
import dev.mvvasilev.skring.library.print.PrintFormatFunction;
import dev.mvvasilev.skring.library.print.PrintFunction;
import dev.mvvasilev.skring.library.print.PrintLineFunction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkringAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(V8Context.class)
    public V8Context v8Context() {
        V8Context context = new V8Context();
        context.put(new PrintFunction());
        context.put(new PrintLineFunction());
        context.put(new PrintFormatFunction());
        return context;
    }

}
