/*
 * Copyright 2014-2025 The Ideal Authors. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://theideal.org/license/
 */

package ideal.development.parsers;

import java_cup.runtime.*;
import ideal.library.elements.*;
import ideal.runtime.elements.*;
import ideal.development.elements.*;
import ideal.development.scanners.*;
import ideal.development.notifications.*;
import ideal.development.symbols.base_parser;

public class base_wrapper extends base_parser {

  /**
   * Constructor which sets the default scanner.
   *
   * We want to use <code>new DefaultSymbolFactory</code> when is
   * deprecated as of JavaCUP 11b.
   */
  @SuppressWarnings("deprecation")
  public base_wrapper(Scanner s) {
    super(s, new DefaultSymbolFactory());
  }

  /** Report a non fatal error (or warning).  This method takes a message
   *  string and an additional object (to be used by specializations
   *  implemented in subclasses).  Here in the base class a very simple
   *  implementation is provided which simply prints the message to
   *  System.err.
   *
   * @param message an error message.
   * @param info    an extra object reserved for use by specialized subclasses.
   */
  public void report_error(String message, Object info) {
    if (info instanceof Symbol) {
      Symbol s = (Symbol) info;
      /* if (s.left != -1) {
        err.writeln(" at character " + ((Symbol)info).left + " of input"); */
      if (s.value instanceof origin) {
        new base_notification(new base_string(message), ((origin) s.value)).report();
        return;
      }
    }
    new base_notification(new base_string(message), null).report();
  }
}
