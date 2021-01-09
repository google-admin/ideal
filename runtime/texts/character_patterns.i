-- Copyright 2014-2021 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://developers.google.com/open-source/licenses/bsd

--- Helper functions for constructing charcater-based grammars.
--- TODO: move to patterns?
namespace character_patterns {
  implicit import ideal.library.patterns;
  implicit import ideal.runtime.patterns;

  pattern[character] one(function[boolean, character] the_predicate) pure {
    return predicate_pattern[character].new(the_predicate);
  }

  pattern[character] one_character(character the_character) pure {
    return singleton_pattern[character].new(the_character);
  }

  pattern[character] zero_or_more(function[boolean, character] the_predicate) pure {
    return repeat_element[character].new(the_predicate, true);
  }

  pattern[character] one_or_more(function[boolean, character] the_predicate) pure {
    return repeat_element[character].new(the_predicate, false);
  }

  pattern[character] repeat_or_none(pattern[character] the_pattern) pure {
    return repeat_pattern[character].new(the_pattern, true);
  }

  -- TODO: return sequence_pattern[character] (which breaks list initializer.)
  pattern[character] sequence(readonly list[pattern[character]] patterns_list) {
    return sequence_pattern[character].new(patterns_list);
  }

  option_pattern[character] option(readonly list[pattern[character]] patterns_list) {
    return option_pattern[character].new(patterns_list);
  }

  option_matcher[character, text_fragment] option_fragment_list(
      readonly list[matcher[character, text_fragment]] matchers) {
    return option_matcher[character, text_fragment].new(matchers);
  }

  -- TODO: the conversions should be inferred.
  option_matcher[character, text_fragment] option_fragment(
      matcher[character, string] attr_value,
      matcher[character, special_text] entity_ref) {
    return option_fragment_list([
        entity_ref as matcher[character, text_fragment],
        attr_value as matcher[character, text_fragment],
    ]);
  }

  -- TODO: wrapper function is redundant
  text_fragment join_fragments(readonly list[text_fragment] fragments) {
    return text_util.join(fragments);
  }

  matcher[character, text_fragment] repeat_or_none_fragment(
      matcher[character, text_fragment] the_matcher) pure {
    return repeat_matcher[character, text_fragment, text_fragment].new(the_matcher, true,
        join_fragments);
  }

  string as_string_procedure(readonly list[character] the_character_list) pure {
    return the_character_list.frozen_copy() as base_string;
  }

  matcher[character, string] as_string(pattern[character] the_pattern) pure {
    return procedure_matcher[character, string].new(the_pattern, as_string_procedure);
  }

  string select_2nd_string(readonly list[any value] the_list) pure => the_list[1] as string;

  text_fragment select_2nd_text_fragment(readonly list[any value] the_list) pure =>
      the_list[1] as text_fragment;
}
