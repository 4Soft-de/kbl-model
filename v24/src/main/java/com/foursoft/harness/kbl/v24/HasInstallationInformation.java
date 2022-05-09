/*-
 * ========================LICENSE_START=================================
 * kbl-v24
 * %%
 * Copyright (C) 2020 - 2022 4Soft GmbH
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */
package com.foursoft.harness.kbl.v24;

import com.foursoft.harness.kbl.v24.util.StreamUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface HasInstallationInformation {

    List<KblInstallationInstruction> getInstallationInformations();

    /**
     * Filters the list of {@link KblInstallationInstruction} key.
     *
     * @param instructionType defines the meaning of the value
     * @return the first value with the given type.
     */
    default Optional<String> getInstallationInstructionValue(final String instructionType) {
        return getInstallationInstructionValue(c -> c.getInstructionType().equals(instructionType));
    }

    /**
     * Filters the list of {@link KblInstallationInstruction} key.
     *
     * @param matches defines the meaning of the value
     * @return the first value with the given type.
     */
    default Optional<String> getInstallationInstructionValue(final Predicate<KblInstallationInstruction> matches) {
        return getInstallationInformations()
                .stream()
                .filter(matches)
                .map(KblInstallationInstruction::getInstructionValue)
                .collect(StreamUtils.findOneOrNone());
    }

    /**
     * Filters the list of {@link KblInstallationInstruction} key.
     *
     * @param instructionType defines the meaning of the value
     * @return a list with all instruction values for the given type.
     */
    default List<String> getInstallationInstructionValues(final String instructionType) {
        return getInstallationInstructionValues(c -> c.getInstructionType().equals(instructionType));
    }

    /**
     * Filters the list of {@link KblInstallationInstruction} key.
     *
     * @param matches defines the meaning of the value
     * @return a list with all instruction values for the given type.
     */
    default List<String> getInstallationInstructionValues(final Predicate<KblInstallationInstruction> matches) {
        return getInstallationInformations()
                .stream()
                .filter(matches)
                .map(KblInstallationInstruction::getInstructionValue)
                .collect(Collectors.toList());
    }
}
