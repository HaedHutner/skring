package dev.mvvasilev.skring.library.print;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import dev.mvvasilev.skring.library.SkringVoidFunction;
import dev.mvvasilev.skring.util.V8StringUtils;

public class PrintFunction implements SkringVoidFunction {
    @Override
    public String getName() {
        return "print";
    }

    @Override
    public void invoke(V8Object ctx, V8Array args) {
        String result = V8StringUtils.v8ConcatStringArray(args);
        System.out.print(result);
    }

}
