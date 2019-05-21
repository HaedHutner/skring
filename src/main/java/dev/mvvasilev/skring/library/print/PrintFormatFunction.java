package dev.mvvasilev.skring.library.print;

import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import dev.mvvasilev.skring.library.SkringVoidFunction;

public class PrintFormatFunction implements SkringVoidFunction {
    @Override
    public String getName() {
        return "printf";
    }

    @Override
    public void invoke(V8Object ctx, V8Array args) {

    }
}
