-- Copyright 2014-2025 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://theideal.org/license/

implicit import ideal.library.elements;
import ideal.library.texts.text_fragment;

namespace elements {

  class array[any value element] {
    extends value;

    -- TODO: use constructor.
    array(integer length);

    nonnegative size;

    implicit element get(nonnegative index) pure;
    implicit mutable reference[element] at(nonnegative index) mutable pure;
    not_yet_implemented implicit writeonly reference[element]
        at(nonnegative index) writeonly pure;
    set(nonnegative index, element value) writeonly;

    move(nonnegative source, nonnegative destination, nonnegative length);
    copy(nonnegative source_begin,
          array[any value] destination, nonnegative destination_begin,
          nonnegative length);
    sort(order[element] the_order, nonnegative begin, nonnegative length);
    scrub(nonnegative index, nonnegative length); -- writeonly;
  }

  class runtime_util {
    implicit import ideal.machine.adapters.java.lang;

    static equivalence_with_hash[readonly value] default_equivalence;

    static integer compute_hash_code(readonly value d);
    --static boolean data_equals(readonly value d1, readonly value d2);
    static boolean data_equals(readonly Object d1, readonly Object d2);
    static string string_of(readonly value the_value);
    static string value_identifier(readonly value the_value);
    -- TODO: return unreachable
    static noreturn do_panic(String message);
    static do_stack(String message);
    static string escape_markup(string the_string);

    static start_test(string name);
    static end_test();

    static text_fragment display(readonly value obj);

    -- Included for testing.
    static string short_class_name(readonly value the_value);
    static boolean values_equal(readonly value or null first, readonly value or null second);
  }
}
