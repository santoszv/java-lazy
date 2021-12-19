/*
 * Copyright 2021 Santos Zatarain Vera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mx.com.inftel.javax.lazy.impl;

import mx.com.inftel.javax.lazy.ClearableLazy;

import java.util.Objects;
import java.util.function.Supplier;

public class UnsafeClearableLazyImpl<T> implements ClearableLazy<T> {

    private final Supplier<T> initializer;

    public UnsafeClearableLazyImpl(Supplier<T> initializer) {
        this.initializer = Objects.requireNonNull(initializer);
    }

    private Object value = UNINITIALIZED;

    @SuppressWarnings("unchecked")
    @Override
    public T getValue() {
        if (value == UNINITIALIZED) {
            value = initializer.get();
        }
        return (T) value;
    }

    @Override
    public boolean isInitialized() {
        return (this.value != UNINITIALIZED);
    }

    @Override
    public void clear() {
        this.value = UNINITIALIZED;
    }

    private static final Object UNINITIALIZED = new Object();

}
