-- Copyright 2014-2021 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://developers.google.com/open-source/licenses/bsd

class test_list_pattern {

  test_case test_match() {
    the_pattern : list_pattern[character].new("abc");

    assert the_pattern("abc");
    assert !the_pattern("xx");
    assert !the_pattern("ab");
    assert !the_pattern("abcd");
  }

  test_case test_viable_prefix() {
    the_pattern : list_pattern[character].new("abc");

    assert the_pattern.is_viable_prefix("");
    assert the_pattern.is_viable_prefix("a");
    assert the_pattern.is_viable_prefix("abc");
    assert !the_pattern.is_viable_prefix("xy");
    assert !the_pattern.is_viable_prefix("aa");
    assert !the_pattern.is_viable_prefix("abcdef");
  }

  test_case test_match_prefix() {
    the_pattern : list_pattern[character].new("abc");

    assert the_pattern.match_prefix("") is null;
    assert the_pattern.match_prefix("abc") == 3;
    assert the_pattern.match_prefix("ab") is null;
    assert the_pattern.match_prefix("abcdef") == 3;
  }

  test_case test_find_first() {
    the_pattern : list_pattern[character].new("abc");

    assert the_pattern.find_first("", 0) is null;
    assert the_pattern.find_first("foo", 0) is null;
    assert the_pattern.find_first("bca", 1) is null;

    match : the_pattern.find_first("abc", 0);
    assert match is_not null;
    assert match.begin == 0;
    assert match.end == 3;

    match2 : the_pattern.find_first("abcxabcdef", 2);
    assert match2 is_not null;
    assert match2.begin == 4;
    assert match2.end == 7;
  }

  test_case test_split() {
    the_pattern : list_pattern[character].new("abc");

    split0: the_pattern.split("foo");
    assert split0.size == 1;
    assert equals(split0[0], "foo");

    split1: the_pattern.split("fooabcxyzabc");
    assert split1.size == 3;
    assert equals(split1[0], "foo");
    assert equals(split1[1], "xyz");
    assert equals(split1[2], "");

    split2: the_pattern.split("abc1abc2abc3");
    assert split2.size == 4;
    assert equals(split2[0], "");
    assert equals(split2[1], "1");
    assert equals(split2[2], "2");
    assert equals(split2[3], "3");
  }

  -- TODO: This hack shouldn't be needed.
  boolean equals(immutable list[character] s0, string s1) {
    -- deeply_immutable list[character] dil : s0;
    return (s0 !> string) == s1;
  }
}
