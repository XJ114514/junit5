/*
 * Copyright 2015-2024 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.console.tasks;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

/**
 * class provide access to printDiff function
 */
class DiffPrinter {
	//print the difference of two print to out
	static void printDiff(PrintWriter out, String expected, String actual) {
		boolean inlineDiffByWordFlag = false;
		if (expected.contains(" ") || actual.contains(" ")) {
			inlineDiffByWordFlag = true;
		}
		DiffRowGenerator generator = DiffRowGenerator.create().showInlineDiffs(true).inlineDiffByWord(
			inlineDiffByWordFlag).oldTag(f -> "~~").newTag(f -> "**").build();
		List<DiffRow> rows = generator.generateDiffRows(Arrays.asList(expected), Arrays.asList(actual));
		out.println();
		out.println("    | expected | actual |");
		out.println("    | -------- | ------ |");
		for (DiffRow row : rows) {
			out.printf("    | %s | %s |", row.getOldLine(), row.getNewLine());
			out.println();
		}
	}
}
