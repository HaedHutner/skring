package dev.mvvasilev.skring.library;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

public interface SkringVoidFunction extends JavaVoidCallback {

    String getName();

    @Override
    void invoke(V8Object ctx, V8Array args);
}
