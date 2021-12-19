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

package mx.com.inftel.javax.lazy;

import mx.com.inftel.javax.lazy.impl.UnsafeLazyImpl;

import java.util.function.Supplier;

/**
 * Represents a value with lazy initialization.
 *
 * @param <T> Type of value.
 */
public interface Lazy<T> {

    /**
     * Gets the lazily initialized value of the current Lazy instance.
     * <p>
     * Once the value was initialized it must not change during the rest of lifetime of this Lazy instance.
     * </p>
     *
     * @return Lazily initialized value of the current Lazy instance.
     */
    T getValue();

    /**
     * Returns {@code true} if a value for this Lazy instance has been already initialized, and {@code false} otherwise.
     * <p>
     * Once this function has returned {@code true} it stays {@code true} for the rest of lifetime of this Lazy
     * instance.
     * </p>
     *
     * @return {@code true} if a value for this Lazy instance has been already initialized, and {@code false} otherwise.
     */
    boolean isInitialized();

    /**
     * Create an instance of Lazy.
     * <p>
     * The new instance is not safe for multi-thread access.
     * </p>
     *
     * @param initializer Supplier for initializing the value.
     * @param <T>         Type of value.
     * @return New instance of Lazy.
     */
    static <T> Lazy<T> lazy(Supplier<T> initializer) {
        return new UnsafeLazyImpl<>(initializer);
    }
}