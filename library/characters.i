-- Copyright 2014-2021 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://developers.google.com/open-source/licenses/bsd

--- Character-related functions, such as |is_letter()| or |is_whitespace()|.
package characters {
  implicit import ideal.library.elements;

  interface character_handler {
    extends deeply_immutable data;

    boolean is_letter(the character) pure;
    boolean is_letter_or_digit(the character) pure;
    boolean is_whitespace(the character) pure;
    boolean is_upper_case(the character) pure;
    boolean is_digit(the character) pure;

    nonnegative or null from_digit(the character, nonnegative radix) pure;

    character to_lower_case(the character);

    nonnegative to_code(the character);
    character from_code(nonnegative code);
  }

  namespace radixes {
    MINIMUM_RADIX : 2;
    --- Most humans have ten fingers.
    DEFAULT_RADIX : 10;
    MAXIMUM_RADIX : 36;
  }
}
