package dev.mvvasilev.skring.library;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

public interface SkringFunction extends JavaCallback {

    String getName();

    @Override
    Object invoke(V8Object ctx, V8Array args);
}
