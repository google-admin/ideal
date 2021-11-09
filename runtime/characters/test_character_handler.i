-- Copyright 2014-2021 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://developers.google.com/open-source/licenses/bsd

test_suite test_character_handler {
  import ideal.machine.characters.unicode_handler;

  test_case predicate_test() {
    the_character_handler : unicode_handler.instance;

    assert the_character_handler.is_letter('x');
    assert !the_character_handler.is_letter('6');

    assert the_character_handler.is_letter_or_digit('x');
    assert the_character_handler.is_letter_or_digit('8');
    assert !the_character_handler.is_letter_or_digit(' ');

    assert the_character_handler.is_whitespace(' ');
    assert !the_character_handler.is_whitespace('x');
    assert !the_character_handler.is_whitespace('4');

    assert the_character_handler.is_upper_case('X');
    assert !the_character_handler.is_upper_case('x');
    assert !the_character_handler.is_upper_case('5');

    assert the_character_handler.is_digit('0');
    assert the_character_handler.is_digit('5');
    assert !the_character_handler.is_digit('x');
    assert !the_character_handler.is_digit('?');
  }

  test_case digit_test() {
    assert radixes.MINIMUM_RADIX == 2;
    assert radixes.DEFAULT_RADIX == 10;
    assert radixes.MAXIMUM_RADIX == 36;

    the_character_handler : unicode_handler.instance;

    assert the_character_handler.from_digit('0', radixes.DEFAULT_RADIX) == 0;
    assert the_character_handler.from_digit('5', radixes.DEFAULT_RADIX) == 5;
    assert the_character_handler.from_digit('F', 16) == 15;
    assert the_character_handler.from_digit('X', 16) is null;
  }

  test_case conversion_test() {
    the_character_handler : unicode_handler.instance;

    assert the_character_handler.to_lower_case('X') == 'x';
    assert the_character_handler.to_lower_case('x') == 'x';
    assert the_character_handler.to_lower_case('5') == '5';
  }

  test_case test_quoted_character() {
    the_character_handler : unicode_handler.instance;

    for (the_quoted_character : quoted_character.all_list) {
      assert the_character_handler.to_code(the_quoted_character.value_character) ==
          the_quoted_character.ascii_code;
    }
  }
}
