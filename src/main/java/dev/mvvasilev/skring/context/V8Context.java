package dev.mvvasilev.skring.context;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import dev.mvvasilev.skring.library.SkringFunction;
import dev.mvvasilev.skring.library.SkringVoidFunction;
import dev.mvvasilev.skring.util.Assert;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class V8Context {

    private V8 runtime;

    public V8Context() {
        this.runtime = V8.createV8Runtime();
    }

    public V8Context(V8 runtime) {
        this.runtime = runtime;
    }

    public void put (String key, JavaCallback callback) {
        runtime.registerJavaMethod(callback, key);
    }

    public void put (String key, JavaVoidCallback callback) {
        runtime.registerJavaMethod(callback, key);
    }

    public void put (SkringFunction skringFunction) {
        runtime.registerJavaMethod(skringFunction, skringFunction.getName());
    }

    public void put (SkringVoidFunction skringVoidFunction) {
        runtime.registerJavaMethod(skringVoidFunction, skringVoidFunction.getName());
    }

    public void put(String key, Object value) {
        Assert.notEmpty(key, "Cannot assign value to null or empty key.");
        Assert.notNull(value, "Cannot assign key to null value.");

        if (value instanceof String) {
            runtime.add(key, (String) value);
        }

        if (value instanceof Integer) {
            runtime.add(key, (Integer) value);
        }

        if (value instanceof Double) {
            runtime.add(key, (Double) value);
        }

        if (value instanceof Boolean) {
            runtime.add(key, (Boolean) value);
        }

        // TODO: Create architecture for automatically converting java objects to V8Object and back
    }

    public boolean containsKey(String key) {
        Assert.notEmpty(key, "Cannot check for null or empty key.");

        return runtime.contains(key);
    }

    public void run(String script) {
        Assert.notEmpty(script, "The script to run cannot be null or empty.");
        runtime.executeVoidScript(script);
    }

    public void runResource(String resourceName) throws IOException {
        Assert.notEmpty(resourceName, "The resource to run cannot be null or empty.");

        URL resource = this.getClass().getClassLoader().getResource(resourceName);

        Assert.notNull(resource, "Resource does not exist.");

        run(Files.readString(Paths.get(resource.getPath())));
    }

    public <T> T invoke(Class<T> expectedResult, String functionName, Object... arguments) {
        if (expectedResult.equals(String.class)) {
            return (T) invokeString(functionName, arguments);
        }

        if (expectedResult.equals(Integer.class)) {
            return (T) invokeInteger(functionName, arguments);
        }

        if (expectedResult.equals(Double.class)) {
            return (T) invokeDouble(functionName, arguments);
        }

        if (expectedResult.equals(Boolean.class)) {
            return (T) invokeBoolean(functionName, arguments);
        }

        if (expectedResult.equals(Void.class)) {
            invokeVoid(functionName, arguments);
        }

        return expectedResult.cast(invokeObject(functionName, arguments));
    }

    private String invokeString(String functionName, Object... arguments) {
        return runtime.executeStringFunction(functionName, createArray(arguments));
    }

    private Integer invokeInteger(String functionName, Object... arguments) {
        return runtime.executeIntegerFunction(functionName, createArray(arguments));
    }

    private Double invokeDouble(String functionName, Object... arguments) {
        return runtime.executeDoubleFunction(functionName, createArray(arguments));
    }

    private Boolean invokeBoolean(String functionName, Object... arguments) {
        return runtime.executeBooleanFunction(functionName, createArray(arguments));
    }

    private void invokeVoid(String functionName, Object... arguments) {
        runtime.executeVoidFunction(functionName, createArray(arguments));
    }

    private Object invokeObject(String functionName, Object... arguments) {
        if (arguments.length == 0) {
            return runtime.executeJSFunction(functionName);
        } else {
            return runtime.executeJSFunction(functionName, arguments);
        }
    }

    private V8Array createArray(Object... elements) {
        V8Array result = new V8Array(runtime);

        for (Object element : elements) {
            result.push(element);
        }

        return result;
    }
}
